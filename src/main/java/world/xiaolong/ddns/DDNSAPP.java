package world.xiaolong.ddns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import world.xiaolong.ddns.util.CommonUtil;

@SpringBootApplication
@EnableScheduling
public class DDNSAPP {

    public static void main(String[] args) throws Exception {
        CommonUtil.confTraceID();
        SpringApplication.run(DDNSAPP.class, args);
    }

}