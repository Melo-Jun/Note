package com.melo.notes.service.inter;

import com.melo.notes.entity.Note;

import java.util.Vector;

/**
 * @author Jun
 * @program Note
 * @description �û��ʼ���Ϣҳ������߼��ӿ�
 * @date 2021-4-11 19:19
 */
public interface UserNoteService {

    /**
     * ��ȡ�ʼ����������
     * @param tempNote ��ʱ�ıʼǶ���
     * @return ����ֵ����
     */
     Vector<Object> fillTable(Note tempNote);

    /**
     * ����ʼǷ������
     * @param authorId ����id
     * @return String �ʼǷ������
     */
     String countGroup(String authorId);
}
