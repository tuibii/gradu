package com.gradu.friend.service;

import com.gradu.friend.entity.FriendEntity;
import service.MPBaseService;

public interface FriendService extends MPBaseService<FriendEntity> {

    int add(String userid, String friendid);
}
