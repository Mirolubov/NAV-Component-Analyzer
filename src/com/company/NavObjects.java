package com.company;

import java.util.ArrayList;

public class NavObjects {
    private final ArrayList<NavObject> navObjectsList;

    public NavObjects() {
        navObjectsList = new ArrayList<>();
    }
    
    private static class NavObject {
        private final int id;
        private final String name;
        private final NavType navType;
        public NavObject(int id, String name, NavType navType){
            this.id = id;
            this.name = name;
            this.navType = navType;
        }
        @Override
        public String toString() {
            return String.format("%s %s (%d)", navType.toString(), name, id);
        }
    }
    
    public void add(int id, String name, String strNavType) {
        NavType navType = null; 
        switch (strNavType){
            case "Table": navType = NavType.Table; break;
            case "Codeunit": navType = NavType.Codeunit; break;
            case "Report": navType = NavType.Report; break;
            case "Page": navType = NavType.Page; break;
            default: return;
        }
        NavObject newNavObject = new NavObject(id, name, navType);
        navObjectsList.add(newNavObject);
    }
    
    public void print() {
        navObjectsList.forEach((n) -> System.out.println(n.toString()));
    }
}

enum NavType {
    Table,
    Codeunit,
    Report,
    Page
}
