package eun.byeol.jo.service;

import eun.byeol.jo.bean.QueryBean;

public class QueryId {
	
	public QueryBean id(String table) {
		QueryBean target = null;
		
		if (table.equals("notice")) {
			target = new QueryBean("selectData","insertData","deleteData","updateData");
		} else if (table.equals("users")) {
			target = new QueryBean("selectUser", "insertUser", "deleteUser", "updateUser");
		}
		
		return target;
	}
	
}
