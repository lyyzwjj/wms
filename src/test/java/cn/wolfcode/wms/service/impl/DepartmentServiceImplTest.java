package cn.wolfcode.wms.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午3:00:50
 *@mail   wzzst310@163.com
 *@desp
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mvc.xml")
public class DepartmentServiceImplTest {
	@Autowired
	IDepartmentService deparmentService;
	@Test
	public void testSave() {
		Department department = new Department();
		department.setName("法务");
		department.setSn("dept009");
		deparmentService.save(department);
		System.out.println(deparmentService);
	}

	@Test
	public void testDelete() {
		deparmentService.delete(8L);
		System.out.println(deparmentService);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		System.out.println(deparmentService.getClass());
		Department department = deparmentService.get(1L);
		System.out.println(department);
	}

	@Test
	public void testList() {
		List<Department> list = deparmentService.list();
		System.out.println(list);
	}
	@Test
	public void testQuery() {
		QueryObject qo = new QueryObject();
		PageResult result = deparmentService.query(qo);
		System.out.println(result);
	}

}
