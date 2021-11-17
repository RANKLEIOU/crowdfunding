package com.ajv.crowd.service.impl;

import com.ajv.crowd.constant.CrowdConstant;
import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.entity.AdminExample;
import com.ajv.crowd.mapper.AdminMapper;
import com.ajv.crowd.service.api.AdminService;
import com.ajv.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
		// 利用mybatis代码生成器生成的example类添加自定义条件
		AdminExample example = new AdminExample();
		AdminExample.Criteria criteria = example.createCriteria();

		// 自定义条件通过账号查询相关信息
		criteria.andLoginAcctEqualTo(loginAcct);
		List<Admin> admins = adminMapper.selectByExample(example);

		// 如果信息为空则抛出异常
		if (admins == null && admins.size() == 0){
			throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
		}
		if (admins.size() > 0){
			throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
		}

		//拿到查询的信息
		Admin admin = admins.get(0);
		if (admin == null){
			throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
		}

		//拿出查询到的信息中的密码
		String userPswdDB = admin.getUserPswd();
		//将用户输入的密码进行MD5加密
		String userPswdFrom = CrowdUtil.md5(userPswd);
		//二者对比，不相同则抛出异常，相同就返回对象
		if (!Objects.equals(userPswdDB,userPswdFrom)){
			throw new RuntimeException(CrowdConstant.MESSAGE_LOGIN_FAILED);
		}
		return admin;
	}

	@Override
	public List<Admin> findAll() {
		return adminMapper.selectByExample(null);
	}

	@Override
	public void saveAdmin(Admin admin) {
		adminMapper.insert(admin);
	}
}
