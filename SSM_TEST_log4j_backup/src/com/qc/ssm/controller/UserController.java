package com.qc.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qc.ssm.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/hello.do",method=RequestMethod.POST)//method指定参数传递方式，默认为RequestMethod.GET
//	public String find(@RequestParam(value="userid",required=false)String userid, @RequestParam(value="username",required=false)String name){
	//根据注解的value来匹配参数名，required默认为true
	public String find(String userid, String username, ModelMap model){//直接根据参数名匹配前端传过来的参数，required都是false
		System.out.println(RequestMethod.POST);
		System.out.println("userid:"+userid);
		System.out.println("username:"+username);
		String age = userService.findAge(userid);
		System.out.println(age);
		//用ModelMap来将参数传至目标页面index。
		model.addAttribute("user", userid);
		model.addAttribute("username", username);
		model.addAttribute("age", age);
		System.out.println("ModelMap");
		System.out.println(System.getProperty("catalina.home"));
		Logger logger = Logger.getLogger(getClass());
		logger.info("log4j.info");
		
		return "index";
	}
//	@RequestMapping(value="/hello.do",method=RequestMethod.POST)
//	public ModelAndView find(String userid, String username){//直接根据参数名匹配前端传过来的参数，required都是false
//		System.out.println(RequestMethod.POST);
//		System.out.println("userid:"+userid);
//		System.out.println("username:"+username);
//		String age = userService.findAge(userid);
//		System.out.println(age);
//		//也可以不用ModelMap,而使用ModelAndView，同时指定返回的Url映射和参数Map。
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("user", userid);
//		data.put("username", username);
//		data.put("age", age);
//		System.out.println("ModelAndView");
//		return new ModelAndView("index",data);
//	}
}
