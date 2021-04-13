package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.UserTableService;
import com.melo.notes.util.BeanFactory;

import java.util.LinkedList;
import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description 所有用户信息页面相关逻辑实现类
 * @date 2021-4-3 14:30
 */
public class UserTableServiceImpl extends BaseServiceImpl implements UserTableService {

    /**
     * 创建相关操作类
     */
    UserDaoImpl userDao=(UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    /**
     * 列出所有用户信息
     * @param user 用户对象
     * @return 用户信息链表
     */
    @Override
    public LinkedList<User> showUserAll(User user){
        return userDao.showUserAll(user);
    }

    /**
     * 提取用户属性填充表格
     * @param tempUser 临时的用户对象
     * @return 属性值集合
     */
    @Override
    public Vector<Object> fillTable(User tempUser) {
        Vector<Object> values = new Vector<>();
        values.add(tempUser.getId());
        values.add(tempUser.getUserName());
        values.add(tempUser.getPassword());
        values.add(tempUser.getValidity());
        return values;
    }

    /**
     * 更改用户有效性(拉黑与否)
     * @param updateValidity 更改后的有效性
     * @param userId 用户id
     * @return int 影响的行数
     */
    @Override
    public int updateValidity(String updateValidity, String userId){
        if(super.notNull(updateValidity,userId)) {
            User user = new User();
            user.setValidity(updateValidity);
            user.setId(userId);
            return userDao.updateUser(user);
        }
        return 0;
    }
}
