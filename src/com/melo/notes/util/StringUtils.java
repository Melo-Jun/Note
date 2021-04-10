

package com.melo.notes.util;


/**
 * @author Jun
 * @program Note
 * @description �����ַ�����ת��
 * @date 2021-3-30 19:42
 */
public class StringUtils {

    /**
     * ��������ת��Ϊ���ݿ��ֶ���
     * @param fieldName ������
     * @return
     */
    public static StringBuilder toColumnName(String fieldName){

        StringBuilder columnName=new StringBuilder(fieldName);
        for(int i=0;i<columnName.length();i++){
            if(columnName.charAt(i)>='A'&&columnName.charAt(i)<='Z'){
                columnName.insert(i,'_');
                columnName.setCharAt(i+1, (char) (columnName.charAt(i+1)+32));
            }
        }return columnName;
    }

    /**
     * �����ݿ��ֶ���ת��Ϊ������
     * @param columnName ���ݿ��ֶ���
     * @return
     */
    public static String toEntityField(String columnName){

        StringBuilder filedName=new StringBuilder(columnName);
        for(int i=0;i<filedName.length();i++){
            if(filedName.charAt(i)=='_'){
                filedName.deleteCharAt(i);
                filedName.setCharAt(i+1,(char)(filedName.charAt(i+1)-32));
            }
        }
        return filedName.toString();
    }

    /**
     * ���ַ���+1
     * @param temp ���޸��ַ���
     * @return
     */
    public static String increaseOne(String temp) {
        char[] chars = temp.toCharArray();
        int i = (int)chars[0] + 1;
        return String.valueOf((char)i);
    }

    /**
     * ���ַ���-1
     * @param temp ���޸��ַ���
     * @return �޸ĺ���ַ���
     */
    public static String decreaseOne(String temp) {
        char[] chars = temp.toCharArray();
        int i = (int)chars[0] - 1;
        return "0".equals(temp)?temp: String.valueOf((char)i);
    }
}
