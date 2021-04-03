package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.RegisterService;

import static com.melo.notes.util.Md5Utils.getDigest;

/**
 * @author Jun
 * @program Note
 * @description 注册页面相关逻辑实现类
 * @date 2021-4-3 14:30
 */
public class RegisterServiceImpl implements RegisterService {
    /**
     * 判断输入是否有效
     *
     * @param userName
     * @param firstPass
     * @param secondPass
     * @return String 呈现给页面的信息
     */
    @Override
    public String isValid(String userName, String firstPass, String secondPass) {
        if (userName.isEmpty()) {
            return "用户名不能为空";
        }
        if (firstPass.isEmpty()) {
            return "密码不能为空";
        }
        if (secondPass.isEmpty()) {
            return "请输入两次密码";
        }
        if (firstPass.equals(secondPass) ) {
            User user = new User(userName, getDigest(secondPass));
            if (new UserDaoImpl().isExist(user) == true) {
                return "该用户名已存在";
            }else {
                return "注册成功";
            }
        }if(!firstPass.equals(secondPass)){
            return "两次输入不一致";
        }
        return "注册失败";
    }
}
