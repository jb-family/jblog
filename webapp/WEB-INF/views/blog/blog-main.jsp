<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					
					<c:choose>
						<c:when test='${bVo.logoFile == null || bVo.logoFile == "파일경로" }'>
							<!-- 기본이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
						</c:when>
						<c:otherwise>
							<!-- 설정이미지 -->
							<img id="proImg" src="${pageContext.request.contextPath}/uploadFile/${bVo.logoFile}">
						</c:otherwise>
					</c:choose>
					<div id="nick">${uVo.id}님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cList}" var="cList">
							<li><a href="${pageContext.request.contextPath}/blog/blog-main/${bVo.id}?cateNo=${cList.cateNo}&postNo=0">${cList.cateName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				<c:choose>
					<c:when test="${post.postNo != null}">
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>${post.postTitle}</strong></div>
							<div id="postDate" class="text-left"><strong>${post.regDate}</strong></div>
							<div id="postNick">${uVo.id}님</div>
						</div>
				
						<div id="post">
							${post.postContent}
						</div>
					</c:when>
					<c:when test="${param.postNo == 0}">
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>${postList[0].postTitle}</strong></div>
							<div id="postDate" class="text-left"><strong>${postList[0].regDate}</strong></div>
							<div id="postNick">${uVo.id}님</div>
						</div>
				
						<div id="post">
							${postList[0].postContent}
						</div>
					</c:when>	
					<c:otherwise>		
						<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
						</div>
						<div id="post"> </div>
					</c:otherwise>
				</c:choose>
				
				<c:if test="${user != null}">
				<div id="comment" style="width:100%; background-color:pink; height:30%;">
					<div>
						<form action="${pageContext.request.contextPath}/comment/insert" method="get">
							<input type="text" name = "userName" style="width:100px; height:30px;" value="${user.userName}">
							<input type="text" name = "cmtContent" style="width:300px; height:30px;">
							<input type="hidden" name = "userNo" value="${user.userNo}">
							<input type="hidden" name = "postNo" value="${post.postNo}">
							<button type="submit" style="width:100px; height:50px;">댓글작성</button>
						</form>
						<div id="commentList" style="background-color:skyblue; width: 100%; border:1px solid black;">
							
						</div>
					</div>
				</div>
				</c:if>
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong>
				</div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						
						<c:forEach items="${postList}" var="postList">
							<tr>
								<td class="text-left"><a href="${pageContext.request.contextPath}/blog/blog-main/${bVo.id}?cateNo=${postList.cateNo}&postNo=${postList.postNo}">${postList.postTitle}</a></td>
								<td class="text-right">${postList.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
			
			
		</div>	
		<!-- //content -->
		<div class=></div>
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>
		
<script type="text/javascript">

		$(document).ready(function() {
		
			$.ajax({
			url : "${pageContext.request.contextPath}/comment/list",		
			type : "post",
			//contentType : "application/json",
			//data : categoryVo,

			dataType : "json",
			success : function(coMap){
				/*성공시 처리해야될 코드 작성*/
				console.log(coMap.commentsInfo);
				
				 for(var i = 0; i < coMap.coList.length; i++) {
					render(coMap.coList[i], coMap.commentsInfo);
					
				} 
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		})
		
		
		function render(commentsVo, UsersVo) {
			var str = '';
			str += '<table>';
			str += '	<tr>';
			str += '		<td style="width:100px; text-align:center">'+ UsersVo.userName +'</td>';
			str += '		<td style="width:800px; text-align:center">'+ commentsVo.cmtContent +'</td>';
			str += '		<td style="width:100px; text-align:right">'+ commentsVo.regDate +'</td>';
			str += '	</tr>';
			str += '</table>';
			
			
			$("#commentList").append(str);
			
		}
		
		
</script>







</html>