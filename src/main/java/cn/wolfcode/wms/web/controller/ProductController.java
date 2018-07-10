package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.domain.Product;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.ProductQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IProductService;
import cn.wolfcode.wms.util.JsonResult;
import cn.wolfcode.wms.util.RequiredPermission;
import cn.wolfcode.wms.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:27:02
 *@mail   wzzst310@163.com
 *@desp
 */
@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	IProductService productService;
	@Autowired
	IBrandService brandService;

	@Autowired
	ServletContext ctx;


	@RequiredPermission("商品列表")
	@RequestMapping("list")
	public String list(Model model,ProductQueryObject qo) {
		PageResult result = productService.query(qo);
		model.addAttribute("result", result);
		model.addAttribute("qo", qo);
		model.addAttribute("brands", brandService.selectAll());
		return "product/list";
	}
	@RequestMapping("selectList")
	public String selectList(Model model,ProductQueryObject qo) {
		PageResult result = productService.query(qo);
		model.addAttribute("result", result);
		model.addAttribute("qo", qo);
		model.addAttribute("brands", brandService.selectAll());
		return "product/selectList";
	}

	@RequiredPermission("商品删除")
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		try {

			productService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg(e.getMessage());
		}
		return result;
	}
	@RequiredPermission("商品编辑")
	@RequestMapping("input")
	public String input(Model model,Long id) {
		model.addAttribute("brands",brandService.selectAll());
		if (id != null) {
			model.addAttribute("product", productService.selectByPrimaryKey(id));
		}
		return "/product/input";
	}
	@RequiredPermission("商品保存或更新")
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public JsonResult saveOrUpdate(Product product, MultipartFile pic) {
		JsonResult result = new JsonResult();
		//调用工具类
		//获得文件在项目中的路径
		try {
			if (product.getId() != null) {
																	//ajaxForm可以没有传值过来一定是null
				if (!StringUtils.isEmpty(product.getImagePath()) && pic != null) {
					UploadUtil.deleteFile(ctx, product.getImagePath());
					String realPath = ctx.getRealPath("/upload");
					String imagePath = UploadUtil.upload(pic, realPath);//给文件和项目文件夹在磁盘中的位置
					product.setImagePath(imagePath);
				}
				productService.updateByPrimaryKey(product);
			} else {
				if (pic != null) {
					String realPath = ctx.getRealPath("/upload");
					String imagePath = UploadUtil.upload(pic, realPath);//给文件和项目文件夹在磁盘中的位置
					product.setImagePath(imagePath);
				}
				productService.insert(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.markMsg("操作失败,请重新尝试一遍");
		}
		return result;
	}
}
