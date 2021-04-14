package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.inter.RegisterService;
import com.melo.notes.util.BeanFactory;

import static com.melo.notes.util.Md5Utils.getDigest;

/**
 * @author Jun
 * @program Note
 * @description ע��ҳ������߼�ʵ����
 * @date 2021-4-3 14:30
 */
public class RegisterServiceImpl extends BaseServiceImpl implements RegisterService {

/**
 * ������ز�����
 */
UserDaoImpl userDao=(UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);


    /**
     * �ж������Ƿ���Ч
     *
     * @param userName �û���
     * @param firstPass ��һ������
     * @param secondPass �ڶ�������
     * @return Result ���ؽ����װ��
     */
    @Override
    public Result isValid(String userName, String firstPass, String secondPass) {
        return super.isValid(userName,firstPass,secondPass);
    }

    /**
     * ע���û��������û������ݿ���
     * @param userName �û���
     * @param password ����
     * @return boolean �Ƿ����ӳɹ�
     */
    @Override
    public boolean addUser(String userName, String password){
        if(super.notNull(userName,password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(getDigest(password));
            return userDao.addUser(user);
        }
        return false;
    }


}
