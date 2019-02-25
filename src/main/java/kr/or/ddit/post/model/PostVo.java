package kr.or.ddit.post.model;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PostVo {
	private String postNo;
	private String postContent;
	private Date post_Reg_Dt;
	private String post_Gn;
	private String post_Del_Flag;
	private String userId;
	private String boardNo;
	private String parent_Postno;
	private String postTitle;
	private int level;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getFmtDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd :hh:mm");
		return sdf.format(post_Reg_Dt);
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Date getPost_Reg_Dt() {
		return post_Reg_Dt;
	}
	public void setPost_Reg_Dt(Date post_Reg_Dt) {
		this.post_Reg_Dt = post_Reg_Dt;
	}
	public String getPost_Gn() {
		return post_Gn;
	}
	public void setPost_Gn(String post_Gn) {
		this.post_Gn = post_Gn;
	}
	public String getPost_Del_Flag() {
		return post_Del_Flag;
	}
	public void setPost_Del_Flag(String post_Del_Flag) {
		this.post_Del_Flag = post_Del_Flag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getParent_Postno() {
		return parent_Postno;
	}
	public void setParent_Postno(String parent_Postno) {
		this.parent_Postno = parent_Postno;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	@Override
	public String toString() {
		return "PostVo [postNo=" + postNo + ", postContent=" + postContent + ", post_Reg_Dt=" + post_Reg_Dt
				+ ", post_Gn=" + post_Gn + ", post_Del_Flag=" + post_Del_Flag + ", userId=" + userId + ", boardNo="
				+ boardNo + ", parent_Postno=" + parent_Postno + ", postTitle=" + postTitle + ", level=" + level + "]";
	}
	
	
}
