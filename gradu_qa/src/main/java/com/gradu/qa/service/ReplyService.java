package com.gradu.qa.service;

import com.gradu.qa.dto.ReplyDTO;
import com.gradu.qa.entity.ReplyEntity;
import service.MPBaseService;

import java.util.List;

public interface ReplyService extends MPBaseService<ReplyEntity> {

    void add(ReplyEntity replyEntity);

    void delete(String id);

    void update(ReplyEntity replyEntity);

    ReplyEntity selectById(String id);

    List<ReplyDTO> listByProblemId(String problemId);

    void rate(String userid, Double rate, ReplyEntity replyEntity);
}
