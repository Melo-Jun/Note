

import com.melo.notes.entity.User;
import com.melo.notes.view.AddNoteView;
import com.melo.notes.view.LoginView;

/**
 * @author Jun
 * @program Note
 * @description ²âÊÔÐÂÔö±Ê¼Ç
 * @date 2021-3-27 16:49
 */
public class TextAddNote {

    public static void main(String[] args) {
        LoginView.USER=new User();
        LoginView.USER.setId("1");
        new AddNoteView(LoginView.USER);
    }
}
