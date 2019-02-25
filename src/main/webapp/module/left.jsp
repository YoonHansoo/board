<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
           <li><a href="${pageContext.request.contextPath}/boardList">게시판관리</a></li> 
          <c:forEach items="${boardList}" var="board">
           <li><a href="<%=request.getContextPath()%>/postList?boardNo=${board.boardNo}&boardNm=${board.boardNm}">${board.boardNm}</a></li> 
           </c:forEach>
          </ul>
        </div>