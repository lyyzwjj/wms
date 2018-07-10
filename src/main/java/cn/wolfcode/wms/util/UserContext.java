package cn.wolfcode.wms.util;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.wolfcode.wms.domain.Employee;

/**
 * @author WangZhe
 * @time 2018年6月21日上午12:09:55
 * @mail wzzst310@163.com
 * @desp
 */
public class UserContext {
    public static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    public static final String EXPRESSIONS_IN_SESSION = "EXPRESSIONS_IN_SESSION";

    private UserContext() {
    }

    public static HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest().getSession();
    }

    public static void setCurrentUser(Employee currentUser) {
        getSession().setAttribute(EMPLOYEE_IN_SESSION, currentUser);
    }

    public static Employee getCurrentUser() {
        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    public static void setExpressions(Set<String> expressions) {
        getSession().setAttribute(EXPRESSIONS_IN_SESSION, expressions);
    }

    public static Set<String> getExpressions() {
        return (Set<String>) getSession().getAttribute(EXPRESSIONS_IN_SESSION);
    }
}
