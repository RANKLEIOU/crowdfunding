package com.ajv.crowd.service.api;

import com.ajv.crowd.entity.Admin;

import java.util.List;

public interface AdminService {

	List<Admin> findAll();

	int saveAdmin(Admin admin);
}
