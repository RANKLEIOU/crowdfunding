<%--
  Created by IntelliJ IDEA.
  User: 半夏时光i
  Date: 2021/11/15
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
	<script src="jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			$("#btn").click(function (){
				$.ajax({
					url:'index',
					type:'GET',
					success:function (data){
						console.log(data)
					},
					error:function (data){
						console.log(data)
					}
				})
			})
			$("#btn1").click(function (){
				layer.msg("这里是layer弹框")
			})
		})
	</script>
</head>
<body>
	<a href="index">SSM整合测试</a>
	<button id="btn">Ajax请求</button>
	<button id="btn1">layer弹框</button>
</body>
</html>
