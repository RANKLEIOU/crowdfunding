package com.ajv.crowd.mvc.controller;

import com.ajv.crowd.entity.Menu;
import com.ajv.crowd.service.api.MenuService;
import com.ajv.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/menu/get/page")
	public ResultEntity<Menu> getAll() {

		List<Menu> menuList = menuService.getAll();

		// 声明一个变量装载根节点
		Menu root = null;
		// 声明一个map用来储存id和menu对象的关系
		Map<Integer, Object> menuMap = new HashMap<>();

		// 遍历集合绑定对象关系
		for (Menu menu : menuList) {
			// 获取当前遍历对象的id
			int id = menu.getId();

			// 将id和当前对象绑定
			menuMap.put(id, menu);
		}

		// 再次遍历集合找出根节点，组装树结构
		for (Menu menu : menuList) {

			Integer pid = menu.getPid();

			// 判断id是否为null，为null则是根节点
			if (pid == null) {
				root = menu;

				continue;
			}

			// 获取当前遍历对象的父节点
			Menu father = (Menu) menuMap.get(pid);

			// 将当前节点添加到父节点中
			father.getChildren().add(menu);

		}

		return ResultEntity.successWithData(root);
	}

	@RequestMapping("/menu/save")
	public ResultEntity<String> saveMenu(Menu menu){

		menuService.saveMenu(menu);

		return ResultEntity.success();
	}

	@RequestMapping("menu/edit")
	public ResultEntity<String> editMenu(Menu menu){
		menuService.editMenu(menu);
		return ResultEntity.success();
	}

	@RequestMapping("menu/remove")
	public ResultEntity<String> removeMenu(Integer id){
		menuService.removeMenu(id);
		return ResultEntity.success();
	}
}
