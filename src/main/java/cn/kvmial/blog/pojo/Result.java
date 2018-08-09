package cn.kvmial.blog.pojo;

import java.io.Serializable;

/**
 * TODO
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/9 0009 上午 10:36
 */


public class Result<T> implements Serializable {
    /**
     * 服务器响应数据
     */
    private T data;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String msg;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(boolean success, String msg,T data) {
        this.data = data;
        this.success = success;
        this.msg = msg;
    }

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success,String msg) {
        this.msg = msg;
    }

    public static Result ok() {
        return new Result(true,"请求成功");
    }

    public static <T> Result ok(T data) {
        return new Result(true,"请求成功",data);
    }

    public static Result fail() {
        return new Result(false,"请求失败");
    }

    public static Result fail(String msg) {
        return new Result(false,msg);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
}
