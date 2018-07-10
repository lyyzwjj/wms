package cn.wolfcode.wms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("client")
public class ClientController {
	@Autowired
	IClientService clientService;
	@RequiredPermission("客户列表")
	@RequestMapping("list")
	public String list(Model model,QueryObject qo) {
		PageResult result = clientService.query(qo);
		model.addAttribute("result", result);
		return "client/list";
	}
	@RequiredPermission("客户删除")

	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {
			clientService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("客户编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		if (id != null) {
			model.addAttribute("client", clientService.selectByPrimaryKey(id));
		}
		return "/client/input";
	}
	@RequiredPermission("客户保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Client client) {
		if (client.getId() != null) {
			clientService.updateByPrimaryKey(client);
		}else {
			clientService.insert(client);
		}
		return "redirect:/client/list";
	}
}
