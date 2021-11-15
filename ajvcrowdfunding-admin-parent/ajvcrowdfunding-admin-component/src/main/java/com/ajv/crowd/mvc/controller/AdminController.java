package com.ajv.crowd.mvc.controller;

import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("index")
	public String getAll(Model model){
		List<Admin> info = adminService.findAll();
		model.addAttribute("info",info);
		return "info";
	}
}
