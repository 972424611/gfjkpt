package com.cslg.gfjkpt.utils;

import com.cslg.gfjkpt.beans.bp.WeatherData;

import java.util.HashMap;
import java.util.Map;

public class WeatherDataUtil {

    /** 长沙经度 */
    private static final String CS_LONG = "112.578";

    /** 长沙纬度 */
    private static final String CS_LAT = "28.117";

    /** 阿里云气象数据调用Api token */
    private static final String TOKEN = "5xpFbefMyJ";

    /** 太阳辐射实况代号 */
    private static final String RADIATION = "CLDAS-SSRA";

    /** 太阳辐射预报代号 */
    private static final String RADIATION_PREDICT = "GFS-DSWRF-F";

    /** 气温实况 */
    private static final String TEMP = "CLDAS-TEMP";

    /** 气温预报 */
    private static final String PREDICT_TEMP = "CMPA-TEMP-F";

    private static final String url = "http://nature.data.aliyun.com/api";

    private static Map<String, String> map = new HashMap<>(5);

    static {
        map.put("token", TOKEN);
        map.put("action", "data_value_by_names");
        map.put("geometry", CS_LONG.concat(",").concat(CS_LAT));
    }

    public static WeatherData getIrrAndTemp(String date) {
        map.put("name", RADIATION.concat(",").concat(TEMP));
        map.put("date", date);
        String json = HttpClientUtils.doGet(url, map);
        return JsonUtils.jsonToPojo(json, WeatherData.class);
    }

    public static WeatherData getPredictIrr(String date) {
        Integer time = Integer.valueOf(date.split("_")[1].substring(0, 2));
        if(time >= 0 && time <= 6) {
            date = date.split("_")[0].concat("_020000");
        } else if(time > 6 && time <= 12) {
            date = date.split("_")[0].concat("_080000");
        } else if(time > 12 && time <= 18) {
            date = date.split("_")[0].concat("_140000");
        } else if(time > 18 && time <= 24) {
            date = date.split("_")[0].concat("_200000");
        }
        map.put("name", RADIATION_PREDICT);
        map.put("date", date);
        String json = HttpClientUtils.doGet(url, map);
        return JsonUtils.jsonToPojo(json, WeatherData.class);
    }

    public static WeatherData getPredictTemp(String date) {
        Integer time = Integer.valueOf(date.split("_")[1].substring(0, 2));
        if(time <= 14) {
            date = date.split("_")[0].concat("_080000");
        } else {
            date = date.split("_")[0].concat("_200000");
        }
        map.put("name", PREDICT_TEMP);
        map.put("date", date);
        String json = HttpClientUtils.doGet(url, map);
        return JsonUtils.jsonToPojo(json, WeatherData.class);
    }

    public static void main(String[] args) {
        System.out.println(getIrrAndTemp("20180714_080000"));
        System.out.println(getPredictIrr("20180714_080000"));
        //System.out.println(getPredictIrrAndTEMP("20180716_200000"));
    }
}

