package eun.byeol.jo.service;

import eun.byeol.jo.bean.DataBean;
import eun.byeol.jo.bean.UserBean;
import net.sf.json.JSONObject;

public interface NoticeServiceInterface {
	
	public JSONObject getData();
	public boolean setData(String crud, DataBean data);
	
}
