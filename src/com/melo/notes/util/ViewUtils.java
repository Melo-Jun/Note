package com.melo.notes.util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jun
 * @program Note
 * @description View层工具类
 * @date 2021-4-13 18:17
 */
public class ViewUtils {

    /**
     * 设置全局字体
     */
    public static void setUiFont()
    {
        Font f = new Font("微软雅黑",Font.PLAIN,16);
        String[] names ={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField","TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item+ ".font",f);
        }
    }


}
