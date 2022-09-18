package world.xiaolong.ddns.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DDNSRequestVO {

    /**
     * 子域名
     */
    private String rr;
    /**
     * 类型<br>
     * A:ipv4<br>
     * AAAA:ipv6<>
     */
    private String type;
    /**
     * ip地址
     */
    private String value;

}
