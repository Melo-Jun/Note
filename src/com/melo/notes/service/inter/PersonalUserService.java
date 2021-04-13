package com.melo.notes.service.inter;

import com.melo.notes.service.Result;

/**
 * @author Jun
 * @program Note
 * @description 设置个人用户信息逻辑接口
 * @date 2021-4-10 15:35
 */
public interface PersonalUserService {

    /**
     * 在父类判断有效的基础上增加条件
     * @param userName 用户名
     * @param firstPass 第一次密码
     * @param secondPass 第二次密码
     * @return Result 返回结果集封装类
     */
    Result isValid(String userName, String firstPass, String secondPass);

    /**
     * 判断密码
     * @param userName 用户名
     * @param password 密码
     * @return boolean 是否正确
     */
    boolean judgePass(String userName,String password);

    /**
     * 更新用户信息
     * @param userName 更新后的用户名
     * @param password  更新后的密码
     * @return int 影响的行数
     */
     int updateUser(String userName,String password);

}
