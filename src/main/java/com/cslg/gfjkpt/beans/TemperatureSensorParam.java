package com.cslg.gfjkpt.beans;

public class TemperatureSensorParam {
    private int id;
    private Double temperature;
    private Double humidity;
    private String time;

    public TemperatureSensorParam(int id, Double temperature, Double humidity, String time) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.time = time;
    }

    public TemperatureSensorParam() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
