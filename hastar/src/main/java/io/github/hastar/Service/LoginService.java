package io.github.hastar.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.hastar.Dao.LoginDao;
import io.github.hastar.Util.HttpUtil;
import io.github.hastar.Util.QueryId;
import io.github.hastar.VO.LoginVO;
import net.sf.json.JSONObject;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	public void step1(HttpServletResponse res) {		
		try {
			String url = "https://kauth.kakao.com/oauth/authorize";
			url += "?client_id=4083645aa3b56c3390fa00b273141f7f&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20016/KakaoBack", "UTF-8");
//			url += URLEncoder.encode("http://localhost:8080/KakaoBack", "UTF-8");
			url += "&response_type=code";
			res.sendRedirect(url);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	};

	public boolean step2(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		boolean login = false;
		try {
			String code = req.getParameter("code");		
			String url = "https://kauth.kakao.com/oauth/token";
			url += "?client_id=4083645aa3b56c3390fa00b273141f7f&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20016/KakaoBack", "UTF-8");
//			url += URLEncoder.encode("http://localhost:8080/KakaoBack", "UTF-8");
			url += "&code=" + code;
			url += "&grant_type=authorization_code";
			HashMap<String, Object> resultMap = HttpUtil.getUrl(url);
			String access_token = resultMap.get("access_token").toString();
			
			String userUrl = "https://kapi.kakao.com/v2/user/me";
			userUrl += "?access_token=" + access_token; 
			resultMap = HttpUtil.getUrl(userUrl);
			
			HashMap<String, Object> returnMap = setData(resultMap);
			if ("true".equals(returnMap.get("status"))) {
				session.setAttribute("id", returnMap.get("id"));
				session.setAttribute("name", returnMap.get("name"));
				session.setAttribute("image", returnMap.get("image"));
				session.setAttribute("access_token", access_token);
				login = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		return login;
	}
	
	public void logout(HttpServletResponse res, HttpSession session) {
		try {
			String url = "https://kapi.kakao.com/v1/user/unlink";
	        HttpPost post = new HttpPost(url);
	        post.addHeader("Authorization", "Bearer " + session.getAttribute("access_token").toString());
	        
	        HttpClient client = HttpClientBuilder.create().build();
	        client.execute(post);
	        session.invalidate();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private HashMap<String, Object> setData(HashMap<String, Object> userMap) {
		JSONObject p = JSONObject.fromObject(userMap.get("properties"));
		String id = userMap.get("id").toString();
		String name = p.getString("nickname");
		String image = p.getString("profile_image");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "insert");
		paramMap.put("queryId", query.id("loginInfo")); // queryVo
		paramMap.put("params", new LoginVO(id, name, image)); // loginVo
		
		return loginDao.db(paramMap);
	}
	
}
