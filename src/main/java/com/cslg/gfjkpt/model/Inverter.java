package com.cslg.gfjkpt.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Twilight
 */
public class Inverter {
    private Integer id;

    @JsonProperty("inverter_name")
    private String inverterName;

    @JsonProperty("times")
    private Date times;

    @JsonProperty("daily_output")
    private Double dailyOutput;

    @JsonProperty("total_output")
    private Double totalOutput;

    @JsonProperty("a_phase_current")
    private Double aPhaseCurrent;

    @JsonProperty("a_phase_voltage")
    private Double aPhaseVoltage;

    @JsonProperty("b_phase_current")
    private Double bPhaseCurrent;

    @JsonProperty("b_phase_voltage")
    private Double bPhaseVoltage;

    @JsonProperty("c_phase_current")
    private Double cPhaseCurrent;

    @JsonProperty("c_phase_voltage")
    private Double cPhaseVoltage;

    @JsonProperty("total_active_power")
    private Double totalActivePower;

    @JsonProperty("tans_temp")
    private Double tansTemp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInverterName() {
        return inverterName;
    }

    public void setInverterName(String inverterName) {
        this.inverterName = inverterName;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

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

    public Double getaPhaseCurrent() {
        return aPhaseCurrent;
    }

    public void setaPhaseCurrent(Double aPhaseCurrent) {
        this.aPhaseCurrent = aPhaseCurrent;
    }

    public Double getaPhaseVoltage() {
        return aPhaseVoltage;
    }

    public void setaPhaseVoltage(Double aPhaseVoltage) {
        this.aPhaseVoltage = aPhaseVoltage;
    }

    public Double getbPhaseCurrent() {
        return bPhaseCurrent;
    }

    public void setbPhaseCurrent(Double bPhaseCurrent) {
        this.bPhaseCurrent = bPhaseCurrent;
    }

    public Double getbPhaseVoltage() {
        return bPhaseVoltage;
    }

    public void setbPhaseVoltage(Double bPhaseVoltage) {
        this.bPhaseVoltage = bPhaseVoltage;
    }

    public Double getcPhaseCurrent() {
        return cPhaseCurrent;
    }

    public void setcPhaseCurrent(Double cPhaseCurrent) {
        this.cPhaseCurrent = cPhaseCurrent;
    }

    public Double getcPhaseVoltage() {
        return cPhaseVoltage;
    }

    public void setcPhaseVoltage(Double cPhaseVoltage) {
        this.cPhaseVoltage = cPhaseVoltage;
    }

    public Double getTotalActivePower() {
        return totalActivePower;
    }

    public void setTotalActivePower(Double totalActivePower) {
        this.totalActivePower = totalActivePower;
    }

    public Double getTansTemp() {
        return tansTemp;
    }

    public void setTansTemp(Double tansTemp) {
        this.tansTemp = tansTemp;
    }

    @Override
    public String toString() {
        return "Inverter{" +
                "id=" + id +
                ", inverterName='" + inverterName + '\'' +
                ", times=" + times +
                ", dailyOutput=" + dailyOutput +
                ", totalOutput=" + totalOutput +
                ", aPhaseCurrent=" + aPhaseCurrent +
                ", aPhaseVoltage=" + aPhaseVoltage +
                ", bPhaseCurrent=" + bPhaseCurrent +
                ", bPhaseVoltage=" + bPhaseVoltage +
                ", cPhaseCurrent=" + cPhaseCurrent +
                ", cPhaseVoltage=" + cPhaseVoltage +
                ", totalActivePower=" + totalActivePower +
                ", tansTemp=" + tansTemp +
                '}';
    }
}