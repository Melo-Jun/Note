package com.melo.notes.service.inter;

import com.melo.notes.entity.User;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关逻辑接口
 * @date 2021-4-1 18:26
 */
public interface LoginViewService {

    /**
     * 设置Id
     * @param user
     */
    void setId(User user);
}
