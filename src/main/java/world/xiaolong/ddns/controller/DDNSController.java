package world.xiaolong.ddns.controller;

import com.aliyun.alidns20150109.models.DescribeDomainRecordsResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.xiaolong.ddns.enums.CodeEnum;
import world.xiaolong.ddns.enums.OperateEnum;
import world.xiaolong.ddns.enums.ResultEnum;
import world.xiaolong.ddns.service.DDNSService;
import world.xiaolong.ddns.util.MemoryData;
import world.xiaolong.ddns.vo.DDNSRequestVO;
import world.xiaolong.ddns.vo.ResultVO;

import java.util.List;

@RestController
@RequestMapping("/ddns")
@Slf4j
public class DDNSController {

    /**
     * ddns服务
     */
    private DDNSService ddnsService;
    private ObjectMapper objectMapper;

    @RequestMapping("/ip")
    public ResultVO ip() throws Exception {
        String ip = MDC.get("ip");
        log.info("发起ip查询", ip);
        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, ip);
    }

    @RequestMapping("/info")
    public ResultVO info() throws Exception {
        log.info("发起dns记录查询");
        List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> list = MemoryData.getList();
        log.info("dns记录: {}", objectMapper.writeValueAsString(list));
        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, list);
    }

    @RequestMapping("/info/force")
    public ResultVO infoForce() throws Exception {
        log.info("发起dns记录强制查询");
        ddnsService.updateDDNS();
        List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> list = MemoryData.getList();
        log.info("dns记录: {}", objectMapper.writeValueAsString(list));
        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, list);
    }

    @RequestMapping("/test")
    public ResultVO test(DDNSRequestVO request) throws Exception {
        log.info("发起请求参数打印[{}]", request);
        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, request);
    }

    @RequestMapping("/add")
    public ResultVO addDDns(DDNSRequestVO request) throws Exception {
        log.info("发起域名新增[{}]", request);
        return ddnsService.configDDns(OperateEnum.ADD, request);
    }

    @RequestMapping("/update")
    public ResultVO updateDDns(DDNSRequestVO request) throws Exception {
        log.info("发起域名更新[{}]", request);
        return ddnsService.configDDns(OperateEnum.UPDATE, request);
    }

    @RequestMapping("/delete")
    public ResultVO configDDns(DDNSRequestVO request) throws Exception {
        log.info("发起域名删除[{}]", request);
        return ddnsService.configDDns(OperateEnum.DELETE, request);
    }

    @Autowired
    public void setDdnsService(DDNSService ddnsService) {
        this.ddnsService = ddnsService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
