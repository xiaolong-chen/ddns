package world.xiaolong.ddns.enums;

public enum ResultEnum {

    SUCCESS(1, "成功"),
    INPROCESS(0, "处理中"),
    FAIL(-1, "失败");

    private int stat;
    private String info;

    ResultEnum(int stat, String info) {
        this.stat = stat;
        this.info = info;
    }

    public int getStat() {
        return stat;
    }

    public String getInfo() {
        return info;
    }

}
