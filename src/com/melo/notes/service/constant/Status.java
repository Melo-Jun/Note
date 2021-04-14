package com.melo.notes.service.constant;

import com.melo.notes.view.LoginView;

/**
 * @author Jun
 * @program Note
 * @description ״̬��ö����
 * @date 2021-4-10 22:25
 */
public enum Status {

    /*
     **************************************************************
     *              ͨ��
     **************************************************************
     */

    /**
     * �����ɹ�
     */
    SUCCESS("�����ɹ�"),
    /**
     * ����ʧ��
     */
    FAILED("����ʧ��"),
    /**
     * �Ƿ��ո��
     */
    SPACE(" "),


    USER_ID(LoginView.USER.getId()),


    /*
     **************************************************************
     *              ��¼����
     **************************************************************
     */


    /**
     * ��¼�ɹ�
     */
    LOGIN_SUCCESS("��¼�ɹ�"),
    /**
     * �û������ϸ�
     */
    NOT_USERNAME("�û������ϸ�"),
    /**
     * ���벻�ϸ�
     */
    NOT_PASSWORD("���벻�ϸ�"),
    /**
     * ��ӭ����Ա
     */
    WELCOME_ADMIN("��ӭ����Ա"),
    /**
     * ���ǹ���Ա
     */
    NOT_ADMIN("��ɶ�ر���"),
    /**
     * ������Ч�û�
     */
    NOT_VALID_USER("������Ч�û�"),
    /**
     * �û�����������
     */



    /*
     **************************************************************
     *              ע����޸ĸ�����Ϣ����
     **************************************************************
     */
    ERROR_PASS("�û�����������"),
    /**
     * ��������������
     */
    AGAIN_PASS("��������������"),
    /**
     * ���û����Ѵ���
     */
    EXIST_USERNAME("���û����Ѵ���"),
    /**
     * �������벻һ��
     */
    NOT_SAME_PASS("�������벻һ��"),
    /**
     * �޸ĳɹ�,��Ҫ�ֶ�����
     */
    REOPEN("�޸ĳɹ�,��Ҫ�ֶ�����"),
    /**
     * ԭ������������
     */
    WRONG_OLD_PASS("ԭ������������");

    private String message;

    Status (String message){
        this.setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
