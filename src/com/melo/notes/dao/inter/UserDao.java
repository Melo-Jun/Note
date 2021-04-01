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
