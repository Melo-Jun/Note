package com.melo.notes.dao.inter;

import com.melo.notes.exception.DaoException;

import java.sql.PreparedStatement;
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
     * @name insert
     * @notice none
     * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
     * @date 2019/4/9
     */
    int insert(Object obj);

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
