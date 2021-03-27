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
 * @description ͨ�����ݿ����ʵ����
 * @date 2021-3-27 11:05
 */
public class BaseDaoImpl implements BaseDao {

    //private static BaseDaoImpl INSTANCE;

    public static BaseDaoImpl getInstance() {
        return new BaseDaoImpl();
    }

    /**
     * ����һ������
     * @param obj ������
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
     * ������ӳ���������������ֵ
     * @param obj ����
     * @param fieldNames ������
     * @param fieldValues ����ֵ
     */
    @Override
    public void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException {
        if (obj == null) {
            return;
        }

        /**
         * ȡ�������������ڵ����з���������
         */
        LinkedList<Method> methods = getMethods(obj);
        LinkedList<Field> fields = getFields(obj);
        //�޳���id(insertʱid��Ĭ������)
        fields.pop();
        for (Field field : fields) {
            /**
             * ȡ��ÿ�����Ե�ֵ
             */
            for (Method method : methods) {
                if (method.getName().startsWith("get") && method.getName().substring(3).equalsIgnoreCase(field.getName())) {
                    Object value = null;
                    try {
                        value = method.invoke(obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new DaoException("����ִ��get�����쳣��" + method.getName(), e);
                    }
                    /**
                     * ֻ��Ӳ�Ϊnullֵ���ֶ�
                     */
                    if (value != null) {
                        fieldValues.add(value);
                        /**
                         * ȡ�������Ե����ƣ�ӳ������ݿ��ֶ���
                         */
                       // fieldNames.add(field2SqlField(field.getName()));
                    }
                }
            }
        }
    }

    /**
     * �жϲ����Ƿ�ɹ�
     * @param ps
     * @return boolean �Ƿ�ɹ�
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
            throw new DaoException("���޴���");
        }
    }
}
