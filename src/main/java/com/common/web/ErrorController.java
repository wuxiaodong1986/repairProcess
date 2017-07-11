package com.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController 
{
	@RequestMapping(value = "/outError.htm")
	public ModelAndView outError(@RequestParam(value="errorMessage", required = false, defaultValue = "")String errorMessage, @RequestParam(value="errorCode", required = false, defaultValue = "")String errorCode, @RequestParam(value="returnUrl", required = false, defaultValue = "")String returnUrl)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", errorMessage);
		modelAndView.addObject("errorCode", errorCode);
		modelAndView.addObject("returnUrl", returnUrl);
		modelAndView.setViewName("frame/outError");
		
        return modelAndView;
	}
	
	@RequestMapping(value = "/error404.htm")
	public ModelAndView error404(@RequestParam(value="errorMessage", required = false, defaultValue = "")String errorMessage, @RequestParam(value="errorCode", required = false, defaultValue = "")String errorCode, @RequestParam(value="returnUrl", required = false, defaultValue = "")String returnUrl)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", "Ò³Ãæ²»´æÔÚ");
		modelAndView.addObject("errorCode", "404");
		modelAndView.addObject("returnUrl", returnUrl);
		modelAndView.setViewName("frame/outError");
		
        return modelAndView;
	}
	
	@RequestMapping(value = "/inError.htm")
	public ModelAndView inError(@RequestParam(value="errorMessage", required = false, defaultValue = "")String errorMessage, @RequestParam(value="errorCode", required = false, defaultValue = "")String errorCode, @RequestParam(value="returnUrl", required = false, defaultValue = "")String returnUrl)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", errorMessage);
		modelAndView.addObject("errorCode", errorCode);
		modelAndView.addObject("returnUrl", returnUrl);
		modelAndView.setViewName("frame/inError");
		
        return modelAndView;
	}
}
