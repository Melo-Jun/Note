package com.melo.notes.service.inter;

import com.melo.notes.entity.Note;

import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description 用户笔记信息页面相关逻辑接口
 * @date 2021-4-11 19:19
 */
public interface UserNoteService {

    /**
     * 提取笔记属性填充表格
     * @param tempNote 临时的笔记对象
     * @return 属性值集合
     */
     Vector<Object> fillTable(Note tempNote);

    /**
     * 计算笔记分组个数
     * @param authorId 作者id
     * @return String 笔记分组个数
     */
     String countGroup(String authorId);
}
