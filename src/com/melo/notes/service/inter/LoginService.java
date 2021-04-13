package com.melo.notes.service.inter;

import com.melo.notes.entity.User;
import com.melo.notes.service.Result;

/**
 * @author Jun
 * @program Note
 * @description ��¼ҳ������߼��ӿ�
 * @date 2021-4-1 18:26
 */
public interface LoginService {

    /**
     * ����Id
     *
     * @param user �û�ʵ����
     */
     void setId(User user);

    /**
     * �жϵ�¼�Ƿ�ɹ�
     * @param userName �û���
     * @param password ����
     * @param access ���
     * @return String ���ָ�ҳ�����Ϣ
     */
     Result login(String userName, String password, String access);

    /**
     * �жϸ��û��Ƿ�Ϊ��Ч�û�
     * @param userName �û���
     * @return boolean �Ƿ���Ч
     */
    public boolean isValidUser(String userName);
}
