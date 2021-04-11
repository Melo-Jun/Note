package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.GroupDaoImpl;
import com.melo.notes.dao.impl.LikeListDaoImpl;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.dao.impl.UserDaoImpl;
import com.melo.notes.entity.Group;
import com.melo.notes.entity.LikeList;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.service.inter.NoteTableService;
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
public class NoteTableServiceImpl implements NoteTableService {
    /**
     * 创建相关操作类对象
     */
    private final NoteDaoImpl noteDao = (NoteDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.NoteDao);
    private final LikeListDaoImpl likeListDao=(LikeListDaoImpl)BeanFactory.getBean(BeanFactory.DaoType.LikeListDao);
    private final UserDaoImpl userDao=(UserDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.UserDao);
    private final GroupDaoImpl groupDao=(GroupDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.GroupDao);
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

    /**
     * 获取点赞过该笔记的用户
     * @param noteId 笔记id
     * @return 用户链表
     */
    @Override
    public LinkedList showLikeUser(String noteId){
        LikeList likeList = new LikeList();
        likeList.setNoteId(noteId);
        return likeListDao.showLikeUser(likeList);
    }

    /**
     * 更新点赞数
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 是否操作成功
     */
    @Override
    public boolean updateLikeCount(String updateLikeCount, String noteId){
        Note note = new Note();
        note.setId(noteId);
        note.setLikeCount(updateLikeCount);
        return noteDao.updateNote(note)==1;
    }

    /**
     * 点赞
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 操作是否成功
     */
    @Override
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


    /**
     * 取消点赞
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 操作是否成功
     */
    @Override
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

    /**
     * 展示笔记作者名
     * @param authorId 作者id
     * @return 作者名
     */
    @Override
    public String showNoteAuthor(String authorId){
        User user = new User();
        user.setId(authorId);
        return userDao.showUserName(user);
    }

    /**
     * 展示笔记分组名
     * @param groupId 笔记id
     * @return String 笔记分组名
     */
    @Override
    public String showGroupName(String groupId){
        Group group = new Group();
        group.setId(groupId);
        return groupDao.showGroupName(group);
    }
}
