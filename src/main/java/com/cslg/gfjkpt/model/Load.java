package com.cslg.gfjkpt.model;

import java.util.Date;

/**
 * @author Twilight
 */
public class Load {

    private Integer id;

    private String loadName;

    private Date times;

    private String local;

    private Double current;

    private Double voltage;

    private Double apparentPower;

    private Double activePower;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoadName() {
        return loadName;
    }

    public void setLoadName(String loadName) {
        this.loadName = loadName;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getApparentPower() {
        return apparentPower;
    }

    public void setApparentPower(Double apparentPower) {
        this.apparentPower = apparentPower;
    }

    public Double getActivePower() {
        return activePower;
    }

    public void setActivePower(Double activePower) {
        this.activePower = activePower;
    }
}
