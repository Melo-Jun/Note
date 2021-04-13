package com.melo.notes.service.inter;

import com.melo.notes.entity.User;
import com.melo.notes.exception.DaoException;
import com.melo.notes.service.Result;

import java.util.LinkedList;


/**
 * @author Jun
 * @program Note
 * @description 通用逻辑接口
 * @date 2021-4-8 16:55
 */
public interface BaseService {
    /**
     * 验证是否已存在该用户
     *
     * @param userName 用户名
     * @return boolean 是否存在该用户
     */
     boolean isExist(String userName);

    /**
     * 判断用户名密码输入是否有效
     * @param userName 用户名
     * @param firstPass 第一次密码
     * @param secondPass 第二次密码
     * @return Result 返回结果封装类
     */
     Result isValid(String userName, String firstPass, String secondPass);

    /**
     * 密码验证
     *
     * @param userName 用户名
     * @param password 密码
     * @return boolean 验证是否正确
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     */
     boolean judgePass(String userName,String password);

    /**
     * 判断文本框输入是否为空或全为空白符
     * @param text 文本框输入
     * @return boolean 是否非空
     */
     boolean notNull(String ...text);


}
