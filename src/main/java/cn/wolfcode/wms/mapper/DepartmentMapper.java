package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.QueryObject;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午12:35:58
 *@mail   wzzst310@163.com
 *@desp
 */
public interface DepartmentMapper {
	void save(Department department);
	void delete(Long id);
	void update(Department department);
	Department get(Long id);
	List<Department> list();
	List<Department> queryForList(QueryObject qo);
	Integer queryForCount(QueryObject qo);
}
