package com.gradu.qa.service;

import com.gradu.qa.entity.ProblemUserEntity;
import service.BaseService;

public interface ProblemUserService extends BaseService<ProblemUserEntity> {
    Boolean focus(String userid, String problemid);
}
