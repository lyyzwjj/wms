package cn.wolfcode.wms.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.wolfcode.wms.util.UserContext;

/**
 *@author WangZhe
 *@time	  2018年6月20日下午10:54:06
 *@mail   wzzst310@163.com
 *@desp
 */
public class CheckLoginInteceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object currentUser = UserContext.getCurrentUser();
		if (currentUser == null) {
			response.sendRedirect("/login.jsp");
			return false;
		}
		return true;
	}
}
