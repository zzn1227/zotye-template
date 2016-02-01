package com.system.base;

public class ReturnModel {

    // 调用是否成功
    private boolean success  = true;
    // 返回结果
    private Object  data     = null;
    // 错误描述
    private String  errorMsg = null;
    // 正常信息
    private String  message  = null;

    public ReturnModel() {
        super();
    }

    public ReturnModel(String errorMsg) {
        super();
        this.success = false;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorMsg(String errorMsg) {
        this.success = false;
        this.errorMsg = errorMsg;
    }

}
