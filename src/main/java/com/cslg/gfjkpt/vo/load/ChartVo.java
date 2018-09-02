package com.cslg.gfjkpt.vo.load;

public class ChartVo {

    private double current;

    private double voltage;

    private double activePower;

    private double apparentPower;

    private String times;

    public double getApparentPower() {
        return apparentPower;
    }

    public void setApparentPower(double apparentPower) {
        this.apparentPower = apparentPower;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }
}
