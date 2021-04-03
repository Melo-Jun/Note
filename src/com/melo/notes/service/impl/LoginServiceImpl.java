package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;

/**
 * @author Jun
 * @program Note
 * @description ��¼ҳ������߼�ʵ����
 * @date 2021-4-1 18:26
 */
public class LoginServiceImpl implements LoginService {


    private static String ADMIN = "����Ա";
    /**
     * ��ز��������
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * ����Id
     *
     * @param user
     */
    @Override
    public void setId(User user) {
        userDao.setId(user);
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
     *
     * @param userName
     * @param password
     * @param access
     * @return String ���ָ�ҳ�����Ϣ
     */
    @Override
    public String isValid(String userName, String password, String access) {
        if (userName.isEmpty()) {
            return "�û�������Ϊ��";
        }
        if (password.isEmpty()) {
            return "���벻��Ϊ��";
        }
        if (access == ADMIN) {

        } else {
            User user = new User(userName, password);
            if (this.login(user)) {
                return "��¼�ɹ�";
            } else {
                return "�û�����������";
            }
        }
        return "";
    }

}
