<%--
  Created by IntelliJ IDEA.
  User: 半夏时光i
  Date: 2021/11/18
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="static/css/pagination.css">
<script src="static/jquery/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function () {
		//调用分页函数，生成分页导航栏
		initPagination()
	})

	function initPagination() {
		//获取总页数
		var total =
		${requestScope.pageInfo.total}
		//配置分页属性
		var properties = {
			num_edge_entries: 3,                               //边缘页数
			num_display_entries: 5,                            //主体页数
			items_per_page:${requestScope.pageInfo.pageSize},  //每页显示的数据量
			current_page:${requestScope.pageInfo.pageNum-1},   //当前页
			prev_text: "上一页",
			next_text: "下一页",
			callback: pageSelectCallback,
		}
		$("#Pagination").pagination(total, properties)
	}

	/**
	 * 用户点击导航栏后进行页面跳转
	 * @param pageIndex 由pagination传入，从0开始的页码
	 * @param jQuery jQuery对象
	 */
	function pageSelectCallback(pageIndex, jQuery) {
		console.log("这里进来了")
		//通过计算得到当前页码
		var pageNum = pageIndex + 1;
		//跳转页面
		window.location.href = "admin/get/page?pageNum=" + pageNum + "&keyword=${param.keyword}";
		console.info("这里以及提交了表单")
		//超链接默认会跳转页面，返回false取消默认行为
		return false;
	}
</script>
<body>
<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
	<div class="row">
		<%@include file="/WEB-INF/include-sidebar.jsp" %>

		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<i class="glyphicon glyphicon-th"></i> 数据列表
					</h3>
				</div>
				<div class="panel-body">
					<form action="admin/get/page" method="post" class="form-inline" role="form" style="float: left;">
						<div class="form-group has-feedback">
							<div class="input-group">
								<div class="input-group-addon">查询条件</div>
								<input name="keyword" class="form-control has-success" type="text"
									   placeholder="请输入查询条件">
							</div>
						</div>
						<button type="submit" class="btn btn-warning">
							<i class="glyphicon glyphicon-search"></i> 查询
						</button>
					</form>
					<button type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
						<i class=" glyphicon glyphicon-remove"></i> 删除
					</button>
					<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='add.html'">
						<i class="glyphicon glyphicon-plus"></i> 新增
					</button>
					<br>
					<hr style="clear: both;">
					<div class="table-responsive">
						<table class="table  table-bordered">
							<thead>
							<tr>
								<th width="30">#</th>
								<th width="30"><input type="checkbox"></th>
								<th>账号</th>
								<th>名称</th>
								<th>邮箱地址</th>
								<th width="100">操作</th>
							</tr>
							</thead>
							<tbody>
							<c:if test="${empty requestScope.pageInfo.list }">
								<tr>
									<td colspan="6" align="center">抱歉！没有查询到您要的数据！</td>
								</tr>
							</c:if>
							<c:if test="${!empty requestScope.pageInfo.list }">
								<c:forEach items="${requestScope.pageInfo.list }" var="admin" varStatus="myStatus">
									<tr>
										<td>${myStatus.count }</td>
										<td><input type="checkbox"></td>
										<td>${admin.loginAcct }</td>
										<td>${admin.userName }</td>
										<td>${admin.email }</td>
										<td>
											<button type="button" class="btn btn-success btn-xs">
												<i class=" glyphicon glyphicon-check"></i>
											</button>
											<button type="button" class="btn btn-primary btn-xs">
												<i class=" glyphicon glyphicon-pencil"></i>
											</button>
											<button type="button" class="btn btn-danger btn-xs">
												<i class=" glyphicon glyphicon-remove"></i>
											</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>
							<tfoot>
							<tr>
								<td colspan="6" align="center">
									<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
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
</body>
</html>
