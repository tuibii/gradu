package com.gradu.user.service.impl;

import com.gradu.user.dao.UserDao;
import com.gradu.user.entity.UserEntity;
import com.gradu.user.service.UserService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class UserServiceImpl extends MPBaseServiceImpl<UserDao, UserEntity> implements UserService {

}
