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

	/**
	 * 条件查询
	 * @param keyword 前端传入的查询关键字
	 * @param pageNum 当前页面
	 * @param pageSize 页面显示的数据量
	 * @param model 将分页查询信息存到请求域中
	 * @return 跳转到管理员信息页面
	 */
	@RequestMapping("/admin/get/page")
	public String toPage(@RequestParam(value = "keyword",defaultValue = "")String keyword,
						 @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
						 @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
						 Model model){
		PageInfo<Admin> pageInfo = adminService.selectAdminBykeyword(keyword, pageNum, pageSize);
		model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		return "admin/admin-page";
	}

	// 添加管理员
	@RequestMapping("/admin/save")
	public String saveAdmin(Admin admin){
		adminService.saveAdmin(admin);
		//重定向到分页页面
		return "redirect:/admin/get/page?pageNum="+Integer.MAX_VALUE;
	}

	//删除管理员
	@RequestMapping("/admin/remove")
	public String removeAdmin(@RequestParam("id")Integer id,
							  @RequestParam("pageNum")Integer pageNum,
							  @RequestParam("keyword")String keyword){

		adminService.removeAdmin(id);

		return "redirect:/admin/get/page?pageNum="+pageNum+"&keyword="+keyword;
	}

	//跳转管理员信息
	@RequestMapping("/admin/to/edit/page")
	public String findById(@RequestParam Integer id,Model model){
		Admin admin = adminService.findById(id);
		model.addAttribute("admin",admin);
		return "admin/admin-edit";
	}

	/**
	 * 修改管理员信息
	 * @param keyword 前端传入的查询关键字
	 * @param pageNum 当前页面
	 * @param admin 从请求中获取提交的数据
	 * @return 重定向到信息页面
	 */
	@RequestMapping("/admin/edit")
	public String updateAdmin(@RequestParam("keyword")String keyword,
							  @RequestParam("pageNum")Integer pageNum,
							  Admin admin){

		adminService.updateAdmin(admin);

		return "redirect:/admin/get/page?pageNum="+pageNum+"&keyword="+keyword;
	}
}
