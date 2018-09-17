package com.cslg.gfjkpt.beans.load;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Twilight
 * @date 18-9-16 下午8:40
 * 发电和用电对比pojo
 */
public class ContrastParam {

    @NotBlank(message = "日期不能为空")
    private String date;

    @NotBlank(message = "日期类型不能为空")
    private String type;

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
}
