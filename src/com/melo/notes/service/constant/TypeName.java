package com.melo.notes.service.constant;

/**
 * @author Jun
 * @program Note
 * @description 用于判断类型的枚举量
 * @date 2021-4-10 22:10
 */
public enum TypeName {

    /**
     * 知识库类型
     */
     FOLDER("知识库") ,
    /**
     * 笔记类型
     */
    NOTE("笔记"),
    /**
     * 笔记分组类型
     */
    GROUP("笔记分组"),
    /**
     * 默认
     */
    DEFAULT("默认"),
    /**
     * 身份
     */
    ADMIN("管理员"),
    USER("用户"),

    /**
     * 用户有效性
     */
    VALID("有效"),
    NOT_VALID("无效"),

    /**
     * 权限
     */
    PUBLIC("公开"),
    PRIVATE("私有");







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
