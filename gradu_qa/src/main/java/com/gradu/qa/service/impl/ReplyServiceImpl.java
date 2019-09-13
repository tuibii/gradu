package com.gradu.qa.service.impl;

import com.gradu.qa.dao.ReplyDao;
import com.gradu.qa.entity.ReplyEntity;
import com.gradu.qa.service.ReplyService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class ReplyServiceImpl extends MPBaseServiceImpl<ReplyDao, ReplyEntity> implements ReplyService {
}
