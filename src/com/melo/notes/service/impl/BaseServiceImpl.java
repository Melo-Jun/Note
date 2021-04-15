package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.inter.BaseService;
import com.melo.notes.util.BeanFactory;

import static com.melo.notes.util.ServiceUtils.setResult;


/**
 * @author Jun
 * @program Note
 * @description 通用逻辑接口
 * @date 2021-4-8 16:55
 */
public class BaseServiceImpl implements BaseService {

    /**
     * 相关操作类对象
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * 验证是否已存在该用户
     * @description isValid中要用到
     * @param userName 用户名
     * @return boolean 是否存在该用户
     */
    @Override
    public boolean isExist(String userName) {
        if(notNull(userName)) {
            User user = new User();
            user.setUserName(userName);
            return userDao.isExist(user);
        }
        return false;
    }

    /**
     * 判断用户名密码输入是否有效
     * @param userName 用户名
     * @param firstPass 第一次密码
     * @param secondPass 第二次密码
     * @return Result 返回结果封装类
     */
    @Override
    public Result isValid(String userName, String firstPass, String secondPass) {
        if (userName.contains(Status.SPACE.getMessage())||userName.isEmpty() ) {
            return setResult(Status.NOT_USERNAME);
        }
        if (firstPass.contains(Status.SPACE.getMessage())||firstPass.isEmpty()) {
            return setResult(Status.NOT_PASSWORD);
        }
        if (secondPass.isEmpty()) {
            return setResult(Status.AGAIN_PASS);
        }
        if (firstPass.equals(secondPass) ) {
            if (isExist(userName) ) {
                return setResult(Status.EXIST_USERNAME);
            }else {
                return setResult(Status.SUCCESS);
            }
        }else {
            return setResult(Status.NOT_SAME_PASS);
        }
    }


    /**
     * 密码验证
     *
     * @param userName 用户名
     * @param password 密码
     * @return boolean 验证是否正确
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     */
    @Override
    public boolean judgePass(String userName,String password){
        if(notNull(userName,password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            return userDao.judgePass(user);
        }
        return false;
    }

    /**
     * 判断文本框输入是否非空
     * @param text 文本框输入
     * @return boolean 是否非空
     */
    @Override
    public boolean notNull(String ...text){
        if(text.length>0) {
            for (String temp : text) {
                if(temp==null||temp.isEmpty()||temp.trim().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
}
