package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IEmployeeService {
	void save(Employee employee, Long[] roleIds);
	void delete(Long id);
	void update(Employee employee, Long[] roleIds);
	Employee get(Long id);
	List<Employee> list();
	PageResult query(QueryObject qo);
	void login(String username, String password);
	void batchDelete(Long[] ids);

    Employee selectByUsername(String name);
}
