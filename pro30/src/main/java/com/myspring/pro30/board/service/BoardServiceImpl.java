package com.myspring.pro30.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.board.dao.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;


@Service("boardService")
// 트랜잭션이 있다면 기존꺼 사용, 없다면 새로 생성. 
// 서비스에서, 예) 2개의 sql 묶어서, 처리를 한다. 
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl  implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	public List<ArticleVO> listArticles() throws Exception{
		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList();
        return articlesList;
	}

	
	//단일 이미지 글쓰기.
//	@Override
//	public int addNewArticle(Map articleMap) throws Exception{
//		// 동네2 -> 동네3 
//		return boardDAO.insertNewArticle(articleMap);
//	}
	
	//단일  이미지 답글쓰기.
		@Override
		public int addReplyNewArticle(Map articleMap) throws Exception{
			// 동네2 -> 동네3 
			return boardDAO.insertReplyNewArticle(articleMap);
		}
	
	 //다중 이미지 글쓰기.
		// articleMap : 일반 데이터 + 여러 파일 이미지 이름 담아져 있다.
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		// 새 게시글 번호를 가져오는 로직 
		int articleNO = boardDAO.insertNewArticle(articleMap);
		// 일반 데이터를 디비에 저장하고, 새 게시글 번호를 가져 왔음.
		
		// 새게시글 번호를 맵에 저장.
		articleMap.put("articleNO", articleNO);
		// 파일 데이터를 디비에 저장 하는 로직. 
		// 이미지 테이블에, 해당 게시글 번호로, 여러 이미지를 저장 할 예정. 
		boardDAO.insertNewImage(articleMap);
		return articleNO;
	}
	
	 //다중 이미지 이미지만 디비에 저장
	// articleMap : 일반 데이터 + 여러 파일 이미지 이름 담아져 있다.
@Override
public void addOnlyImage(Map articleMap) throws Exception{
	// 새 게시글 번호를 가져오는 로직 
	int articleNO = boardDAO.insertNewArticle(articleMap);
	// 일반 데이터를 디비에 저장하고, 새 게시글 번호를 가져 왔음.
	
	// 새게시글 번호를 맵에 저장.
	articleMap.put("articleNO", articleNO);
	// 파일 데이터를 디비에 저장 하는 로직. 
	// 이미지 테이블에, 해당 게시글 번호로, 여러 이미지를 저장 할 예정. 
	boardDAO.insertNewImage(articleMap);
	
}

// 이미지만 업로드, 기존 게시글에서 추가하는 부분.
@Override
public void addOnlyImage2(Map articleMap, int articleNO) throws Exception{
	articleMap.put("articleNO", articleNO);
	boardDAO.insertNewImage(articleMap);
	
}
	
	//다중 이미지 상세보기
	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
   
	
	/*
	 //단일 이미지, 상세페이지 보기. 
	@Override
	public ArticleVO viewArticle(int articleNO) throws Exception {
		// 동네 2번 -> 동네 3번으로 articleNO=4 예)
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		return articleVO;
	}
	*/
	
	
	// 단일 이미지 , 글 수정시 로직. 
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}
	
	// 다중 이미지 , 글 수정시 로직. 일반 데이터만 업데이트  
		@Override
		public void modArticle2(Map articleMap) throws Exception {
			boardDAO.updateArticle2(articleMap);
		}
	
	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}
	
	// 이미지만 디비에서 삭제. 
	@Override
	public void removeImage(int imageFileNO) throws Exception {
		
		boardDAO.deleteImage(imageFileNO);
	}

	
}
