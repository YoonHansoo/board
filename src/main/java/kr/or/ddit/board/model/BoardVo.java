package kr.or.ddit.board.model;

public class BoardVo {

	private String boardNo;
	private String boardNm;
	private String boardFlag;
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardNm() {
		return boardNm;
	}
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", boardNm=" + boardNm + ", boardFlag=" + boardFlag + "]";
	}
	public String getBoardFlag() {
		return boardFlag;
	}
	public void setBoardFlag(String boardFlag) {
		this.boardFlag = boardFlag;
	}
	
	
	
}	
