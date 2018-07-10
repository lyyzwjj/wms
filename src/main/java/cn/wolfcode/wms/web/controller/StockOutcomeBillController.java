package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.StockOutcomeBillQueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("stockOutcomeBill")
public class StockOutcomeBillController {
	@Autowired
	IStockOutcomeBillService stockOutcomeBillService;
	@Autowired
	IDepotService depotService;
	@Autowired
	IClientService clientService;
	
	@RequiredPermission("订单列表")
	@RequestMapping("list")
	public String list(Model model,@ModelAttribute("qo")StockOutcomeBillQueryObject qo) {
		PageResult result = stockOutcomeBillService.query(qo);
		model.addAttribute("qo", qo);
		model.addAttribute("result", result);
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("clients", clientService.selectAll());
		return "stockOutcomeBill/list";
	}
	@RequiredPermission("订单删除")
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			stockOutcomeBillService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("查看明细")
	@RequestMapping("show")
	public String show(Model model,Long id) {
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("clients", clientService.selectAll());
		if (id != null) {
			model.addAttribute("stockOutcomeBill", stockOutcomeBillService.selectByPrimaryKey(id));
		}
		return "/stockOutcomeBill/showList";
	}


	@RequiredPermission("订单审核")
	@RequestMapping("audit")
	@ResponseBody
	public JsonResult audit(Long id) {
		JsonResult result = new JsonResult();
		try {
			stockOutcomeBillService.audit(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("订单编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		model.addAttribute("depots", depotService.selectAll());
		model.addAttribute("clients", clientService.selectAll());
		if (id != null) {
			model.addAttribute("stockOutcomeBill", stockOutcomeBillService.selectByPrimaryKey(id));
		}
		return "/stockOutcomeBill/input";
	}
	@RequiredPermission("订单保存或更新")
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public JsonResult saveOrUpdate(StockOutcomeBill stockOutcomeBill) {
		JsonResult result = new JsonResult();
		try {
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBillService.updateByPrimaryKey(stockOutcomeBill);
			}else {
				stockOutcomeBillService.insert(stockOutcomeBill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("操作失败");
		}
		return result;
	}
}
