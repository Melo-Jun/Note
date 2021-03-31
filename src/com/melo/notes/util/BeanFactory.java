package com.melo.notes.util;

import com.melo.notes.exception.DaoException;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Jun
 * @program Note
 * @description 负责生产实现类
 * @date 2021-03-30 9:52
 */
    public class BeanFactory {

        /**
         * 配置文件
         */
        private static final String PROPERTIES = "factory.properties";

        private BeanFactory() {

        }

        public enum DaoType {
            /**
             * Dao实现类
             */
            UserDao("UserDao"), NoteDao("NoteDao"),LoginDao("LoginDao"),
            FolderDao("FolderDao"),GroupDao("GroupDao");

            private String name;

            /**
             * 构造方法
             * @param name
             */
            DaoType(String name) {
                this.name=name;
            }
        }

        public static Object getBean(Enum beanType) {
            String className;
            Object bean;
            /**
             * 加载配置文件
             */
            Properties prop = new Properties();
            try {
                prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
                className = prop.getProperty(beanType.name());
            } catch (IOException e) {
                e.printStackTrace();
                throw new DaoException("无法加载配置文件 ：" + PROPERTIES, e);
            }
            try {
                bean = Class.forName(className).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new DaoException("无法加载类 : " + className, e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new DaoException("无法实例化类 : " + className, e);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new DaoException("无法初始化实例 ：" + className, e);
            }
            return bean;
        }

    }

