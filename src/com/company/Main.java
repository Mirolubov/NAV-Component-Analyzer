//Copyright 2022 Artiom Mirolyubov
//Licensed under the Apache License, Version 2.0

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Main extends JFrame{

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        //--MENU
        JMenuBar menubar = createMenuBar();
        setJMenuBar(menubar);

        // -------------------------------------------
        // настройка окна
        setTitle("Navision Component Analyzer"); // заголовок окна
        // желательные размеры окна
        //setPreferredSize(new Dimension(320, 200));
        setMinimumSize(new Dimension(320, 200));
        //setMaximumSize(new Dimension(640, 480));
        // завершить приложение при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // устанавливаем желательные размеры
        setVisible(true); // отображаем окно
    }

    private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        // создаем меню
        JMenu menu = createMenuFile();
        menubar.add(menu);

        menu = createMenuThemes();
        menubar.add(menu);
        return menubar;
    }

    private JMenu createMenuFile() {
        JMenu menu = new JMenu("File");
        FileListener fileListener = new FileListener(this);
        // ------------------------------------
        // добавление простых элементов меню
        // элемент 1
        JMenuItem itm = new JMenuItem("Open");
        itm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        menu.add(itm);
        itm.addActionListener(fileListener);

        // элемент 2
        JMenu submenu = new JMenu("OpenRecent");
        itm = new JMenuItem("Recent File 1");
        itm.addActionListener(fileListener);
        submenu.add(itm);
        itm = new JMenuItem("Recent File 2");
        itm.addActionListener(fileListener);
        submenu.add(itm);
        itm = new JMenuItem("Recent File 3");
        itm.addActionListener(fileListener);
        submenu.add(itm);
        menu.add(submenu);

        // элемент 3
        itm = new JMenuItem("Close");
        itm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.CTRL_MASK));
        itm.addActionListener(fileListener);
        menu.add(itm);
        return menu;
    }

    private JMenu createMenuThemes() {
        JMenu menu = new JMenu("Themes");
        ButtonGroup group = new ButtonGroup();
        ThemeListener themeListener = new ThemeListener(this);
        ArrayList<Theme> themeList = themeListener.getThemeList();
        String currentLookAndFeelName =
                UIManager.getLookAndFeel().getClass().getName();
        for (Theme theme : themeList) {
            JRadioButtonMenuItem itm = new JRadioButtonMenuItem (theme.name);
            itm.addActionListener(themeListener);
            if (currentLookAndFeelName.equals(theme.className)) {
                itm.setSelected(true);
            }
            group.add(itm);
            menu.add(itm);
        }
        return menu;
    }

    public void finish() {
        dispose();
        System.exit(0);
    }


}
