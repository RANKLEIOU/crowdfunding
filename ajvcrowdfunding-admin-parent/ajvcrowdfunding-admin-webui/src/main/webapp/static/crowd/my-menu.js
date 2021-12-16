// 创建树形结构
function generateTree() {
	$.ajax({
		url: 'menu/get/page',
		type: 'POST',
		success: function (res) {

			var result = res.result

			if (result == 'SUCCESS') {
				// 用于存储对zTree树结构的设置
				var setting = {
					view: {
						addDiyDom: addDiyDom,
						addHoverDom: addHoverDom,
						removeHoverDom: removeHoverDom,
					},
					data: {
						key: {
							// 当url没有值的时候不进行跳转，值可以随意设置
							url: "nojump"
						}
					}
				};

				// 用来构建树结构的json数据
				var zNodes = res.data

				// 初始化树形结构
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);

			} else if (result == 'FAILED') {
				layer.msg(res.message)
			}
		}
	})
}

// 使用自定义节点图标
function addDiyDom(treeId, treeNode) {

	// treeId是整个树形结构附着的ul的id
	// console.log(treeId)
	// treeNode是树节点的全部数据
	// console.log(treeNode)

	// 根据id的生成规则来拼接
	var spanId = treeNode.tId + "_ico"

	//根据span标签的id来移除旧样式，添加新样式
	$("#" + spanId).removeClass().addClass(treeNode.icon)
}

// 鼠标悬停时显示操作按钮
function addHoverDom(treeId, treeNode) {
	// 为了精确定位到每一个span标签，给按钮组设置一个有规律的id
	var btnGroupId = treeNode.tId + "_btnGrp"

	// 判断以前是否添加了按钮组，添加了则直接返回
	if ($("#" + btnGroupId).length > 0) return;

	// 准备按钮组的HTML标签
	var addBtn = "<a id='" + treeNode.id + "' class='btn btn-info dropdown-toggle btn-xs addNodeBtn' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>"
	var removeBtn = "<a id='" + treeNode.id + "' class='btn btn-info dropdown-toggle btn-xs removeNodeBtn' style='margin-left:10px;padding-top:0px;' href='#' title=' 删 除 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>"
	var editBtn = "<a id='" + treeNode.id + "' class='btn btn-info dropdown-toggle btn-xs editNodeBtn' style='margin-left:10px;padding-top:0px;' href='#' title=' 修 改 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>"

	// 获取当前节点的级别
	var level = treeNode.level

	// 声明一个变量储存拼装的按钮组
	var btnHTML = ""

	// 节点级别为0是根节点，只能添加子节点
	if (level == 0) {
		btnHTML = addBtn
	}

	// 级别为1是父节点，可以添加子节点，修改父节点
	if (level == 1) {
		btnHTML = addBtn + " " + editBtn

		// 如果当前节点没有子节点，则可以删除
		if (treeNode.children.length == 0) {
			btnHTML = btnHTML + " " + removeBtn
		}
	}

	// 级别为2是叶子节点，可以修改和删除节点
	if (level == 2) {
		btnHTML = editBtn + " " + removeBtn
	}

	// 找到附着按钮的超链接
	var aId = treeNode.tId + "_a"

	// 执行在超链接后面附加span的操作
	$("#" + aId).after("<span id='" + btnGroupId + "'>" + btnHTML + "</span>")
};

// 鼠标离开节点范围删除操作按钮
function removeHoverDom(treeId, treeNode) {
	// 拼接按钮组的id
	var btnGroupId = treeNode.tId + "_btnGrp"
	// 移除对应元素
	$("#" + btnGroupId).remove();
};

$(function (){

	// 添加节点按钮事件
	$("#treeDemo").on('click','.addNodeBtn',function () {

		// 将当前节点的id作为新节点的pid
		window.pid = this.id

		// 打开模态框
		$("#menuAddModal").modal('show')

		// 这是超链接形式的按钮，所以要返回false阻止超链接默认提交行为
		return false
	})

	// 添加节点事件
	$("#menuSaveBtn").click(function (){

		// 输入的节点名称
		var name = $("#menuAddModal [name=name]").val()
		// 输入的url地址
		var url = $("#menuAddModal [name=url]").val()
		// 选中的节点图标
		var icon = $("#menuAddModal [name=icon]:checked").val()

		// 发送Ajax保存节点
		$.ajax({
			url:'menu/save',
			type: 'POST',
			data:{
				pid:window.pid,
				name:name,
				url:url,
				icon:icon
			},
			dataType:'json',
			success:function (res) {
				var result = res.result

				if (result == 'SUCCESS'){
					layer.msg("添加成功！")

					// 重新加载树形节点
					// 这里是异步Ajax，所以需要在请求完成后再刷新页面，否则没有新增的数据
					generateTree()
				}else if (result == "FAILED"){
					layer.msg("添加失败！"+res.message)
				}
			},
			error:function (res) {
				layer.msg(res.status+" "+res.statusText)
			}
		})

		// 关闭模态框
		$("#menuAddModal").modal('hide')

		// 清空模态框,直接调用重置按钮即可
		$("#menuResetBtn").click()
	})
})
