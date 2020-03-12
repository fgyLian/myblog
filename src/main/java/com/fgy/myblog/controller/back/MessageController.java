package com.fgy.myblog.controller.back;

import com.fgy.myblog.bean.MessageInfo;
import com.fgy.myblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 留言管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/message/")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("list")
	public String list(MessageInfo message, Model model) {
		List<MessageInfo> messageList = messageService.selecetAll(message);
		model.addAttribute("messageList",messageList);
		model.addAttribute("messageInfo",message);
		return "back/message/message_list";
	}
	
	@RequestMapping("update")
	public String add(MessageInfo message,Model model) {
		List<MessageInfo> messageList = messageService.selecetAll(message);
		model.addAttribute("messageList",messageList);
		messageService.update(message);
		return "redirect:/back/message/list";
	}


	@RequestMapping("delete")
	public String delete(Integer id) {

		messageService.deleteById(id);
		return "redirect:/back/message/list";
	}
}
