package com.melo.notes.dao.inter;

import com.melo.notes.entity.User;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关数据库操作接口
 * @date 2021-3-27 20:32
 */
public interface LoginDao {
    /**
     * 注册新用户
     * @param user
     * @return boolean 是否成功注册
     */
    boolean register(User user);

    /**
     * 密码验证
     * @param user
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return
     */
    boolean login(User user);


}
