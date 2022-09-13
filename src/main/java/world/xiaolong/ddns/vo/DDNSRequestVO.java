package world.xiaolong.ddns.vo;

import lombok.Data;

@Data
public class DDNSRequestVO {

    /**
     * 子域名
     */
    private String rr;
    /**
     * 类型<br>
     * AA:ipv4<br>
     * AAAA:ipv6<>
     */
    private String type;
    /**
     * ip地址
     */
    private String value;

}
