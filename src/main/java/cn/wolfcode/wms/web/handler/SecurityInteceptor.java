package cn.wolfcode.wms.web.handler;

import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.util.UserContext;

/**
 *@author WangZhe
 *@time	  2018年6月20日下午10:54:06
 *@mail   wzzst310@163.com
 *@desp
 */
public class SecurityInteceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Employee currentUser = UserContext.getCurrentUser();
		if (currentUser.isAdmin()) {
			return true;
		}
		HandlerMethod hm = (HandlerMethod)handler;
		Method method = hm.getMethod();
		if (!method.isAnnotationPresent(cn.wolfcode.wms.util.RequiredPermission.class)) {
			return true;
		}
		String controllerName = hm.getBeanType().getName();
		String methodName = method.getName();
		String expression = controllerName +":" + methodName;
		Set<String> expressions = UserContext.getExpressions();
		if (expressions.contains(expression)) {
			return true;
		}
		response.sendRedirect("/nopermission.jsp");
		return false;
	}
}
