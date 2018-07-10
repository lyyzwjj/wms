package cn.wolfcode.wms.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wolfcode.wms.service.IEmployeeService;

/**
 *@author WangZhe
 *@time	  2018年6月20日下午9:50:54
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
public class LogoutController{
	@Autowired
	IEmployeeService employeeService;
	@RequestMapping("logout")
	public String login(HttpSession session){
		session.invalidate();
		
		return "redirect:/login.jsp";
	}
}
