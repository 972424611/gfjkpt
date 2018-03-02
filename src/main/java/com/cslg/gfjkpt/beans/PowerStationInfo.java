package com.cslg.gfjkpt.beans;

import java.util.Date;

public class PowerStationInfo {

    private Double dailyOutput;

    private Double totalOutput;

    private Date updateTimes;

    private String title;

    public Double getDailyOutput() {
        return dailyOutput;
    }

    public void setDailyOutput(Double dailyOutput) {
        this.dailyOutput = dailyOutput;
    }

    public Double getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(Double totalOutput) {
        this.totalOutput = totalOutput;
    }

    public Date getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(Date updateTimes) {
        this.updateTimes = updateTimes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
