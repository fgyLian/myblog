package com.fgy.myblog.controller.back;

import com.fgy.myblog.bean.ArticleInfo;
import com.fgy.myblog.bean.CategoryInfo;
import com.fgy.myblog.bean.UserInfo;
import com.fgy.myblog.service.ArticleService;
import com.fgy.myblog.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * 文章管理的控制器
 * @author Administrator
 *
 *
 */
@Controller
@RequestMapping("/back/article/")
public class ArticleController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 查询文章列表
	 * @param articleInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(ArticleInfo articleInfo, Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<ArticleInfo> articleList = articleService.selecetAll(articleInfo);

		PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleList);

		model.addAttribute("articleInfo",articleInfo);
		model.addAttribute("articleList",articleList);
		model.addAttribute("pageInfo",pageInfo);
		return "back/article/article_list";
	}


	/**
	 * 跳转到文章添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("toAdd")
	public String toAdd(Model model) {
		CategoryInfo categoryInfo = new CategoryInfo();
		List<CategoryInfo> categoryList = categoryService.categoryList(categoryInfo);
		model.addAttribute("categoryList",categoryList);
		return "back/article/article_add";
	}

	/**
	 * 执行文章添加操作
	 * @param article
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("doAdd")
	public String doAdd(ArticleInfo article, Model model, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("loginInfo");
		if(user!=null){
			article.setUserId(user.getUserId());

			if(article.getCategoryId()==null||article.getCategoryId().equals("")){
				article.setCategoryId(1);
			}

			article.setArticleTime(new Date());


		}
		int row = articleService.add(article);
		if(row>0){

			return "redirect:/back/article/list";
		}else {

			return "redirect:/back/article/toAdd";
		}


	}

	/**
	 * 上传图片
	 * @param upload
	 * @return
	 */
	@RequestMapping("upload")
	@ResponseBody
	public  String upload(@RequestParam MultipartFile upload){
		return articleService.doPutFile(upload);
	}

	/**
	 * 富文本编辑器图片上传
	 * @param upload
	 * @param request
	 * @param response
	 */
	@RequestMapping("uploadfile")
	public  void  uploadFile(@RequestParam MultipartFile upload,HttpServletRequest request, HttpServletResponse response){

		try {
			String url =articleService.doPutFile(upload);
			PrintWriter out = response.getWriter();;
			String callBack = request.getParameter("CKEditorFuncNum");
			out.println("<script>window.parent.CKEDITOR.tools.callFunction("+callBack+",'"+url+"')</script>");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@GetMapping("/delete")
	public String delete(Integer id) {

		int row = articleService.deleteById(id);


		return "redirect:/back/article/list";
	}

	/**
	 * 跳转到修改文章页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(Integer id, Model model) {

		CategoryInfo categoryInfo = new CategoryInfo();
		List<CategoryInfo> categoryList = categoryService.categoryList(categoryInfo);
		model.addAttribute("categoryList",categoryList);

		ArticleInfo articleInfo = articleService.selectById(id);
		System.out.println(articleInfo);
		model.addAttribute("articleInfo",articleInfo);
		return "back/article/article_update";
	}

	/**
	 * 执行修改操作
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping("doUpdate")
	public String doUpdate(ArticleInfo article, Model model){
		int row = articleService.update(article);
		if(row>0){

			return "redirect:/back/article/list";
		}else {

			return "redirect:/back/article/update";
		}
	}
}
