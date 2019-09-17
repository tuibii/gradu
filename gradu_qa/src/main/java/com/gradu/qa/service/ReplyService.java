package com.gradu.qa.service;

import com.gradu.qa.entity.ReplyEntity;
import service.MPBaseService;

public interface ReplyService extends MPBaseService<ReplyEntity> {

    void add(ReplyEntity replyEntity);

    void delete(String id);

    void update(ReplyEntity replyEntity);

    ReplyEntity selectById(String id);

}
