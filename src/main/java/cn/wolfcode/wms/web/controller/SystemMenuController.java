package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.SystemMenuQueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("menu")
public class SystemMenuController {
	@Autowired
	ISystemMenuService menuService;
	@RequiredPermission("菜单列表")
	@RequestMapping("list")
	public String list(Model model,SystemMenuQueryObject qo) {
		List<SystemMenu> parents = new ArrayList<>();
		if (qo != null) {
			SystemMenu parent = menuService.get(qo.getParentId());
			while(parent != null) {
				parents.add(parent);
				parent = parent.getParent();
			}
		}
		Collections.reverse(parents);
		model.addAttribute("parents", parents);
		model.addAttribute("qo", qo);
		PageResult result = menuService.query(qo);
		model.addAttribute("result", result);
		return "menu/list";
	}
	@RequiredPermission("菜单删除")
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			menuService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("菜单编辑")
	@RequestMapping("input")
	public String input(Model model,Long id,Long parentId) {
			model.addAttribute("parent", menuService.get(parentId));
			model.addAttribute("menu", menuService.get(id));
		return "/menu/input";
	}
	@RequiredPermission("菜单保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(SystemMenu menu) {
		System.out.println(menu);
		if (menu.getId() != null) {
			menuService.update(menu);
		}else {
			menuService.save(menu);
			if (menu.getParent().getId() == null) {
				return "forward:/menu/list";
			}
		}
		return "forward:/menu/list?parentId="+menu.getParent().getId();
	}
	@RequestMapping("selectByParentSn")
	@ResponseBody
	public List<Map<String,Object>> selectByParentSn(String parentSn){
		return menuService.selectByParentSn(parentSn);
	}
}
