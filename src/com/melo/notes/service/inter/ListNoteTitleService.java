package com.melo.notes.service.inter;


/**
 * @author Jun
 * @program Note
 * @description 展示标题页面相关逻辑接口
 * @date 2021-4-3 20:24
 */
public interface ListNoteTitleService {

    /**
     * 将LinkedList转化为String数组返回给List作为填充数据
     * 填充标题
     * @param obj
     * @return
     */
    String[] listNoteTitle(Object obj);

    /**
     *
     * @param obj
     * @return
     */
    String[] listNoteAll(Object obj);
}
