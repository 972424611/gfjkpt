package com.cslg.gfjkpt.common;

import com.cslg.gfjkpt.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ResultJson {

    private boolean ret;

    private String msg;

    private Object data;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultJson(boolean ret) {
        this.ret = ret;
    }

    public ResultJson() {

    }

    public static ResultJson success(Object object, String msg) {
        ResultJson resultJson = new ResultJson(true);
        resultJson.data = object;
        resultJson.msg = msg;
        return resultJson;
    }

    public static ResultJson success(Object object) {
        ResultJson resultJson = new ResultJson(true);
        resultJson.data = object;
        return resultJson;
    }

    public static ResultJson success() {
        return new ResultJson(true);
    }

    public static ResultJson fail(String msg) {
        ResultJson resultJson = new ResultJson(false);
        resultJson.msg = msg;
        return resultJson;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    public String returnJsonp(Object object, HttpServletRequest request) {
        ResultJson resultJson = ResultJson.success(object);
        String json = JsonUtils.objectToJson(resultJson);
        return request.getParameter("callback") + "(" + json + ")";
    }

    public String returnJsonp(Object object) {
        ResultJson resultJson = ResultJson.success(object);
        String json = JsonUtils.objectToJson(resultJson);
        HttpServletRequest request = RequestHolder.getCurrentRequest();
        return request.getParameter("callback") + "(" + json + ")";
    }
}
