package com.melo.notes.text;

import com.melo.notes.entity.User;
import com.melo.notes.view.ShowNoteView;

/**
 * @author Jun
 * @program Note
 * @description չʾ�ʼ�
 * @date 2021-3-29 18:23
 */
public class TestShowNote {
    public static void main(String[] args) {
        User user = new User("����", "1111");
        new ShowNoteView(user);
    }
}
