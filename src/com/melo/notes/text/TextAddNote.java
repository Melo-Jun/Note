package com.melo.notes.text;

import com.melo.notes.entity.User;
import com.melo.notes.view.AddNoteView;

/**
 * @author Jun
 * @program Note
 * @description 测试新增笔记
 * @date 2021-3-27 16:49
 */
public class TextAddNote {

    public static void main(String[] args) {
        User user = new User("鸣人", "1111");
        new AddNoteView(user).setVisible(true);
    }
}
