package com.qc.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qc.ssm.mapper.UserMapper;
import com.qc.ssm.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	public UserMapper userMapper;
	@Override
	public String findAge(String id) {
		return userMapper.findAge(id);
	}

}
