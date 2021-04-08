package com.melo.notes.service.inter;

import com.melo.notes.entity.Note;

import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description 表格页面相关逻辑接口
 * @date 2021-4-8 16:16
 */
public interface TableService {

    /**
     * 提取笔记属性填充表格
     * @param tempNote 临时的笔记对象
     * @return 属性值集合
     */
     Vector<Object> fillTable(Note tempNote);
}
