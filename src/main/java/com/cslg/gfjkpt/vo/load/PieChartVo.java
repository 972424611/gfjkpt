package com.cslg.gfjkpt.vo.load;

import java.util.List;

public class PieChartVo {

    private String dateType;

    private List<Vo> voList;

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public List<Vo> getVoList() {
        return voList;
    }

    public void setVoList(List<Vo> voList) {
        this.voList = voList;
    }

    public class Vo {

        private double consume;

        private String local;

        public double getConsume() {
            return consume;
        }

        public void setConsume(double consume) {
            this.consume = consume;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

    }

}
