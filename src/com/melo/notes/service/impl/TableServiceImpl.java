package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.LikeListDaoImpl;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.LikeList;
import com.melo.notes.entity.Note;
import com.melo.notes.service.inter.TableService;
import com.melo.notes.util.BeanFactory;
import com.melo.notes.view.LoginView;

import java.util.LinkedList;
import java.util.Vector;


/**
 * @author Jun
 * @program Note
 * @description 表格页面相关逻辑实现类
 * @date 2021-4-8 16:16
 */
public class TableServiceImpl implements TableService {
    /**
     * 创建相关操作类对象
     */
    private final NoteDaoImpl noteDao = (NoteDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.NoteDao);
    private final LikeListDaoImpl likeListDao=(LikeListDaoImpl)BeanFactory.getBean(BeanFactory.DaoType.LikeListDao);

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

    public LinkedList showLikeUser(String noteId){
        LikeList likeList = new LikeList();
        likeList.setNoteId(noteId);
        return likeListDao.showLikeUser(likeList);
    }

    public boolean updateLikeCount(String updateLikeCount,String noteId){
        Note note = new Note();
        note.setId(noteId);
        note.setLikeCount(updateLikeCount);
        return noteDao.update(note)==1;
    }


    public boolean increaseLikeCount(String updateLikeCount, String noteId){
        LinkedList likeUsers = showLikeUser(noteId);
        if(!likeUsers.isEmpty()&&likeUsers.contains(LoginView.USER.getId())){
            return false;
        }
        LikeList likeList = new LikeList();
        likeList.setNoteId(noteId);
        likeList.setUserId(LoginView.USER.getId());
        likeListDao.addLikeList(likeList);
        return updateLikeCount(updateLikeCount,noteId);
    }


    public boolean decreaseLikeCount(String updateLikeCount, String noteId){
        LinkedList likeUsers = showLikeUser(noteId);
        if(!likeUsers.contains(LoginView.USER.getId())){
            return false;
        }
        LikeList likeList = new LikeList();
        likeList.setNoteId(noteId);
        likeList.setUserId(LoginView.USER.getId());
        likeListDao.deleteLikeList(likeList);
        return updateLikeCount(updateLikeCount,noteId);
    }

}
