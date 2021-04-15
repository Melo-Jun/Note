package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.Result;
import com.melo.notes.service.constant.TypeName;
import com.melo.notes.service.inter.LoginService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.service.constant.Status;

import static com.melo.notes.util.ServiceUtils.setResult;

/**
 * @author Jun
 * @program Note
 * @description ��¼ҳ������߼�ʵ����
 * @date 2021-4-1 18:26
 */
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

    /**
     * ��ز��������
     */
    UserDaoImpl userDao = (UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);
    AdminServiceImpl adminService=(AdminServiceImpl) BeanFactory.getBean(BeanFactory.ServiceType.AdminService);


    /**
     * ����Id
     *
     * @param user �û�ʵ����
     */
    @Override
    public void setId(User user) {
        if(super.notNull(user.toString())) {
            User temp = new User();
            temp.setUserName(user.getUserName());
            userDao.setId(temp);
        }
    }

    /**
     * �жϵ�¼�Ƿ�ɹ�
     * @param userName �û���
     * @param password ����
     * @param access ���
     * @return Result ���ؽ����װ��
     */
    @Override
    public Result login(String userName, String password, String access) {
        if (userName.isEmpty()||userName.contains(Status.SPACE.getMessage())) {
            return setResult(Status.NOT_USERNAME);
        }
        if (password.isEmpty()||password.contains(Status.SPACE.getMessage())) {
            return setResult(Status.NOT_PASSWORD);
        }
        if (access.equals(TypeName.ADMIN.getMessage())) {
            if(adminService.isAdmin(userName,password)){
                return setResult(Status.WELCOME_ADMIN);
            }else{
                return setResult(Status.NOT_ADMIN);
            }
        } else {
            if (super.judgePass(userName,password)) {
                if(isValidUser(userName)) {
                    return setResult(Status.LOGIN_SUCCESS);
                }else {
                   return setResult(Status.NOT_VALID_USER);
                }
            } else {
                return setResult(Status.ERROR_PASS);
            }
        }
    }

    /**
     * �жϸ��û��Ƿ�Ϊ��Ч�û�
     * @param userName �û���
     * @return boolean �Ƿ���Ч
     */
    @Override
    public boolean isValidUser(String userName){
        if(super.notNull(userName)) {
            User user = new User();
            user.setUserName(userName);
            return userDao.isValidUser(user);
        }
        return false;
    }

}
