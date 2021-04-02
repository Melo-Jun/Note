package com.melo.notes.dao.inter;

import com.melo.notes.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


     ResultSet executeQuery(Object obj,String sql,Object value);


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
     * @return int 更新的数据库记录数
     */
    int delete(Object obj);

    /**
     * 查找记录
     * @param src 根据的对象
     * @param des 查找的对象
     * @return
     */
    ResultSet search(Object src,Object des);

    /**
     * 将对象映射成属性名和属性值
     * @param obj
     * @param fieldNames
     * @param fieldValues
     * @throws DaoException
     */
    void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException;

    /**
     * 检验数据库相应操作是否成功
     * @param ps
     * @return
     */
    boolean isSuccess(PreparedStatement ps);
}
