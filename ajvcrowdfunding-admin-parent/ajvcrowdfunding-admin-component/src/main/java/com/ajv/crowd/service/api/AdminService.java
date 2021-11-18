package com.ajv.crowd.service.api;

import com.ajv.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {

	List<Admin> findAll();

	void saveAdmin(Admin admin);

	Admin getAdminByLoginAcct(String loginAcct, String userPswd);

	PageInfo<Admin> selectAdminBykeyword(String keyword, Integer pageNum, Integer pageSize);
}
