package com.base.model;

import java.util.Map;

public class AjaxJson {
    private boolean success = true;//是否成功
    private String msg = "操作成功";//提示信息
    private Object obj = null;//其他信息
    private Map<String,Object> attr;//其他信息


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Map<String, Object> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, Object> attr) {
        this.attr = attr;
    }
}
