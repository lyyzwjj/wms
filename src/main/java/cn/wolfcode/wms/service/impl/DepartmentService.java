package cn.wolfcode.wms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.mapper.DepartmentMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class DepartmentService implements IDepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;
	@Override
	public void save(Department department) {
		departmentMapper.save(department);

	}

	@Override
	public void delete(Long id) {
		departmentMapper.delete(id);

	}

	@Override
	public void update(Department department) {
		departmentMapper.update(department);

	}

	@Override
	public Department get(Long id) {
		return departmentMapper.get(id);
	}

	@Override
	public List<Department> list() {
		return departmentMapper.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = departmentMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Department> departments = departmentMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

}
