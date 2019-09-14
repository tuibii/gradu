package com.gradu.qa.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradu.qa.dao.ProblemDao;
import com.gradu.qa.entity.ProblemEntity;
import com.gradu.qa.service.ProblemService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

import java.util.List;

@Service
public class ProblemServiceImpl extends MPBaseServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {

    @Override
    public Page<ProblemEntity> getNewProblem(int current, int size,String labelid) {
        List<ProblemEntity> newProblemList = this.baseMapper.newProblemList(labelid);
        Page<ProblemEntity> page = new Page<>(current,size,newProblemList.size());
        page.setRecords(newProblemList);
        return page;
    }

    @Override
    public Page<ProblemEntity> getHotProblem(int current, int size,String labelid) {
        List<ProblemEntity> hotProblemList = this.baseMapper.hotProblemList(labelid);
        Page<ProblemEntity> page = new Page<>(current,size,hotProblemList.size());
        page.setRecords(hotProblemList);
        return page;
    }
}
