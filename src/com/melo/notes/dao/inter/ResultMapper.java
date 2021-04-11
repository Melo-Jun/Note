package com.melo.notes.dao.inter;

import java.sql.ResultSet;

/**
 * @author Jun
 * @program Note
 * @see com.melo.notes.dao.impl.BaseDaoImpl
 * @description 将结果集映射为对象
 * @date 2021-4-6 19:42
 */
public interface ResultMapper {
    /**
     * 负责提供一个映射数据库查询结果集的方法
     * @name doMap
     * @param rs 需要映射的结果集
     * @return java.lang.Object
     */
    Object doMap(ResultSet rs);
}
