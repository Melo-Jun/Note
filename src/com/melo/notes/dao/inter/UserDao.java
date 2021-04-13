package com.melo.notes.dao.inter;

import com.melo.notes.entity.User;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 用户类数据库操作接口
 * @date 2021-3-28 20:42
 */
public interface UserDao {

    /**
     * 验证是否已存在该用户
     * @param user 用户实体类
     * @return boolean 是否有效
     */
    boolean isExist(User user);

    /**
     * 验证是否为有效用户
     * @param user 用户实体类
     * @return boolean 是否有效
     */
     boolean isValidUser(User user);

    /**
     * 验证密码是否正确
     * @param user 用户实体类
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return boolean 密码是否正确
     */
    boolean judgePass(User user);

    /**
     * 增加用户
     * @param user 用户实体类
     * @return boolean 是否增加成功
     */
     boolean addUser(User user);

    /**
     * 修改用户
     * @param user 用户实体类
     * @return int 影响的行数
     */
    int updateUser(User user);

    /**
     * 根据id展示用户名
     * @param user 用户实体类
     * @return String 用户名
     */
    String showUserName(User user);

    /**
     * 展示用户所有信息
     * @param user 用户实体类
     * @return 用户信息链表
     */
    LinkedList showUserAll(User user);

    /**
     * 为登录进来的用户设置Id以便后续查询
     * @param user 用户实体类
     */
     void setId(User user);

}
