import com.melo.notes.entity.User;
import com.melo.notes.view.LoginView;
import com.melo.notes.view.ShowNoteTitleView;

/**
 * @author Jun
 * @program Note
 * @description չʾ�ʼ�
 * @date 2021-3-29 18:23
 */
public class TestShowNote {
    public static void main(String[] args) {
        LoginView.USER=new User();
        LoginView.USER.setId("1");
        new ShowNoteTitleView(LoginView.USER);
    }
}
