package com.ajv.crowd.test;

import com.ajv.crowd.entity.Admin;
import com.ajv.crowd.mapper.AdminMapper;
import com.ajv.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private AdminService adminService;

	//连接数据库测试
	@Test
	public void dataSourceTest() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}

	//测试mapper是否生效
	@Test
	public void mapperTest() {
		List<Admin> admins = adminMapper.selectByExample(null);
		for (Admin admin : admins) {
			System.out.println(admin);
		}
	}

	//测试业务层方法以及配置的事务是否生效
	@Test
	public void serviceTest() {
		Admin admin = new Admin(null, "account", "123456", "zs", "zs@rankle.com", "2021-11-15");
		adminService.saveAdmin(admin);
	}
}
