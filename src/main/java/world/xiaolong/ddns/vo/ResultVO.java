package world.xiaolong.ddns.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import world.xiaolong.ddns.enums.CodeEnum;
import world.xiaolong.ddns.enums.ResultEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /**
     * 结果：-1失败;0处理;中1成功
     */
    private int result;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public static <T> ResultVO<T> buildResult(ResultEnum resultEnum, CodeEnum codeEnum, T t) {
        ResultVO<T> vo = new ResultVO();
        vo.setResult(resultEnum.getStat());
        vo.setCode(codeEnum.getCode());
        vo.setMsg(codeEnum.getInfo());
        vo.setData(t);
        return vo;
    }

    public static <T> ResultVO<T> buildResult(ResultEnum resultEnum, CodeEnum codeEnum, String info, T t) {
        ResultVO<T> vo = buildResult(resultEnum, codeEnum, t);
        vo.setMsg(String.format("%s[%s]", vo.getMsg(), info));
        return vo;
    }

    public static ResultVO<Exception> buildExceptionResult(Exception e) {
        return buildExceptionResult(ResultEnum.INPROCESS, CodeEnum.EXCEPTION, e);
    }

    public static ResultVO<Exception> buildExceptionResult(ResultEnum resultEnum, CodeEnum codeEnum, Exception e) {
        ResultVO<Exception> vo = new ResultVO();
        vo.setResult(resultEnum.getStat());
        vo.setCode(codeEnum.getCode());
        vo.setMsg(String.format("%s[%s]", codeEnum.getInfo(), e.getMessage()));
        vo.setData(null);
        return vo;
    }

}
