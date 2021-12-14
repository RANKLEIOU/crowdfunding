package com.ajv.crowd.service.api;

import com.ajv.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

	PageInfo<Role> getRoleByKeyword(String keyword,Integer pageNum,Integer pageSize);

	void saveRole(Role role);

	void updateRoleById(Role role);
}
