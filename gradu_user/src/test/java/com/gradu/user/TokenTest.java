package com.gradu.user;

import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import util.JwtUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenTest {

    @Autowired
    JwtUtil jwtUtil;

    @Test
    public void te(){
        Claims claims = jwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NzIzMjc2MDYsInJvbGUiOiJhZG1pbiIsImV4cCI6MTU3MjMzNDgwNn0.gOFwHyT6SMhbEfMqQ3_sU3aX4nQpQnUq6RHBMTDpX6Y");
        System.out.println(claims.getSubject());
    }

    @Test
    public void tes(){
        String admin = jwtUtil.cteateToken("1", "2", "admin");
        Claims claims = jwtUtil.parseToken(admin);
        System.out.println(claims.getSubject());

    }
}
