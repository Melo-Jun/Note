package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.AdminDaoImpl;
import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.Admin;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.service.constant.Status;
import com.melo.notes.util.ServiceUtils;

import static com.melo.notes.util.ServiceUtils.setResult;

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
     * @param userName 用户名
     * @param password 密码
     * @param access 身份
     * @return String 呈现给页面的信息
     */
    @Override
    public Result login(String userName, String password, String access) {
        Result result = new Result();
        if (userName.isEmpty()||userName.contains(Status.SPACE.getMessage())) {
            return setResult(Status.NOT_USERNAME);
        }
        if (password.isEmpty()||password.contains(Status.SPACE.getMessage())) {
            return setResult(Status.NOT_PASSWORD);
        }
        if (access.equals(ADMIN)) {
            Admin admin = new Admin(userName,password);
            if(new AdminDaoImpl().isAdmin(admin)){
                return setResult(Status.WELCOME_ADMIN);
            }else{
                return setResult(Status.NOT_ADMIN);
            }
        } else {
            if (super.judgePass(userName,password)) {
                if(isValidUser(userName)) {
                    return setResult(Status.LOGIN_SUCCESS);
                }else {
                   return setResult(Status.NOT_VALID_USER);
                }
            } else {
                return setResult(Status.ERROR_USERPASS);
            }
        }
    }

    /**
     * 判断该用户是否为有效用户
     * @param userName 用户名
     * @return boolean 是否有效
     */
    @Override
    public boolean isValidUser(String userName){
        User user = new User();
        user.setUserName(userName);
        return userDao.isValidUser(user);
    }

}
