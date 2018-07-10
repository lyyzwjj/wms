package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IRoleService {
	void save(Role role, Long[] permissionIds, Long[] menuIds);
	void delete(Long id);
	void update(Role role, Long[] permissionIds, Long[] menuIds);
	Role get(Long id);
	List<Role> list();
	PageResult query(QueryObject qo);

    List<Role> selectByEmployeeId(Long id);

}
