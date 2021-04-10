

package com.melo.notes.util;


/**
 * @author Jun
 * @program Note
 * @description 用于字符串的转换
 * @date 2021-3-30 19:42
 */
public class StringUtils {

    /**
     * 将属性名转化为数据库字段名
     * @param fieldName 属性名
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
     * 将数据库字段名转化为属性名
     * @param columnName 数据库字段名
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
     * 将字符串+1
     * @param temp 待修改字符串
     * @return
     */
    public static String increaseOne(String temp) {
        char[] chars = temp.toCharArray();
        int i = (int)chars[0] + 1;
        return String.valueOf((char)i);
    }

    /**
     * 将字符串-1
     * @param temp 待修改字符串
     * @return 修改后的字符串
     */
    public static String decreaseOne(String temp) {
        char[] chars = temp.toCharArray();
        int i = (int)chars[0] - 1;
        return "0".equals(temp)?temp: String.valueOf((char)i);
    }
}
