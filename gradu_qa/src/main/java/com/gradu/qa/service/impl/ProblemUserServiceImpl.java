package com.gradu.qa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.qa.dao.ProblemUserDao;
import com.gradu.qa.entity.ProblemUserEntity;
import com.gradu.qa.service.ProblemUserService;
import org.springframework.stereotype.Service;
import service.impl.BaseServiceImpl;

import java.util.Map;

@Service
public class ProblemUserServiceImpl extends BaseServiceImpl<ProblemUserDao,ProblemUserEntity> implements ProblemUserService {
    @Override
    public QueryWrapper<ProblemUserEntity> getWrapper(Map<String, Object> params) {
        return null;
    }

    @Override
    public Boolean focus(String userid, String problemid) {
        if (StringUtils.isNotEmpty(userid) && StringUtils.isNotEmpty(problemid)) {
            QueryWrapper<ProblemUserEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("userid",userid);
            wrapper.eq("problemid",problemid);
            Integer count = baseDao.selectCount(wrapper);
            return count.equals(1);
        }
        return false;
    }
}
