package com.ajv.crowd.service.impl;

import com.ajv.crowd.entity.Auth;
import com.ajv.crowd.mapper.AuthMapper;
import com.ajv.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthMapper authMapper;

	@Override
	public List<Auth> getAuthList() {
		return authMapper.selectByExample(null);
	}

	@Override
	public List<Integer> getAuthIdByRoleId(Integer roleId) {
		return authMapper.selectAuthIdByRoleId(roleId);
	}

	@Override
	public void saveAuth(Map<String, List<Integer>> map) {

		// 角色id
		List<Integer> roleIds = map.get("roleId");
		Integer roleId = roleIds.get(0);

		// 删除旧的角色权限
		authMapper.deleteOldAuth(roleId);

		// 权限id
		List<Integer> authIds = map.get("authIdArray");

		// 判断权限id数组是否可用
		if (authIds != null && authIds.size() > 0){
			// 保存新的角色权限
			authMapper.saveAuth(authIds,roleId);
		}
	}
}
