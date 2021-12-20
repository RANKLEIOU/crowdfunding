<%--
  Created by IntelliJ IDEA.
  User: 半夏时光i
  Date: 2021/12/19
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="/WEB-INF/include/include-head.jsp" %>
<script type="application/javascript">
$(function () {
	$("#addToRight").click(function () {
		// 添加到右侧的分配栏中
		$("select:eq(0)>option:selected").appendTo($("select:eq(1)"))
	})
	$("#addToLeft").click(function () {
		// 添加到左侧的分配栏中
		$("select:eq(1)>option:selected").appendTo($("select:eq(0)"))
	})
	$("#submitBtn").click(function () {
		// 在提交表单的时候选中右侧分配栏的所有角色，将左侧的选中全部清除，否则会导致提交失败
		$("select:eq(0)>option").prop("selected","")
		$("select:eq(1)>option").prop("selected","selected")
	})
})
</script>
<body>
<%@include file="/WEB-INF/include/include-nav.jsp" %>
<div class="container-fluid">
	<div class="row">
		<%@ include file="/WEB-INF/include/include-sidebar.jsp" %>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
				<li><a href="admin/to/main/toMain">首页</a></li>
				<li><a href="admin/get/page">数据列表</a></li>
				<li class="active">分配角色</li>
			</ol>
			<div class="panel panel-default">
				<div class="panel-body">
					<form action="assign/to/role" role="form" class="form-inline">
						<input type="hidden" name="adminId" value="${param.adminId}"/>
						<input type="hidden" name="pageNum" value="${param.pageNum}"/>
						<input type="hidden" name="keyword" value="${param.keyword}"/>
						<div class="form-group">
							<label>未分配角色列表</label><br>
							<select class="form-control" multiple size="10" style="width:100px;overflow-y:auto;">
								<c:forEach items="${requestScope.unAssignedRoleList}" var="role">
									<option value="${role.id}">${role.roleName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<ul>
								<li id="addToRight" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
								<br>
								<li id="addToLeft" class="btn btn-default glyphicon glyphicon-chevron-left"
									style="margin-top:20px;"></li>
							</ul>
						</div>
						<div class="form-group" style="margin-left:40px;">
							<label>已分配角色列表</label><br>
							<select name="roleIdList" class="form-control" multiple size="10" style="width:100px;overflow-y:auto;">
								<c:forEach items="${requestScope.assignedRoleList}" var="role">
									<option value="${role.id}">${role.roleName}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" id="submitBtn" style="width: 200px;margin: 20px 0 0 60px;" class="btn btn-lg btn-success btn-block">保存</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

