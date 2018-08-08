package com.cslg.gfjkpt.vo;

import java.util.Date;

public class HomePageVo {

    private String name;

    private String scale;

    private double currentPower;

    private double dailyOutput;

    private double totalOutput;

    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public double getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(double currentPower) {
        this.currentPower = currentPower;
    }

    public double getDailyOutput() {
        return dailyOutput;
    }

    public void setDailyOutput(double dailyOutput) {
        this.dailyOutput = dailyOutput;
    }

    public double getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(double totalOutput) {
        this.totalOutput = totalOutput;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
