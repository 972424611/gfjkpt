package com.cslg.gfjkpt.beans.bp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    private String name;

    private String unit;

    private String value;

    private String date;

    @JsonProperty("time_zone")
    private String timeZone;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", value='" + value + '\'' +
                ", date='" + date + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
