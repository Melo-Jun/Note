package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.inter.BaseService;
import com.melo.notes.util.BeanFactory;

import static com.melo.notes.util.ServiceUtils.setResult;


/**
 * @author Jun
 * @program Note
 * @description ͨ���߼��ӿ�
 * @date 2021-4-8 16:55
 */
public class BaseServiceImpl implements BaseService {

    /**
     * ��ز��������
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * ��֤�Ƿ��Ѵ��ڸ��û�
     * @description isValid��Ҫ�õ�
     * @param userName �û���
     * @return boolean �Ƿ���ڸ��û�
     */
    @Override
    public boolean isExist(String userName) {
        if(notNull(userName)) {
            User user = new User();
            user.setUserName(userName);
            return userDao.isExist(user);
        }
        return false;
    }

    /**
     * �ж��û������������Ƿ���Ч
     * @param userName �û���
     * @param firstPass ��һ������
     * @param secondPass �ڶ�������
     * @return Result ���ؽ����װ��
     */
    @Override
    public Result isValid(String userName, String firstPass, String secondPass) {
        if (userName.contains(Status.SPACE.getMessage())||userName.isEmpty() ) {
            return setResult(Status.NOT_USERNAME);
        }
        if (firstPass.contains(Status.SPACE.getMessage())||firstPass.isEmpty()) {
            return setResult(Status.NOT_PASSWORD);
        }
        if (secondPass.isEmpty()) {
            return setResult(Status.AGAIN_PASS);
        }
        if (firstPass.equals(secondPass) ) {
            if (isExist(userName) ) {
                return setResult(Status.EXIST_USERNAME);
            }else {
                return setResult(Status.SUCCESS);
            }
        }else {
            return setResult(Status.NOT_SAME_PASS);
        }
    }


    /**
     * ������֤
     *
     * @param userName �û���
     * @param password ����
     * @return boolean ��֤�Ƿ���ȷ
     * @notice ��Ҫ���������������Md5���뿴�Բ���Ӧ���ݿ��е��ֶ�
     */
    @Override
    public boolean judgePass(String userName,String password){
        if(notNull(userName,password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            return userDao.judgePass(user);
        }
        return false;
    }

    /**
     * �ж��ı��������Ƿ�ǿ�
     * @param text �ı�������
     * @return boolean �Ƿ�ǿ�
     */
    @Override
    public boolean notNull(String ...text){
        if(text.length>0) {
            for (String temp : text) {
                if(temp==null||temp.isEmpty()||temp.trim().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
}
