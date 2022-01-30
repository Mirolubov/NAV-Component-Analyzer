//Copyright 2022 Artiom Mirolyubov
//Licensed under the Apache License, Version 2.0
package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FileListener implements ActionListener {
    private Main rootFrame;

    public FileListener(Main rootFrame) {
        this.rootFrame = rootFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case ("Close"):
                rootFrame.close();
                break;
            case "Open":
                openFile();
                break;
            default:
                processFile(command);
                break;
        }
    }

    private void openFile() {
        JFileChooser fileOpen = new JFileChooser();
        int ret = fileOpen.showDialog(null, "Open File");
        if (ret == JFileChooser.APPROVE_OPTION) {
            ConfigFile configFile = ConfigFile.getInstance();
            String path = fileOpen.getSelectedFile().getAbsolutePath();
            configFile.setRecentFile(path);
            File file = new File(path);
            processFile(file.getName());
        }
    }

    private void processFile(String command) {
        ConfigFile configFile = ConfigFile.getInstance();
        ArrayList<String> recentFiles = configFile.getRecentFiles();
        for (String path : recentFiles) {
            File file = new File(path);
            if(file.getName().equals(command)) {
                if (file.exists()) {
                    ProcessFile process = new ProcessFile(file);
                    process.printObjects();
                } else {
                    String fileNotExists = String.format("File %s not exists", path);
                    System.out.println(fileNotExists);
                    JOptionPane.showMessageDialog(rootFrame, fileNotExists);
                }
            }
        }
    }
}
