package io.github.hastar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BController {
	
	@RequestMapping("/myInfo")
	public String myinfo(HttpSession session,RedirectAttributes redirectAttributes) {
		if(session.getAttribute("id") == null) {
			redirectAttributes.addAttribute("rKey", "please Login");
			return "redirect:/test";
		}
		return "board/myInfo";
	}
}
