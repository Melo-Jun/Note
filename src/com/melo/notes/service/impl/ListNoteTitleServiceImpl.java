package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.exception.DaoException;
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
     * @param obj
     * @return String[]标题大全
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
     *展示笔记详情内容(Text另外单独分页)
     * @param obj 根据的对象
     * @return String[] 笔记详情
     */
    @Override
    public String[] listNoteAll(Object obj) {
        LinkedList<Object> objects = noteDao.showNoteAll(obj);
        String[] strings = new String[100];
        int i=0;
        for(Object temp:objects){
            strings[i]=(String) temp;
            i++;
        }
        return strings;
    }

    /**
     * 展示笔记文本内容
     *
     * @param obj
     * @return
     */
    @Override
    public String showNoteText(Object obj) {
        return noteDao.showNoteText(obj);
    }

    /*public void fieldMapper(Object obj, LinkedList fieldNames, LinkedList fieldValues) throws DaoException {
        noteDao.fieldMapper(obj,fieldNames,fieldValues);
    }*/
}
