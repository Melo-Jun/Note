package com.melo.notes.controller;

import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.RegisterServiceImpl;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.RegisterView;

import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description 注册页面控制器
 * @date 2021-4-13 16:50
 */
public class RegisterController {

    RegisterServiceImpl registerService=(RegisterServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.RegisterService);

    public void register(RegisterView registerView){
        /*
          获取文本框内容
         */
        String userName = registerView.getUserNameText();
        String firstPass = registerView.getFirstPass();
        String secondPass = registerView.getSecondPass();
        Result register = registerService.isValid(userName, firstPass, secondPass);
        Status status=register.getStatus();
        if (status == Status.SUCCESS) {
            JOptionPane.showMessageDialog(null, status.getMessage());
            registerService.addUser(userName, secondPass);
            registerView.dispose();
        } else {
            JOptionPane.showMessageDialog(null, status.getMessage());
        }

    }
}
