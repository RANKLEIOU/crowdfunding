package com.ajv.crowd.mvc.controller;

import com.ajv.crowd.constant.CrowdConstant;
import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	/**
	 * 登录方法
	 * @param loginAcct 从前端传入的用户账号
	 * @param userPswd 从前端传入的用户密码
	 * @param session 用于将登录的用户信息存入session域中
	 * @return 登录成功后重定向到管理员后台界面
	 */
	@RequestMapping("/admin/do/login")
	public String doLogin(@RequestParam("loginAcct")String loginAcct, @RequestParam("userPswd")String userPswd, HttpSession session){
		Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
		return "redirect:/admin/to/main/toMain";
	}

	/**
	 * 退出登录
	 * @param session 让登录后存入的用户信息失效
	 * @return 退出登录后跳转到登陆页面
	 */
	@RequestMapping("/admin/do/logout")
	public String doLogout(HttpSession session){
		// 强制session域对象失效
		session.invalidate();
		return "redirect:/admin/to/login/toLogin";
	}

	@RequestMapping("/admin/get/page")
	public String toPage(@RequestParam(value = "keyword",defaultValue = "")String keyword, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize, Model model){
		PageInfo<Admin> pageInfo = adminService.selectAdminBykeyword(keyword, pageNum, pageSize);
		model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		return "admin-page";
	}
}
