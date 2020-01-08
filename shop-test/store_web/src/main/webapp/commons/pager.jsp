<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/9
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${page.pageNum==1}">
    首页 上一页
</c:if>
<c:if test="${page.pageNum>1}">
    <a href="javascript:;" onclick="queryPageList(1)">首页</a>
    <a href="javascript:;" onclick="queryPageList(${page.pageNum-1})">上一页</a>
</c:if>
共${page.totalCount}条数据    当前第${page.pageNum}页 共${page.pageCount}页
<c:if test="${page.pageNum==page.pageCount}">
    尾页 下一页
</c:if>
<c:if test="${page.pageNum<page.pageCount}">
    <a href="javascript:;" onclick="queryPageList(${page.pageCount})">尾页</a>
    <a href="javascript:;" onclick="queryPageList(${page.pageNum+1})">下一页</a>
</c:if>
<select id="pageSize" onchange="change()">
    <option ${page.pageSize==2?"selected":""} value="2">2</option>
    <option ${page.pageSize==5?"selected":""} value="5">5</option>
    <option ${page.pageSize==10?"selected":""} value="10">10</option>
</select>
<script type="text/javascript">
    function change(){
        pageSize = $("#pageSize").val();
        queryPageList(1);
    }

</script>



</body>
</html>
