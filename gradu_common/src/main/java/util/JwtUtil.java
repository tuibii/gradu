package util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "token.config")
public class JwtUtil {

    private Long effectiveTime;

    private String privateKey;

    public static String cteateToken(){


        return null;
    }
}
