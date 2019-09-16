package spring.project.summer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
	
	@GetMapping("/")
	public String main() {
		return "home";
	}
	
	@GetMapping("/write")
	public String boardNew() {
		return "board/write";
	}
	
	@GetMapping("/detail/{noticeNo}")
	public String boardDetail(@PathVariable String noticeNo, HttpSession ses) {
		ses.setAttribute("noticeNo", noticeNo);
		return "board/detail";
	}
}
