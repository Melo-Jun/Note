package com.melo.notes.service;

import com.melo.notes.service.constant.Status;
import com.melo.notes.service.constant.TypeName;

/**
 * ����״̬��Ϣ��������Ϣ
 */
public class Result {
    /**
     * ����״̬ö�ٳ���
     */
    private Status status;
    /**
     * ��Ӧ������
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
