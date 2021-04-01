package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.dao.inter.UserDao;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.LoginViewService;
import com.melo.notes.util.BeanFactory;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关逻辑实现类
 * @date 2021-4-1 18:26
 */
public class LoginViewServiceImpl implements LoginViewService {


    /**
     * 相关操作类对象
     */
     UserDao userDao = (UserDao) BeanFactory.getBean(BeanFactory.DaoType.UserDao);
    /**
     * 设置Id
     * @param user
     */
    @Override
    public void setId(User user) {
        userDao.setId(user);
    }
}
