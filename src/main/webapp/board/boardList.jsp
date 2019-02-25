<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/dashboard.css"
	rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
.postTr:hover {
	background-color: black;
	color: white;
}

.table-striped>tbody>tr:nth-of-type(odd):hover {
	background-color: black;
	color: white;
}
.table-striped>tbody>tr:nth-of-type(even):hover {
	background-color: black;
	color: white;
}

#select {
color: black;
}
.updbtn{
color: black;
}

</style>
</head>


<!--  헤더  삽입 -->
<%@ include file="/module/header.jsp"%>
<!--  내부에서 처리하기 떄문에 getContextPath를 사용할 필요가 없음. -->

<div class="container-fluid">
	<div class="row">
		<!--사이드 삽입 -->
		<%@ include file="/module/left.jsp"%>

		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">게시판 관리</h1>
			<div class="form-group">


				<div class="form-group">
					<label for="post_Reg_Dt" class="col-sm-1 control-label">${sessionScope.userVo.userId}</label>
					<form action="${pageContext.request.contextPath}/boardList" method="post">
					<div class="col-sm-7" style="display: inline;">
						
						<input type="text" name="boardNm"
						placeholder="게시판 이름" />
					</div>
					<button type="submit" id="CmtCreateBtn" name="CmtCreateBtn"
						class="btn btn-default">게시판 생성</button>
						</form>
				</div>
				
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>게시판 번호</th>
								<th>게시판 이름</th>
								<th>활성화</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="0" end="${boardAllList.size()-1}" step="1" var="i">
							
								<tr class='userTr' data-boardno="${boardAllList.get(i).boardNo}">
									<td>${boardAllList.get(i).boardNo}</td>
									<td>
									${boardAllList.get(i).boardNm}
									</td>
									<td>
									<form action="${pageContext.request.contextPath}/updboard">
									<select id="select" name ="boardFlag">
									<c:choose>
									<c:when test="${boardAllList.get(i).boardFlag eq 'F'}">
											<option value="T" >활성화</option>
											<option value="F" selected>비활성화</option>
									</c:when>
									<c:otherwise>									
										<option value="T" >활성화</option>
											<option value="F">비활성화</option>
											</c:otherwise>
											</c:choose>
										</select>
										<input type="hidden" name="boardNo" value="${boardAllList.get(i).boardNo}"> 
										<input type="submit" class="updbtn" value="수정"> 
									</form>
									</td>
									
								</tr>
								
							</c:forEach>
						</tbody>
					</table>
				
				</div>
				
				




			</div>


		</div>
	</div>
</div>

</body>
</html>