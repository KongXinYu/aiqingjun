package com.aiqingjun.framework.result;

import lombok.*;

/**
 * @program: aiqingjun
 * @description: Json返回结果
 * @author: WuZhenYu
 * @create: 2019-07
 */
@Setter
@Getter
@ToString
public class Result {

    private int status;

    private String msg;

    private Object data;

    public Result() {
        super();
    }

    public Result(int status, String msg, Object data) {
        super();
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
