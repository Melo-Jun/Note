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
     * 执行一条查询语句,并对结果集进行封装
     * @param obj 对象
     * @param sql sql语句
     * @param resultMapper 实现不同功能映射的实现类
     * @return 映射结果
     */
     Object executeQuery(Object obj, String sql, ResultMapper resultMapper);

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


    /**
     * 更新记录
     * @param obj 与更新有关的对象
     * @return int 影响的数据库记录数
     */
    int update(Object obj);


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
     LinkedList queryList(String sql,Object obj);

    /**
     * 查询所有
     * @param sql
     * @param obj
     * @param clazz 用来创建实例的类
     * @return
     */
    LinkedList queryAll(String sql,Object obj,Class clazz);


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
     *  用于插入一条记录时使用
     * @description 数据库id设置为String无法自增(设置int映射时无法转化为object)
     * @param obj 对象
     * @return String  该对象表中最大id
     */
    String getMaxId(Object obj);

    /**
     * 检验数据库相应操作是否成功
     * @param ps
     * @return
     */
    boolean isSuccess(PreparedStatement ps);
}
