package com.melo.notes.service.constant;

/**
 * @author Jun
 * @program Note
 * @description �����ж����͵�ö����
 * @date 2021-4-10 22:10
 */
public enum TypeName {

    /**
     * ֪ʶ������
     */
     FOLDER("֪ʶ��") ,
    /**
     * �ʼ�����
     */
    NOTE("�ʼ�"),
    /**
     * �ʼǷ�������
     */
    GROUP("�ʼǷ���"),
    /**
     * Ĭ��
     */
    DEFAULT("Ĭ��"),
    /**
     * ���
     */
    ADMIN("����Ա"),
    USER("�û�"),

    /**
     * �û���Ч��
     */
    VALID("��Ч"),
    NOT_VALID("��Ч"),

    /**
     * Ȩ��
     */
    PUBLIC("����"),
    PRIVATE("˽��");







    private String message;

    TypeName(String message){
        this.setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
