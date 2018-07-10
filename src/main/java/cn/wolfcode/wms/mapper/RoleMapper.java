package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午12:35:58
 *@mail   wzzst310@163.com
 *@desp
 */
public interface RoleMapper {
	void save(Role role);
	void delete(Long id);
	void update(Role role);
	Role get(Long id);
	List<Role> list();
	List<Role> queryForList(QueryObject qo);
	Integer queryForCount(QueryObject qo);
	void saveRolePermissionRelation(@Param("roleId")Long id, @Param("departmentId")Long pd);
	void deleteRolePermissionRelation(Long id);

    void saveRoleMenuRelation(@Param("roleId")Long id, @Param("menuId")Long md);

	void deleteRoleMenuRelation(Long id);

	List<Role> selectByEmployeeId(Long id);
}
