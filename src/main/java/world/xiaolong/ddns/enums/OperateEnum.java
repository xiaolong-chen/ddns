package world.xiaolong.ddns.enums;

import java.util.Arrays;

public enum OperateEnum {

    ADD(1, "新增"),
    UPDATE(0, "修改"),
    DELETE(-1, "删除"),
    UNKNOW(-2, "未知");

    private int operate;
    private String info;

    OperateEnum(int operate, String info) {
        this.operate = operate;
        this.info = info;
    }

    public int getOperate() {
        return operate;
    }

    public String getInfo() {
        return info;
    }

    public static OperateEnum get(Integer operate) {
        return Arrays.stream(OperateEnum.values()).filter(e -> e.operate == operate).findFirst().orElse(UNKNOW);
    }

}
