package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.AdminDaoImpl;
import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.Admin;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关逻辑实现类
 * @date 2021-4-1 18:26
 */
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {


    private final String ADMIN = "管理员";
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
     * @param user 用户实体类
     */
    @Override
    public void setId(User user) {
        User temp = new User();
        temp.setUserName(user.getUserName());
        userDao.setId(temp);
    }

    /**
     * 判断登录是否成功
     * @param userName
     * @param password
     * @param access
     * @return String 呈现给页面的信息
     */
    public String login(String userName, String password, String access) {
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
            if (super.judgePass(user)) {
                if(isValidUser(userName)) {
                    return "登录成功";
                }else {
                    return "该用户已被拉黑";
                }
            } else {
                return "用户或密码有误";
            }
        }
    }

    public boolean isValidUser(String userName){
        User user = new User();
        user.setUserName(userName);
        return userDao.isValidUser(user);
    }

}
