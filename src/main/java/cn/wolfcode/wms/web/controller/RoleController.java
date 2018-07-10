package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.service.IRoleService;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	IRoleService roleService;
	@Autowired
	IPermissionService permissionService;
	@Autowired
	ISystemMenuService menuService;
	@RequiredPermission("角色列表")
	@RequestMapping("list")
	public String list(Model model,QueryObject qo) {
		PageResult result = roleService.query(qo);
		model.addAttribute("result", result);
		return "role/list";
	}
	@RequiredPermission("角色删除")
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			roleService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("角色编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		model.addAttribute("permissions", permissionService.list());
		model.addAttribute("menus", menuService.list());
		if (id != null) {
			model.addAttribute("role", roleService.get(id));
		}
		return "/role/input";
	}
	@RequiredPermission("角色保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Role role,Long[] permissionIds,Long[] menuIds) {
		System.out.println(role);
		if (role.getId() != null) {
			roleService.update(role,permissionIds,menuIds);
		}else {
			roleService.save(role,permissionIds,menuIds);
		}
		return "redirect:/role/list";
	}
}
