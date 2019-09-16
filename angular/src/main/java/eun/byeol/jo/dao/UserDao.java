package eun.byeol.jo.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eun.byeol.jo.bean.QueryBean;
import eun.byeol.jo.bean.UserBean;

@Repository
public class UserDao implements UserDaoInterface {
	
	@Autowired
	SqlSession sql;
	
	@Override
	public String db(HashMap<String, Object> paramMap) {
		String result = null;
		
		// queryType // queryId // params
		String queryType = paramMap.get("queryType").toString();
		QueryBean queryId = (QueryBean) paramMap.get("queryId");
		UserBean params = (UserBean) paramMap.get("params");
		
		switch (queryType) {
		case "selectOne":
			result = sql.selectOne(queryId.getSelect(), params);
		}
		
		return result;
	}

}
