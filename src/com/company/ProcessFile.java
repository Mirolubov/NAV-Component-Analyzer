package com.company;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class ProcessFile {
    private final NavObjects navObjects;

    public ProcessFile(File file) {
        navObjects = new NavObjects();
        try {
            LineIterator it = FileUtils.lineIterator(file, "CP866");
            while (it.hasNext()) {
                String line = it.nextLine();
                fillNavObjectList(line);
            }
        }catch  (IOException e){
            e.printStackTrace();
        }
    }

    private void fillNavObjectList(String line) {
        String[] parts = line.split(" ");
        if(parts.length < 4 )
            return;
        if(parts[0].equals("OBJECT")){
            int id;
            String name;
            String strNavType;
            strNavType = parts[1];
            id = Integer.parseInt(parts[2]);
            name = parts[3];
            navObjects.add(id, name, strNavType);
        }
    }

    public void printObjects() {
        navObjects.print();
    }
}
