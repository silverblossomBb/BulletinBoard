package spring.project.summer.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import spring.project.summer.service.BoardService;
import spring.project.summer.vo.NoticeVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bs;
	
	@PostMapping("/notice/{crud}")
	public void noticeSelect(@PathVariable String crud, NoticeVO nv, HttpSession ses, HttpServletResponse res) {
		if ("select".equals(crud)) {
			try {
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(bs.getNotice(nv).toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			nv.setId(ses.getAttribute("id").toString());
			nv.setName(ses.getAttribute("name").toString());
			bs.setNotice(crud, nv);
		}
	}
	
}
