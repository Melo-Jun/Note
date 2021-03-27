package com.melo.notes.impl;

import com.melo.notes.exception.DaoException;
import com.melo.notes.inter.BaseDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import static com.melo.notes.util.JdbcUtil.getConnection;
import static com.melo.notes.util.ReflectUtils.getFields;
import static com.melo.notes.util.ReflectUtils.getMethods;

/**
 * @author Jun
 * @program Note
 * @description 通用数据库操作实现类
 * @date 2021-3-27 11:05
 */
public class BaseDaoImpl implements BaseDao {

    //private static BaseDaoImpl INSTANCE;

    public static BaseDaoImpl getInstance() {
        return new BaseDaoImpl();
    }

    /**
     * 增加一个对象
     * @param obj 对象名
     * @return
     */
    @Override
    public boolean insert(Object obj) {
        Connection conn= getConnection();
        PreparedStatement ps=null;
        String sql="insert into user values (id,?,?)";
        try {
            ps=conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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
        //剔除掉id(insert时id会默认自增)
        fields.pop();
        for (Field field : fields) {
            /**
             * 取出每个属性的值
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
                       // fieldNames.add(field2SqlField(field.getName()));
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
