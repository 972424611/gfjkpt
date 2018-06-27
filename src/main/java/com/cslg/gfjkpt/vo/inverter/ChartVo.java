package com.cslg.gfjkpt.vo.inverter;

public class ChartVo {

    private double field;

    private double tansTemp1;

    private double tansTemp2;

    private String times;

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

    public double getField() {
        return field;
    }

    public void setField(double field) {
        this.field = field;
    }
}
