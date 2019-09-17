package com.gradu.qa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.qa.entity.ProblemEntity;
import service.MPBaseService;


public interface ProblemService extends MPBaseService<ProblemEntity> {

    Page<ProblemEntity> getNewProblem(int current,int size,String labelid);

    Page<ProblemEntity> getHotProblem(int current,int size,String labelid);

    void add(ProblemEntity problemEntity);

    void delete(String id);

    void update(ProblemEntity problemEntity);

    ProblemEntity selectById(String id);

}
