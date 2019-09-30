package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

@Data
@ConfigurationProperties(prefix = "token.config")
public class JwtUtil {

    private Long effectiveTime;

    private String privateKey;

    public String cteateToken(String id,String subject,String role){

        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,privateKey).claim("role",role);

        if (effectiveTime>0){
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis()+effectiveTime));
        }

        return jwtBuilder.compact();
    }

    public Claims parseToken(String token){
        return Jwts.parser().setSigningKey(privateKey).parseClaimsJwt(token).getBody();
    }
}
