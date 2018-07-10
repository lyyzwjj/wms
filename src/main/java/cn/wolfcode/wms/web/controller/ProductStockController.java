package cn.wolfcode.wms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("productStock")
public class ProductStockController {
	@Autowired
	IProductStockService productStockService;
	@Autowired
	IDepotService depotService;
	@Autowired
	IBrandService brandService;
	
	@RequiredPermission("报表列表")
	@RequestMapping("list")
	public String list(Model model,@ModelAttribute("qo")ProductStockQueryObject qo) {
		List<ProductStock> productStocks = productStockService.query(qo);
		model.addAttribute("productStocks", productStocks);
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("brands", brandService.selectAll());
		return "productStock/list";
	}
	@RequiredPermission("报表删除")

	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			productStockService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("报表编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("brands", brandService.selectAll());
		if (id != null) {
			model.addAttribute("productStock", productStockService.selectByPrimaryKey(id));
		}
		return "/productStock/input";
	}
	@RequiredPermission("报表保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(ProductStock productStock) {
		if (productStock.getId() != null) {
			productStockService.updateByPrimaryKey(productStock);
		}else {
			productStockService.insert(productStock);
		}
		return "redirect:/productStock/list";
	}
}
