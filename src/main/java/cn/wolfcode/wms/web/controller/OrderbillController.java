package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Orderbill;
import cn.wolfcode.wms.query.OrderbillQueryObject;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.service.IOrderbillService;
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
@RequestMapping("orderbill")
public class OrderbillController {
	@Autowired
	IOrderbillService orderbillService;
	@Autowired
	ISupplierService supplierService;
	
	@RequiredPermission("订单列表")
	@RequestMapping("list")
	public String list(Model model,@ModelAttribute("qo")OrderbillQueryObject qo) {
		PageResult result = orderbillService.query(qo);
		model.addAttribute("qo", qo);
		model.addAttribute("result", result);
		model.addAttribute("suppliers", supplierService.selectAll());
		return "orderbill/list";
	}
	@RequiredPermission("订单删除")
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			orderbillService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("查看明细")
	@RequestMapping("show")
	public String show(Model model,Long id) {
		model.addAttribute("suppliers", supplierService.selectAll());
		if (id != null) {
			model.addAttribute("orderbill", orderbillService.selectByPrimaryKey(id));
		}
		return "/orderbill/showList";
	}


	@RequiredPermission("订单审核")
	@RequestMapping("audit")
	@ResponseBody
	public JsonResult audit(Long id) {
		System.out.println(id);
		JsonResult result = new JsonResult();
		try {
			orderbillService.audit(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("订单编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		model.addAttribute("suppliers", supplierService.selectAll());
		if (id != null) {
			model.addAttribute("orderbill", orderbillService.selectByPrimaryKey(id));
		}
		return "/orderbill/input";
	}
	@RequiredPermission("订单保存或更新")
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public JsonResult saveOrUpdate(Orderbill orderbill) {
		JsonResult result = new JsonResult();
		try {
			if (orderbill.getId() != null) {
				orderbillService.updateByPrimaryKey(orderbill);
			}else {
				orderbillService.insert(orderbill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("操作失败");
		}
		return result;
	}
}
