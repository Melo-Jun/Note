

import com.melo.notes.entity.User;
import com.melo.notes.view.AddNoteView;

/**
 * @author Jun
 * @program Note
 * @description ���������ʼ�
 * @date 2021-3-27 16:49
 */
public class TextAddNote {

    public static void main(String[] args) {
        User user = new User("����", "1111");
        new AddNoteView(user).setVisible(true);
    }
}
