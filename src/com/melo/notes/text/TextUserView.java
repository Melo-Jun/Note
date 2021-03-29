package com.melo.notes.text;

import com.melo.notes.entity.User;
import com.melo.notes.view.UserView;



/**
 * @author Jun
 * @program Note
 * @description 测试用户界面相关功能
 * @data 2021-3-28 15:21
 */
public class TextUserView {

    public static void main(String[] args) {
        User user = new User("鸣人", "1111");
        new UserView(user).setVisible(true);
    }
}
