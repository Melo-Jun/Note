package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.AdminDaoImpl;
import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.Admin;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;

import javax.swing.*;

/**
 * @author Jun
 * @program Note
 * @description ��¼ҳ������߼�ʵ����
 * @date 2021-4-1 18:26
 */
public class LoginServiceImpl implements LoginService {


    private String ADMIN = "����Ա";
    /**
     * ��ز��������
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * �Ƿ��ո��
     */
    private final String SPACE=" ";

    /**
     * ����Id
     *
     * @param user
     */
    @Override
    public void setId(User user) {
        User temp = new User();
        temp.setUserName(user.getUserName());
        userDao.setId(temp);
    }

    /**
     * ������֤
     *
     * @param user
     * @return
     * @notice ��Ҫ���������������Md5���뿴�Բ���Ӧ���ݿ��е��ֶ�
     */
    @Override
    public boolean login(User user) {
        return userDao.judgePass(user);
    }


    /**
     * �ж������Ƿ���Ч
     * @param userName
     * @param password
     * @param access
     * @return String ���ָ�ҳ�����Ϣ
     */
    @Override
    public String isValid(String userName, String password, String access) {
        if (userName.isEmpty()||userName.contains(SPACE)) {
            return "�û������ϸ�";
        }
        if (password.isEmpty()||password.contains(SPACE)) {
            return "���벻�ϸ�";
        }
        if (access.equals(ADMIN)) {
            Admin admin = new Admin(userName,password);
            if(new AdminDaoImpl().isAdmin(admin)){
                return "��ӭ����Ա";
            }else{
                return "��ɶ�ر���";
            }

        } else {
            User user = new User(userName, password);
            if (this.login(user)) {
                return "��¼�ɹ�";
            } else {
                return "�û�����������";
            }
        }
    }

}
