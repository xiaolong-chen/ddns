package world.xiaolong.ddns.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ddns")
@Data
public class DDNSProperties {

    /**
     * 阿里云dns服务地址
     */
    private String endpoint;
    /**
     * 阿里云accessKeyId
     */
    private String accessKeyId;
    /**
     * 阿里云accessKeySecret
     */
    private String accessKeySecret;
    /**
     * 域名
     */
    private String domainName;

}
