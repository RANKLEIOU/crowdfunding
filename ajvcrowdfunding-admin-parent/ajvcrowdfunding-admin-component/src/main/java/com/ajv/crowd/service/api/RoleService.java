package com.ajv.crowd.service.api;

import com.ajv.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

	PageInfo<Role> getRoleByKeyword(String keyword,Integer pageNum,Integer pageSize);

	void saveRole(Role role);

	void updateRole(Role role);

	void removeRole(List<Integer> roleIdList);

	List<Role> getAssignedRole(Integer adminId);

	List<Role> getUnAssignedRole(Integer adminId);

	void saveAssignRole(List<Integer> roleIdList, Integer adminId);
}
