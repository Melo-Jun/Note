import com.melo.notes.bean.NoteTextBean;
import com.melo.notes.dao.impl.NoteDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.NoteTextView;

public class TextNoteTextView {
    public static void main(String[] args) {
        LoginView.USER=new User();
        LoginView.USER.setId("1");
        new NoteTextView(new NoteDaoImpl().showNoteText(new NoteTextBean("想"))).setVisible(true);
    }
}
