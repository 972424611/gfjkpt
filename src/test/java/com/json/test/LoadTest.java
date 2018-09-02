//package com.json.test;
//
//import com.cslg.gfjkpt.beans.inverter.ChartParam;
//import com.cslg.gfjkpt.common.BpDeep;
//import com.cslg.gfjkpt.mapper.InverterMapper;
//import com.cslg.gfjkpt.mapper.LoadMapper;
//import com.cslg.gfjkpt.model.Inverter;
//import com.cslg.gfjkpt.model.Load;
//import com.cslg.gfjkpt.service.InverterService;
//import com.cslg.gfjkpt.service.LoadService;
//import com.cslg.gfjkpt.vo.inverter.ChartVo;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.text.ParsePosition;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
//public class LoadTest {
//
//    @Autowired
//    private InverterService inverterService;
//
//    @Test
//    public void test() {
//        ChartParam chartParam = new ChartParam();
//        chartParam.setType("day");
//        chartParam.setDate("2018-07-10");
//        chartParam.setField("总有功功率");
//        chartParam.setName("inverter1");
//
//
//        List<ChartVo> chartVoList = inverterService.getInverterChart(chartParam);
//        //chartVoList.forEach(System.out::println);
//
//        BpDeep bpDeep = new BpDeep();
//        bpDeep.test();
//    }
//}
