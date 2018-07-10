package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.service.IEmployeeService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@author WangZhe
 *@time	  2018年6月20日下午9:50:54
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
public class LoginController{
	@Autowired
	IEmployeeService employeeService;

	@RequestMapping("main")
	public String main() {
		return "main";
	}

	@RequestMapping("login")
	public void login(String username, String password, HttpServletRequest req, HttpServletResponse res) throws Exception {
		String errMsg = "";
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		//根据shiro返回的异常类路径判断，抛出指定异常信息
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				//最终会抛给异常处理器
				errMsg = "账号不存在";
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				errMsg = "用户名/密码错误";
			} else {
				errMsg = "其他异常信息";
			}
		}
		req.setAttribute("errMsg", errMsg);
		req.getRequestDispatcher("/login.jsp").forward(req, res);
	}
}
