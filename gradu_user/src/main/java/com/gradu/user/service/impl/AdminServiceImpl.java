package com.gradu.user.service.impl;

import com.gradu.user.dao.AdminDao;
import com.gradu.user.entity.AdminEntity;
import com.gradu.user.service.AdminService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class AdminServiceImpl extends MPBaseServiceImpl<AdminDao, AdminEntity> implements AdminService {

}
