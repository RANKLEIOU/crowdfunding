package com.ajv.crowd.mvc.controller;

import com.ajv.crowd.constant.CrowdConstant;
import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("")
	public String doLogin(@RequestParam("loginAcct")String loginAcct, @RequestParam("userPswd")String userPswd, HttpSession session){
		Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
		return "admin-main";
	}
}
