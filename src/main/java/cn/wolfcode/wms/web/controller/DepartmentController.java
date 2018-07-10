package cn.wolfcode.wms.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Department;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepartmentService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("department")
public class DepartmentController {
	@Autowired
	IDepartmentService departmentService;
	@RequiredPermission("部门列表")
	@RequestMapping("list")
	@RequiresPermissions("department:list")
	public String list(Model model,QueryObject qo) {
		PageResult result = departmentService.query(qo);
		model.addAttribute("result", result);
		return "department/list";
	}
	@RequiredPermission("部门删除")
	@RequiresPermissions("department:delete")
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			departmentService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("部门编辑")
	@RequiresPermissions("department:input")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		if (id != null) {
			model.addAttribute("department", departmentService.get(id));
		}
		return "/department/input";
	}
	@RequiredPermission("部门保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Department department) {
		if (department.getId() != null) {
			departmentService.update(department);
		}else {
			departmentService.save(department);
		}
		return "redirect:/department/list";
	}
}
