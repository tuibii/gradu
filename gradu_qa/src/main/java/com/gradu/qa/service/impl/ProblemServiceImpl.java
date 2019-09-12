package com.gradu.qa.service.impl;

import com.gradu.qa.dao.ProblemDao;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class ProblemServiceImpl extends MPBaseServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {
}
