package world.xiaolong.ddns.service;

import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.xiaolong.ddns.config.DDNSProperties;
import world.xiaolong.ddns.enums.CodeEnum;
import world.xiaolong.ddns.enums.OperateEnum;
import world.xiaolong.ddns.enums.ResultEnum;
import world.xiaolong.ddns.util.MemoryData;
import world.xiaolong.ddns.vo.DDNSRequestVO;
import world.xiaolong.ddns.vo.ResultVO;

@Service
@Slf4j
public class DDNSService {

    /**
     * ddns配置
     */
    private DDNSProperties ddnsProperties;
    private Client client;
    private ObjectMapper objectMapper;

    public void updateDDNS() throws Exception {
        log.info("更新内存DNS记录");
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        request.setDomainName(ddnsProperties.getDomainName()).setPageSize(100L);
        DescribeDomainRecordsResponse response = client.describeDomainRecords(request);
        MemoryData.setList(response.body.domainRecords.record);
        log.info("查询结果: {}", objectMapper.writeValueAsString(response));
    }

    public ResultVO configDDns(OperateEnum operate, DDNSRequestVO request) throws Exception {
        request.setRr(request.getRr().toLowerCase());
        log.info("{}dns: rr[{}]type[{}]value[{}]", operate.getInfo(), request.getRr(), request.getType(), request.getValue());
        ResultVO result;
        DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord record = MemoryData.getList()
                .stream().filter(r -> r.getRR().equals(request.getRr()) && r.getType().equals(request.getType())).findFirst().orElse(null);

        if (operate == OperateEnum.ADD) {
            if (record == null) {
                result = addDns(request);
                updateDDNS();
            } else {
                result = ResultVO.buildResult(ResultEnum.FAIL, CodeEnum.FOUND, "该记录已存在", record);
            }
            return result;
        }

        if (operate == OperateEnum.UPDATE) {
            if (record == null) {
                result = ResultVO.buildResult(ResultEnum.FAIL, CodeEnum.NOT_FOUND, "不存在该记录", null);
            } else if (record.value.equals(request.getValue())) {
                result = ResultVO.buildResult(ResultEnum.FAIL, CodeEnum.FOUND, "该值已存在", record);
            } else {
                result = updateDns(request, record.getRecordId());
                updateDDNS();
            }
            return result;
        }

        if (operate == OperateEnum.DELETE) {
            if (record == null) {
                result = ResultVO.buildResult(ResultEnum.FAIL, CodeEnum.NOT_FOUND, "不存在该记录", null);
            } else {
                result = deleteDns(record.recordId);
                updateDDNS();
            }
            return result;
        }

        return ResultVO.buildResult(ResultEnum.FAIL, CodeEnum.NOT_IMPL, null);
    }

    public ResultVO addDns(DDNSRequestVO requestVO) throws Exception {
        AddDomainRecordRequest request = new AddDomainRecordRequest().setDomainName(ddnsProperties.getDomainName()).setRR(requestVO.getRr()).setType(requestVO.getType()).setValue(requestVO.getValue());
        AddDomainRecordResponse response = client.addDomainRecord(request);
        log.info("新增结果: {}", objectMapper.writeValueAsString(response));

        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, response);
    }

    public ResultVO updateDns(DDNSRequestVO requestVO, String recordId) throws Exception {
        UpdateDomainRecordRequest request = new UpdateDomainRecordRequest().setRecordId(recordId).setRR(requestVO.getRr()).setType(requestVO.getType()).setValue(requestVO.getValue());
        UpdateDomainRecordResponse response = client.updateDomainRecord(request);
        log.info("修改结果: {}", objectMapper.writeValueAsString(response));

        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, response);
    }

    public ResultVO deleteDns(String recordId) throws Exception {
        DeleteDomainRecordRequest request = new DeleteDomainRecordRequest().setRecordId(recordId);
        DeleteDomainRecordResponse response = client.deleteDomainRecord(request);
        log.info("删除结果: {}", objectMapper.writeValueAsString(response));

        return ResultVO.buildResult(ResultEnum.SUCCESS, CodeEnum.SUCCESS, response);
    }

    @Autowired
    public void setDdnsProperties(DDNSProperties ddnsProperties) {
        this.ddnsProperties = ddnsProperties;
    }

    @Autowired
    public void setClient(Client client) {
        this.client = client;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
