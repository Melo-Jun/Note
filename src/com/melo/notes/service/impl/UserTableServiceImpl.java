package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.UserTableService;
import com.melo.notes.util.BeanFactory;

import java.util.LinkedList;
import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description �����û���Ϣҳ������߼�ʵ����
 * @date 2021-4-3 14:30
 */
public class UserTableServiceImpl extends BaseServiceImpl implements UserTableService {

    /**
     * ������ز�����
     */
    UserDaoImpl userDao=(UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * �г������û���Ϣ
     * @param user �û�����
     * @return �û���Ϣ����
     */
    @Override
    public LinkedList<User> showUserAll(User user){
        return userDao.showUserAll(user);
    }

    /**
     * ��ȡ�û����������
     * @param tempUser ��ʱ���û�����
     * @return ����ֵ����
     */
    @Override
    public Vector<Object> fillTable(User tempUser) {
        Vector<Object> values = new Vector<>();
        values.add(tempUser.getId());
        values.add(tempUser.getUserName());
        values.add(tempUser.getPassword());
        values.add(tempUser.getValidity());
        return values;
    }

    /**
     * �����û���Ч��(�������)
     * @param updateValidity ���ĺ����Ч��
     * @param userId �û�id
     * @return int Ӱ�������
     */
    @Override
    public int updateValidity(String updateValidity, String userId){
        if(super.notNull(updateValidity,userId)) {
            User user = new User();
            user.setValidity(updateValidity);
            user.setId(userId);
            return userDao.updateUser(user);
        }
        return 0;
    }
}
