package com.cslg.gfjkpt.beans;

public class ControllableSocketParam {
    private int id;
    private Double voltage;
    private Double current;
    private Double electricPower;
    private String time;

    public ControllableSocketParam(int id, Double voltage, Double current, Double electricPower, String time) {
        this.id = id;
        this.voltage = voltage;
        this.current = current;
        this.electricPower = electricPower;
        this.time = time;
    }

    public ControllableSocketParam() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getElectricPower() {
        return electricPower;
    }

    public void setElectricPower(Double electricPower) {
        this.electricPower = electricPower;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
