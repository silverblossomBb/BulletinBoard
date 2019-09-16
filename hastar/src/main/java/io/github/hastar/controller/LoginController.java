package io.github.hastar.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.hastar.Service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/login")
	public void login(HttpServletResponse res) {
		loginService.step1(res);
	}
	
	@RequestMapping("/KakaoBack")
	public String kakaoBack(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		loginService.step2(req, res, session);
		
		return "redirect:/test";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletResponse res, HttpSession session) {
		loginService.logout(res, session);
		//session.invalidate();
		
		return "redirect:/test";
	}
}
