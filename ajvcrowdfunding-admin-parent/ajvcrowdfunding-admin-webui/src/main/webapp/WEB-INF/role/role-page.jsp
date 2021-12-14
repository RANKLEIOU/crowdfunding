<%--
  Created by IntelliJ IDEA.
  User: 半夏时光i
  Date: 2021/12/13
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@include file="../include/include-head.jsp" %>
<link rel="stylesheet" href="static/css/pagination.css">
<script type="application/javascript" charset="UTF-8" src="static/jquery/jquery.pagination.js"></script>
<script type="application/javascript" charset="UTF-8" src="static/crowd/my-role.js"></script>
<script type="application/javascript">
	$(function () {
		// 设置全局分页数值
		window.pageNum = "1";
		window.pageSize = "5";
		window.keyword = "";

		// 调用my-role.js中的分页方法
		generatePage();
	});


</script>
<body>
<%@include file="../include/include-nav.jsp" %>
<div class="container-fluid">
	<div class="row">
		<%@ include file="/WEB-INF/include/include-sidebar.jsp" %>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
				</div>
				<div class="panel-body">
					<form class="form-inline" role="form" style="float:left;">
						<div class="form-group has-feedback">
							<div class="input-group">
								<div class="input-group-addon">查询条件</div>
								<input id="roleSearchInput" class="form-control has-success" type="text"
									   placeholder="请输入查询条件">
							</div>
						</div>
						<button type="button" id="roleSearchBtn" class="btn btn-warning"><i
								class="glyphicon glyphicon-search"></i> 查询
						</button>
					</form>
					<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
							class=" glyphicon glyphicon-remove"></i> 删除
					</button>
					<button type="button" id="addModalBtn" class="btn btn-primary" style="float:right;"><i
							class="glyphicon glyphicon-plus"></i> 新增
					</button>
					<br>
					<hr style="clear:both;">
					<div class="table-responsive">
						<table class="table  table-bordered">
							<thead>
							<tr>
								<th width="30">#</th>
								<th width="30"><input type="checkbox"></th>
								<th>名称</th>
								<th width="100">操作</th>
							</tr>
							</thead>
							<tbody id="rolePageBody">
							</tbody>
							<tfoot>
							<tr>
								<td colspan="6" align="center">
									<div id="Pagination" class="pagination"></div>
								</td>
							</tr>

							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@include file="/WEB-INF/modal/modal-role-add.jsp" %>
<%@include file="/WEB-INF/modal/modal-role-edit.jsp"%>
</body>
</html>
