package com.gradu.user.service.impl;

import com.gradu.user.dao.DynamicDao;
import com.gradu.user.entity.DynamicEntity;
import com.gradu.user.service.DynamicService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class DynamicServiceImpl extends MPBaseServiceImpl<DynamicDao, DynamicEntity> implements DynamicService {
}
