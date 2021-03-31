package com.melo.notes.text;

import com.melo.notes.entity.User;
import com.melo.notes.view.FloderView;

/**
 * @author Jun
 */
public class TextFloderView {
    public static void main(String[] args) {
        User user = new User("√˘»À", "1111");
        new FloderView(user);
    }
}
