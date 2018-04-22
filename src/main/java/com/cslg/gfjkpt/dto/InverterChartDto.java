package com.cslg.gfjkpt.dto;

public class InverterChartDto {

    private double totalActivePower;

    private double tansTemp1;

    private double tansTemp2;

    private String times;

    public double getTotalActivePower() {
        return totalActivePower;
    }

    public void setTotalActivePower(double totalActivePower) {
        this.totalActivePower = totalActivePower;
    }

    public double getTansTemp1() {
        return tansTemp1;
    }

    public void setTansTemp1(double tansTemp1) {
        this.tansTemp1 = tansTemp1;
    }

    public double getTansTemp2() {
        return tansTemp2;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public void setTansTemp2(double tansTemp2) {
        this.tansTemp2 = tansTemp2;
    }


}
