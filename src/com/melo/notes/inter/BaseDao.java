package com.melo.notes.inter;

import com.melo.notes.exception.DaoException;

import java.sql.PreparedStatement;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 通用数据库操作接口
 * @date 2021-3-27 11:00
 */
public interface BaseDao {
    /**
     * 向表中增加一个对象
     * @param obj 对象名
     * @return 是否增加成功
     */
    boolean insert(Object obj);

    /**
     * 将对象映射成属性名和属性值
     * @param obj
     * @param fieldNames
     * @param fieldValues
     * @exception
     */
    void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException;

    /**
     * 检验数据库相应操作是否成功
     * @param ps
     * @return
     */
    boolean isSuccess(PreparedStatement ps);
}
