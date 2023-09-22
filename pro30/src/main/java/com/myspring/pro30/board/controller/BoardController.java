package com.myspring.pro30.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


public interface BoardController {
	
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
	// 다중 이미지 글 추가
	public ResponseEntity addMultiImageNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
//	public ResponseEntity replyNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	//단일 이미지 답글 추가
	public ResponseEntity replyNewArticle(@RequestParam("parentNO") int parentNO,MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,
			                        HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 다중 이미지 처리 하는 로직.
	public ResponseEntity modArticle(@RequestParam("articleNO") int articleNO,MultipartHttpServletRequest multipartRequest,  HttpServletResponse response) throws Exception;
	public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
                              HttpServletRequest request, HttpServletResponse response) throws Exception;
	// 수정시, 이미지만 따로 삭제하는 로직. 
	public ResponseEntity  deleteImage(@RequestParam("imageFileNO") int imageFileNO,
			@RequestParam("articleNO") int articleNO,
			@RequestParam("imageFileName") String imageFileName,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	// 이미지들만 추가하기. 
	public ResponseEntity onlyImageUpload(@RequestParam("articleNO") int articleNO,MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;

}
