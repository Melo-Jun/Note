package com.melo.notes.dao.inter;

import com.melo.notes.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 数据库通用操作接口
 * @date 2021-3-28 20:35
 */
public interface BaseDao {

    /**
     * 封装数据库更新操作
     * @param obj
     * @param sql
     * @return int 影响的行数
     */
     int executeUpdate(Object obj, String sql);

    /**
     * 增加一条记录进入数据库
     *
     * @param obj 要插入的对象
     * @return int 更新的数据库记录数
     */
    int insert(Object obj);

    /**
     * 删除记录
     *
     * @param obj 与删除有关的对象
     * @return int 影响的数据库记录数
     */
    int delete(Object obj);



    //int update(Object obj);


    /**
     * 查找记录(需要键值对类型)
     *
     * @param sql 特定查询语句
     * @param obj 特定查询语句
     * @return HashMap 结果集封装Map
     */
     HashMap<Object, Object> queryMap(String sql, Object obj);

    /**
     * 查找记录(只查找单一值)
     *
     * @param sql 查询语句
     * @param obj 用以填充的属性值
     * @return LinkedList 结果集封装LinkedList
     */
     LinkedList<Object> queryList(String sql,Object obj);

    /**
     * 查询所有
     * @param sql
     * @param obj
     * @return
     */
    LinkedList<Object> queryAll(String sql,Object obj);


    /**
     * 将对象映射成属性名和属性值
     * @param obj 对象
     * @param fieldNames 属性名
     * @param fieldValues 属性值
     * @throws DaoException
     */
    void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException;

    /**
     * 根据xxx获取id
     * @param obj xxx
     * @return String id
     */
    String getId(Object obj);


    /**
     * 检验数据库相应操作是否成功
     * @param ps
     * @return
     */
    boolean isSuccess(PreparedStatement ps);
}
