package com.melo.notes.service.impl;

import com.melo.notes.entity.Note;
import com.melo.notes.service.inter.TableService;

import java.util.Vector;


/**
 * @author Jun
 * @program Note
 * @description 表格页面相关逻辑实现类
 * @date 2021-4-8 16:16
 */
public class TableServiceImpl implements TableService {

    /**
     * 提取笔记属性填充表格
     * @param tempNote 临时的笔记对象
     * @return 属性值集合
     */
    @Override
    public Vector<Object> fillTable(Note tempNote) {
        Vector<Object> values = new Vector<>();
        values.add(tempNote.getId());
        values.add(tempNote.getTitle());
        values.add(tempNote.getAuthorId());
        values.add(tempNote.getAccess());
        values.add(tempNote.getLikeCount());
        values.add(tempNote.getLocatedGroup());
        return values;
    }

}
