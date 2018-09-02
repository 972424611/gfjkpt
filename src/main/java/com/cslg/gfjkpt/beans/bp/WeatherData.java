package com.cslg.gfjkpt.beans.bp;

public class WeatherData {

    private Node data;

    public Node getData() {
        return data;
    }

    public void setData(Node data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WeatherDataUtil{" +
                "data=" + data +
                '}';
    }
}
