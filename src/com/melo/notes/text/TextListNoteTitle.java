import com.melo.notes.entity.User;
import com.melo.notes.view.ListNoteTitle;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.List;

/**
 * @author Jun
 * @program Note
 * @description Õ¹Ê¾±Ê¼Ç
 * @date 2021-3-29 18:23
 */
public class TextListNoteTitle {
    public static void main(String[] args) {
        LoginView.USER=new User();
        LoginView.USER.setId("1");
        new ListNoteTitle();
    }
}
