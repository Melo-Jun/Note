package com.melo.notes.service.inter;

import com.melo.notes.entity.User;

/**
 * @author Jun
 * @program Note
 * @description ��¼ҳ������߼��ӿ�
 * @date 2021-4-1 18:26
 */
public interface LoginService {

    /**
     * ����id
     * @param user
     */
    void setId(User user);

    /**
     * �ж������Ƿ���Ч
     *
     * @param userName
     * @param password
     * @param access
     * @return String ���ָ�ҳ�����Ϣ
     */
     String isValid(String userName,String password,String access);
}
