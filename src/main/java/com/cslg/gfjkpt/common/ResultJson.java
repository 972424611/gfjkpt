package com.cslg.gfjkpt.common;

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
}
