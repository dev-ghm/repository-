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
	<c:choose>
	 <c:when test="${ p ==  null}">
		<script>
			window.alert("존재하지 않는 글입니다.");
			history.go(-1);
		</script>
		</c:when>
	<c:otherwise>
	<div style="width: 840px; margin: 0 auto;">
		<h2>상세보기</h2>
		<div>
			<h3 style="text-align: center; margin-bottom:8px; 
				border-bottom: 1px solid #ccc; padding : 8px;">${ found.title}</h3>		
			<div style="padding : 4px; text-align: right; ">
				<span style="color : #777">작성일</span> <span>${found.writedAt}</span> |
				<span style="color : #777">작성자</span>  <span>${found.title}</span> |
				<span style="color : #777">조회</span> <span>${found.readCnt}</span>
			</div>
			<p>
					
			</p>
		</div>
		<div style="text-align: right;">
			<a href="${application.getContextPath}/feed/list.jsp" class="no-deco-link"><button>목록</button></a>
			
			<c:choose>
				<c:when test="${found.writerId != null &&	found.writerId eq sessionScope.authUser }">
			<a href="${application.getContextPath}/feed/delete-handle.jsp?no=${found.no}" class="no-deco-link">
				<button>삭제</button>
			</a>
			<a href="${application.getContextPath}/feed/update.jsp?no=${found.no}" class="no-deco-link">
				<button>수정</button>
			</a>
	</c:when>
	</c:choose>
		</div>
	</div>
	
			</c:otherwise>
			</c:choose>
	
</body>
</html>