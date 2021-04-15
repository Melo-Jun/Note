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
 * @description 用户类数据库操作实现类
 * @date 2021-3-28 20:45
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    /**
     * 本表对应所有字段和表名
     */
    private final String TABLE_NAME="user";
    private final String ALL_FIELD_NAME="id,user_name,password,validity";

    /**
     * 验证是否已存在该用户
     *
     * @param user 用户实体类
     * @return boolean 是否存在该用户
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
     * 验证是否为有效用户
     * @param user 用户实体类
     * @return boolean 是否有效
     */
    @Override
    public boolean isValidUser(User user){
        String sql="select validity from "+TABLE_NAME +" where user_name=? ";
        LinkedList<Object> validity = queryList(sql, user);
        return TypeName.VALID.getMessage().equals(validity.getFirst().toString());
    }

    /**
     * 验证密码是否正确
     * @param user 用户实体类
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return boolean 密码是否正确
     */
    @Override
    public boolean judgePass(User user){
        String sql="select password from "+TABLE_NAME+" where user_name=? ";
        User temp = new User();
        temp.setUserName(user.getUserName());
        LinkedList<Object> password = queryList(sql, temp);
        //搜不到密码
        if(password.isEmpty()){
            return false;
        }
        //md5后验证
        return password.getFirst().equals(getDigest(user.getPassword()));
    }

    /**
     * 增加用户
     * @param user 用户实体类
     * @return boolean 是否增加成功
     */
    @Override
    public boolean addUser(User user) {
        return super.insert(user) == 1;
    }

    /**
     * 修改用户
     * @param user 用户实体类
     * @return int 影响的行数
     */
    @Override
    public int updateUser(User user){
        return super.update(user);
    }

    /**
     * 根据id展示用户名
     * @param user 用户实体类
     * @return String 用户名
     */
    @Override
    public String showUserName(User user){
        String sql="select user_name from "+TABLE_NAME+" where id=?";
        return queryList(sql,user).getFirst().toString();
    }

    /**
     * 展示用户所有信息
     * @param user 用户实体类
     * @return 用户信息链表
     */
    @Override
    public LinkedList showUserAll(User user){
        String sql="select "+ALL_FIELD_NAME+" from "+TABLE_NAME;
        return queryAll(sql,user,User.class);
    }

    /**
     * 为登录进来的用户设置Id以便后续查询
     * @param user 用户实体类
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
