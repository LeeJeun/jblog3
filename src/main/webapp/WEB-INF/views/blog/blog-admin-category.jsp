<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function(){
	$("#btn-add-cat").click(function(){
		var name = $("#name").val();
		var explain = $("#explain").val();
		if(name == "" || explain == ""){
			return;
		}
		
		var category = {
		        "name" : name,
		        "explain" : explain
		}
	
		// ajax 통신
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/add",
			type: "post",
			dataType: "json",
			data: JSON.stringify(category),
			contentType : "application/json; charset=UTF-8",
			success: function(response){
				if(response.result == "fail"){
					console.error(response.message);
					return;
				}
				
				if(response.data == true){
					$("#name").val("");
					$("#explain").val("");
					$("#explain").focus();
					return;
				}
				
			},
			error: function(xhr, error){
				console.error("error:" + error);
			}
		});
	});
});


function createTable(){
    
    var param = {};
    param.BoardVo.bdId = "01111";
    param = JSON.stringify(param);

    $.ajax({
        url : "/getBoardList.do",
        data : param,
        type : 'post',
        success : function(data){
            var results = data.boardList;
            var str = '<TR>';
            $.each(results , function(i){
                str += '<TD>' + results[i].bdTitl + '</TD><TD>' + results[i].bdWriter + '</TD><TD>' + results[i].bdRgDt + '</TD>';
                str += '</TR>';
           });
           $("#boardList").append(str); 
        },
        error : function(){
            alert("error");
        }
    });
}

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/adminHeader.jsp" />
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>

					<c:set var="count" value='${fn:length(list) }' ></c:set>
					
					<c:forEach items="${list }" var="vo" varStatus="status">
							<tr>
								<td>[${count - status.index }]</td>
								<td>${vo.name }</td>
								<td>join해야돼</td>
								<td>${vo.explain }</td>
								<td><a href="${pageContext.servletContext.contextPath }/${authUser.id }/admin/category/delete/${vo.no}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
							</tr>
					</c:forEach>					  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<%-- <form action="${pageContext.servletContext.contextPath }/${authUser.id }/admin/category/add" method="post"> --%>
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name" id="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="explain" id="explain"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input id="btn-add-cat" type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
		     <%--  	</form> --%>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>