package eun.byeol.jo.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eun.byeol.jo.bean.DataBean;
import eun.byeol.jo.bean.QueryBean;

@Repository
public class NoticeDao implements NoticeDaoInterface {

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Object> db(HashMap<String, Object> paramMap) {
		List<Object> result = null;
		
		// queryType // queryId // params
		String queryType = paramMap.get("queryType").toString();
		QueryBean queryId = (QueryBean) paramMap.get("queryId");
		DataBean params = (DataBean) paramMap.get("params");
		
		switch (queryType) {
		case "selectList":
			result =  sql.selectList(queryId.getSelect());
			break;
			
		case "insert":
			sql.insert(queryId.getInsert(), params);
			break;
			
		case "update":
			sql.update(queryId.getUpdate(), params);
			break;
			
		case "delete":
			sql.delete(queryId.getDelete(), params);
			break;
		}
		
		return result;
	}

}
