package io.github.hastar.Util;

import io.github.hastar.VO.QueryVO;

public class QueryId {
	
	public QueryVO id(String table) {
		QueryVO target = null;
		
		switch (table) {
		case "loginInfo":
			target = new QueryVO("selectLogin", "insertLogin", "deleteLogin", "updateLogin");
			break;
			
		case "upload":
			target = new QueryVO("selectUpload", "insertUpload", "deleteUpload", "updateUpload");
			break;
			
		case "download":
			target = new QueryVO("selectDownload", "insertDownload", "deleteDownload", "updateDownload");
			break;
			
		}
		
		return target;
	}
	
}
