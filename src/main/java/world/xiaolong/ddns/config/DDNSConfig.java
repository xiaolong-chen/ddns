package world.xiaolong.ddns.config;

import com.aliyun.alidns20150109.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class DDNSConfig {

    @Bean
    public Client client(DDNSProperties ddnsProperties) {
        Config config = new Config();
        config.setAccessKeyId(ddnsProperties.getAccessKeyId())
                .setAccessKeySecret(ddnsProperties.getAccessKeySecret())
                .setEndpoint(ddnsProperties.getEndpoint());
        try {
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        // 对象json格式化时，不含类名
        view.setExtractValueFromSingleKeyModel(true);
        return view;
    }

}
