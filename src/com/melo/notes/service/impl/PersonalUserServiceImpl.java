package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.inter.PersonalUserService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.LoginView;

import static com.melo.notes.util.Md5Utils.getDigest;
import static com.melo.notes.util.ServiceUtils.setResult;

/**
 * @author Jun
 * @program Note
 * @description 设置个人用户信息逻辑实现类
 * @date 2021-4-10 15:50
 */
public class PersonalUserServiceImpl extends BaseServiceImpl implements PersonalUserService {

    /**
     * 相关操作类对象
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * 判断输入是否有效
     * 在父类判断有效的基础上增加条件
     * @param userName 用户名
     * @param firstPass 第一次密码
     * @param secondPass 第二次密码
     * @return Result 返回结果集封装类
     */
    @Override
    public Result isValid(String userName, String firstPass, String secondPass) {
        Result message = super.isValid(userName, firstPass, secondPass);
        Status status=message.getStatus();
        //父类的判断主要在注册时候判断是否已存在该用户名,无法筛选该用户是否为本用户
        if(Status.EXIST_USERNAME.equals(status)){
            //名称与登录进来时一样,则相当于没有改名称
                if(userName.equals(LoginView.USER.getUserName())){
                    return setResult(Status.SUCCESS);
                }else {
                    return  setResult(Status.EXIST_USERNAME);
                }
            }else {
                return super.isValid(userName,firstPass,secondPass);
            }

    }

    /**
     * 判断密码
     * @param userName 用户名
     * @param password 密码
     * @return boolean 是否正确
     */
    @Override
    public boolean judgePass(String userName,String password){
        return super.judgePass(userName,password);
    }

    /**
     * 更新用户信息
     * @param userName 更新后的用户名
     * @param password  更新后的密码
     * @return int 影响的行数
     */
    @Override
    public int updateUser(String userName, String password){
        if(super.notNull(userName,password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(getDigest(password));
            user.setId(LoginView.USER.getId());
            return userDao.updateUser(user);
        }
        return 0;
    }

}
