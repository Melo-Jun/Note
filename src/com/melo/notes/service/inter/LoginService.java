package com.melo.notes.service.inter;

import com.melo.notes.entity.User;
import com.melo.notes.service.Result;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关逻辑接口
 * @date 2021-4-1 18:26
 */
public interface LoginService {

    /**
     * 设置Id
     *
     * @param user 用户实体类
     */
     void setId(User user);

    /**
     * 判断登录是否成功
     * @param userName 用户名
     * @param password 密码
     * @param access 身份
     * @return String 呈现给页面的信息
     */
     Result login(String userName, String password, String access);

    /**
     * 判断该用户是否为有效用户
     * @param userName 用户名
     * @return boolean 是否有效
     */
    public boolean isValidUser(String userName);
}
