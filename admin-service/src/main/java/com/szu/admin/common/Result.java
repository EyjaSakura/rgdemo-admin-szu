package com.szu.admin.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code; // 0 = success
    private String msg;
    private T data;

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.code = 0;
        r.data = data;
        r.msg = "success";
        return r;
    }

    public static Result<?> ok() {
        return ok(null);
    }

    public static Result<?> fail(String msg) {
        Result<?> r = new Result<>();
        r.code = -1;
        r.msg = msg;
        return r;
    }
}