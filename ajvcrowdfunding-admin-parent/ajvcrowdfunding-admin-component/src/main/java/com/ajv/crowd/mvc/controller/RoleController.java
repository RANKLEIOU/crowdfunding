package com.ajv.crowd.mvc.controller;

import com.ajv.crowd.constant.CrowdConstant;
import com.ajv.crowd.entity.Role;
import com.ajv.crowd.service.api.RoleService;
import com.ajv.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/role/get/page")
	public ResultEntity<PageInfo> rolePageInfo(@RequestParam(value = "keyword", defaultValue = "") String keyword,
											   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
											   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
											   Model model) {

		PageInfo<Role> pageInfo = roleService.getRoleByKeyword(keyword, pageNum, pageSize);
		model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		return ResultEntity.successWithData(pageInfo);
	}

	@RequestMapping("/role/save")
	public ResultEntity<String> saveRole(Role role) {

		roleService.saveRole(role);

		return ResultEntity.success();
	}
}
