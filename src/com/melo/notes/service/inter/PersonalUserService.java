package com.melo.notes.service.inter;

import com.melo.notes.service.Result;

/**
 * @author Jun
 * @program Note
 * @description ���ø����û���Ϣ�߼��ӿ�
 * @date 2021-4-10 15:35
 */
public interface PersonalUserService {

    /**
     * �ڸ����ж���Ч�Ļ�������������
     * @param userName �û���
     * @param firstPass ��һ������
     * @param secondPass �ڶ�������
     * @return Result ���ؽ������װ��
     */
    Result isValid(String userName, String firstPass, String secondPass);

    /**
     * �ж�����
     * @param userName �û���
     * @param password ����
     * @return boolean �Ƿ���ȷ
     */
    boolean judgePass(String userName,String password);

    /**
     * �����û���Ϣ
     * @param userName ���º���û���
     * @param password  ���º������
     * @return int Ӱ�������
     */
     int updateUser(String userName,String password);

}
