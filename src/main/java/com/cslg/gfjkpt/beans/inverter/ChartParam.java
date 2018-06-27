package com.cslg.gfjkpt.beans.inverter;

import org.hibernate.validator.constraints.NotBlank;

public class ChartParam {

    @NotBlank(message = "日期不能为空")
    private String date;

    @NotBlank(message = "日期类型不能为空")
    private String type;

    @NotBlank(message = "逆变器名称不能为空")
    private String name;

    /** 字段类型 例如日发电量、总发电量等 */
    private String field;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
