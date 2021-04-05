import com.melo.notes.dao.impl.FolderDaoImpl;
import com.melo.notes.entity.User;
import com.melo.notes.view.FolderView;
import com.melo.notes.view.LoginView;
import org.junit.jupiter.api.Test;

/**
 * @author Jun
 */
public class TextFolderView {

    @Test
    public void test01(){
        LoginView.USER = new User();
        LoginView.USER.setId("1");
        new FolderView(LoginView.USER);
        //new FolderDaoImpl().showFolderName(LoginView.USER);
    }

    public static void main(String[] args) {
        LoginView.USER = new User();
        LoginView.USER.setId("1");
        new FolderView(LoginView.USER);
    }



}



