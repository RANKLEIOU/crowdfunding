package com.ajv.crowd.service.impl;

import com.ajv.crowd.entity.Menu;
import com.ajv.crowd.mapper.MenuMapper;
import com.ajv.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getAll() {
		return menuMapper.selectByExample(null);
	}
}
