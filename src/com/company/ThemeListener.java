package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ThemeListener  implements ActionListener {
    private ArrayList<Theme> themeList;
    private JFrame rootFrame;

    public ThemeListener(JFrame rootFrame) {
        this.rootFrame = rootFrame;
        this.themeList = getUILookAndFill();
    }

    public ArrayList<Theme> getThemeList() {
        return themeList;
    }

    private static ArrayList<Theme> getUILookAndFill() {
        ArrayList<Theme> lookAndFeelList = new ArrayList<>();
        UIManager.LookAndFeelInfo[] infoArray =
                UIManager.getInstalledLookAndFeels();

        for(UIManager.LookAndFeelInfo info : infoArray) {
            Theme t = new Theme();
            t.name = info.getName();
            t.className = info.getClassName();
            lookAndFeelList.add(t);
        }
        return lookAndFeelList;
    }

    private void changeTheme(String themeClassName) {
        String currentLookAndFeelClassName =
                UIManager.getLookAndFeel().getClass().getName();
        if (currentLookAndFeelClassName.equals(themeClassName)) {
            return;
        }
        try {
            UIManager.setLookAndFeel(themeClassName);
            SwingUtilities.updateComponentTreeUI(rootFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Theme theme : themeList) {
            if (e.getActionCommand().equals(theme.name)) {
                changeTheme(theme.className);
                return;
            }
        }
    }
}
