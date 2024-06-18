<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="my-3"><c:choose>
				<c:when test="${sessionScope.authUser == null }">
					로그인하세요.
				</c:when>
				<c:otherwise>
					로그인중입니다. 
					${sessionScope.authUser.name } (${sessionScope.authUser.id })				
				</c:otherwise>
			</c:choose>
		
		</div>