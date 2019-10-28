package com.gradu.user.service;

import com.gradu.user.dto.AdminDTO;
import com.gradu.user.entity.AdminEntity;
import service.MPBaseService;

import java.util.List;

public interface AdminService extends MPBaseService<AdminEntity> {
    void add(AdminEntity adminEntity);

    AdminEntity login(AdminDTO dto);

    List<Long> queryAllMenuId(Long userId);
}
