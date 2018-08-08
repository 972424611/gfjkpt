package com.cslg.gfjkpt.beans.bp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Node {

    @JsonProperty("data_value_by_names")
    private Data[] dataValueByNames;

    public Data[] getDataValueByNames() {
        return dataValueByNames;
    }

    public void setDataValueByNames(Data[] dataValueByNames) {
        this.dataValueByNames = dataValueByNames;
    }

    @Override
    public String toString() {
        return "Node{" +
                "dataValueByNames=" + Arrays.toString(dataValueByNames) +
                '}';
    }
}
