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
     FOLDER_TYPE(2),
    /**
     * �ʼǷ�������
     */
    GROUP("�ʼǷ���"),
    GROUP_TYPE(3),
    /**
     * �ʼ�����
     */
    NOTE("�ʼ�"),
    NOTE_TYPE(4),
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
    private int treePath;

    TypeName(String message){
        this.setMessage(message);
    }
    TypeName(int treePath){ this.setTreePath(treePath);}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTreePath() {
        return treePath;
    }

    public void setTreePath(int treePath) {
        this.treePath = treePath;
    }
}
