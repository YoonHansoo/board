package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

public interface IBoardService {

	
	/**
	 * Method : getAllBoard
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 :모든 게시판을 조회하는 쿼리 활성화 된 것만
	 * 
	 */
	public List<BoardVo> getAllBoard();
	
	
	public List<BoardVo> getAllBoardList();


	public int insertBoard(String boarNm);


	public int updBoard(BoardVo boardVo);
	
}
