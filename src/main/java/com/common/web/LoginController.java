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
	 * 登录页面
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
	 * 登录
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
            modelAndView.addObject("errorMessage", "用户名或密码错误");
            modelAndView.addObject("errorCode", "");
            
            return modelAndView;
		}
		else if (operator.getStatus() != 1)
        {
			modelAndView.setViewName("frame/outError");
            modelAndView.addObject("errorMessage", "账户状态异常");
            modelAndView.addObject("errorCode", "");
            
            return modelAndView;
		}
		
		Function function = functionService.getFunctionsByOprId(operator.getId());
		
		session.setAttribute("operator", operator);
		session.setAttribute("function", function);
		
		if (function.getNodes().size() == 0)
		{
			modelAndView.setViewName("frame/outError");
            modelAndView.addObject("errorMessage", "该账户无权限 请联系管理员");
            modelAndView.addObject("errorCode", "");
            
            return modelAndView;
		}
		
		Function welcomeFunction = (Function)function.getNodes().get(1).getNodes().get(0);
        modelAndView.setViewName("redirect:"+welcomeFunction.getUrl());
        return modelAndView;
    }
	
	/**
	 * 个人资料修改页面
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
	 * 个人资料修改
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
	 * 密码修改页面
	 */
	@RequestMapping(value = "/preUpdatePassword.htm")
    public ModelAndView preUpdatePassword()
    {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("frame/updatePassword");
        return modelAndView;
    }
	
	/**
	 * 密码修改
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
			modelAndView.addObject("errorMessage", "旧密码错误");
			modelAndView.addObject("errorCode", "");
			modelAndView.addObject("returnUrl", "");
			modelAndView.setViewName("frame/inError");
		}
		
        return modelAndView;
    }
	
	/**
	 * 登录
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
