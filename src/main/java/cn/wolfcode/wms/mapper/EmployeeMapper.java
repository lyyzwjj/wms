package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午12:35:58
 *@mail   wzzst310@163.com
 *@desp
 */
public interface EmployeeMapper {
	void save(Employee employee);
	void delete(Long id);
	void update(Employee employee);
	Employee get(Long id);
	List<Employee> list();
	List<Employee> queryForList(QueryObject qo);
	Integer queryForCount(QueryObject qo);
	void deleteEmployeeRoleRelation(Long id);
	void saveEmployeeRoleRelation(@Param("id")Long id, @Param("roleId")Long roleId);
	Employee selectByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
	Set<String> selectAllExpressionByEmployeeId(Long id);
	void batchDelete(Long[] ids);

    Employee selectByUsername(String name);
}
