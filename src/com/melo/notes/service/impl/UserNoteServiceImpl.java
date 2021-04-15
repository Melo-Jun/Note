package com.melo.notes.service.impl;

import com.melo.notes.dao.impl.GroupDaoImpl;
import com.melo.notes.entity.Group;
import com.melo.notes.entity.Note;
import com.melo.notes.service.inter.UserNoteService;
import com.melo.notes.util.BeanFactory;

import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description 管理员查看用户笔记信息页面相关逻辑实现类
 * @date 2021-4-11 19:19
 */
public class UserNoteServiceImpl extends BaseServiceImpl implements UserNoteService {

    GroupDaoImpl groupDao=(GroupDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.GroupDao);

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
        values.add(tempNote.getLikeCount());
        values.add(tempNote.getLocatedGroup());
        return values;
    }

    /**
     * 计算笔记分组个数
     * @param authorId 作者id
     * @return String 笔记分组个数
     */
    @Override
    public String countGroup(String authorId){
        if(super.notNull(authorId)) {
            Group group = new Group();
            group.setAuthorId(authorId);
            return groupDao.countGroup(group);
        }
        return null;
    }
}
