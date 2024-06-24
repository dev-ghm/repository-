<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${empty title }">
		<title>핏투게더</title>
	</c:when>
	<c:otherwise>
		<title>${title }::핏투게더</title>
	</c:otherwise>
</c:choose>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/style.css?<%=System.currentTimeMillis() %>" />
</head>
<body>
	<%@ include file="/WEB-INF/view/common/navbar.jsp"%>
	<div class="container px-1">
		<div>
			<h1 class="text-center">자유게시판</h1>
		</div>
		<table class= "default-table">
		<thead>
			<tr class="border-bottom">
				<th style="width: 10%">번호</th>
				<th>제목</th>
				<th style="width: 10%">글쓴이</th>
				<th style="width: 15%">등록일</th>
				<th style="width: 10%">조회</th>
			</tr>	
		</thead>
		<tbody>
			<c:forEach items="${feeds }" var="one">
		<tr style="border-bottom: 1px solid #ddd; height: 30px;">
					<td class="text-center">${one.no }</td>
					<td >
						<a href="${pageContext.servletContext.contextPath }/feeds/view?p=${one.no}"
							class="no-deco-link"> 
							${one.title}
						</a>
					</td>
					<td class="text-center">${one.title}</td>
					<td class="text-center">${one.writedAt}</td>
					<td class="text-center">${one.readCnt}</td>
				</tr>
			</c:forEach>
		</tbody>
		
		</table>
		<div style="text-align: center; margin-top: 30px">
			<c:forEach begin="1" end="${totalPages }" var="i">
				<c:choose>
					<c:when test="${i == currentPage }">
						<b style="color: red">${i }</b>
							</c:when>
				<c:otherwise>
					<a href="${pageContext.servletContext.contextPath }/sports?p=${i}">${i }</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		
		<div style="margin-top:10px; text-align: right;">
			<a href="${pageContext.servletContext.contextPath }/feeds/write">
			<button>의견쓰기</button>
			</a>
		
		</div>
	</div>
</body>
</html>