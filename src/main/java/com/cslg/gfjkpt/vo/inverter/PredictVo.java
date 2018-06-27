package com.cslg.gfjkpt.vo.inverter;

import java.util.List;

public class PredictVo {

    private List<Vo> voDayList;

    private List<Vo> voMonthList;

    public class Vo {

        private double currentOutput;

        private double predictOutput;

        private double currentIncome;

        private double predictIncome;

        private String times;

        public double getCurrentOutput() {
            return currentOutput;
        }

        public void setCurrentOutput(double currentOutput) {
            this.currentOutput = currentOutput;
        }

        public double getPredictOutput() {
            return predictOutput;
        }

        public void setPredictOutput(double predictOutput) {
            this.predictOutput = predictOutput;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public double getCurrentIncome() {
            return currentIncome;
        }

        public void setCurrentIncome(double currentIncome) {
            this.currentIncome = currentIncome;
        }

        public double getPredictIncome() {
            return predictIncome;
        }

        public void setPredictIncome(double predictIncome) {
            this.predictIncome = predictIncome;
        }
    }

    public List<Vo> getVoDayList() {
        return voDayList;
    }

    public void setVoDayList(List<Vo> voDayList) {
        this.voDayList = voDayList;
    }

    public List<Vo> getVoMonthList() {
        return voMonthList;
    }

    public void setVoMonthList(List<Vo> voMonthList) {
        this.voMonthList = voMonthList;
    }
}
