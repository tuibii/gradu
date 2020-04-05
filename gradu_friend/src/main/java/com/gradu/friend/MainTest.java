package com.gradu.friend;

import com.gradu.friend.util.NIMPost;
import com.gradu.friend.util.UUIDUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Firrela
 * @time 2016/3/7.
 */
public class MainTest {

    private static Logger logger = LoggerFactory.getLogger(MainTest.class);

    private static String APPKEY = "ef3b4cc8adea4c3914ccf5545ff91849";  //AppKey
    private static String SECRET = "535fbec8f9df";  //AppSecret

    public static final void main(String[] args) throws IOException {
        String res = addUser("1192014778951525", "1192014791112155136", 2, "admin2请求加好友admin");

        System.out.println(res);
        //TODO: 对结果的业务处理，如解析返回结果，并保存成功注册的用户
    }

    public static String createUser(String accid, String name, String token) throws IOException {
        String url = "https://api.netease.im/nimserver/user/create.action";
        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("accid", accid));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("token", token));

        //UTF-8编码,解决中文问题
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");

        String res = NIMPost.postNIMServer(url, entity, APPKEY, SECRET);
        logger.info("createUser httpRes: {}", res);
        return res;
    }

    public static String addUser(String accid, String faccid, int type, String msg) throws IOException {
        String url = "https://api.netease.im/nimserver/friend/add.action";
        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("accid", accid));
        params.add(new BasicNameValuePair("faccid", faccid));
        params.add(new BasicNameValuePair("type", String.valueOf(type)));
        params.add(new BasicNameValuePair("msg", msg));

        //UTF-8编码,解决中文问题
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");

        String res = NIMPost.postNIMServer(url, entity, APPKEY, SECRET);
        logger.info("createUser httpRes: {}", res);
        return res;
    }
}
