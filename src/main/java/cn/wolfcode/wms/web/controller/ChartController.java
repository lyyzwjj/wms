package cn.wolfcode.wms.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.wolfcode.wms.query.ChartQueryObject;
import cn.wolfcode.wms.query.ProfitChartQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IChartService;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.ISupplierService;

/**
 * @author WangZhe
 * @time 2018年6月19日下午6:27:02
 * @mail wzzst310@163.com
 * @desp
 */
@Controller
@RequestMapping("chart")
public class ChartController {
	@Autowired
	IChartService chartService;
	@Autowired
	ISupplierService supplierService;
	@Autowired
	IClientService clientService;
	@Autowired
	IBrandService brandService;

	@RequestMapping("list")
	public String list(Model model, @ModelAttribute("qo") ChartQueryObject qo) {
		List<Map<String, Object>> charts = chartService.queryForList(qo);
		model.addAttribute("charts", charts);
		model.addAttribute("qo", qo);
		model.addAttribute("suppliers", supplierService.selectAll());
		model.addAttribute("groupByTypes", ChartQueryObject.groupByTypes);
		return "/chart/list";
	}

	@RequestMapping("profitList")
	public String profitList(Model model, @ModelAttribute("qo") ProfitChartQueryObject qo) {
		List<Map<String, Object>> charts = chartService.queryForProfitList(qo);
		model.addAttribute("charts", charts);
		model.addAttribute("qo", qo);
		model.addAttribute("clients", clientService.selectAll());
		model.addAttribute("brands", brandService.selectAll());
		model.addAttribute("groupByTypes", ProfitChartQueryObject.groupByTypes);
		return "/chart/profitList";
	}

	@RequestMapping("saleChartByBar")
	public String saleChartByBar(Model model, @ModelAttribute("qo") ProfitChartQueryObject qo) {
		model.addAttribute("groupByType", ProfitChartQueryObject.groupByTypes.get(qo.getGroupByType()));
		List<Map<String, Object>> charts = chartService.queryForProfitList(qo);
		List<Object> groupByTypes = new ArrayList<>();
		List<Object> totalAmounts = new ArrayList<>();
		for (Map<String, Object> chart : charts) {
			groupByTypes.add(chart.get("groupByType"));
			totalAmounts.add(chart.get("totalAmount"));
		}
		model.addAttribute("groupByTypes", JSON.toJSONString(groupByTypes));
		model.addAttribute("totalAmount", JSON.toJSONString(totalAmounts));
		return "/chart/saleChartByBar";
	}

	@RequestMapping("saleChartByPie")
	public String saleChartByPie(Model model, @ModelAttribute("qo") ProfitChartQueryObject qo) {
		model.addAttribute("groupByType", ProfitChartQueryObject.groupByTypes.get(qo.getGroupByType()));
		List<Map<String, Object>> charts = chartService.queryForProfitList(qo);
		List<Object> groupByTypes = new ArrayList<>();
		List<Map<String, Object>> datas = new ArrayList<>();
		for (Map<String, Object> chart : charts) {
			groupByTypes.add(chart.get("groupByType"));
			Map<String, Object> data = new HashMap<>();
			data.put("value", chart.get("totalAmount"));
			data.put("name", chart.get("groupByType"));
			datas.add(data);
		}
		model.addAttribute("groupByTypes", JSON.toJSONString(groupByTypes));
		model.addAttribute("datas", JSON.toJSONString(datas));
		return "/chart/saleChartByPie";
	}
}
