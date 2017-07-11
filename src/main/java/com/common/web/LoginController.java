package com.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.dto.Function;
import com.college.dto.Operator;
import com.common.service.FunctionService;
import com.common.service.OperatorService;

@Controller
public class LoginController 
{
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	@Qualifier("collegeOperatorService")
	private com.college.service.OperatorService collegeOperatorService;
	
	@Autowired
	private FunctionService functionService;
	
	/**
	 * ��¼ҳ��
	 * @return
	 */
	@RequestMapping(value = "/operatorLogin.htm")
    public ModelAndView loginPage()
    {
		ModelAndView modelAndView = new ModelAndView();
		
        modelAndView.setViewName("frame/operatorLogin");
        return modelAndView;
    }
	
	/**
	 * ��¼
	 * @return
	 */
	@RequestMapping(value = "/operatorMain.htm")
    public ModelAndView operatorMain(HttpSession session, HttpServletRequest request, Operator operatorform)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		Operator operator = operatorService.getSimpleOperator(operatorform.getUsername(), operatorform.getPassword());
		
		if (operator == null)
        {
			modelAndView.setViewName("frame/outError");
            modelAndView.addObject("errorMessage", "�û������������");
            modelAndView.addObject("errorCode", "");
            
            return modelAndView;
		}
		else if (operator.getStatus() != 1)
        {
			modelAndView.setViewName("frame/outError");
            modelAndView.addObject("errorMessage", "�˻�״̬�쳣");
            modelAndView.addObject("errorCode", "");
            
            return modelAndView;
		}
		
		Function function = functionService.getFunctionsByOprId(operator.getId());
		
		session.setAttribute("operator", operator);
		session.setAttribute("function", function);
		
		if (function.getNodes().size() == 0)
		{
			modelAndView.setViewName("frame/outError");
            modelAndView.addObject("errorMessage", "���˻���Ȩ�� ����ϵ����Ա");
            modelAndView.addObject("errorCode", "");
            
            return modelAndView;
		}
		
		Function welcomeFunction = (Function)function.getNodes().get(1).getNodes().get(0);
        modelAndView.setViewName("redirect:"+welcomeFunction.getUrl());
        return modelAndView;
    }
	
	/**
	 * ���������޸�ҳ��
	 */
	@RequestMapping(value = "/preOperatorUpdateSelf.htm")
    public ModelAndView preOperatorUpdateSelf(HttpSession session, HttpServletRequest request, Operator operatorform)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		Operator operator = (Operator)session.getAttribute("operator");
		
		operator = collegeOperatorService.getObjectById(operator.getId());
		
		modelAndView.addObject("object", operator);
		
		modelAndView.setViewName("frame/operatorUpdateSelf");
        return modelAndView;
    }
	
	/**
	 * ���������޸�
	 */
	@RequestMapping(value = "/operatorUpdateSelf.htm")
    public ModelAndView operatorUpdateSelf(HttpSession session, Operator operatorform)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		collegeOperatorService.updateStaffSelf(operatorform);
		
		Function function = (Function)session.getAttribute("function");		
		Function welcomeFunction = (Function)function.getNodes().get(1).getNodes().get(0);
        modelAndView.setViewName("redirect:"+welcomeFunction.getUrl());
        
        return modelAndView;
    }
	
	/**
	 * �����޸�ҳ��
	 */
	@RequestMapping(value = "/preUpdatePassword.htm")
    public ModelAndView preUpdatePassword()
    {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("frame/updatePassword");
        return modelAndView;
    }
	
	/**
	 * �����޸�
	 */
	@RequestMapping(value = "/updatePassword.htm")
    public ModelAndView updatePassword(HttpSession session, String oldPassword, String newPassword)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		Operator operator = (Operator)session.getAttribute("operator");		
		
		int flag = operatorService.updatePassword(operator.getId(), oldPassword, newPassword);
		
		if (1 == flag)
		{
			Function function = (Function)session.getAttribute("function");		
			Function welcomeFunction = (Function)function.getNodes().get(1).getNodes().get(0);
	        modelAndView.setViewName("redirect:"+welcomeFunction.getUrl());
		}
		else
		{
			modelAndView.addObject("errorMessage", "���������");
			modelAndView.addObject("errorCode", "");
			modelAndView.addObject("returnUrl", "");
			modelAndView.setViewName("frame/inError");
		}
		
        return modelAndView;
    }
	
	/**
	 * ��¼
	 * @return
	 */
	@RequestMapping(value = "/logout.htm")
    public ModelAndView logout(HttpSession session, HttpServletRequest request, Operator operatorform)
	{
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("frame/operatorLogin");
        return modelAndView;
	}
	
	
}
