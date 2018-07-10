package cn.wolfcode.wms.service;

import java.util.List;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IDepartmentService {
	void save(Department department);
	void delete(Long id);
	void update(Department department);
	Department get(Long id);
	List<Department> list();
	PageResult query(QueryObject qo);
}
