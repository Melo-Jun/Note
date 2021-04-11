package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
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
     * �Ƿ��ո��
     */
    private final String SPACE=" ";

    /**
     * �ж������Ƿ���Ч
     *
     * @param userName
     * @param firstPass
     * @param secondPass
     * @return String ���ָ�ҳ�����Ϣ
     */
    @Override
    public String isValid(String userName, String firstPass, String secondPass) {
        return super.isValid(userName,firstPass,secondPass);
    }

    public boolean addUser(String userName,String password){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(getDigest(password));
        user.setId(userDao.getMaxId(user));
        return userDao.addUser(user);
    }


}
