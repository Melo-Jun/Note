package com.melo.notes.service.inter;

import com.melo.notes.entity.User;

import java.util.LinkedList;
import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description 所有用户信息页面相关逻辑接口
 * @date 2021-4-3 14:30
 */
public interface UserTableService {

    /**
     * 列出所有用户信息
     * @param user 用户对象
     * @return 用户信息链表
     */
     LinkedList<User> showUserAll(User user);

    /**
     * 提取用户属性填充表格
     * @param tempUser 临时的用户对象
     * @return 属性值集合
     */
     Vector<Object> fillTable(User tempUser);

    /**
     * 更改用户有效性(拉黑与否)
     * @param updateValidity 更改后的有效性
     * @param userId 用户id
     * @return int 影响的行数
     */
     int updateValidity(String updateValidity,String userId);

}
