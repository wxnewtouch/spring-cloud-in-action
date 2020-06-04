package com.wally.common.dto;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class RespDTO<T> implements Serializable {
    public int code = 0;
    public String error = "";
    public T data;

    public static RespDTO onSuc(Object data) {
        RespDTO respDTO = new RespDTO();
        respDTO.data = data;
        return respDTO;
    }


}
