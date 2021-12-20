package com.ajv.crowd.mvc.controller;

import com.ajv.crowd.entity.Role;
import com.ajv.crowd.service.api.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class AssignController {

	@Autowired
	private RoleService roleService;

	// 查询已分配和未分配角色
	@RequestMapping("/assign/to/page")
	public String getAssignInfo(@RequestParam("adminId")Integer adminId, Model model){

		// 根据用户id查询已分配角色
		List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
		// 根据用户id查询未分配角色
		List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);

		model.addAttribute("assignedRoleList",assignedRoleList);
		model.addAttribute("unAssignedRoleList",unAssignedRoleList);
		return "role/role-assign";
	}

	// 保存配分的角色
	@RequestMapping("/assign/to/role")
	public String saveAssignRole(@RequestParam("adminId")Integer adminId,
								 @RequestParam("pageNum")Integer pageNum,
								 @RequestParam("keyword")String keyword,
								 @RequestParam(value = "roleIdList",defaultValue = "")List<Integer> roleIdList){

		roleService.saveAssignRole(roleIdList,adminId);

		return "redirect:/admin/get/page?pageNum="+pageNum+"&keyword="+keyword;
	}
}
