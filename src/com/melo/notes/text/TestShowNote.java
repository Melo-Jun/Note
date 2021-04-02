package com.melo.notes.text;

import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.Note;
import com.melo.notes.entity.User;
import com.melo.notes.view.ShowNoteTitleView;

import java.util.LinkedList;

/**
 * @author Jun
 * @program Note
 * @description Õ¹Ê¾±Ê¼Ç
 * @date 2021-3-29 18:23
 */
public class TestShowNote {
    public static void main(String[] args) {
        Note note = new Note();
        note.setLocatedGroup("1");
        //new ShowNoteTitleView(user);
        LinkedList<Object> objects = new NoteDaoImpl().showNoteTitle(note,note);
        System.out.println(objects);
    }
}
