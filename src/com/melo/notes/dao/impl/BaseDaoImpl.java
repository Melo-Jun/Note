package com.melo.notes.dao.impl;

import com.melo.notes.dao.inter.BaseDao;
import com.melo.notes.exception.DaoException;
import com.melo.notes.util.StringUtils;
import com.sun.javafx.collections.MappingChange;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

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
            System.out.println(sql);
            System.out.println(count);
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
     * @return int 影响的数据库记录数
     */
    @Override
    public int delete(Object obj) {
        String sql = "delete from " + getTableName(obj) + " where id =?";
        return executeUpdate(obj,sql);
    }

    /**
     * 更新记录
     * @param obj 与更新有关的对象
     * @return int 影响的数据库记录数
     */
    @Override
    public int update(Object obj) {
        /**
         * 根据更新依据的字段名构造对象，取出对应数据库字段名和值
         * 此处id是在父类里边,故fieldNames.getFirst不是id
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        StringBuilder sql = new StringBuilder("update "+getTableName(obj)+" set "+fieldNames.getFirst()+" =?" +" where id=?");
        System.out.println(sql.toString());
        return executeUpdate(obj,sql.toString());
    }

    /**
     * 查找记录封装成Map键值对
     *
     * @param sql 特定查询语句
     * @param obj 根据的对象(用来填充参数)
     * @return HashMap 结果集封装Map
     */
    @Override
    public HashMap<Object, Object> queryMap(String sql, Object obj) {
        HashMap<Object,Object> resultMap = new HashMap<>();
        Connection conn=getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(sql);
            //根据obj注入Sql填充参数
            setParams(ps,obj);
            rs=ps.executeQuery();
            while(rs.next()){
                resultMap.put(rs.getObject(1),rs.getObject(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,rs);
        }
        return resultMap;


        /**
         * 根据搜索依据的字段名构造对象，取出对应数据库字段名和值
         */
        /*LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(src,fieldNames,fieldValues);
        /**
         * des用来获取表名，src用来填充sql
         */
       /* StringBuilder sql=new StringBuilder("select * from "+getTableName(des)+" where "+ fieldNames.getFirst()+" =?");
        /**
         * 将src的value值传过去填充
         */
        /*System.out.println(fieldNames.getFirst());
        System.out.println(fieldValues);
        System.out.println(fieldValues.getFirst());
        return executeQuery(src,sql.toString(),fieldValues.getFirst());*/
    }

    /**
     * 查找记录(只查找单一值)
     *
     * @param sql 查询语句
     * @param obj 用以填充的属性值
     * @return LinkedList 结果集封装LinkedList
     */
    @Override
    public LinkedList<Object> queryList(String sql, Object obj) {
        LinkedList<Object> resultList = new LinkedList<>();
        Connection conn=getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(sql);
            //根据obj注入Sql填充参数
            setParams(ps,obj);
            System.out.println(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                resultList.add(rs.getObject(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,rs);
        }
        return resultList;
    }

    /**
     * 查找所有属性
     * @param sql 查询语句
     * @param obj 用以填充的语句
     * @return LinkedList<Object> values 所有值
     */
    @Override
    public LinkedList<Object> queryAll(String sql, Object obj) {
        LinkedList<Object> values = new LinkedList<>();
        Connection conn=getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        ResultSetMetaData rsmd=null;
        try {
            ps=conn.prepareStatement(sql);
            //根据obj注入Sql填充参数
            setParams(ps,obj);
            rs=ps.executeQuery();
            rsmd=rs.getMetaData();
            while(rs.next()){
                for(int i=1;i<=rsmd.getColumnCount();i++){
                    // text另外分页展示
                    if(!rsmd.getColumnName(i).equals("text")) {
                        values.add(rsmd.getColumnName(i)+" :   "+ rs.getObject(i));
                    }
                }
            }
            System.out.println(values);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            freeConnection(conn);
            close(ps,rs);
        }
        return values;
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
     * 根据xxx获取id
     * @param obj xxx
     * @return String id
     */
    @Override
    public String getId(Object obj) {
        /**
         * 根据前台所选中的信息构造对象，取出对应数据库字段名和值
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        StringBuilder sql = new StringBuilder("select id from " + getTableName(obj) + " where "+fieldNames.getFirst()+" =?");
        return queryList(sql.toString(),obj).getFirst().toString();
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
