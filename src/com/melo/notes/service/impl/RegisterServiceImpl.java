package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.RegisterService;

import static com.melo.notes.util.Md5Utils.getDigest;

/**
 * @author Jun
 * @program Note
 * @description ע��ҳ������߼�ʵ����
 * @date 2021-4-3 14:30
 */
public class RegisterServiceImpl implements RegisterService {

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
        if (userName.contains(SPACE)||userName.isEmpty() ) {
            return "�û������ϸ�";
        }
        if (firstPass.contains(SPACE)||firstPass.isEmpty()) {
            return "���벻�ϸ�";
        }
        if (secondPass.isEmpty()) {
            return "��������������";
        }
        if (firstPass.equals(secondPass) ) {
            User user = new User(userName, getDigest(secondPass));
            if (new UserDaoImpl().isExist(user) == true) {
                return "���û����Ѵ���";
            }else {
                return "ע��ɹ�";
            }
        }else {
            return "�������벻һ��";
        }
    }
}
