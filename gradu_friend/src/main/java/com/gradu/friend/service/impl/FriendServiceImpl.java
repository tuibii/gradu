package com.gradu.friend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.friend.dao.FriendDao;
import com.gradu.friend.entity.FriendEntity;
import com.gradu.friend.service.FriendService;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class FriendServiceImpl extends MPBaseServiceImpl<FriendDao, FriendEntity> implements FriendService {

    @Override
    public int add(String userid, String friendid) {

        QueryWrapper<FriendEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("userid",userid);
        wrapper.eq("friendid",friendid);

        FriendEntity one = getOne(wrapper);
        if (one != null){
            return 0;
        }else {

            /**
             *  user 关注数+1
             *  friend 粉丝数+1
             */



            FriendEntity user = new FriendEntity();
            user.setFriendid(friendid);
            user.setUserid(userid);

            QueryWrapper<FriendEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userid",friendid);
            queryWrapper.eq("userid",userid);

            FriendEntity friend = getOne(queryWrapper);
            if (friend == null){
                user.setIslike("0");
                save(user);
                return 1;
            }else {
                user.setIslike("1");
                friend.setIslike("1");
                save(user);
                updateById(friend);
                return 2;
            }

        }
    }
}
