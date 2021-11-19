package com.ajv.crowd.service.impl;

import com.ajv.crowd.constant.CrowdConstant;
import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.entity.AdminExample;
import com.ajv.crowd.exception.LoginAcctAlreadyInUseException;
import com.ajv.crowd.mapper.AdminMapper;
import com.ajv.crowd.service.api.AdminService;
import com.ajv.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		if (admins.size() > 1){
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
	public PageInfo<Admin> selectAdminBykeyword(String keyword, Integer pageNum, Integer pageSize) {
		// 调用pageHelper静态方法开启分页
		PageHelper.startPage(pageNum,pageSize);
		// 执行查询获取数据
		List<Admin> admins = adminMapper.selectAdminBykeyword(keyword);
		// 将获取的数据封装到pageInfo中
		PageInfo<Admin> pageInfo = new PageInfo<>(admins);
		return pageInfo;
	}

	@Override
	public void saveAdmin(Admin admin) {
		// 获取日期
		Date date = new Date();
		// 格式化工具并设置格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 格式化日期
		String createTime = format.format(date);
		// 设置为创建时间
		admin.setCreateTime(createTime);

		//对密码进行加密
		String source = admin.getUserPswd();
		String userPswd = CrowdUtil.md5(source);
		admin.setUserPswd(userPswd);
		//执行添加操作
		try {
			adminMapper.insert(admin);
		}catch (Exception e){
			e.printStackTrace();
			//判断异常对象是否是DuplicateKeyException，判断正确则表示账号重复，抛出自定义异常
			if (e instanceof DuplicateKeyException){
				throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
			//如果不是DuplicateKeyException异常，则把异常对象再次抛出
			throw e;
		}
	}

	@Override
	public void removeAdmin(Integer id) {
		adminMapper.deleteByPrimaryKey(id);
	}

	//通过id查找个人信息显示在修改界面
	@Override
	public Admin findById(Integer id) {
		return adminMapper.selectByPrimaryKey(id);
	}

	//修改方法
	@Override
	public void updateAdmin(Admin admin) {
		try {
			//动态的修改，不会修改传入值为空的字段
			adminMapper.updateByPrimaryKeySelective(admin);
		}catch (Exception e){
			e.printStackTrace();
			//如果修改的账号在数据库中有了则抛出异常
			if (e instanceof DuplicateKeyException){
				throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
	}

}
