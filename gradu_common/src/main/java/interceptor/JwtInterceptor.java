package interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        if (StringUtils.isNotEmpty(token)){
            try {
                Claims claims = jwtUtil.parseToken(token);
                String role = (String)claims.get("role");
                if (StringUtils.isNotEmpty(role)){
                    request.setAttribute("claims",claims);
                }
            }catch (Exception e){
                System.out.println("未登录用户"+request.getRemoteAddr()+"访问:"+request.getRequestURI());
            }
        }

        return true;
    }
}
