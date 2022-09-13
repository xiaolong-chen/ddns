package world.xiaolong.ddns.controller;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.xiaolong.ddns.enums.CodeEnum;
import world.xiaolong.ddns.enums.OperateEnum;
import world.xiaolong.ddns.enums.ResultEnum;
import world.xiaolong.ddns.service.DDNSService;
import world.xiaolong.ddns.vo.DDNSRequestVO;
import world.xiaolong.ddns.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ddns")
public class DDNSController {

    /**
     * ddns服务
     */
    private DDNSService ddnsService;

    @RequestMapping("/ip")
    public ResultVO ip(HttpServletRequest request) throws Exception {
        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, ServletUtil.getClientIP(request));
    }

    @RequestMapping("/info")
    public ResultVO info() throws Exception {
        return ddnsService.info();
    }

    @RequestMapping("/test")
    public ResultVO test(DDNSRequestVO request) throws Exception {
        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, request);
    }

    @RequestMapping("/add")
    public ResultVO addDDns(DDNSRequestVO request) throws Exception {
        return ddnsService.configDDns(OperateEnum.ADD, request);
    }

    @RequestMapping("/update")
    public ResultVO updateDDns(DDNSRequestVO request) throws Exception {
        return ddnsService.configDDns(OperateEnum.UPDATE, request);
    }

    @RequestMapping("/delete")
    public ResultVO configDDns(DDNSRequestVO request) throws Exception {
        return ddnsService.configDDns(OperateEnum.DELETE, request);
    }

    @Autowired
    public void setDdnsService(DDNSService ddnsService) {
        this.ddnsService = ddnsService;
    }

}
