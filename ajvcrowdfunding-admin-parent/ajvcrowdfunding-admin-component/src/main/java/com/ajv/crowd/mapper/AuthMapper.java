package com.ajv.crowd.mapper;

import com.ajv.crowd.entity.Auth;
import com.ajv.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
	long countByExample(AuthExample example);

	int deleteByExample(AuthExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Auth record);

	int insertSelective(Auth record);

	List<Auth> selectByExample(AuthExample example);

	Auth selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

	int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

	int updateByPrimaryKeySelective(Auth record);

	int updateByPrimaryKey(Auth record);

	List<Integer> selectAuthIdByRoleId(Integer roleId);

	void deleteOldAuth(Integer roleId);

	void saveAuth(@Param("authIds") List<Integer> authIds, @Param("roleId") Integer roleId);
}
