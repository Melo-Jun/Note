package com.melo.notes.service.inter;

import com.melo.notes.entity.User;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关逻辑接口
 * @date 2021-4-1 18:26
 */
public interface LoginService {

    /**
     * 设置id
     * @param user
     */
    void setId(User user);

    /**
     * 密码验证
     * @param user
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return
     */
     boolean login(User user);

    /**
     * 判断输入是否有效
     *
     * @param userName
     * @param password
     * @param access
     * @return String 呈现给页面的信息
     */
     String isValid(String userName,String password,String access);
}
