package com.company;

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
                rootFrame.finish();
                break;
            default:
                System.out.println("Unhandled command: " + command);
                break;
        }
    }
}
