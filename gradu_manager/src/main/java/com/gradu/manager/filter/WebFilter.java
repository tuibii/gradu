package com.gradu.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getHeader("token");

        if (request.getMethod().equals("")){
            return null;
        }

        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login")>0 || url.indexOf("/admin/captcha")>0){
            return null;
        }

        if (!StringUtils.isEmpty(token)){
            try{
                Claims claims = jwtUtil.parseToken(token);
                String role = (String) claims.get("role");

                if (role.equals("admin")){
                    currentContext.addZuulRequestHeader("token",token);
                    return null;
                }

            }catch (Exception e){
                e.printStackTrace();
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(401);
                currentContext.setResponseBody("无权限");
            }
        }


        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(401);
        currentContext.setResponseBody("无权限");
        return null;
    }
}
