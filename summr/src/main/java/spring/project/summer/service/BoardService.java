package spring.project.summer.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import spring.project.summer.mapper.DBMapper;
import spring.project.summer.vo.NoticeVO;

@Service
public class BoardService {
	
	@Autowired
	DBMapper dbm;
	
	public void setNotice(String crud, NoticeVO nv) {
		switch (crud) {
		case "insert":
			dbm.insertNotice(nv);
			break;
		case "update":
			dbm.updateNotice(nv);
			break;
		case "delete":
			dbm.deleteNotice(nv);
			break;
		};
		
	}
	
	public JSONObject getNotice(NoticeVO nv) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("getData", dbm.selectNotice(nv));
		return JSONObject.fromObject(resultMap);
	}
}
