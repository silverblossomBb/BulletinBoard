package spring.project.summer.service;

import java.net.URL;
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

import net.sf.json.JSONObject;
import spring.project.summer.mapper.DBMapper;
import spring.project.summer.util.HttpUtil;
import spring.project.summer.vo.LoginVO;

@Service
public class LoginService {
	
	public void loginStep1(HttpServletResponse res) {
		try {
			String url = "https://kauth.kakao.com/oauth/authorize";
			url += "?client_id=4083645aa3b56c3390fa00b273141f7f&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20016/KakaoBack", "UTF-8");
//			url += URLEncoder.encode("http://localhost:8080/KakaoBack", "UTF-8");
			url += "&response_type=code";
			res.sendRedirect(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean loginStep2(HttpServletRequest req, HttpSession ses) {
		boolean login = false;
		try {
			String url = "https://kauth.kakao.com/oauth/token";
			url += "?client_id=4083645aa3b56c3390fa00b273141f7f&redirect_uri=";
			url += URLEncoder.encode("http://gdj16.gudi.kr:20016/KakaoBack", "UTF-8");
//			url += URLEncoder.encode("http://localhost:8080/KakaoBack", "UTF-8");
			url += "&code=" + req.getParameter("code");
			url += "&grant_type=authorization_code";
			HashMap<String, Object> resultMap = HttpUtil.getUrl(new URL(url));
			
			String access_token = resultMap.get("access_token").toString();
			String userUrl = "https://kapi.kakao.com/v2/user/me";
			userUrl += "?access_token=" + access_token; 
			resultMap = HttpUtil.getUrl(new URL(userUrl));
			
			JSONObject jObject = JSONObject.fromObject(resultMap.get("properties"));
			String id = resultMap.get("id").toString();
			String name = jObject.getString("nickname");
			String imageUrl = "";
			if (jObject.has("profile_image")) {
				imageUrl = jObject.getString("profile_image");
			} else {
				imageUrl = "/resources/img/pin.jpg";
			}
			
			if (setLogin(new LoginVO(id, name, imageUrl)) == 1) {
				ses.setAttribute("id", id);
				ses.setAttribute("name", name);
				ses.setAttribute("imageUrl", imageUrl);
				ses.setAttribute("access_token", access_token);
				login = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return login;
	}
	
	public void logout(HttpSession ses) {
		try {
			String url = "https://kapi.kakao.com/v1/user/unlink";
	        HttpPost post = new HttpPost(url);
	        post.addHeader("Authorization", "Bearer " + ses.getAttribute("access_token").toString());
	        
	        HttpClient client = HttpClientBuilder.create().build();
	        client.execute(post);
	        ses.invalidate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	DBMapper dbm;
	
	private int setLogin(LoginVO lv) {
		return dbm.insertLogin(lv);
	}
	
	public JSONObject getLogin() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("getData", dbm.selectLogin());
		return JSONObject.fromObject(resultMap);
	}
}
