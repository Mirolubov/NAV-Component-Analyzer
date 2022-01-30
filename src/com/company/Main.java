//Copyright 2022 Artiom Mirolyubov
//Licensed under the Apache License, Version 2.0

package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame{
    private JPanel treePanel;
    private JPanel controlPanel;

    public static void main(String[] args) {
        Main navCompAnalyzer = new Main();
        navCompAnalyzer.showTree();
    }

    public Main() {
        prepareGUI();
    }

    private void prepareGUI() {
        //--MENU
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);
        // -------------------------------------------
        setTitle("Navision Component Analyzer"); // заголовок окна
        setMinimumSize(new Dimension(320, 200));
        setLayout(new GridLayout(1, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        add(treePanel);
        add(controlPanel);

        pack(); // устанавливаем желательные размеры
        SwingUtilities.updateComponentTreeUI(this);
        setVisible(true); // отображаем окно
    }
    private void showTree() {
        // Create a root tree item as department
        DefaultMutableTreeNode department = new DefaultMutableTreeNode("Department");

        //create other tree items as department names
        DefaultMutableTreeNode salesDepartment = new DefaultMutableTreeNode("Sales");
        DefaultMutableTreeNode marketingDepartment = new DefaultMutableTreeNode("Marketing");
        DefaultMutableTreeNode manufacturingDepartment = new DefaultMutableTreeNode("Manufacturing");

        //create other tree items as employees
        DefaultMutableTreeNode employee1 = new DefaultMutableTreeNode("Robert");
        DefaultMutableTreeNode employee2 = new DefaultMutableTreeNode("Joe");
        DefaultMutableTreeNode employee3 = new DefaultMutableTreeNode("Chris");

        //add employees to sales department
        salesDepartment.add(employee1);
        salesDepartment.add(employee2);
        salesDepartment.add(employee3);

        //create other tree items as employees
        DefaultMutableTreeNode employee4 = new DefaultMutableTreeNode("Mona");
        DefaultMutableTreeNode employee5 = new DefaultMutableTreeNode("Tena");


        //add employees to marketing department
        marketingDepartment.add(employee4);
        marketingDepartment.add(employee5);

        //create other tree items as employees
        DefaultMutableTreeNode employee6 = new DefaultMutableTreeNode("Rener");
        DefaultMutableTreeNode employee7 = new DefaultMutableTreeNode("Linda");

        //add employees to sales department
        manufacturingDepartment.add(employee6);
        manufacturingDepartment.add(employee7);

        //add departments to department item
        department.add(salesDepartment);
        department.add(marketingDepartment);
        department.add(manufacturingDepartment);

        //create the tree with department as root node
        JTree tree = new JTree(department);
        JScrollPane treeView = new JScrollPane(tree);
        treePanel.add(treeView, BorderLayout.WEST);

        setVisible(true);
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
        JMenu submenu = new JMenu("Open Recent");
        ConfigFile configFile = ConfigFile.getInstance();
        ArrayList<String> recentFiles = configFile.getRecentFiles();
        for (String path : recentFiles) {
            File file = new File(path);
            if(file.exists()) {
                itm = new JMenuItem(file.getName());
                itm.addActionListener(fileListener);
                submenu.add(itm);
            }
        }
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
