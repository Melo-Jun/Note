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
public class NoteTableServiceImpl extends BaseServiceImpl implements NoteTableService {
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
     * 判断该用户是否点赞过该笔记
     * @param noteId 该笔记id
     * @return boolean 是否点赞过
     */
    @Override
    public boolean everLike(String noteId){
        if(super.notNull(noteId)) {
            LikeList likeList = new LikeList();
            likeList.setNoteId(noteId);
            likeList.setUserId(LoginView.USER.getId());
            return likeListDao.everLike(likeList);
        }
        return false;
    }

    /**
     * 更新点赞数
     * @description 用于点赞和取消点赞函数
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 是否操作成功
     */
    @Override
    public boolean updateLikeCount(Integer updateLikeCount, String noteId){
        if(super.notNull(noteId)) {
            Note note = new Note();
            note.setId(noteId);
            note.setLikeCount(updateLikeCount);
            return noteDao.updateNote(note) == 1;
        }
        return false;
    }

    /**
     * 点赞
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 操作是否成功
     */
    @Override
    public boolean increaseLikeCount(Integer updateLikeCount, String noteId){
        if(super.notNull(noteId)) {
            if(everLike(noteId)){
                return false;
            }
            LikeList likeList = new LikeList();
            likeList.setNoteId(noteId);
            likeList.setUserId(LoginView.USER.getId());
            likeListDao.addLikeList(likeList);
            return updateLikeCount(updateLikeCount, noteId);
        }
        return false;
    }


    /**
     * 取消点赞
     * @param updateLikeCount 更改后的点赞数
     * @param noteId 笔记id
     * @return 操作是否成功
     */
    @Override
    public boolean decreaseLikeCount(Integer updateLikeCount, String noteId){
        if(super.notNull(noteId)) {
            //如果没点赞过,则无法取消点赞
            if (!everLike(noteId)) {
                return false;
            }
            LikeList likeList = new LikeList();
            likeList.setNoteId(noteId);
            likeList.setUserId(LoginView.USER.getId());
            likeListDao.deleteLikeList(likeList);
            return updateLikeCount(updateLikeCount, noteId);
        }
        return false;
    }

    /**
     * 根据作者id展示笔记作者名
     * @param authorId 作者id
     * @return 作者名
     */
    @Override
    public String showNoteAuthor(String authorId){
        if(super.notNull(authorId)) {
            User user = new User();
            user.setId(authorId);
            return userDao.showUserName(user);
        }
        return null;
    }

    /**
     * 展示笔记分组名
     * @param groupId 笔记id
     * @return String 笔记分组名
     */
    @Override
    public String showGroupName(String groupId){
        if(super.notNull(groupId)) {
            Group group = new Group();
            group.setId(groupId);
            return groupDao.showGroupName(group);
        }
        return null;
    }
}
