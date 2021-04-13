package com.melo.notes.service.inter;

import com.melo.notes.service.Result;

/**
 * @author Jun
 * @program Note
 * @description 注册页面相关逻辑接口
 * @date 2021-4-3 14:30
 */
public interface RegisterService {

    /**
     * 判断输入是否有效
     *
     * @param userName 用户名
     * @param firstPass 第一次密码
     * @param secondPass 第二次密码
     * @return Result 返回结果集封装类
     */
    Result isValid(String userName, String firstPass, String secondPass);

    /**
     * 注册用户后新增用户到数据库中
     * @param userName 用户名
     * @param password 密码
     * @return boolean 是否增加成功
     */
    boolean addUser(String userName,String password);
}
