package com.gradu.mq.test;

import com.gradu.mq.properties.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsTest {

    @Autowired
    SmsProperties smsProperties;

    @Test
    public void aliSmsTest(){
        System.out.println(smsProperties);
    }

}
