package com.myspring.pro30.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;


@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllArticlesList() throws DataAccessException {
		List<ArticleVO> articlesList = articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}

	// 단일 이미지 답글쓰기
		@Override
		public int insertReplyNewArticle(Map articleMap) throws DataAccessException {
			// 현재, 게시글의 갯수를 이야기를 하고 있음. 
			// 시퀀스, 게시글 번호를 자동으로 생성하거나, 지금처럼 현재 게시글의 갯수의해서, 
			// 번호를 부여. 
			// 새 게시글의 번호를 생성
			int articleNO = selectNewArticleNO();
			// 새 게시글의 번호를, 게시글 작성하는 요소로 추가. 
			articleMap.put("articleNO", articleNO);
			// articleMap : 게시글의 요소를 다 담아서. 실제 디비에 반영하는 로직. 
			sqlSession.insert("mapper.board.insertReplyNewArticle",articleMap);
			return articleNO;
		}
	
	// 단일 이미지 글쓰기
	// 다중 이미지 글쓰기 , 일반 데이터를 디비에 저장.
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		// 현재, 게시글의 갯수를 이야기를 하고 있음. 
		// 시퀀스, 게시글 번호를 자동으로 생성하거나, 지금처럼 현재 게시글의 갯수의해서, 
		// 번호를 부여. 
		// 새 게시글의 번호를 생성
		int articleNO = selectNewArticleNO();
		// 새 게시글의 번호를, 게시글 작성하는 요소로 추가. 
		articleMap.put("articleNO", articleNO);
		// 일반 데이터 글쓰기, 제목, 내용 , 등록일(기본값)
		// articleMap : 게시글의 요소를 다 담아서. 실제 디비에 반영하는 로직. 
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		return articleNO;
	}
    
	//다중이미지 글쓰기
	// 파일 이미지만 따로 저장하는 로직. 
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		// articleMap 에서 담겨진, 파일 이미지들의 목록을 꺼내기. 
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");
		// 작성된 새 게시글 번호도 가져오고,
		int articleNO = (Integer)articleMap.get("articleNO");
		// 준비물 2개 , 1) 파일 이미지 이름들 2) 해당 게시글 번호 
		// 이미지 파일 테이블에도, 새롭게 추가될 식별 번호, 원래는 시퀀스라든지, 자동 번호를 부여는 하는데.ㅣ
		// 백에서 임의로 , 디비에서 번호를 가져와서 사용중. 
		int imageFileNO = selectNewImageFileNO();
		// 디비에서 조회한 결과 값을 : 4를 가지고 오고
		
		for(ImageVO imageVO : imageFileList){
			// 가져온 번호 4번에, 번호를 먼저 증가시키기: 5
			imageVO.setImageFileNO(++imageFileNO);
			// 각 이미지에 식별 번호를 설정. 
			imageVO.setArticleNO(articleNO);
		}
		sqlSession.insert("mapper.board.insertNewImage",imageFileList);
	}
	
   
	
	// 게시글 번호, 게시글 정보 하나 가져오기.
	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}

	// 단일 이미지, 수정된 내용 적용하기. 
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
	}
	
	// 다중 이미지, 수정된 내용 적용하기. 일반 데이터 만 변경. 
	
		@Override
		public void updateArticle2(Map articleMap) throws DataAccessException {
			sqlSession.update("mapper.board.updateArticle2", articleMap);
		}


	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
		
	}
	
	// 이미지만 디비에서 삭제.
	@Override
	public void deleteImage(int imageFileNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteImage", imageFileNO);
		
	}
	
	@Override
	public List selectImageFileList(int articleNO) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList",articleNO);
		return imageFileList;
	}
	
	// 게시글의 갯수를 파악하는 디비
	// 다음 게시글의 번호.
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	
	// 새로운 이미지 테이블의 번호. 
	// 예) 현재, 이미지 테이블에는 이미지가 4개 있고, 
	// 다음 이미지의 식별 번호 , 5개 되는 데, 현재 이미지 테이블의 개수를 조회해서, +1 
	// 결과, 반환, 4를 반환
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}

}
