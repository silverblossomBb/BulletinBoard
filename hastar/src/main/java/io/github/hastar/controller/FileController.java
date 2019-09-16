package io.github.hastar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.github.hastar.Service.FileService;
import io.github.hastar.VO.PostVO;
import io.github.hastar.harin.BoardService;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	BoardService bsi;
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile[] files, PostVO pv, HttpServletRequest req, HttpSession session) {
		int noticeNo = bsi.setNewData(pv,session);
		fileService.fileUpload(files, session, noticeNo);
		
		return "redirect:/board";
	}
	
	@RequestMapping("/download/{no}")
	public void download(@PathVariable("no") String no, HttpSession session, HttpServletResponse res) {
		fileService.fileDownload(Integer.parseInt(no), session.getAttribute("id").toString(), res, session);
		
	}
}
