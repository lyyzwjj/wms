package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.mapper.PermissionMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class PermissionService implements IPermissionService {
	@Autowired
	PermissionMapper permissionMapper;
	@Autowired
	private ApplicationContext ctx;
	@Override
	public void save(Permission permission) {
		permissionMapper.save(permission);

	}

	@Override
	public void delete(Long id) {
		permissionMapper.delete(id);

	}

	@Override
	public List<Permission> list() {
		return permissionMapper.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = permissionMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Permission> permissions = permissionMapper.queryForList(qo);
		return new PageResult(permissions, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public void load() {
		//保存之前先判断当前表达式是否在数据库中存在
				//不存在:保存   
				//查询出所有的权限表达式
				List<String> expressions = permissionMapper.selectAllExpressions();
				// 获取容器中的所有的Controller对象
				//所有的Controller类上都贴了@Controller注解
				Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
				//从结合中获取到所有的Controller对象
				Collection<Object> controllers = beansWithAnnotation.values();
				for (Object object : controllers) {
					//获取到当前Controller中的所有的方法
					Method[] methods = object.getClass().getMethods();
					for (Method method : methods) {
						//判断方法上是否贴了RequiredPermission注解
						if (method.isAnnotationPresent(RequiredPermission.class)) {
							//获取到当前方法所在的Controller类的全限定名
							String controllerName = object.getClass().getName();
							//获取到当前方法的名称
							String methodName = method.getName();
							//拼接权限表达式
							String expression = controllerName + ":" + methodName;
							if (!expressions.contains(expression)) {
								//获取到注解的参数:权限的名称@RequiredPermission("员工列表")  获取"员工类别"
								RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
								String name =rp.value();
								//保存权限信息
								Permission p = new Permission();
								p.setExpression(expression);
								p.setName(name);
								permissionMapper.save(p);
							}
						}
						
					}
				}
	}

	@Override
	public Set<String> selectAllExpressionsByEmployeeId(Long id) {
		return permissionMapper.selectAllExpressionsByEmployeeId(id);
	}

}
