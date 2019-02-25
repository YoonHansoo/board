package kr.or.ddit.util;

public class PartUtil {

	public static String getFileNameFromPart(String contentDisposition) {
		return contentDisposition.substring(contentDisposition.indexOf("filename=")+10, contentDisposition.length()-1);
	}

	
}
