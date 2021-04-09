package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.AdminDaoImpl;
import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.Admin;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;

import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关逻辑实现类
 * @date 2021-4-1 18:26
 */
public class LoginServiceImpl implements LoginService {


    private String ADMIN = "管理员";
    /**
     * 相关操作类对象
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * 非法空格符
     */
    private final String SPACE=" ";

    /**
     * 设置Id
     *
     * @param user
     */
    @Override
    public void setId(User user) {
        User temp = new User();
        temp.setUserName(user.getUserName());
        userDao.setId(temp);
    }

    /**
     * 密码验证
     *
     * @param user
     * @return
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     */
    @Override
    public boolean login(User user) {
        return userDao.judgePass(user);
    }


    /**
     * 判断输入是否有效
     * @param userName
     * @param password
     * @param access
     * @return String 呈现给页面的信息
     */
    @Override
    public String isValid(String userName, String password, String access) {
        if (userName.isEmpty()||userName.contains(SPACE)) {
            return "用户名不合格";
        }
        if (password.isEmpty()||password.contains(SPACE)) {
            return "密码不合格";
        }
        if (access.equals(ADMIN)) {
            Admin admin = new Admin(userName,password);
            if(new AdminDaoImpl().isAdmin(admin)){
                return "欢迎管理员";
            }else{
                return "想啥呢宝贝";
            }

        } else {
            User user = new User(userName, password);
            if (this.login(user)) {
                return "登录成功";
            } else {
                return "用户或密码有误";
            }
        }
    }

}
