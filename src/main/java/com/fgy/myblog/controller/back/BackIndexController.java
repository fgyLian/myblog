package com.fgy.myblog.controller.back;

import com.fgy.myblog.bean.UserInfo;
import com.fgy.myblog.service.ArticleService;
import com.fgy.myblog.service.MessageService;
import com.fgy.myblog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 后台启动控制器
 */
@Controller
@RequestMapping("/back")
public class BackIndexController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MessageService messageService;

	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.invalidate();
		return "back/login";
	}
	
	@RequestMapping("/index")
	public String index(UserInfo user, HttpSession session, Model model) {
		UserInfo u=userInfoService.isLogin(user);

		if(u!=null){
			session.setAttribute("loginInfo",u);

			//获取登陆时间
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			session.setAttribute("loginTime",sdf.format(date));
			model.addAttribute("artcount","artcount");
			return "back/index";
		}else{
			model.addAttribute("mgs","账号或密码错误");
			return "back/login";
		}
	}
	
	@RequestMapping("/main")
	public String main(Model model, HttpServletRequest request) {

		String ip=request.getRemoteAddr();
		int count=userInfoService.getUserCount();
		int messageCount=messageService.getMessageCount();

		int artcount=articleService.getArtCount();
		model.addAttribute("artcount",artcount);
		model.addAttribute("ip",ip);
		model.addAttribute("count",count);
		model.addAttribute("messageCount",messageCount);
		return "back/main";
	}
	
}
