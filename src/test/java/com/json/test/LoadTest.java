//package com.json.test;
//
//import com.cslg.gfjkpt.mapper.InverterMapper;
//import com.cslg.gfjkpt.mapper.LoadMapper;
//import com.cslg.gfjkpt.model.Inverter;
//import com.cslg.gfjkpt.model.Load;
//import com.cslg.gfjkpt.service.InverterService;
//import com.cslg.gfjkpt.service.LoadService;
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
//import java.util.Random;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
//public class LoadTest {
//
//
//    @Autowired
//    private LoadService loadService;
//
//    @Autowired
//    private LoadMapper loadMapper;
//
//    @Autowired
//    private InverterMapper inverterMapper;
//
//    @Test
//    public void test() {
//        //System.out.println(Integer.parseInt("01"));
//        Random random = new Random();
//        for(int i = 0; i < 16; i++) {
//            for(int j = 6; j <= 18; j++) {
//                Load load = new Load();
//                double s = (random.nextDouble() + 2) * 100.0;
//                load.setEnergyConsumption(s);
//                load.setLoadName("root_load_1");
//                Date date = new Date();
//                //Calendar calendar = Calendar.getInstance();
//                //calendar.setTime(date);
//                //后退一个月
//                //calendar.add(Calendar.MONTH, -3);
//                //date = calendar.getTime();
//                date.setDate(i + 1);
//                date.setHours(j);
//                load.setTimes(date);
//                System.out.println(load.toString());
//                loadMapper.insertLoad(load);
//            }
//        }
//    }
//
//    @Test
//    public void test2() {
//        Random random = new Random();
//        long startTime = 1519833600; //2018-1-1 0:0:0
//        long endTime = 1522339200; //2018/1/31 23:59:59
//        for(long i = startTime; i <= endTime;) {
//            Load load = new Load();
//            double s = (random.nextDouble() + 2) * 100.0;
//            String str = String.valueOf(s);
//            str = str.substring(0, 5);
//            load.setEnergyConsumption(Double.parseDouble(str));
//            load.setLoadName("root_load_1");
//            load.setTimes(new Date(i * 1000));
//            System.out.println(new Date(i * 1000));
//            loadMapper.insertLoad(load);
//            System.out.println(load.toString());
//            i = i + 900;
//        }
//    }
//
//    @Test
//    public void testInverter() {
//        Random random = new Random();
//        long startTime = 1520179200; //2018-1-1 0:0:0
//        long endTime = 1520265600; //2018/1/31 23:59:59
//        for(long i = startTime; i <= endTime;) {
//            double s = (random.nextDouble() + 2) * 1000.0;
//            String str = String.valueOf(s);
//            str = str.substring(0, 5);
//
//            Inverter inverter = new Inverter();
//            inverter.setInverterName("root_inverter_1");
//            inverter.setTimes(new Date(i * 1000));
//            inverter.setDailyOutput(Double.parseDouble(str));
//            inverter.setTotalOutput(Double.parseDouble(str) / 100);
//
//            inverter.setaPhaseVoltage(Double.parseDouble(str) / 10);
//            inverter.setaPhaseCurrent(random.nextDouble());
//            inverter.setbPhaseVoltage(Double.parseDouble(str) / 10);
//            inverter.setbPhaseCurrent(random.nextDouble());
//            inverter.setcPhaseVoltage(Double.parseDouble(str) / 10);
//            inverter.setcPhaseCurrent(random.nextDouble());
//
//            inverter.setTotalActivePower(Double.parseDouble(str));
//            //inverterService.saveInverterData(inverter);
//            inverterMapper.insertInverter(inverter);
//            System.out.println(inverter.toString());
//            inverter.setInverterName("root_inverter_2");
//            inverterMapper.insertInverter(inverter);
//            //inverterService.saveInverterData(inverter);
//            System.out.println(inverter.toString());
//            i = i + 900;
//        }
//    }
//}
