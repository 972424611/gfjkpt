package com.cslg.gfjkpt.beans.inverter;

import org.hibernate.validator.constraints.NotBlank;

public class PredictParam {

    @NotBlank(message = "日期不能为空")
    private String dayDate;

    @NotBlank(message = "日期不能为空")
    private String monthDate;

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }
}
