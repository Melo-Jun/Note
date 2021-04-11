package com.melo.notes.service.inter;


import com.melo.notes.entity.Note;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 展示标题页面相关逻辑接口
 * @date 2021-4-3 20:24
 */
public interface NoteService {

    /**
     * 将LinkedList转化为String数组返回给List作为填充数据
     * 填充标题
     * @param obj 对象
     * @return String[]标题大全
     */
    LinkedList showNoteTitle(Object obj);

    /**
     *展示笔记详情内容(Text另外单独分页)
     * @param obj 相关对象
     * @return String[] 笔记所有值链表
     */
    LinkedList showNoteAll(Object obj);


    //String[] listNoteAll(Object obj);

    /**
     * 展示笔记文本内容
     * @param obj 相关对象
     * @return
     */
    String showNoteText(Object obj);

    /**
     * 更改笔记内容
     * @param note
     * @return
     */
     int update(Note note);
}
