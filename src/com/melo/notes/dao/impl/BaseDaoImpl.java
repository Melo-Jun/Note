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
 * @description ���ݿ�ͨ�ò���ʵ����
 * @date 2021-3-28 20:36
 */
public class BaseDaoImpl implements BaseDao {


    /**
     * ��װ���ݿ���²���
     *
     * @param obj ����
     * @param sql sql���
     * @return int Ӱ�������
     */
    @Override
    public int executeUpdate(Object obj, String sql) {
        //Ӱ�������
        int count = 0;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            assert conn != null;
            ps = conn.prepareStatement(sql);
            //ע��Sql������
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
     * ִ��һ����ѯ���,���Խ�������з�װ
     *
     * @param obj          ����
     * @param sql          sql���
     * @param resultMapper ʵ�ֲ�ͬ����ӳ���ʵ����
     * @return ӳ����
     */
    @Override
    public Object executeQuery(Object obj, String sql, ResultMapper resultMapper) {
        Connection conn = getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            assert conn != null;
            ps = conn.prepareStatement(sql);
            //����objע��Sql������
            if (obj != null) {
                setParams(ps, obj);
            }
            rs = ps.executeQuery();
            return resultMapper.doMap(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DaoException("Ԥ�����ѯ����쳣��");
        } finally {
            freeConnection(conn);
            close(ps, rs);
        }
    }


    /**
     * ����һ����¼�������ݿ�
     *
     * @param obj �������йصĶ���
     * @return int Ӱ������ݿ�����
     */
    @Override
    public int insert(Object obj) {
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj, fieldNames, fieldValues);
        /*
          ��������������Ԥ����sql�������
         */
        StringBuilder sql = new StringBuilder("insert into " + getTableName(obj) + " (");
        for (Object name : fieldNames) {
            sql.append(name.toString()).append(",");
        }
        //�����һ��","��Ϊ")"��ʡȥ�ж��Ƿ�Ϊ���һ��")"
        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values (");
        for (int i = 0; i < fieldNames.size(); i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');
        return executeUpdate(obj, sql.toString());
    }

    /**
     * ɾ����¼
     *
     * @param obj ��ɾ���йصĶ���
     * @return int Ӱ������ݿ��¼��
     */
    @Override
    public int delete(Object obj) {
        StringBuilder sql = new StringBuilder( "delete from "+getTableName(obj));
        /*
          ���sqlע���ִ��
         */
        appendWhereToSql(sql,obj);
        return executeUpdate(obj, sql.toString());
    }

    /**
     * ���¼�¼
     * @param obj ������йصĶ���
     * @return int Ӱ������ݿ��¼��
     */
    @Override
    public int update(Object obj) {
        /*
          ���ݸ������ݵ��ֶ����������ȡ����Ӧ���ݿ��ֶ�����ֵ
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj, fieldNames, fieldValues);
        StringBuilder sql = new StringBuilder("update " + getTableName(obj) + " set ");
        for(Object fieldName:fieldNames) {
            //�ȹ���id,idҪ���������Ϊ����
            if(!"id".equals(fieldName.toString())) {
                sql.append(fieldName).append("=?, ");
            }
        }
        //ɾ�����һ������
        sql.deleteCharAt(sql.length()-2);
        sql.append("where id=?");
        return executeUpdate(obj, sql.toString());
    }

    /**
     * ���Ҽ�¼��װ��Map��ֵ��
     *
     * @param sql �ض���ѯ���
     * @param obj ���ݵĶ���(����������)
     * @return HashMap �������װMap
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
     * ���Ҽ�¼(ֻ���ҵ�һֵ)
     *
     * @param sql ��ѯ���
     * @param obj ������������ֵ
     * @return LinkedList �������װLinkedList
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
     * ������������
     * @param sql ��ѯ���
     * @param obj �����������
     * @param clazz �������(����ӳ��Ϊʲô����)
     * @return LinkedList  ��װ��Ķ��󼯺�
     */
    @Override
    public LinkedList queryAll(String sql, Object obj,Class clazz) {
        return (LinkedList) executeQuery(obj,sql,rs -> {
            LinkedList<Object> values = new LinkedList<>();
            ResultSetMetaData rsmd = null;
            try {
                rsmd = rs.getMetaData();
                LinkedList<Method> methods = getMethods(clazz.newInstance());
                //�洢set����
                LinkedList<Method> setMethods = new LinkedList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    /*
                      ������ת��Ϊʵ����������
                     */
                    String columnName = rsmd.getColumnName(i);
                    String fieldName = toEntityField(columnName);
                    /*
                      ��ȡ�������йص�set����,�һ�һһ��Ӧ��֤˳��
                     */
                    for (Method method : methods) {
                        if (method.getName().startsWith("set") && method.getName().substring(3).equalsIgnoreCase(fieldName)) {
                            setMethods.add(method);
                        }
                    }
                }
                /*
                  ����invokeִ��set����,ӳ��ɶ������������
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
                throw new DaoException("ӳ���������쳣");
            }
            return values;
    });
}

    /**
     * ������ӳ���������������ֵ
     * @param obj ����
     * @param fieldNames ������
     * @param fieldValues ����ֵ
     * @throws DaoException ���ݿ����쳣
     */
    @Override
    public void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException {
        if (obj == null) {
            return;
        }
        /*
          ȡ�������������ڵ����з���������
         */
        LinkedList<Method> methods = getMethods(obj);
        LinkedList<Field> fields = getFields(obj);
        for (Field field : fields) {
            /*
              ��ȡget������invokeִ��ȡ������ֵ
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
                    /*
                      ֻ��Ӳ�Ϊnullֵ���ֶ�
                     */
                    if (value != null) {
                        fieldValues.add(value);
                        /*
                          ȡ�������Ե����ƣ�ӳ������ݿ��ֶ���
                         */
                         fieldNames.add(toColumnName(field.getName()));
                    }
                }
            }
        }
    }

    /**
     * ���where������sql���
     * @param sql sql���
     * @param obj ����ֵ����
     */
    @Override
    public void appendWhereToSql(StringBuilder sql, Object obj){
        /*
          ������ӳ������Ժ�ֵ(���Ի�ӳ��Ϊ���ݿ��ֶ���)
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        /*
          ���ֶ�������sql���
          û��where���������
         */
        if(fieldValues.size()!=0) {
            sql.append(" where ");
            for (Object fieldName : fieldNames) {
                sql.append(fieldName + "=? AND ");
            }
            //ɾ�����һ��AND
            sql.delete(sql.length()-4,sql.length());
        }
    }


    /**
     * ����xxx��ȡid
     * @param obj xxx
     * @return String id
     */
    @Override
    public LinkedList getId(Object obj) {
        StringBuilder sql =new StringBuilder( "select id from " + getTableName(obj) );
        appendWhereToSql(sql,obj);
        return queryList(sql.toString(),obj);
    }



}
