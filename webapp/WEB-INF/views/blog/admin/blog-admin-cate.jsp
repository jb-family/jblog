<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/blog/${user.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/blog/${user.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/blog/${user.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      		
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="cateName" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="description" name="desc" value=""></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">
	
	
	//리스트 준비
	$(document).ready(function(){
		$.ajax({
			
			url : "${pageContext.request.contextPath}/category/list",		
			type : "post",
			//contentType : "application/json",
			//data : categoryVo,

			dataType : "json",
			success : function(cList){
				/*성공시 처리해야될 코드 작성*/
				console.log(cList);
				
				for(var i = 0; i < cList.length; i++) {
					
					render(cList[i], "down");	
				}	
					
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	$("#cateList").on("click", ".btnCateDel" ,function() {
		console.log("버튼체크");
		var $this = $(this);
		console.log($this);
		
		var no = $this.date("no");
		
	})
	
	//리스트 그리기
	function render(categoryVo, opt) {
		
		var str = '';
		str += '<tr id="t' + categoryVo.id + '">';
		str += '	<td>' + categoryVo.cateNo + '</td>';
		str += '	<td>' + categoryVo.cateName + '</td>';
		str += '	<td> 아직 안했다 </td>';
		str += '	<td>' + categoryVo.description + '</td>';
		str += '	<td class="text-center">';
		str += '	<img class="btnCateDel" date-no="' + categoryVo.id + '" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	</td>';
		str += '</tr>';
	
		
		if(opt === "down") {
			$("#cateList").append(str);
		}else if(opt === "up") {
			$("#cateList").prepend(str);
		}
		
	}
	
	
	//카테고리 추가 이벤트 
	$("#btnAddCate").on("click", function() {
		var cateName = $("#cateName").val();
		var description = $("#description").val();
		console.log(cateName);
		console.log(description);
		
		var categoryVo = {
				cateName,
				description
		}
		
$.ajax({
			
			url : "${pageContext.request.contextPath}/category/insert",		
			type : "post",
			//contentType : "application/json",
			data : categoryVo,

			dataType : "json",
			success : function(cVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(cVo);
				
				render(cVo, "up");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	})
</script>


</html>