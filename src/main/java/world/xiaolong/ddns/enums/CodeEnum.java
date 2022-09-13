package world.xiaolong.ddns.enums;

public enum CodeEnum {

    SUCCESS(200, "成功"),
    FOUND(302, "已存在该资源"),
    NOT_FOUND(404, "未找到该资源"),
    EXCEPTION(500, "服务器异常"),
    NOT_IMPL(501, "不支持该操作");

    private int code;
    private String info;

    CodeEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

}
