package com.gradu.friend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gradu.friend.client.UserClient;
import com.gradu.friend.dao.FriendDao;
import com.gradu.friend.entity.FriendEntity;
import com.gradu.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.impl.MPBaseServiceImpl;

@Service
public class FriendServiceImpl extends MPBaseServiceImpl<FriendDao, FriendEntity> implements FriendService {

    @Autowired
    UserClient userClient;

    @Override
    public int focus(String userid, String friendid) {

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
            userClient.incFollow(userid,1);
            userClient.incFans(friendid,1);

            FriendEntity user = new FriendEntity();
            user.setFriendid(friendid);
            user.setUserid(userid);

            QueryWrapper<FriendEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userid",friendid);
            queryWrapper.eq("userid",userid);

            FriendEntity friend = getOne(queryWrapper);
            if (friend == null){
                /**
                 *  对方没关注我
                 */
                user.setIslike("0");
                save(user);
                return 1;
            }else {
                /**
                 *  对方关注我
                 */
                user.setIslike("1");
                friend.setIslike("1");
                save(user);
                updateById(friend);
                return 2;
            }

        }
    }

    @Override
    public int unFocus(String userid, String friendid) {
        QueryWrapper<FriendEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("userid",userid);
        wrapper.eq("friendid",friendid);

        FriendEntity one = getOne(wrapper);
        if (one == null){
            return 0;
        }else {
            /**
             *  user 关注数-1
             *  friend 粉丝数-1
             */
            userClient.incFollow(userid,-1);
            userClient.incFans(friendid,-1);

            if (one.getIslike().equals("0")){
                /**
                 *  对方没关注我
                 */
                remove(wrapper);
                return 1;
            }else {
                /**
                 *  对方关注我
                 */
                remove(wrapper);
                FriendEntity friendEntity = new FriendEntity();
                friendEntity.setIslike("0");
                friendEntity.setUserid(one.getFriendid());
                friendEntity.setFriendid(one.getUserid());
                updateById(friendEntity);
                return 2;
            }
        }
    }
}
