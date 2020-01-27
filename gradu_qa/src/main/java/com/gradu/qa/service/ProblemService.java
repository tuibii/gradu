package com.gradu.qa.service;

import com.gradu.qa.entity.ProblemEntity;
import service.BaseService;


public interface ProblemService extends BaseService<ProblemEntity> {

    void add(ProblemEntity problemEntity);

    void delete(String id);

    void update(ProblemEntity problemEntity);

    ProblemEntity selectById(String id);

    void focus(String id, String user);
}
