package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.BaseDao;
import com.melo.notes.exception.DaoException;
import com.melo.notes.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.*;
import static com.melo.notes.util.ReflectUtils.getFields;
import static com.melo.notes.util.ReflectUtils.getMethods;
import static com.melo.notes.util.StringUtils.toColumnName;


/**
 * @author Jun
 * @program Note
 * @description 数据库通用操作实现类
 * @date 2021-3-28 20:36
 */
public class BaseDaoImpl implements BaseDao {


    /**
     * 封装数据库更新操作
     * @param obj
     * @param sql
     * @return int 影响的行数
     */
    @Override
    public int executeUpdate(Object obj, String sql) {
        //影响的行数
        int count=0;
        Connection conn=getConnection();
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement(sql);
            //注入Sql填充参数
            setParams(ps,obj);
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,null);
        }
        return count;
    }


    /**
     * 增加一条记录进入数据库
     * @param obj 对象名
     * @return int 影响的数据库行数
     */
    @Override
    public int insert(Object obj) {
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        /**
             * 根据属性名生成预编译sql插入语句
             */
            StringBuilder sql = new StringBuilder("insert into " + getTableName(obj) + " (");
            for(Object name: fieldNames){
                System.out.println(name.toString());
            sql.append(name.toString()+",");
        }
        //将最后一个","改为")"，省去判断是否为最后一个")"
        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values (");
        for (int i = 0; i < fieldNames.size(); i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');
        return executeUpdate(obj,sql.toString());
        }

    /**
     * 删除记录
     *
     * @param obj 与删除有关的对象
     * @return int 更新的数据库记录数
     */
    @Override
    public int delete(Object obj) {
        String name = getFields(obj).getFirst().getName();
        StringBuilder columnName = toColumnName(name);
        StringBuilder sql = new StringBuilder("delete from " + getTableName(obj) + " where "+columnName+" =?");
        return executeUpdate(obj,sql.toString());
    }

    /**
     * 将对象映射成属性名和属性值
     * @param obj 对象
     * @param fieldNames 属性名
     * @param fieldValues 属性值
     */
    @Override
    public void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException {
        if (obj == null) {
            return;
        }
        /**
         * 取出包括父类在内的所有方法和属性
         */
        LinkedList<Method> methods = getMethods(obj);
        LinkedList<Field> fields = getFields(obj);
        for (Field field : fields) {
            /**
             * 获取get方法并invoke执行取得属性值
             */
            for (Method method : methods) {
                if (method.getName().startsWith("get") && method.getName().substring(3).equalsIgnoreCase(field.getName())) {
                    Object value = null;
                    try {
                        value = method.invoke(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new DaoException("反射执行get方法异常：" + method.getName(), e);
                    }
                    /**
                     * 只添加不为null值的字段
                     */
                    if (value != null) {
                        fieldValues.add(value);
                        /**
                         * 取出该属性的名称，映射成数据库字段名
                         */
                         fieldNames.add(toColumnName(field.getName()));
                    }
                }
            }
        }
    }

    /**
     * 判断操作是否成功
     * @param ps
     * @return boolean 是否成功
     */
    @Override
    public boolean isSuccess(PreparedStatement ps) {
        int count = 0;
        try {
            count = ps.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }if(count==1) {
            return true;
        }else {
            throw new DaoException("查无此人");
        }
    }

}
