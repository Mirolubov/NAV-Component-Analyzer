package com.company;

import com.sun.java.swing.ui.WizardDlg;

import java.util.ArrayList;

public class NavObjects {
    private ArrayList<NavObject> navObjectsList;

    private static class NavObject {
        private int id;
        private String name;
        private NavType navType;
        public NavObject(int id, String name, NavType navType){
            this.id = id;
            this.name = name;
            this.navType = navType;
        }
    }
    enum NavType {
        Table,
        Codeunit,
        Report,
        Page
    }
}
