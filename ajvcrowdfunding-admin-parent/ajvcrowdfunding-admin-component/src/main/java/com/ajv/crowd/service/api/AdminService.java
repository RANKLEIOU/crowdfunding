package com.ajv.crowd.service.api;

import com.ajv.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

public interface AdminService {

	Admin getAdminByLoginAcct(String loginAcct, String userPswd);

	PageInfo<Admin> selectAdminBykeyword(String keyword, Integer pageNum, Integer pageSize);

	void saveAdmin(Admin admin);

	void removeAdmin(Integer id);

	Admin findById(Integer id);

	void updateAdmin(Admin admin);

}
