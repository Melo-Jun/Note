package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.UserDao;
import com.melo.notes.entity.User;
import com.melo.notes.service.constant.TypeName;
import com.melo.notes.view.LoginView;

import java.util.LinkedList;

import static com.melo.notes.util.Md5Utils.getDigest;

/**
 * @author Jun
 * @program Note
 * @description �û������ݿ����ʵ����
 * @date 2021-3-28 20:45
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    /**
     * �����Ӧ�����ֶκͱ���
     */
    private final String TABLE_NAME="user";
    private final String ALL_FIELD_NAME="id,user_name,password,validity";

    /**
     * ��֤�Ƿ��Ѵ��ڸ��û�
     *
     * @param user �û�ʵ����
     * @return boolean �Ƿ���ڸ��û�
     */
    @Override
    public boolean isExist(User user) {
        String sql="select user_name from "+ TABLE_NAME +" where user_name=? ";
        User temp = new User();
        temp.setUserName(user.getUserName());
        LinkedList<Object> objects = queryList(sql, temp);
        return !objects.isEmpty();
    }

    /**
     * ��֤�Ƿ�Ϊ��Ч�û�
     * @param user �û�ʵ����
     * @return boolean �Ƿ���Ч
     */
    @Override
    public boolean isValidUser(User user){
        String sql="select validity from "+TABLE_NAME +" where user_name=? ";
        LinkedList<Object> validity = queryList(sql, user);
        return TypeName.VALID.getMessage().equals(validity.getFirst().toString());
    }

    /**
     * ��֤�����Ƿ���ȷ
     * @param user �û�ʵ����
     * @notice ��Ҫ���������������Md5���뿴�Բ���Ӧ���ݿ��е��ֶ�
     * @return boolean �����Ƿ���ȷ
     */
    @Override
    public boolean judgePass(User user){
        String sql="select password from "+TABLE_NAME+" where user_name=? ";
        User temp = new User();
        temp.setUserName(user.getUserName());
        LinkedList<Object> password = queryList(sql, temp);
        //�Ѳ�������
        if(password.isEmpty()){
            return false;
        }
        //md5����֤
        return password.getFirst().equals(getDigest(user.getPassword()));
    }

    /**
     * �����û�
     * @param user �û�ʵ����
     * @return boolean �Ƿ����ӳɹ�
     */
    @Override
    public boolean addUser(User user) {
        return super.insert(user) == 1;
    }

    /**
     * �޸��û�
     * @param user �û�ʵ����
     * @return int Ӱ�������
     */
    @Override
    public int updateUser(User user){
        return super.update(user);
    }

    /**
     * ����idչʾ�û���
     * @param user �û�ʵ����
     * @return String �û���
     */
    @Override
    public String showUserName(User user){
        String sql="select user_name from "+TABLE_NAME+" where id=?";
        return queryList(sql,user).getFirst().toString();
    }

    /**
     * չʾ�û�������Ϣ
     * @param user �û�ʵ����
     * @return �û���Ϣ����
     */
    @Override
    public LinkedList showUserAll(User user){
        String sql="select "+ALL_FIELD_NAME+" from "+TABLE_NAME;
        return queryAll(sql,user,User.class);
    }

    /**
     * Ϊ��¼�������û�����Id�Ա������ѯ
     * @param user �û�ʵ����
     */
    @Override
    public void setId(User user) {
            String sql="select id from "+TABLE_NAME+ " where user_name=?";
            LinkedList id = queryList(sql, user);
            if(!id.isEmpty()) {
                LoginView.USER.setId(id.getFirst().toString());
            }
    }

}
