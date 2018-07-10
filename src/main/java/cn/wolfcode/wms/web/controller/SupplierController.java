package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISupplierService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("supplier")
public class SupplierController {
	@Autowired
	ISupplierService supplierService;
	@RequiredPermission("供应商列表")
	@RequestMapping("list")
	public String list(Model model,QueryObject qo) {
		PageResult result = supplierService.query(qo);
		model.addAttribute("result", result);
		return "supplier/list";
	}
	@RequiredPermission("供应商删除")

	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			supplierService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("供应商编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		if (id != null) {
			model.addAttribute("supplier", supplierService.selectByPrimaryKey(id));
		}
		return "/supplier/input";
	}
	@RequiredPermission("供应商保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Supplier supplier) {
		if (supplier.getId() != null) {
			supplierService.updateByPrimaryKey(supplier);
		}else {
			supplierService.insert(supplier);
		}
		return "redirect:/supplier/list";
	}
}
