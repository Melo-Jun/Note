package com.melo.notes.service.constant;

import com.melo.notes.view.LoginView;

/**
 * @author Jun
 * @program Note
 * @description 状态类枚举量
 * @date 2021-4-10 22:25
 */
public enum Status {

    /*
     **************************************************************
     *              通用
     **************************************************************
     */

    /**
     * 操作成功
     */
    SUCCESS("操作成功"),
    /**
     * 操作失败
     */
    FAILED("操作失败"),
    /**
     * 非法空格符
     */
    SPACE(" "),


    USER_ID(LoginView.USER.getId()),


    /*
     **************************************************************
     *              登录界面
     **************************************************************
     */


    /**
     * 登录成功
     */
    LOGIN_SUCCESS("登录成功"),
    /**
     * 用户名不合格
     */
    NOT_USERNAME("用户名不合格"),
    /**
     * 密码不合格
     */
    NOT_PASSWORD("密码不合格"),
    /**
     * 欢迎管理员
     */
    WELCOME_ADMIN("欢迎管理员"),
    /**
     * 不是管理员
     */
    NOT_ADMIN("想啥呢宝贝"),
    /**
     * 不是有效用户
     */
    NOT_VALID_USER("不是有效用户"),
    /**
     * 用户或密码有误
     */



    /*
     **************************************************************
     *              注册和修改个人信息界面
     **************************************************************
     */
    ERROR_PASS("用户或密码有误"),
    /**
     * 请输入两次密码
     */
    AGAIN_PASS("请输入两次密码"),
    /**
     * 该用户名已存在
     */
    EXIST_USERNAME("该用户名已存在"),
    /**
     * 两次输入不一致
     */
    NOT_SAME_PASS("两次输入不一致"),
    /**
     * 修改成功,需要手动重启
     */
    REOPEN("修改成功,需要手动重启"),
    /**
     * 原密码输入有误
     */
    WRONG_OLD_PASS("原密码输入有误");

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
