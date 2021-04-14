package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.inter.RegisterService;
import com.melo.notes.util.BeanFactory;

import static com.melo.notes.util.Md5Utils.getDigest;

/**
 * @author Jun
 * @program Note
 * @description 注册页面相关逻辑实现类
 * @date 2021-4-3 14:30
 */
public class RegisterServiceImpl extends BaseServiceImpl implements RegisterService {

/**
 * 创建相关操作类
 */
UserDaoImpl userDao=(UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);


    /**
     * 判断输入是否有效
     *
     * @param userName 用户名
     * @param firstPass 第一次密码
     * @param secondPass 第二次密码
     * @return Result 返回结果封装类
     */
    @Override
    public Result isValid(String userName, String firstPass, String secondPass) {
        return super.isValid(userName,firstPass,secondPass);
    }

    /**
     * 注册用户后新增用户到数据库中
     * @param userName 用户名
     * @param password 密码
     * @return boolean 是否增加成功
     */
    @Override
    public boolean addUser(String userName, String password){
        if(super.notNull(userName,password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(getDigest(password));
            return userDao.addUser(user);
        }
        return false;
    }


}
