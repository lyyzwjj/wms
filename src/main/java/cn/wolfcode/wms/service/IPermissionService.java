package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Set;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IPermissionService {
	void save(Permission permission);
	void delete(Long id);
	List<Permission> list();
	PageResult query(QueryObject qo);
	void load();

    Set<String> selectAllExpressionsByEmployeeId(Long id);
}
