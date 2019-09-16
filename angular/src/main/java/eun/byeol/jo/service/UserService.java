package eun.byeol.jo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eun.byeol.jo.bean.UserBean;
import eun.byeol.jo.dao.UserDaoInterface;
import net.sf.json.JSONObject;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	UserDaoInterface udi;
	
	@Override
	public JSONObject logIn(UserBean user) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "selectOne");
		paramMap.put("queryId", query.id("users"));
		paramMap.put("params", user);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String id = (String) udi.db(paramMap);
		if ((user.getId()).equals(id)) {
			resultMap.put("result", "true");
		} else {
			resultMap.put("result", "false");
		}
	
		return JSONObject.fromObject(resultMap);
	}

}
