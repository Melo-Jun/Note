package com.melo.notes.text;

import com.melo.notes.entity.User;
import com.melo.notes.view.FolderView;

/**
 * @author Jun
 */
public class TextFolderView {
    public static void main(String[] args) {
        User user = new User("√˘»À", "1111");
        new FolderView(user);
    }
}
