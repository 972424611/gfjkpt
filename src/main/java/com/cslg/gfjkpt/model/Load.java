package com.cslg.gfjkpt.model;

import java.util.Date;

/**
 * @author Twilight
 */
public class Load {

    private Integer id;

    private String loadName;

    private Date times;

    private Double energyConsumption;

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

    public Double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    @Override
    public String toString() {
        return "Load{" +
                "id=" + id +
                ", loadName='" + loadName + '\'' +
                ", times=" + times +
                ", energyConsumption=" + energyConsumption +
                '}';
    }
}
