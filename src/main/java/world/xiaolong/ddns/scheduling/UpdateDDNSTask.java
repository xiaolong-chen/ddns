package world.xiaolong.ddns.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import world.xiaolong.ddns.service.DDNSService;
import world.xiaolong.ddns.util.CommonUtil;

/**
 * <b>修改记录：</b>
 * <p>
 * <li>修改本类 ---- ChenXiaoLong 2022年3月1日</li>
 * </p>
 *
 * <b>类说明：</b>
 * <p>
 * 定时更新内存中所有DNS解析记录
 * </p>
 */
@Component
@Slf4j
public class UpdateDDNSTask {

    private DDNSService ddnsService;

    @Scheduled(initialDelay = 3000, fixedDelayString = "${ddns.AutoUpdateIntervalTime}")
    public void updateDDNS() {
        CommonUtil.confTraceID();
        log.info("定时更新内存DNS记录开始");
        try {
            ddnsService.updateDDNS();
            log.info("定时更新内存DNS记录结束");
        } catch (Exception e) {
            log.info("定时更新内存DNS记录异常", e);
        }
    }

    @Autowired
    public void setDdnsService(DDNSService ddnsService) {
        this.ddnsService = ddnsService;
    }

}
