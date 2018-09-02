package com.cslg.gfjkpt.common;

import com.cslg.gfjkpt.beans.bp.BpInput;
import com.cslg.gfjkpt.beans.bp.Data;
import com.cslg.gfjkpt.beans.bp.WeatherData;
import com.cslg.gfjkpt.beans.inverter.ChartParam;
import com.cslg.gfjkpt.service.InverterService;
import com.cslg.gfjkpt.utils.WeatherDataUtil;
import com.cslg.gfjkpt.vo.inverter.ChartVo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BpDeep {

    /** 神经网络各层节点 */
    private double[][] layer;

    /** 神经网络各节点误差 */
    private double[][] layerErr;

    /** 各层节点权重 */
    private double[][][] layer_weight;

    /** 各层节点权重动量 */
    private double[][][] layer_weight_delta;

    /** 动量系数 */
    private double mobp;

    /** 学习系数 */
    private double rate;

    public BpDeep() {}

    public BpDeep(int[] layernum, double rate, double mobp) {
        this.mobp = mobp;
        this.rate = rate;
        layer = new double[layernum.length][];
        layerErr = new double[layernum.length][];
        layer_weight = new double[layernum.length][][];
        layer_weight_delta = new double[layernum.length][][];
        Random random = new Random();
        for(int l=0;l<layernum.length;l++) {
            layer[l]=new double[layernum[l]];
            layerErr[l]=new double[layernum[l]];
            if(l+1<layernum.length){
                layer_weight[l]=new double[layernum[l]+1][layernum[l+1]];
                layer_weight_delta[l]=new double[layernum[l]+1][layernum[l+1]];
                for(int j=0;j<layernum[l]+1;j++) {
                    for(int i=0;i<layernum[l+1];i++) {
                        //随机初始化权重
                        layer_weight[l][j][i]=random.nextDouble();
                    }
                }
            }
        }
    }

    /** 逐层向前计算输出 */
    public double[] computeOut(double[] in){
        for(int l=1;l<layer.length;l++){
            for(int j=0;j<layer[l].length;j++){
                double z=layer_weight[l-1][layer[l-1].length][j];
                for(int i=0;i<layer[l-1].length;i++){
                    layer[l-1][i]=l==1?in[i]:layer[l-1][i];
                    z+=layer_weight[l-1][i][j]*layer[l-1][i];
                }
                layer[l][j]=1/(1+Math.exp(-z));
            }
        }
        return layer[layer.length-1];
    }

    /** 逐层反向计算误差并修改权重 */
    public void updateWeight(double[] tar){
        int l=layer.length-1;
        for(int j=0;j<layerErr[l].length;j++) {
            layerErr[l][j]=layer[l][j]*(1-layer[l][j])*(tar[j]-layer[l][j]);
        }

        while(l-->0){
            for(int j=0;j<layerErr[l].length;j++){
                double z = 0.0;
                for(int i=0;i<layerErr[l+1].length;i++){
                    z=z+l>0?layerErr[l+1][i]*layer_weight[l][j][i]:0;
                    //隐含层动量调整
                    layer_weight_delta[l][j][i]= mobp*layer_weight_delta[l][j][i]+rate*layerErr[l+1][i]*layer[l][j];
                    //隐含层权重调整
                    layer_weight[l][j][i]+=layer_weight_delta[l][j][i];
                    if(j==layerErr[l].length-1){
                        //截距动量调整
                        layer_weight_delta[l][j+1][i]= mobp*layer_weight_delta[l][j+1][i]+rate*layerErr[l+1][i];
                        //截距权重调整
                        layer_weight[l][j+1][i]+=layer_weight_delta[l][j+1][i];
                    }
                }
                //记录误差
                layerErr[l][j]=z*layer[l][j]*(1-layer[l][j]);
            }
        }
    }

    public void train(double[] in, double[] tar){
        double[] out = computeOut(in);
        updateWeight(tar);
    }

    /**
     * 初始化神经网络的基本配置
     * 第一个参数是一个整型数组，表示神经网络的层数和每层节点数，
     * 比如{3,10,10,10,10,2}表示输入层是3个节点，输出层是2个节点，
     * 中间有4层隐含层，每层10个节点
     * 第二个参数是学习步长，第三个参数是动量系数
     */
    private static BpDeep bp = new BpDeep(new int[]{5,10,1}, 0.15, 0.8);

    public void test() {
        ChartParam chartParam = new ChartParam();
        chartParam.setType("day");
        chartParam.setDate("2018-07-10");
        chartParam.setField("总有功功率");
        chartParam.setName("inverter1");
        InverterService inverterService = ApplicationContextHelper.getBean(InverterService.class);
        List<ChartVo> chartVoList = inverterService.getInverterChart(chartParam);
        List<BpInput> bpInputList = new ArrayList<>();
        for(ChartVo chartVo : chartVoList) {
            Integer time = Integer.valueOf(chartVo.getTimes().split(":")[0]);
            if(time < 8 || time > 17) {
                continue;
            }
            BpInput bpInput = new BpInput();
            bpInput.setActivePower(chartVo.getField());
            String date = StringUtils.join(chartParam.getDate().split("-"), "");
            String s = chartVo.getTimes().split(":")[0];
            if (Integer.parseInt(s) < 10) {
                date = date.concat("_0").concat(s).concat("0000");
            } else {
                date = date.concat("_").concat(s).concat("0000");
            }
            System.out.println(date);
            WeatherData irrAndTEMP = WeatherDataUtil.getIrrAndTemp(date);
            Data[] dataValueByNames = irrAndTEMP.getData().getDataValueByNames();
            try {
                bpInput.setIrradiation(Double.parseDouble(dataValueByNames[0].getValue()));
                bpInput.setTemp(Double.parseDouble(dataValueByNames[1].getValue()));
            } catch (Exception e) {
                continue;
            }
            s = String.valueOf(Integer.parseInt(chartParam.getDate().split("-")[2]) - 1);
            if(Integer.parseInt(s) < 10) {
                s = "0" + s;
            }
            date = date.substring(0, 6) + s + date.substring(8);
            //getPre(bpInput, date);
            irrAndTEMP = WeatherDataUtil.getIrrAndTemp(date);
            System.out.println("pre: " + date);
            dataValueByNames = irrAndTEMP.getData().getDataValueByNames();
            try {
                bpInput.setPreIrradiation(Double.parseDouble(dataValueByNames[0].getValue()));
                bpInput.setPreTemp(Double.parseDouble(dataValueByNames[1].getValue()));
            } catch (Exception e) {
                continue;
            }
            System.out.println(bpInput);
            bpInputList.add(bpInput);
        }




        //设置样本数据，对应上面的4个二维坐标数据
        double[][] data = new double[][]{{1,2},{2,2},{1,1},{2,1}};
        //设置目标数据，对应4个坐标数据的分类
        double[][] target = new double[][]{{},{},{},{}};

        //迭代训练5000次
        for(int n=0;n<5000;n++) {
            for(int i=0;i<data.length;i++) {
                bp.train(data[i], target[i]);

            }
        }

        //根据训练结果来检验样本数据
        for(int j=0;j<data.length;j++){
            double[] result = bp.computeOut(data[j]);
            System.out.println(Arrays.toString(data[j])+":"+ Arrays.toString(result));
        }

        //根据训练结果来预测一条新数据的分类
        double[] x = new double[]{3,1};
        double[] result = bp.computeOut(x);
        System.out.println(Arrays.toString(x)+":"+Arrays.toString(result));
    }

    public BpInput getPre(BpInput bpInput, String date) {
        WeatherData predictIrr = WeatherDataUtil.getPredictIrr(date);
        Data[] dataValueByNames = predictIrr.getData().getDataValueByNames();
        bpInput.setPreIrradiation(Double.parseDouble(dataValueByNames[0].getValue()));
        WeatherData predictTemp = WeatherDataUtil.getPredictTemp(date);
        dataValueByNames = predictTemp.getData().getDataValueByNames();
        bpInput.setPreTemp(Double.parseDouble(dataValueByNames[0].getValue()));
        return bpInput;
    }


}

