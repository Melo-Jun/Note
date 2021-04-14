package com.melo.notes.controller;

import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.PersonalUserServiceImpl;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.PersonalUserView;

import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description 修改个人信息页面控制器
 * @date 2021-4-13 16:50
 */
public class PersonalUserController {

    PersonalUserServiceImpl personalUserService = (PersonalUserServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.PersonalUserService);

    public void updatePersonalUser(PersonalUserView personalUserView) {
        /*
          获取文本框内容
         */
        String userName = personalUserView.getUserName();
        String oldPass = personalUserView.getOldPass();
        String firstPass = personalUserView.getFirstPass();
        String secondPass = personalUserView.getSecondPass();
        Result valid = personalUserService.isValid(userName, firstPass, secondPass);
        Status status = valid.getStatus();
        switch (status) {
            case SUCCESS:
                if (personalUserService.judgePass(LoginView.USER.getUserName(), oldPass)) {
                    personalUserService.updateUser(userName, secondPass);
                    JOptionPane.showMessageDialog(null, Status.REOPEN.getMessage());
                    System.exit(-1);
                } else {
                    JOptionPane.showMessageDialog(null, Status.WRONG_OLD_PASS.getMessage());
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, status.getMessage());
                break;
        }
    }
}
