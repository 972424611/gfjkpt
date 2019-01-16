package com.cslg.gfjkpt.model;



import java.sql.Timestamp;

public class ControllableSocketInfo {
    private int id;
    private Double voltage;
    private Double current;
    private Integer electricPower;
    private Timestamp time;

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

    public Integer getElectricPower() {
        return electricPower;
    }

    public void setElectricPower(Integer electricPower) {
        this.electricPower = electricPower;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
