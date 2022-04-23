package com.codetype.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回值的封装
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    private String msg;
    private int code;
    private T data;

    public ResultVo(T data) {
        this.data = data;
    }

    public ResultVo(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
