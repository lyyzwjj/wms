package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IPermissionService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp	
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	IPermissionService permissionService;
	
	@RequestMapping("list")
	@RequiredPermission("权限列表")
	public String list(Model model,QueryObject qo) {
		PageResult result = permissionService.query(qo);
		model.addAttribute("result", result);
		return "permission/list";
	}
	@RequestMapping("delete")
	@ResponseBody
	@RequiredPermission("权限删除")
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			permissionService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequestMapping("load")
	@RequiredPermission("权限加载")
	public ModelAndView load() {
		permissionService.load();
		return null;
	}
	
}
