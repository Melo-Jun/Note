package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.constant.TypeName;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.service.constant.Status;

import static com.melo.notes.util.ServiceUtils.setResult;

/**
 * @author Jun
 * @program Note
 * @description 登录页面相关逻辑实现类
 * @date 2021-4-1 18:26
 */
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

    /**
     * 相关操作类对象
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);
    AdminServiceImpl adminService=(AdminServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.AdminService);


    /**
     * 设置Id
     *
     * @param user 用户实体类
     */
    @Override
    public void setId(User user) {
        if(super.notNull(user.toString())) {
            User temp = new User();
            temp.setUserName(user.getUserName());
            userDao.setId(temp);
        }
    }

    /**
     * 判断登录是否成功
     * @param userName 用户名
     * @param password 密码
     * @param access 身份
     * @return Result 返回结果封装类
     */
    @Override
    public Result login(String userName, String password, String access) {
        if (userName.isEmpty()||userName.contains(Status.SPACE.getMessage())) {
            return setResult(Status.NOT_USERNAME);
        }
        if (password.isEmpty()||password.contains(Status.SPACE.getMessage())) {
            return setResult(Status.NOT_PASSWORD);
        }
        if (access.equals(TypeName.ADMIN.getMessage())) {
            if(adminService.isAdmin(userName,password)){
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
                return setResult(Status.ERROR_PASS);
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
        if(super.notNull(userName)) {
            User user = new User();
            user.setUserName(userName);
            return userDao.isValidUser(user);
        }
        return false;
    }

}
