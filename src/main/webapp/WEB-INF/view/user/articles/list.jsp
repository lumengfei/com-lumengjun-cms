<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/resource/css/css.css">
<script type="text/javascript" src="/resource/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
</head>
<body>
 	<table class="table">
         <thead>
          <tr>
            <th>id</th>
            <th>标题</th>
            <th>栏目</th>
            <th>分类</th>
            <th>发布时间</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach items="${articlePage.list}" var="article">
        		<tr>
        			<td>${article.id}</td>
        			<td>${article.title}</td>
        			<td>${article.channel.name}</td>
        			<td>${article.category.name}</td>
        			<td><fmt:formatDate value="${article.created}" pattern="yyyy年MM月dd日"/></td>
        			<td>
        				<c:choose>
        					<c:when test="${article.status==0}"> 待审核</c:when>
        					<c:when test="${article.status==1}"> 审核通过</c:when>
        					<c:when test="${article.status==2}"> 审核被拒</c:when>
        					<c:otherwise>
        						未知
        					</c:otherwise>
        				</c:choose>
        			</td>
        			<td width="200px">
        				<input type="button" value="删除"  class="btn btn-danger" onclick="del(${article.id})">
        				<input type="button" value="修改"  class="btn btn-warning" onclick="update(${article.id})" >
        			</td>
        		</tr>
        	</c:forEach>
        </tbody>
      </table>
</body>
</html>