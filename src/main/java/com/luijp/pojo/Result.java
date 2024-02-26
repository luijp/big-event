package com.luijp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <E> Result<E> success(E data) {
        return new Result<>(0, "success", data);
    }

    public static <E> Result<E> success() {
        return new Result<>(0, "success", null);
    }

    public static <E> Result<E> error(String message) {
        return new Result<>(1, message, null);
    }

}
