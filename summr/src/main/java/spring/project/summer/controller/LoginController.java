package spring.project.summer.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;
import spring.project.summer.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService ls;
	
	@GetMapping("/login")
	public void login(HttpServletResponse res) {
		ls.loginStep1(res);
	}
	
	@RequestMapping("/KakaoBack")
	public String KakaoBack(HttpServletRequest req, HttpSession ses) {
		ls.loginStep2(req, ses);
		ses.setAttribute("login", "true");
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession ses) {
		ls.logout(ses);
		return "redirect:/";
	}
	
	@PostMapping("/getVisitors")
	public void getVisitors(HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(ls.getLogin().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/loginCheck")
	public void loginCheck(HttpSession ses, HttpServletResponse res) {
		try {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			if (ses.getAttribute("login") != null) {
				resultMap.put("login", ses.getAttribute("login").toString());
				res.getWriter().write(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
