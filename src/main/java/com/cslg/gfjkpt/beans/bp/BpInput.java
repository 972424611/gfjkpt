package com.cslg.gfjkpt.beans.bp;

public class BpInput {

    private double activePower;

    private double temp;

    private double irradiation;

    private double preTemp;

    private double preIrradiation;

    public double getActivePower() {
        return activePower;
    }

    public void setActivePower(double activePower) {
        this.activePower = activePower;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getIrradiation() {
        return irradiation;
    }

    public void setIrradiation(double irradiation) {
        this.irradiation = irradiation;
    }

    public double getPreTemp() {
        return preTemp;
    }

    public void setPreTemp(double preTemp) {
        this.preTemp = preTemp;
    }

    public double getPreIrradiation() {
        return preIrradiation;
    }

    public void setPreIrradiation(double preIrradiation) {
        this.preIrradiation = preIrradiation;
    }

    @Override
    public String toString() {
        return "BpInput{" +
                "activePower=" + activePower +
                ", temp=" + temp +
                ", irradiation=" + irradiation +
                ", preTeamp=" + preTemp +
                ", preIrradiation=" + preIrradiation +
                '}';
    }
}