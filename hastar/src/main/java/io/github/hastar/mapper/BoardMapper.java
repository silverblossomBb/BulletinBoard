package io.github.hastar.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.github.hastar.VO.PostVO;

@Mapper
public interface BoardMapper {
	public List<PostVO> getAllData();
	public PostVO getOneData(String no);
	public int setNewData(PostVO pv);
	public PostVO selectNewData(PostVO pv);
	public int updateData(PostVO pv);
	public int deleteData(HashMap<String,Object> test);
	public List<HashMap<String,Object>> getFileData(String no);
}
