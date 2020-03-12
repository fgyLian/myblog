package com.fgy.myblog.controller.back;

import com.fgy.myblog.bean.CategoryInfo;
import com.fgy.myblog.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 栏目管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/category")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl categoryService;

	
	@RequestMapping("/list")
	public String list(Model model) {

		CategoryInfo categoryInfo = new CategoryInfo();
		List<CategoryInfo> categoryList=categoryService.categoryList(categoryInfo);
		model.addAttribute("categoryList",categoryList);
		return "back/category/category";
	}

	@RequestMapping("/add")
	public String add(CategoryInfo category,Model model) {
		int row=categoryService.add(category);
		if(row>0){
			model.addAttribute("tip","添加成功");

		}else{
			model.addAttribute("tip","添加失败");

		}
		return "back/category/tip";

	}

	@RequestMapping("toUpdate")
	public String toUpdate(Integer id,Model model) {
		CategoryInfo categoryInfo=categoryService.selectById(id);
		model.addAttribute("categoryInfo",categoryInfo);
		return "back/category/category_update";
	}

	@RequestMapping("doUpdate")
	public String doUpdate(CategoryInfo category,Model model) {
		int row=categoryService.doUpdate(category);

		if(row>0){
			model.addAttribute("tip","修改成功");
		}else{
			model.addAttribute("tip","修改失败");
		}
		CategoryInfo categoryInfo=categoryService.selectById(category.getCategoryId());
		model.addAttribute("categoryInfo",categoryInfo);
		return "back/category/category_update";
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		int row=categoryService.delete(id);

		if(row>0){
			return "redirect:/back/category/list";
		}else{
			return "back/category/category_update";
		}
	}

}
