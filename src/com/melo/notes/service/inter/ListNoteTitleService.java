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
     * @return String[]标题大全
     */
    String[] listNoteTitle(Object obj);

    /**
     *展示笔记详情内容(Text另外单独分页)
     * @param obj 根据的对象
     * @return String[] 笔记详情
     */
    String[] listNoteAll(Object obj);

    /**
     * 展示笔记文本内容
     * @param obj
     * @return
     */
    String showNoteText(Object obj);
}
