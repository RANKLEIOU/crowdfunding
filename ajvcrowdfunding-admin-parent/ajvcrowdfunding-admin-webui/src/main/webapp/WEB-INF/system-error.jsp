<%--
  Created by IntelliJ IDEA.
  User: 半夏时光i
  Date: 2021/11/17
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
	<h1>异常页面</h1>
	<%--显示异常信息--%>
	${requestScope.exception.message}
</body>
</html>
