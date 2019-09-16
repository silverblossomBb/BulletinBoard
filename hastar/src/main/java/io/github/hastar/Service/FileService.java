package io.github.hastar.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.github.hastar.Dao.FileDao;
import io.github.hastar.Util.QueryId;
import io.github.hastar.VO.DownloadVO;
import io.github.hastar.VO.UploadVO;

@Service
public class FileService {
	
	@Autowired
	FileDao fileDao;
	
	public void fileUpload(@RequestParam("file") MultipartFile[] files, HttpSession session, int noticeNo) {
		if (files.length > 0) {
			try {
				for (int i = 0; i < files.length; i++) {
					MultipartFile file = files[i];
					
					String path = "D:\\HastarData\\";
					File f = new File(path);
					if (!f.isDirectory()) {
						f.mkdirs();
					}
					
					String originName = file.getOriginalFilename();
					if (originName != "") {
						String uuid = UUID.randomUUID().toString();
						OutputStream os = new FileOutputStream(new File(path + uuid));
						byte[] data = file.getBytes();
						os.write(data);
						os.close();
						
						setUpload(new UploadVO(noticeNo, session.getAttribute("id").toString(), session.getAttribute("name").toString(), originName, uuid));
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void fileDownload(int noticeNo, String id, HttpServletResponse res, HttpSession session) {
		HashMap<String,Object> fileInfo = getUpload(noticeNo);
		String uuid = fileInfo.get("uuid").toString();
		String originName = fileInfo.get("originName").toString();
		System.out.println("uuid: " + uuid);
		System.out.println("originName: " + originName);
		
		String path = "D:\\HastarData\\";
		
		try {
			InputStream input = new FileInputStream(path + uuid);
			OutputStream output = res.getOutputStream();
			IOUtils.copy(input, output);
			
//			res.setHeader("Content-Disposition", "inline");
//			res.setContentType("application/octet-stream");
//			res.setContentType("image/png");
			res.setHeader("Content-Disposition", "attachment; filename=\""+ originName + "\"");
			input.close();
			output.close();
			
			setDownload(new DownloadVO(session.getAttribute("id").toString(), uuid));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setUpload(UploadVO vo) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "insert");
		paramMap.put("queryId", query.id("upload"));
		paramMap.put("params", vo);
		fileDao.upload(paramMap);
		
	}
	
	private HashMap<String,Object> getUpload(int no) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		UploadVO vo = new UploadVO();
		vo.setNo(no);
		paramMap.put("queryType", "selectList");
		paramMap.put("queryId", query.id("upload"));
		paramMap.put("params", vo);
		
		List<HashMap<String,Object>> returnList =(List<HashMap<String,Object>>) fileDao.upload(paramMap).get("result");
		return returnList.get(0);
	}
	
	private void setDownload(DownloadVO vo) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		QueryId query = new QueryId();
		paramMap.put("queryType", "insert");
		paramMap.put("queryId", query.id("download"));
		paramMap.put("params", vo);
		fileDao.download(paramMap);
	}
}
