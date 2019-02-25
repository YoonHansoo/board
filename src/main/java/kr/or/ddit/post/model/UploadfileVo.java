package kr.or.ddit.post.model;

public class UploadfileVo {
	private String uploadFileNo;
	private String postNo;
	private String  uploadFileName;
	private String  uploadRealFilePath;
	public String getUploadFileNo() {
		return uploadFileNo;
	}
	public void setUploadFileNo(String uploadFileNo) {
		this.uploadFileNo = uploadFileNo;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadRealFilePath() {
		return uploadRealFilePath;
	}
	public void setUploadRealFilePath(String uploadRealFilePath) {
		this.uploadRealFilePath = uploadRealFilePath;
	}
	@Override
	public String toString() {
		return "UploadfileVo [uploadFileNo=" + uploadFileNo + ", postNo=" + postNo + ", uploadFileName="
				+ uploadFileName + ", uploadRealFilePath=" + uploadRealFilePath + "]";
	}
	
	
	
	
}
