package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.constant.Status;
import com.melo.notes.service.inter.PersonalUserService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.LoginView;

import static com.melo.notes.util.Md5Utils.getDigest;
import static com.melo.notes.util.ServiceUtils.setResult;

/**
 * @author Jun
 * @program Note
 * @description ���ø����û���Ϣ�߼�ʵ����
 * @date 2021-4-10 15:50
 */
public class PersonalUserServiceImpl extends BaseServiceImpl implements PersonalUserService {

    /**
     * ��ز��������
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * �ж������Ƿ���Ч
     * �ڸ����ж���Ч�Ļ�������������
     * @param userName �û���
     * @param firstPass ��һ������
     * @param secondPass �ڶ�������
     * @return Result ���ؽ������װ��
     */
    @Override
    public Result isValid(String userName, String firstPass, String secondPass) {
        Result message = super.isValid(userName, firstPass, secondPass);
        Status status=message.getStatus();
        //������ж���Ҫ��ע��ʱ���ж��Ƿ��Ѵ��ڸ��û���,�޷�ɸѡ���û��Ƿ�Ϊ���û�
        if(Status.EXIST_USERNAME.equals(status)){
            //�������¼����ʱһ��,���൱��û�и�����
                if(userName.equals(LoginView.USER.getUserName())){
                    return setResult(Status.SUCCESS);
                }else {
                    return  setResult(Status.EXIST_USERNAME);
                }
            }else {
                return super.isValid(userName,firstPass,secondPass);
            }

    }

    /**
     * �ж�����
     * @param userName �û���
     * @param password ����
     * @return boolean �Ƿ���ȷ
     */
    @Override
    public boolean judgePass(String userName,String password){
        return super.judgePass(userName,password);
    }

    /**
     * �����û���Ϣ
     * @param userName ���º���û���
     * @param password  ���º������
     * @return int Ӱ�������
     */
    @Override
    public int updateUser(String userName, String password){
        if(super.notNull(userName,password)) {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(getDigest(password));
            user.setId(LoginView.USER.getId());
            return userDao.updateUser(user);
        }
        return 0;
    }

}
