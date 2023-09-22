package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro30.board.vo.ArticleVO;


public interface BoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	//답글 작성하기.
	public int insertReplyNewArticle(Map articleMap) throws DataAccessException;
	// 일반 데이터 글쓰기, 단일 이미지 사용하고, 다중 이미지도 사용중. 
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	// 다중이미지 글쓰기- 여러 이미지들만 따로 디비에 저장하는 로직. 
	public void insertNewImage(Map articleMap) throws DataAccessException;
	
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	// 단일 이미지 버전의 수정적용하기. 이미지 변경도 포함.
	public void updateArticle(Map articleMap) throws DataAccessException;
	// 다중 이미지 버전의 수정 적용하기. 일반 데이터만 변경.
	public void updateArticle2(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	// 이미지만 디비에서 따로 삭제
	public void deleteImage(int imageFileNO) throws DataAccessException;
	
	
	public List selectImageFileList(int articleNO) throws DataAccessException;
	
}
