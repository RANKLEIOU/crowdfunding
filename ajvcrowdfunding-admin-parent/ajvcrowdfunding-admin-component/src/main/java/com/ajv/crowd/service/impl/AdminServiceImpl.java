package com.ajv.crowd.service.impl;

import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.mapper.AdminMapper;
import com.ajv.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public List<Admin> findAll() {
		return adminMapper.selectByExample(null);
	}

	@Override
	public int saveAdmin(Admin admin) {
		return adminMapper.insert(admin);
	}
}
