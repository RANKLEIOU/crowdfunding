// 执行分页，每次调用都会刷新页面
function generatePage() {

	// 1.获取分页数据
	var pageInfo = getPageInfo();

	// 2.填充角色信息表格
	fillRoleTable(pageInfo);
}

// 获取分页数据
function getPageInfo() {
	var ajaxResult = $.ajax({
		url: 'role/get/page',
		type: 'POST',
		data: {
			"pageNum": window.pageNum,
			"pageSize": window.pageSize,
			"keyword": window.keyword
		},
		async: false,
		dataType: 'json'
	})

	// 获取响应状态码
	var statusCode = ajaxResult.status

	// 判断响应状态是否正常，不正常则停止函数并提示错误信息
	if (statusCode != 200) {
		layer.msg("失败！状态响应码：" + statusCode + "错误信息：" + ajaxResult.statusText)
	}


	// 获取响应数据
	var resultEntity = ajaxResult.responseJSON

	// 获取响应结果
	var result = resultEntity.result

	// 判断响应结果是否成功
	if (result == "FAILED") {
		Layer.msg(resultEntity.message)
	}

	// 获取分页信息
	var pageInfo = resultEntity.data

	// 返回pageInfo
	return pageInfo

}

// 填充角色表格信息
function fillRoleTable(pageInfo) {

	// 每次填充前先清空一下表格数据
	$("#rolePageBody").empty()

	// 当没有数据的时候不显示分页导航栏
	$("#Pagination").empty()

	// 判读pageInfo中是否有数据
	if (pageInfo == null || pageInfo == undefined || pageInfo.list.length == 0) {
		$("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到数据</td></tr>")
		return
	}

	// 遍历出pageInfo中的数据
	for (var i = 0; i < pageInfo.list.length; i++) {

		// 每一个role对象
		var role = pageInfo.list[i]

		// role对象的id
		var roleId = role.id
		// role对象的name
		var roleName = role.roleName

		// i+1作为数据排序
		var index = "<td>" + (i + 1) + "</td>"

		// role对象的name
		var name = "<td>" + roleName + "</td>"

		// checkbox
		var checkbox = "<td><input type='checkbox' value= " + roleId + " ></td>"

		var checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class= 'glyphicon glyphicon-check'></i></button>"
		var editBtn = "<button type='button' class='btn btn-primary btn-xs'><i class= 'glyphicon glyphicon-pencil'></i></button>"
		var delBtn = "<button type='button' class='btn btn-danger btn-xs'><i class= 'glyphicon glyphicon-remove'></i></button>"

		var btn = "<td>" + checkBtn + " " + editBtn + " " + delBtn

		var tr = "<tr>" + index + checkbox + name + btn + "</tr>"

		$("#rolePageBody").append(tr)
	}

	// 调用分页导航栏函数
	generateNavigate(pageInfo)
}

// 创建分页导航栏
function generateNavigate(pageInfo) {

	// 获取总页数
	var total = pageInfo.total

	// 配置分页属性
	var properties = {
		num_edge_entries: 3,               // 边缘页数
		num_display_entries: 5,            // 主体页数
		items_per_page: pageInfo.pageSize,  // 每页显示的数据量
		current_page: pageInfo.pageNum - 1,   // 当前页
		prev_text: '上一页',
		next_text: '下一页',
		callback: paginationCallBack,
	}
	$("#Pagination").pagination(total, properties)
}

// 分页的回调函数,翻页时调用
function paginationCallBack(pageIndex, jQuery) {

	// 修改全局的当前页码
	window.pageNum = pageIndex + 1

	// 重新执行分页
	generatePage()

	// 取消超链接的默认行为
	return false

}

$(function () {
	// 搜索按钮单击事件
	$("#roleSearchBtn").click(function () {

		// 将搜索框中的值传递给全局变量
		window.keyword = $("#roleSearchInput").val()

		// 调用分页方法刷新页面
		generatePage()
	});

	// 显示添加角色模态框
	$("#addModalBtn").click(function () {
		$("#addModal").modal("show")
	});

	// 添加角色信息
	$("#saveRoleBtn").click(function () {
		// 获取用户在文本框中输入的角色名称
		// #addModal 表示找到整个模态框
		// 空格表示在后代元素中继续查找
		// [name=roleName]表示匹配 name 属性等于 roleName 的元素
		var roleName = $("#addModal [name=roleName]").val();
		console.log($("#addModal [name=roleName]"))
		console.log($("#addModal [name=roleName]").val())
		console.log(roleName)

		// 发送Ajax请求
		$.ajax({
			url: 'role/save',
			type: 'POST',
			data: {
				roleName: roleName
			},
			dataType: 'json',
			success: function (res) {

				// 获取响应结果
				var result = res.result

				if (result == 'SUCCESS') {

					layer.msg("添加成功")

					// 将页码跳转到最后一页
					window.pageNum = 999999

					// 分页刷新页面
					generatePage()

				} else if (result == 'FAILED') {
					// 如果响应失败则提示错误信息
					layer.msg("添加失败! " + res.message)
				}
			},
			error: function (res) {
				// 请求失败提示失败的信息
				layer.msg(res.status + " " + res.statusText)
			}
		})

		// 隐藏模态框
		$("#addModal").modal('hide')

		// 清除模态框的信息
		$("#addModal[name=roleName]").val()
	});
})

