package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("depot")
public class DepotController {
	@Autowired
	IDepotService depotService;
	@RequiredPermission("仓库列表")
	@RequestMapping("list")
	public String list(Model model,QueryObject qo) {
		PageResult result = depotService.query(qo);
		model.addAttribute("result", result);
		return "depot/list";
	}
	@RequiredPermission("仓库删除")

	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			depotService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("仓库编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		if (id != null) {
			model.addAttribute("depot", depotService.selectByPrimaryKey(id));
		}
		return "/depot/input";
	}
	@RequiredPermission("仓库保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Depot depot) {
		if (depot.getId() != null) {
			depotService.updateByPrimaryKey(depot);
		}else {
			depotService.insert(depot);
		}
		return "redirect:/depot/list";
	}
}
