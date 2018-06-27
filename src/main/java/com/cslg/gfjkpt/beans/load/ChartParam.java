package com.cslg.gfjkpt.beans.load;

import org.hibernate.validator.constraints.NotBlank;

public class ChartParam {

    @NotBlank(message = "日期不能为空")
    private String date;

    @NotBlank(message = "日期类型不能为空")
    private String type;

    @NotBlank(message = "位置名称不能为空")
    private String local;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
