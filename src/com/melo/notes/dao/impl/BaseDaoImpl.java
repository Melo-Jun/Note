package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.BaseDao;
import com.melo.notes.dao.inter.ResultMapper;
import com.melo.notes.exception.DaoException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtils.*;
import static com.melo.notes.util.ReflectUtils.getFields;
import static com.melo.notes.util.ReflectUtils.getMethods;
import static com.melo.notes.util.StringUtils.*;


/**
 * @author Jun
 * @program Note
 * @description 数据库通用操作实现类
 * @date 2021-3-28 20:36
 */
public class BaseDaoImpl implements BaseDao {

    //private Class<?> cls;

    /**
     * 封装数据库更新操作
     *
     * @param obj 对象
     * @param sql sql语句
     * @return int 影响的行数
     */
    @Override
    public int executeUpdate(Object obj, String sql) {
        //影响的行数
        int count = 0;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //注入Sql填充参数
            setParams(ps, obj);
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            freeConnection(conn);
            close(ps, null);
        }
        return count;
    }

    /**
     * 执行一条查询语句,并对结果集进行封装
     *
     * @param obj          对象
     * @param sql          sql语句
     * @param resultMapper 实现不同功能映射的实现类
     * @return 映射结果
     */
    @Override
    public Object executeQuery(Object obj, String sql, ResultMapper resultMapper) {
        Connection conn = getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            assert conn != null;
            ps = conn.prepareStatement(sql);
            //根据obj注入Sql填充参数
            if (obj != null) {
                setParams(ps, obj);
            }
            rs = ps.executeQuery();
            return resultMapper.doMap(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException("预编译查询语句异常：");
        } finally {
            freeConnection(conn);
            close(ps, rs);
        }
    }


    /**
     * 增加一条记录进入数据库
     *
     * @param obj 与增加有关的对象
     * @return int 影响的数据库行数
     */
    @Override
    public int insert(Object obj) {
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj, fieldNames, fieldValues);
        /*
          根据属性名生成预编译sql插入语句
         */
        StringBuilder sql = new StringBuilder("insert into " + getTableName(obj) + " (");
        for (Object name : fieldNames) {
            sql.append(name.toString() + ",");
        }
        //将最后一个","改为")"，省去判断是否为最后一个")"
        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values (");
        for (int i = 0; i < fieldNames.size(); i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');
        return executeUpdate(obj, sql.toString());
    }

    /**
     * 删除记录
     *
     * @param obj 与删除有关的对象
     * @return int 影响的数据库记录数
     */
    @Override
    public int delete(Object obj) {
        StringBuilder sql = new StringBuilder( "delete from "+getTableName(obj));
        /*
          将对象映射成属性和值(属性会映射为数据库字段名)
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        /*
          将字段名填入sql语句
          没有where条件则不添加
         */
        if(fieldValues.size()!=0) {
            sql.append(" where ");
            for (Object fieldName : fieldNames) {
                sql.append(fieldName).append("=? AND ");
            }
        }
        //删除最后一个AND
        sql.delete(sql.length()-4,sql.length());
        /*
          完成sql注入和执行
         */
        return executeUpdate(obj, sql.toString());
    }

    /**
     * 更新记录
     * @param obj 与更新有关的对象
     * @return int 影响的数据库记录数
     */
    @Override
    public int update(Object obj) {
        /*
          根据更新依据的字段名构造对象，取出对应数据库字段名和值
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj, fieldNames, fieldValues);
        StringBuilder sql = new StringBuilder("update " + getTableName(obj) + " set ");
        for(Object fieldName:fieldNames) {
            //先过滤id,id要留到最后作为根据
            if(!"id".equals(fieldName.toString())) {
                sql.append(fieldName + "=?, ");
            }
        }
        //删除最后一个逗号
        sql.deleteCharAt(sql.length()-2);
        sql.append("where id=?");
        return executeUpdate(obj, sql.toString());
    }

    /**
     * 查找记录封装成Map键值对
     *
     * @param sql 特定查询语句
     * @param obj 根据的对象(用来填充参数)
     * @return HashMap 结果集封装Map
     */
    @Override
    public HashMap queryMap(String sql, Object obj) {
        return (HashMap) executeQuery(obj, sql, rs -> {
            HashMap resultMap = new HashMap<>();
            try {
                while (rs.next()) {
                    resultMap.put(rs.getObject(1), rs.getObject(2));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return resultMap;
        });
    }

    /**
     * 查找记录(只查找单一值)
     *
     * @param sql 查询语句
     * @param obj 用以填充的属性值
     * @return LinkedList 结果集封装LinkedList
     */
    @Override
    public LinkedList queryList(String sql, Object obj) {
            return (LinkedList) executeQuery(obj, sql, rs -> {
                LinkedList<Object> resultList = new LinkedList<>();
                try {
                    while (rs.next()) {
                        resultList.add(rs.getObject(1));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return resultList;
            });
        }


    /**
     * 查找所有属性
     * @param sql 查询语句
     * @param obj 用以填充的语句
     * @param clazz 相关类名(决定映射为什么对象)
     * @return LinkedList<Object> values 所有值
     */
    @Override
    public LinkedList queryAll(String sql, Object obj,Class clazz) {
        return (LinkedList) executeQuery(obj,sql,rs -> {
            LinkedList<Object> values = new LinkedList<>();
            ResultSetMetaData rsmd = null;
            try {
                rsmd = rs.getMetaData();
                LinkedList<Method> methods = getMethods(clazz.newInstance());
                //存储set方法
                LinkedList<Method> setMethods = new LinkedList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    /*
                      将列名转化为实体类属性名
                     */
                    String columnName = rsmd.getColumnName(i);
                    String fieldName = toEntityField(columnName);
                    /*
                      获取与列名有关的set方法,且会一一对应保证顺序
                     */
                    for (Method method : methods) {
                        if (method.getName().startsWith("set") && method.getName().substring(3).equalsIgnoreCase(fieldName)) {
                            setMethods.add(method);
                        }
                    }
                }
                /*
                  调用invoke执行set方法,映射成对象加入链表中
                 */
                while (rs.next()) {
                    Object newInstance = clazz.newInstance();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        if(rsmd.getColumnName(i).equals(("id"))){
                            Integer temp = rs.getInt(i);
                            setMethods.get(i-1).invoke(newInstance,temp.toString());
                        }else {
                            setMethods.get(i - 1).invoke(newInstance, rs.getObject(i));
                        }
                    }
                    values.add(newInstance);
                }
            } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException throwables) {
                throw new DaoException("映射对象出现异常");
            }
            return values;
    });
}

    /**
     * 将对象映射成属性名和属性值
     * @param obj 对象
     * @param fieldNames 属性名
     * @param fieldValues 属性值
     * @throws DaoException 数据库类异常
     */
    @Override
    public void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException {
        if (obj == null) {
            return;
        }
        /*
          取出包括父类在内的所有方法和属性
         */
        LinkedList<Method> methods = getMethods(obj);
        LinkedList<Field> fields = getFields(obj);
        for (Field field : fields) {
            /*
              获取get方法并invoke执行取得属性值
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
                    /*
                      只添加不为null值的字段
                     */
                    if (value != null) {
                        fieldValues.add(value);
                        /*
                          取出该属性的名称，映射成数据库字段名
                         */
                         fieldNames.add(toColumnName(field.getName()));
                    }
                }
            }
        }
    }


    /**
     * 根据xxx获取id
     * @param obj xxx
     * @return String id
     */
    @Override
    public LinkedList getId(Object obj) {
        /*
          根据前台所选中的信息构造对象，取出对应数据库字段名和值
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        String sql = "select id from " + getTableName(obj) + " where "+fieldNames.getFirst()+" =?";
        return queryList(sql,obj);
    }



}
