package world.xiaolong.ddns.util;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

/**
 * <b>修改记录：</b>
 * <p>
 * <li>修改本类 ---- ChenXiaoLong 2022年3月1日</li>
 * </p>
 *
 * <b>类说明：</b>
 * <p>
 * 公共工具类
 * </p>
 */
public class CommonUtil {

    /**
     * <b>方法说明：</b>
     * <ul>
     * 配置日志编号
     * </ul>
     */
    public static void confTraceID() {
        confTraceID(null);
    }

    /**
     * <b>方法说明：</b>
     * <ul>
     * 配置日志编号
     * </ul>
     *
     * @param traceID
     */
    public static void confTraceID(String traceID) {
        if (StrUtil.isEmpty(traceID)) {
            traceID = StrUtil.toString(System.currentTimeMillis());
        }
        MDC.clear();
        MDC.put("traceID", traceID);
    }

    /**
     * <b>方法说明：</b>
     * <ul>
     * 获取当前线程日志编号<br/>
     * 如果当前线程没有设置日志编号，则先设置一个日志编号，再返回
     * </ul>
     *
     * @return
     */
    public static String getCurrentTraceID() {
        String traceID = MDC.get("traceID");
        if (StrUtil.isEmpty(traceID)) {
            confTraceID();
            traceID = MDC.get("traceID");
        }
        return traceID;
    }

}
