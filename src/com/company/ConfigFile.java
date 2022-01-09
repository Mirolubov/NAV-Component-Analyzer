//Copyright 2022 Artiom Mirolyubov
//Licensed under the Apache License, Version 2.0
package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigFile {
    private static ConfigFile INSTANCE = new ConfigFile();
    private final String fileName = "NavCompanantAnalyzer.config";;
    private HashMap<String, String> configValues;
    private static final String THEME = "Theme";

    public void setTheme(String value) {
        setConfigValue(THEME, value);
    }
    public String getTheme() {
        return getConfigValue(THEME);
    }
    public String getConfigValue(String key) {
        return configValues.get(key);
    }
    public void setConfigValue(String key, String value) {
        configValues.put(key, value);
    }

    public static ConfigFile getInstance() {
        return INSTANCE;
    }

    public void closeAndSaveConfig() throws IOException{
        OutputStream outputStream = null;
        try {
            File file = new File(fileName);
            outputStream = new FileOutputStream(file);
            writeToOutputStream(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try{
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ConfigFile() {
        configValues = new HashMap<>();
        InputStream inputStream = null;
        try {
            File file = new File(fileName);
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    readFromInputStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void readFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split("=", 2);
                setConfigValue(splitLine[0], splitLine[1]);
            }
        }
    }

    private void writeToOutputStream(OutputStream outputStream) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            for (Map.Entry entry : configValues.entrySet()) {
                bw.write(entry.toString());
            }
        }
    }
}
