package com.ajv.crowd.service.api;

import com.ajv.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {

	List<Auth> getAuthList();

	List<Integer> getAuthIdByRoleId(Integer roleId);

	void saveAuth(Map<String, List<Integer>> map);
}
