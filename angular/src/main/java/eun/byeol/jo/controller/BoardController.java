package eun.byeol.jo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eun.byeol.jo.bean.DataBean;
import eun.byeol.jo.bean.UserBean;
import eun.byeol.jo.service.NoticeServiceInterface;
import eun.byeol.jo.service.UserServiceInterface;

@Controller
public class BoardController {
	
	@Autowired
	NoticeServiceInterface nsi;
	
	@Autowired
	UserServiceInterface usi;
	
	@RequestMapping("/")
	public String main(HttpServletRequest req) {
		return "board";
	}
	
	@RequestMapping("/notice/{crud}")
	public void insert(@PathVariable String crud, HttpServletRequest req, HttpServletResponse res) {
		if (crud.equals("select")) {
			try {
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(nsi.getData().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			nsi.setData(crud, new DataBean(Integer.parseInt(req.getParameter("no")), req.getParameter("id"), req.getParameter("txt")));
		}
		
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("login()");
		try {
			res.getWriter().write(usi.logIn(new UserBean(req.getParameter("id"), req.getParameter("pw"))).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
