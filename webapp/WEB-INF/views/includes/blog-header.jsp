<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<c:if test ="${user != null}">
				<h1><a href="">${bVo.id}의 블로그입니다.</a></h1>
			</c:if>
			<ul class="clearfix">
			
			<c:choose>
				<c:when test="${user == null}"> 
					<!-- 로그인 전 메뉴 -->
					<li><a class="btn_s" href="${pageContext.request.contextPath}/user/loginForm">로그인</a></li>
				</c:when>
				<c:otherwise>
					<!-- 로그인 후 메뉴 -->
					<!-- 자신의 블로그일때만 관리 메뉴가 보인다. -->
					<c:if test ="${user.id == bVo.id}">
						<li><a class="btn_s" href="${pageContext.request.contextPath}/blog/${user.id}/admin/basic">내블로그 관리</a></li>
					</c:if>
					<li><a class="btn_s" href="${pageContext.request.contextPath}/blog/logout">로그아웃</a></li>
				</c:otherwise>
 			</c:choose>
		 		
			</ul>
		</div>
		<!-- //header -->
		