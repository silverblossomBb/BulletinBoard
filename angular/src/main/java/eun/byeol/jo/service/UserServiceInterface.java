package eun.byeol.jo.service;

import eun.byeol.jo.bean.UserBean;
import net.sf.json.JSONObject;

public interface UserServiceInterface {
	
	public JSONObject logIn(UserBean user);
	
}
