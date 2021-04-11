package com.melo.notes.controller;

import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.impl.LoginServiceImpl;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.AdminView;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.UserView;

import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description 控制页面跳转
 * @date 2021-3-27 19:12
 */
public class LoginController {
    /**
     * 相关操作类
     */
    LoginServiceImpl loginViewService = (LoginServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.LoginService);

    public  void login(LoginView loginView) {
        String userName = loginView.getUserName();
        String pass = loginView.getPass();
        String access = loginView.getAccess();
        Result login = loginViewService.login(userName, pass, access);
        Status status=login.getStatus();
        switch (status){
            case LOGIN_SUCCESS:
                LoginView.USER=new User(userName,pass);
                new LoginServiceImpl().setId(LoginView.USER);
                JOptionPane.showMessageDialog(null,status.getMessage());
                loginView.dispose();
                new UserView(LoginView.USER);
                break;
            case WELCOME_ADMIN:
                JOptionPane.showMessageDialog(null,status.getMessage());
                loginView.dispose();
                new AdminView();
                break;
            case NOT_PASSWORD:
            case NOT_USERNAME:
            case NOT_VALID_USER:
            case NOT_ADMIN:
            case ERROR_USERPASS:
                JOptionPane.showMessageDialog(null,status.getMessage());
            default:
        }
    }

    /**
     * 为各操作添加监听器
     */
    /*public static class Listener implements MouseListener {

        @Override
        public  void mouseClicked(MouseEvent e) {
            if(e.getSource()==loginView.login){
                System.out.println("你在登录");
                login();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }*/

    public static void main(String[] args) {
       new LoginView();
    }
}
