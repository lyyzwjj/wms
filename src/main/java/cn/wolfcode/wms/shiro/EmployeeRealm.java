package cn.wolfcode.wms.shiro;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by WangZhe on 2018/7/7.
 */

public class EmployeeRealm extends AuthorizingRealm{
	@Setter
    private IEmployeeService employeeService;
	@Setter
    private IRoleService roleService;
	@Setter
    private IPermissionService permissionService;

    @Override
    public String getName(){
        return "myRealm";
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Employee currentUser = (Employee) principals.getPrimaryPrincipal();
        List<String> roles = new ArrayList<>();
        Set<String> permissions = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (currentUser.isAdmin()){
            roles.add("Admin MGR");
            permissions.add("*:*");
        }else{
            List<Role> list = roleService.selectByEmployeeId(currentUser.getId());
            for (Role role : list) {
                roles.add(role.getSn());
            }
            permissions = permissionService.selectAllExpressionsByEmployeeId(currentUser.getId());
        }
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }
    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String)token.getPrincipal();
        Employee employee =employeeService.selectByUsername(name);
        if (employee==null){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(employee, employee.getPassword(), getName());
        return info;
    }
    //清除缓存
    public void clearCached(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
