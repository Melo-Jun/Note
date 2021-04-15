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
 * @description ����Ա�鿴�û��ʼ���Ϣҳ������߼�ʵ����
 * @date 2021-4-11 19:19
 */
public class UserNoteServiceImpl extends BaseServiceImpl implements UserNoteService {

    GroupDaoImpl groupDao=(GroupDaoImpl) BeanFactory.getBean(BeanFactory.DaoType.GroupDao);

    /**
     * ��ȡ�ʼ����������
     * @param tempNote ��ʱ�ıʼǶ���
     * @return ����ֵ����
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
     * ����ʼǷ������
     * @param authorId ����id
     * @return String �ʼǷ������
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
