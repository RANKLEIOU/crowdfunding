package com.ajv.crowd.mvc.controller;

import com.ajv.crowd.entity.Auth;
import com.ajv.crowd.entity.Role;
import com.ajv.crowd.service.api.AuthService;
import com.ajv.crowd.service.api.RoleService;
import com.ajv.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class AssignController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthService authService;

	/**
	 * 查询已分配和未分配角色
	 *
	 * @param adminId 管理员id
	 * @param model   用于装载数据
	 * @return 转发到角色授权页面
	 */
	@RequestMapping("/assign/to/page")
	public String getAssignInfo(@RequestParam("adminId") Integer adminId, Model model) {

		// 根据用户id查询已分配角色
		List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
		// 根据用户id查询未分配角色
		List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);

		model.addAttribute("assignedRoleList", assignedRoleList);
		model.addAttribute("unAssignedRoleList", unAssignedRoleList);
		return "role/role-assign";
	}

	/**
	 * 保存配分的角色
	 *
	 * @param adminId    管理员id
	 * @param pageNum    当前页数
	 * @param keyword    查询关键字
	 * @param roleIdList 角色id数组
	 * @return 重定向到数据页面
	 */
	@RequestMapping("/assign/to/role")
	public String saveAssignRole(@RequestParam("adminId") Integer adminId,
								 @RequestParam("pageNum") Integer pageNum,
								 @RequestParam("keyword") String keyword,
								 @RequestParam(value = "roleIdList", defaultValue = "") List<Integer> roleIdList) {

		roleService.saveAssignRole(roleIdList, adminId);

		return "redirect:/admin/get/page?pageNum=" + pageNum + "&keyword=" + keyword;
	}

	/**
	 * 查询所有权限
	 *
	 * @return
	 */
	@RequestMapping("/assign/get/auth")
	@ResponseBody
	public ResultEntity<List<Auth>> getAuth() {

		List<Auth> authList = authService.getAuthList();
		return ResultEntity.successWithData(authList);
	}

	@RequestMapping("/assign/get/authId")
	@ResponseBody
	public ResultEntity<List<Integer>> getAuthIdByRoleId(@RequestParam("roleId") Integer roleId) {
		List<Integer> authIdList = authService.getAuthIdByRoleId(roleId);
		return ResultEntity.successWithData(authIdList);
	}

	@RequestMapping("/assign/to/auth")
	@ResponseBody
	public ResultEntity<String> saveAuth(@RequestBody Map<String, List<Integer>> map) {
		authService.saveAuth(map);
		return ResultEntity.success();
	}
}
