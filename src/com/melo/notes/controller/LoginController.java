package com.melo.notes.controller;

import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.LoginServiceImpl;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.util.ViewUtils;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.AdminView;
import com.melo.notes.view.UserView;

import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description 登录页面控制器
 * @date 2021-4-10
 */
public class LoginController {
    /**
     * 相关操作类
     */
    LoginServiceImpl loginViewService = (LoginServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.LoginService);

    public void login(LoginView loginView) {
        String userName = loginView.getUserName();
        String pass = loginView.getPass();
        String access = loginView.getAccess();
        Result login = loginViewService.login(userName, pass, access);
        Status status = login.getStatus();
        switch (status) {
            case LOGIN_SUCCESS:
                LoginView.USER.setUserName(userName);
                LoginView.USER.setPassword(pass);
                loginViewService.setId(LoginView.USER);
                JOptionPane.showMessageDialog(null, status.getMessage());
                loginView.dispose();
                new UserView();
                break;
            case WELCOME_ADMIN:
                JOptionPane.showMessageDialog(null, status.getMessage());
                loginView.dispose();
                new AdminView();
                break;
            case NOT_PASSWORD:
            case NOT_USERNAME:
            case NOT_VALID_USER:
            case NOT_ADMIN:
            case ERROR_PASS:
                JOptionPane.showMessageDialog(null, status.getMessage());
            default:
        }
    }


    public static void main(String[] args) {
        //初始化字体
        ViewUtils.setUiFont();
       new LoginView();
    }
}