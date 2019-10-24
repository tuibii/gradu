package com.gradu.user.service;

import com.gradu.user.dto.AdminDTO;
import com.gradu.user.entity.AdminEntity;
import service.MPBaseService;

public interface AdminService extends MPBaseService<AdminEntity> {
    void add(AdminEntity adminEntity);

    AdminEntity login(AdminDTO dto);
}
