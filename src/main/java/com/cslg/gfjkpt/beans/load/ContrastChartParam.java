package com.cslg.gfjkpt.beans.load;

import org.hibernate.validator.constraints.NotBlank;

public class ContrastChartParam {

    @NotBlank(message = "日期不能为空")
    private String date;

    @NotBlank(message = "日期类型不能为空")
    private String type;

    /** 工一-工二-综教 */
    private String locals;

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

    public String getLocals() {
        return locals;
    }

    public void setLocals(String locals) {
        this.locals = locals;
    }
}
