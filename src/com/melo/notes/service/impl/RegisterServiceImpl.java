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
public class RegisterServiceImpl extends BaseServiceImpl implements RegisterService {

    /**
     * 非法空格符
     */
    private final String SPACE=" ";

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
        return super.isValid(userName,firstPass,secondPass);
    }
}
