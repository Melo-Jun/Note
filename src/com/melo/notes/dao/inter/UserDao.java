package com.melo.notes.dao.inter;

import com.melo.notes.entity.User;

/**
 * @author Jun
 * @program Note
 * @description 用户类数据库操作接口
 * @date 2021-3-28 20:42
 */
public interface UserDao {

    /**
     * 验证是否已存在该用户
     * @param user
     * @return
     */
    boolean isExist(User user);


    /**
     * 密码验证
     * @param user
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return
     */
    boolean judgePass(User user);

    /**
     * 增加用户
     * @param user
     * @return boolean 是否增加成功
     */
     boolean add(User user);

    /**
     * 设置Id
     * @param user
     * @return boolean 是否增加成功
     */
     boolean setId(User user);
}
