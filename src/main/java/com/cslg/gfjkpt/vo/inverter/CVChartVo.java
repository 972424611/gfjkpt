package com.cslg.gfjkpt.vo.inverter;

import java.util.List;

/**
 * @author Twilight
 * @date 18-9-16 下午7:50
 */
public class CVChartVo {

    private List<Current> currentList;

    private List<Voltage> voltageList;

    public List<Current> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<Current> currentList) {
        this.currentList = currentList;
    }

    public List<Voltage> getVoltageList() {
        return voltageList;
    }

    public void setVoltageList(List<Voltage> voltageList) {
        this.voltageList = voltageList;
    }

    public class Current {

        private double aPhaseCurrent;

        private double bPhaseCurrent;

        private double cPhaseCurrent;

        private String times;

        public double getaPhaseCurrent() {
            return aPhaseCurrent;
        }

        public void setaPhaseCurrent(double aPhaseCurrent) {
            this.aPhaseCurrent = aPhaseCurrent;
        }

        public double getbPhaseCurrent() {
            return bPhaseCurrent;
        }

        public void setbPhaseCurrent(double bPhaseCurrent) {
            this.bPhaseCurrent = bPhaseCurrent;
        }

        public double getcPhaseCurrent() {
            return cPhaseCurrent;
        }

        public void setcPhaseCurrent(double cPhaseCurrent) {
            this.cPhaseCurrent = cPhaseCurrent;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }
    }

    public class Voltage {

        private double aPhaseVoltage;

        private double bPhaseVoltage;

        private double cPhaseVoltage;

        private String times;

        public double getaPhaseVoltage() {
            return aPhaseVoltage;
        }

        public void setaPhaseVoltage(double aPhaseVoltage) {
            this.aPhaseVoltage = aPhaseVoltage;
        }

        public double getbPhaseVoltage() {
            return bPhaseVoltage;
        }

        public void setbPhaseVoltage(double bPhaseVoltage) {
            this.bPhaseVoltage = bPhaseVoltage;
        }

        public double getcPhaseVoltage() {
            return cPhaseVoltage;
        }

        public void setcPhaseVoltage(double cPhaseVoltage) {
            this.cPhaseVoltage = cPhaseVoltage;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }
    }
}
