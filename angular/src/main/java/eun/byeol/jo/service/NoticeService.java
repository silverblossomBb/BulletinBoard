package eun.byeol.jo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eun.byeol.jo.bean.DataBean;
import eun.byeol.jo.bean.UserBean;
import eun.byeol.jo.dao.NoticeDaoInterface;
import net.sf.json.JSONObject;

@Service
//@Primary
public class NoticeService implements NoticeServiceInterface {
	
	@Autowired
	NoticeDaoInterface cdi;
	
	@Override
	public JSONObject getData() {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "selectList");
		paramMap.put("queryId", query.id("notice")); //querybean
		paramMap.put("params", null);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", cdi.db(paramMap));
		return JSONObject.fromObject(resultMap);
	}

	@Override
	public boolean setData(String crud, DataBean data) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", crud);
		paramMap.put("queryId", query.id("notice")); //querybean
		paramMap.put("params", data);
		cdi.db(paramMap);
		return true;
	}
	
}
