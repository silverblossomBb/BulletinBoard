package io.github.hastar.Dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.hastar.VO.LoginVO;
import io.github.hastar.VO.QueryVO;

@Repository
public class LoginDao {
	
	@Autowired
	SqlSession sql;
	
	public HashMap<String, Object> db(HashMap<String, Object> paramMap) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		// queryType // queryId // params
		String queryType = paramMap.get("queryType").toString();
		QueryVO queryId = (QueryVO) paramMap.get("queryId");
		LoginVO params = (LoginVO) paramMap.get("params");
		
		switch (queryType) {
		case "insert":
			if (sql.insert(queryId.getInsert(), params) == 1) {
				resultMap.put("status", "true");
				resultMap.put("id", params.getId());
				resultMap.put("name", params.getName());
				resultMap.put("image", params.getImage());
			} else {
				resultMap.put("status", "false");
			};
			break;
		}
		
		return resultMap;
	}
}
