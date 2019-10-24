package com.gradu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gradu.user.dao.AdminDao;
import com.gradu.user.dto.AdminDTO;
import com.gradu.user.entity.AdminEntity;
import com.gradu.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;
import util.IdWorker;

@Service
public class AdminServiceImpl extends MPBaseServiceImpl<AdminDao, AdminEntity> implements AdminService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void add(AdminEntity adminEntity){
        adminEntity.setId(String.valueOf(idWorker.nextId()));
        adminEntity.setPassword(bCryptPasswordEncoder.encode(adminEntity.getPassword()));
        adminEntity.setState("1");
        save(adminEntity);
    }

    @Override
    public AdminEntity login(AdminDTO dto) {

        QueryWrapper<AdminEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(dto.getLoginname()),"loginname",dto.getLoginname());
        AdminEntity one = getOne(wrapper);

        if (one != null && bCryptPasswordEncoder.matches(dto.getPassword(),one.getPassword())){
            return  one;
        }

        return null;
    }
}
