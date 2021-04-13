package com.melo.notes.service.inter;

import com.melo.notes.entity.User;

import java.util.LinkedList;
import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description �����û���Ϣҳ������߼��ӿ�
 * @date 2021-4-3 14:30
 */
public interface UserTableService {

    /**
     * �г������û���Ϣ
     * @param user �û�����
     * @return �û���Ϣ����
     */
     LinkedList<User> showUserAll(User user);

    /**
     * ��ȡ�û����������
     * @param tempUser ��ʱ���û�����
     * @return ����ֵ����
     */
     Vector<Object> fillTable(User tempUser);

    /**
     * �����û���Ч��(�������)
     * @param updateValidity ���ĺ����Ч��
     * @param userId �û�id
     * @return int Ӱ�������
     */
     int updateValidity(String updateValidity,String userId);

}
