//Copyright 2022 Artiom Mirolyubov
//Licensed under the Apache License, Version 2.0
package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                System.out.println("Unhandled command: " + command);
                break;
        }
    }

    private void openFile() {
        JFileChooser fileOpen = new JFileChooser();
        int ret = fileOpen.showDialog(null, "Open File");
        if (ret == JFileChooser.APPROVE_OPTION) {
            ConfigFile configFile = ConfigFile.getInstance();
            configFile.setRecentFile(fileOpen.getSelectedFile().getAbsolutePath());
        }
    }
}
