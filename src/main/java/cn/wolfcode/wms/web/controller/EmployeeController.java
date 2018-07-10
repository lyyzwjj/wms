package cn.wolfcode.wms.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	IEmployeeService employeeService;
	@Autowired
	IDepartmentService departmentService;
	@Autowired
	IRoleService roleService;
	@RequiredPermission("员工列表")
	@RequestMapping("list")
	@RequiresPermissions("employee:list")
	public String list(Model model,@ModelAttribute("qo")EmployeeQueryObject qo) {
		PageResult result = employeeService.query(qo);
		model.addAttribute("result", result);
		model.addAttribute("qo", qo);
		model.addAttribute("departments", departmentService.list());
		return "employee/list";
	}
	@RequiredPermission("员工删除")
	@RequestMapping("delete")
	@ResponseBody
	@RequiresPermissions("employee:delete")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
				employeeService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("员工批量删除")
	@RequestMapping("batchDelete")
	@ResponseBody
	public JsonResult batchDelete(Long[] ids) {
		JsonResult result = new JsonResult();
		try {
			employeeService.batchDelete(ids);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("员工编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		model.addAttribute("departments", departmentService.list());
		model.addAttribute("roles", roleService.list());
		if (id != null) {
			model.addAttribute("employee", employeeService.get(id));
		}
		return "/employee/input";
	}
	@RequiredPermission("员工保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Employee employee,Long[] roleIds) {
		if (employee.getId() != null) {
			employeeService.update(employee,roleIds);
		}else {
			employeeService.save(employee,roleIds);
		}
		return "redirect:/employee/list";
	}
}
