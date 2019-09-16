package spring.project.summer.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.project.summer.vo.LoginVO;
import spring.project.summer.vo.NoticeVO;

@Mapper
public interface DBMapper {
	
	// login Table
	public List<HashMap<String, Object>> selectLogin();
	public int insertLogin(LoginVO lv);
	
	// notice Table
	public List<HashMap<String, Object>> selectNotice(NoticeVO nv);
	public int insertNotice(NoticeVO nv);
	public int updateNotice(NoticeVO nv);
	public int deleteNotice(NoticeVO nv);
	
}
