package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.service.inter.ListNoteTitleService;
import com.melo.notes.util.BeanFactory;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 展示标题页面相关逻辑接口
 * @date 2021-4-3 20:24
 */
public class ListNoteTitleServiceImpl implements ListNoteTitleService {

    /**
     * 创建相关操作类对象
     */
    private NoteDaoImpl noteDao = (NoteDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.NoteDao);

    /**
     * 将LinkedList转化为String数组返回给List作为填充数据
     * 填充标题
     * @return String[]
     */
    @Override
    public String[] listNoteTitle(Object obj) {
        LinkedList<Object> objects = noteDao.showNoteTitle(obj);
        String[] strings = new String[100];
        int i=0;
        for(Object temp:objects){
            strings[i]=(String) temp;
            i++;
        }
        return strings;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public String[] listNoteAll(Object obj) {
        LinkedList<Object> objects = noteDao.listNoteAll(obj);
        String[] strings = new String[100];
        int i=0;
        for(Object temp:objects){
            strings[i]=(String) temp;
            i++;
        }
        return strings;
    }


}
