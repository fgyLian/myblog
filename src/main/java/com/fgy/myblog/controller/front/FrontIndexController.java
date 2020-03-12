package com.fgy.myblog.controller.front;

import com.fgy.myblog.bean.ArticleInfo;
import com.fgy.myblog.bean.CategoryInfo;
import com.fgy.myblog.bean.MessageInfo;
import com.fgy.myblog.service.ArticleService;
import com.fgy.myblog.service.CategoryService;
import com.fgy.myblog.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/front/")
public class FrontIndexController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageService messageService;

    public void init(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        CategoryInfo categoryInfo = new CategoryInfo();
        List<CategoryInfo> catList = categoryService.categoryList(categoryInfo);
        model.addAttribute("catList",catList);

        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleList = articleService.selecetAll(null);
        PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleList);
        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("articleList",articleList);

    }

    @RequestMapping("index")
    public String index (Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){

        init(model, pageNum, pageSize);


        return "front/index";
    }

    @RequestMapping("listview")
    public String listview(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize,Integer articleId){

        init(model, pageNum, pageSize);

        ArticleInfo articleInfo = articleService.selectById(articleId);

        model.addAttribute("articleInfo",articleInfo);
        return "front/listview";

    }

    @RequestMapping("category")
    public String category(Integer categoryId,Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize){
        CategoryInfo category = new CategoryInfo();
        List<CategoryInfo> catList = categoryService.categoryList(category);


        PageHelper.startPage(pageNum,pageSize);
        List<ArticleInfo> articleList= articleService.selectArtBycategoryId(categoryId);
        PageInfo<ArticleInfo> pageInfo = new PageInfo<>(articleList);

        CategoryInfo categoryInfo = categoryService.selectById(categoryId);

        model.addAttribute("id",categoryId);
        model.addAttribute("catList",catList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("articleList",articleList);
        model.addAttribute("categoryInfo",categoryInfo);
        return "front/list";

    }


    @RequestMapping("message")
    public  String message(Model model){
        CategoryInfo category = new CategoryInfo();
        List<CategoryInfo> catList = categoryService.categoryList(category);
        model.addAttribute("catList",catList);

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessageMark("1");

        List<MessageInfo> messageInfoList = messageService.selecetAll(messageInfo);

        model.addAttribute("messageInfoList",messageInfoList);
        return "front/message";
    }

    @RequestMapping("addMessage")
    public  String addMessage(Model model, MessageInfo messageInfo){
        messageInfo.setMessageTime(new Date());
        messageInfo.setMessageMark("1");
        int row = messageService.add(messageInfo);

        return "redirect:/front/message";

    }

}
