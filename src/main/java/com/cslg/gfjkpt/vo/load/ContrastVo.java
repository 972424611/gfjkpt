package com.cslg.gfjkpt.vo.load;

/**
 * @author Twilight
 * @date 18-9-16 下午8:42
 * 发电和用电对比pojo
 */
public class ContrastVo {

    private double powerGeneration;

    private double powerConsumption;

    private String times;


    public double getPowerGeneration() {
        return powerGeneration;
    }

    public void setPowerGeneration(double powerGeneration) {
        this.powerGeneration = powerGeneration;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
