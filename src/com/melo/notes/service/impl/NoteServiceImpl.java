package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Note;
import com.melo.notes.service.inter.NoteService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.FolderView;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.TreeView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description 展示标题页面相关逻辑接口
 * @date 2021-4-3 20:24
 */
public class NoteServiceImpl extends BaseServiceImpl implements NoteService {

    /**
     * 创建相关操作类对象
     */
    private final NoteDaoImpl noteDao = (NoteDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.NoteDao);

    /**
     * 根据笔记分组id填充标题
     * @description 用在嵌套生成JTree时
     * @param groupId 笔记分组id
     * @return HashMap id-name键值对
     */
    @Override
    public HashMap showNoteTitle(String groupId) {
        if(super.notNull(groupId)) {
            Note note = new Note();
            note.setLocatedGroup(groupId);
            return noteDao.showNoteTitle(note);
        }
        return null;
    }

    /**
     *展示笔记详情内容(Text另外单独分页)
     * @param obj 相关对象
     * @return LinkedList 笔记所有值链表
     */
   @Override
   public LinkedList<Note> showNoteAll(Object obj) {
       return noteDao.showNoteAll(obj);
   }


    /**
     * 根据笔记id展示笔记文本内容
     *
     * @param noteId 笔记id
     * @return String 笔记文本内容
     */
    @Override
    public String showNoteText(String noteId) {
        if(super.notNull()) {
            Note note = new Note();
            note.setId(noteId);
            return noteDao.showNoteText(note);
        }
        return null;
    }

    /**
     * 更改笔记内容
     * @param title 标题
     * @param text 文本内容
     * @param access 权限
     * @param noteId 笔记id
     * @return int 影响的行数
     */
    @Override
    public int updateNote(String noteId,String title,String text,String access) {
        if(super.notNull(noteId,title,text,access)) {
            Note note = new Note(title, text, access);
            note.setId(noteId);
            return noteDao.updateNote(note);
            }
        return 0;
    }

    /**
     * 删除笔记
     * @param noteId 笔记id
     * @return int 影响的行数
     */
    @Override
    public int delete(String noteId){
        if(super.notNull(noteId)) {
            Note note = new Note();
            note.setId(noteId);
            return noteDao.delete(note);
        }
        return 0;
    }
}
