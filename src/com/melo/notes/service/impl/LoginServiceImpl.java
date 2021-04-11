package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.AdminDaoImpl;
import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.Admin;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.service.constant.Status;
import com.melo.notes.util.ServiceUtils;

import static com.melo.notes.util.ServiceUtils.setResult;

/**
 * @author Jun
 * @program Note
 * @description ��¼ҳ������߼�ʵ����
 * @date 2021-4-1 18:26
 */
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {


    private final String ADMIN = "����Ա";
    /**
     * ��ز��������
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * �Ƿ��ո��
     */
    private final String SPACE=" ";

    /**
     * ����Id
     *
     * @param user �û�ʵ����
     */
    @Override
    public void setId(User user) {
        User temp = new User();
        temp.setUserName(user.getUserName());
        userDao.setId(temp);
    }

    /**
     * �жϵ�¼�Ƿ�ɹ�
     * @param userName �û���
     * @param password ����
     * @param access ���
     * @return String ���ָ�ҳ�����Ϣ
     */
    public Result login(String userName, String password, String access) {
        Result result = new Result();
        if (userName.isEmpty()||userName.contains(SPACE)) {
            return setResult(Status.NOT_USERNAME);
        }
        if (password.isEmpty()||password.contains(SPACE)) {
            return setResult(Status.NOT_PASSWORD);
        }
        if (access.equals(ADMIN)) {
            Admin admin = new Admin(userName,password);
            if(new AdminDaoImpl().isAdmin(admin)){
                return setResult(Status.WELCOME_ADMIN);
            }else{
                return setResult(Status.NOT_ADMIN);
            }
        } else {
            User user = new User(userName, password);
            if (super.judgePass(user)) {
                if(isValidUser(userName)) {
                    return setResult(Status.LOGIN_SUCCESS);
                }else {
                   return setResult(Status.NOT_VALID_USER);
                }
            } else {
                return setResult(Status.ERROR_USERPASS);
            }
        }
    }

    public boolean isValidUser(String userName){
        User user = new User();
        user.setUserName(userName);
        return userDao.isValidUser(user);
    }

}
