package com.lumengjun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lumengjun.dao.LockedMapper;
import com.lumengjun.service.LockedService;

@Service
public class LockedServiceImpl implements LockedService {

	@Autowired
	LockedMapper lockedMapper;
	
	
	
}
