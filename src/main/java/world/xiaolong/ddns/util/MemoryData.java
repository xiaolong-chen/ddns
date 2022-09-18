package world.xiaolong.ddns.util;

import com.aliyun.alidns20150109.models.DescribeDomainRecordsResponseBody;

import java.util.List;

/**
 * <b>修改记录：</b>
 * <p>
 * <li>修改本类 ---- ChenXiaoLong 2022年3月1日</li>
 * </p>
 *
 * <b>类说明：</b>
 * <p>
 * 内存对象类
 * </p>
 */
public class MemoryData {

    /**
     * 所有dns解析记录
     */
    private static List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> list;

    public static synchronized List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> getList() {
        return list;
    }

    public static synchronized void setList(List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> list) {
        MemoryData.list = list;
    }

}
