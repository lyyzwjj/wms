package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Set;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午12:35:58
 *@mail   wzzst310@163.com
 *@desp
 */
public interface PermissionMapper {
	void save(Permission permission);
	void delete(Long id);
	List<Permission> list();
	List<Permission> queryForList(QueryObject qo);
	Integer queryForCount(QueryObject qo);
	List<String> selectAllExpressions();

    Set<String> selectAllExpressionsByEmployeeId(Long id);

}
