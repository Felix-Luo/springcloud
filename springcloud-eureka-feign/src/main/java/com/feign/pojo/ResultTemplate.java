package com.feign.pojo;

/**
* @Description:    结果返回类类作用描述
* @Author:         luoyhong
* @CreateDate:     2019/6/19 14:53
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/6/19 14:53
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ResultTemplate {

    private int code;//状态码
    private String message;//描述信息
    private Object data;//返回的具体结果
    private String token;//token值

    public ResultTemplate(){

    }

    public ResultTemplate(int vCode, String vMessage, Object vData, String vToken){
        this.code = vCode;
        this.message = vMessage;
        this.data = vData;
        this.token = vToken;
    }

    public ResultTemplate(int vCode, String vMessage){
        this.code = vCode;
        this.message = vMessage;
        this.data = null;
        this.token = "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
