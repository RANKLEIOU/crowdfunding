package com.ajv.crowd.service.impl;

import com.ajv.crowd.entity.Role;
import com.ajv.crowd.mapper.RoleMapper;
import com.ajv.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageInfo<Role> getRoleByKeyword(String keyword,Integer pageNum,Integer pageSize) {
		//开启分页
		PageHelper.startPage(pageNum,pageSize);
		//将信息封装到分页插件中
		List<Role> roles = roleMapper.getRoleByKeyword(keyword);

		return new PageInfo<>(roles);
	}
}
