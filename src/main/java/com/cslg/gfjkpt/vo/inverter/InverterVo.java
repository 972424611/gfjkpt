package com.cslg.gfjkpt.vo.inverter;
import java.util.Date;
import java.util.List;

public class InverterVo {

    public class Inverter {

        private int id;

        private Date times;

        private double dailyOutput;

        private double totalOutput;

        private double aPhaseCurrent;

        private double aPhaseVoltage;

        private double bPhaseCurrent;

        private double bPhaseVoltage;

        private double cPhaseCurrent;

        private double cPhaseVoltage;

        private double totalActivePower;

        private double tansTemp1;

        private double tansTemp2;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getTimes() {
            return times;
        }

        public void setTimes(Date times) {
            this.times = times;
        }

        public double getDailyOutput() {
            return dailyOutput;
        }

        public void setDailyOutput(double dailyOutput) {
            this.dailyOutput = dailyOutput;
        }

        public double getTotalOutput() {
            return totalOutput;
        }

        public void setTotalOutput(double totalOutput) {
            this.totalOutput = totalOutput;
        }

        public double getaPhaseCurrent() {
            return aPhaseCurrent;
        }

        public void setaPhaseCurrent(double aPhaseCurrent) {
            this.aPhaseCurrent = aPhaseCurrent;
        }

        public double getaPhaseVoltage() {
            return aPhaseVoltage;
        }

        public void setaPhaseVoltage(double aPhaseVoltage) {
            this.aPhaseVoltage = aPhaseVoltage;
        }

        public double getbPhaseCurrent() {
            return bPhaseCurrent;
        }

        public void setbPhaseCurrent(double bPhaseCurrent) {
            this.bPhaseCurrent = bPhaseCurrent;
        }

        public double getbPhaseVoltage() {
            return bPhaseVoltage;
        }

        public void setbPhaseVoltage(double bPhaseVoltage) {
            this.bPhaseVoltage = bPhaseVoltage;
        }

        public double getcPhaseCurrent() {
            return cPhaseCurrent;
        }

        public void setcPhaseCurrent(double cPhaseCurrent) {
            this.cPhaseCurrent = cPhaseCurrent;
        }

        public double getcPhaseVoltage() {
            return cPhaseVoltage;
        }

        public void setcPhaseVoltage(double cPhaseVoltage) {
            this.cPhaseVoltage = cPhaseVoltage;
        }

        public double getTotalActivePower() {
            return totalActivePower;
        }

        public void setTotalActivePower(double totalActivePower) {
            this.totalActivePower = totalActivePower;
        }

        public double getTansTemp1() {
            return tansTemp1;
        }

        public void setTansTemp1(double tansTemp1) {
            this.tansTemp1 = tansTemp1;
        }

        public double getTansTemp2() {
            return tansTemp2;
        }

        public void setTansTemp2(double tansTemp2) {
            this.tansTemp2 = tansTemp2;
        }
    }

    private int sum;

    private List<Inverter> inverterList;

    public List<Inverter> getInverterList() {
        return inverterList;
    }

    public void setInverterList(List<Inverter> inverterList) {
        this.inverterList = inverterList;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
