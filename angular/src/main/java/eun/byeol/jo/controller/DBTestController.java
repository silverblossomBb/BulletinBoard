package eun.byeol.jo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DBTestController {
	
	@Autowired
	SqlSession sql;
	
	@RequestMapping("/dbtest")
	public String test(HttpServletRequest req) {
		
		String id = (String) sql.selectOne("sql.test");
		System.out.println(id);
		
		return "dbtest";
	}
}
