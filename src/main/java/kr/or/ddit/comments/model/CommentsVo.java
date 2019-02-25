package kr.or.ddit.comments.model;

public class CommentsVo {
	private String commentNo;
	private String comment_Reg_Dt;
	private String commentContent;
	private String postNo;
	private String comment_Del_Flag;
	private String userId;
	public String getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}
	public String getComment_Reg_Dt() {
		return comment_Reg_Dt;
	}
	public void setComment_Reg_Dt(String comment_Reg_Dt) {
		this.comment_Reg_Dt = comment_Reg_Dt;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getComment_Del_Flag() {
		return comment_Del_Flag;
	}
	public void setComment_Del_Flag(String comment_Del_Flag) {
		this.comment_Del_Flag = comment_Del_Flag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "CommentsVo [commentNo=" + commentNo + ", comment_Reg_Dt=" + comment_Reg_Dt + ", commentContent="
				+ commentContent + ", postNo=" + postNo + ", comment_Del_Flag=" + comment_Del_Flag + ", userId="
				+ userId + "]";
	}
	
	
} 