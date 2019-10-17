package com.gradu.friend.service;

import com.gradu.friend.entity.FriendEntity;
import service.MPBaseService;

public interface FriendService extends MPBaseService<FriendEntity> {

    int focus(String userid, String friendid);

    int unFocus(String userid, String friendid);
}
