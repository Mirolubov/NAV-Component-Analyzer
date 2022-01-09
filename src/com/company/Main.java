//Copyright 2022 Artiom Mirolyubov
//Licensed under the Apache License, Version 2.0

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame{

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        //--MENU
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
        // -------------------------------------------
        setTitle("Navision Component Analyzer"); // заголовок окна
        setMinimumSize(new Dimension(320, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        pack(); // устанавливаем желательные размеры
        SwingUtilities.updateComponentTreeUI(this);
        setVisible(true); // отображаем окно
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar= new JMenuBar();
        // создаем меню
        JMenu menu = createMenuFile();
        menuBar.add(menu);

        menu = createMenuThemes();
        menuBar.add(menu);
        return menuBar;
    }

    private JMenu createMenuFile() {
        JMenu menu = new JMenu("File");
        FileListener fileListener = new FileListener(this);
        // ------------------------------------
        // добавление простых элементов меню
        // элемент 1
        JMenuItem itm = new JMenuItem("Open");
        itm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_DOWN_MASK));
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
                InputEvent.CTRL_DOWN_MASK));
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

        String configLookAndFeelName = ConfigFile.getInstance().getTheme();
        if(configLookAndFeelName != null) {
            if(!configLookAndFeelName.equals(currentLookAndFeelName)) {
                currentLookAndFeelName = configLookAndFeelName;
                try {
                    UIManager.setLookAndFeel(currentLookAndFeelName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

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

    public void close() {
        try {
            ConfigFile.getInstance().closeAndSaveConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
        System.exit(0);
    }


}
