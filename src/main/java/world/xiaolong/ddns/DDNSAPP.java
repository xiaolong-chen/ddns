package world.xiaolong.ddns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import world.xiaolong.ddns.util.CommonUtil;

@SpringBootApplication
@Slf4j
public class DDNSAPP {

    public static void main(String[] args) throws Exception {
        CommonUtil.confTraceID();
        SpringApplication.run(DDNSAPP.class, args);
    }

}