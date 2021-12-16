<%--
  Created by IntelliJ IDEA.
  User: 半夏时光i
  Date: 2021/12/15
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@include file="/WEB-INF/include/include-head.jsp" %>
<link rel="stylesheet" href="static/ztree/zTreeStyle.css"/>
<script type="application/javascript" charset="UTF-8" src="static/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="application/javascript" charset="UTF-8" src="static/crowd/my-menu.js"></script>
<script type="application/javascript" charset="UTF-8">
	$(function (){
		// 页面加载完之后调用函数生成树形节点
		generateTree()
	})
</script>
<body>
<%@include file="/WEB-INF/include/include-nav.jsp" %>
<div class="container-fluid">
	<div class="row">
		<%@ include file="/WEB-INF/include/include-sidebar.jsp" %>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
					<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal">
						<i class="glyphicon glyphicon-question-sign"></i>
					</div>
				</div>
				<div class="panel-body">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
