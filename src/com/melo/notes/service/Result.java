package com.melo.notes.service;

import com.melo.notes.service.constant.Status;
import com.melo.notes.service.constant.TypeName;

/**
 * 返回状态信息和数据信息
 */
public class Result {
    /**
     * 服务状态枚举常量
     */
    private Status status;
    /**
     * 对应的类型
     */
    private TypeName type;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TypeName getType() {
        return type;
    }

    public void setType(TypeName type) {
        this.type = type;
    }
}
