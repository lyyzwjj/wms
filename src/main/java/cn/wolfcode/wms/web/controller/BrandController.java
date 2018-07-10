package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Brand;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("brand")
public class BrandController {
    Long id;
	@Autowired
	IBrandService brandService;
	@RequiredPermission("品牌列表")
	@RequestMapping("list")
	public String list(Model model,QueryObject qo) {
        PageResult result = brandService.query(qo);
        List<String> list = new ArrayList<>();
        model.addAttribute("result", result);
		return "brand/list";
	}
	@RequiredPermission("品牌删除")

	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
		try {
			brandService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("品牌编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		if (id != null) {
			model.addAttribute("brand", brandService.selectByPrimaryKey(id));
		}
		return "/brand/input";
	}
	@RequiredPermission("品牌保存或更新")
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Brand brand) {
		if (brand.getId() != null) {
			brandService.updateByPrimaryKey(brand);
		}else {
			brandService.insert(brand);
		}
		return "redirect:/brand/list";
	}
}
