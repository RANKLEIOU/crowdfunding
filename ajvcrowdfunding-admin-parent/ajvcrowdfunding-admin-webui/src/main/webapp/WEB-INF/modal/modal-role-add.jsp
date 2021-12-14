<%--
  Created by IntelliJ IDEA.
  User: 半夏时光i
  Date: 2021/12/14
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="addModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">尚筹网系统弹窗</h4>
			</div>
			<div class="modal-body">
				<form class="form-signin" role="form">
					<div class="form-group has-success has-feedback">
						<input type="text" name="roleName" class="form-control" id="roleName" placeholder="请输入角色名称" autofocus>
						<span class="glyphicon glyphicon-user form-control-feedback"></span>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="saveRoleBtn" class="btn btn-primary">添加</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
