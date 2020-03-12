package com.fgy.myblog.controller.back;

import com.fgy.myblog.bean.UserInfo;
import com.fgy.myblog.service.impl.UserInfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/user")
public class UserInfoController {
	@Autowired
	UserInfoServiceImpl userInfoService;

	/*
	查询所有
	 */
	@GetMapping("/list")
	public String list(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3")Integer pageSize, UserInfo user) {
		PageHelper.startPage(pageNum,pageSize);
		List<UserInfo> list = userInfoService.selecetAll(user);
		PageInfo<UserInfo> userInfoPageInfo = new PageInfo<>(list);

		model.addAttribute("userInfo",user);
		model.addAttribute("users",list);
		model.addAttribute("pageInfo",userInfoPageInfo);
		return "back/userinfo/userinfo_list";
	}
	/*
	跳转到添加页面
	 */
	@GetMapping("/add")
	public String add() {
		return "back/userinfo/userinfo_add";
	}
	/*
	执行添加操作
	 */
	@PostMapping("/doAdd")
	public String doAdd(UserInfo user) {
		int row=userInfoService.add(user);
		if (row >0) {
			return "redirect:/back/user/list";
		}else {
			return "back/userinfo/userinfo_add";
		}

	}

	/*
	跳转到修改页面
	 */
	@GetMapping("/update")
	public String update(Integer id,Model model) {
		UserInfo user=userInfoService.selectById(id);
		model.addAttribute("user",user);
		return "back/userinfo/userinfo_add";
	}
	/*
	执行修改操作
	 */
	@PostMapping("/doUpdate")
	public String doUpdate(UserInfo user) {
		//user.setUserMark("1");
		int row=userInfoService.update(user);

		if (row >0) {
			return "redirect:/back/user/list";
		}else {
			return "/back/user/update";
		}

	}


	/*
	删除用户，假删除，把user_mark改为0
	*/

	@GetMapping("/delete")
	public String delete(Integer id) {
		int row=userInfoService.deleteById(id);

		return "redirect:/back/user/list";
	}
}
