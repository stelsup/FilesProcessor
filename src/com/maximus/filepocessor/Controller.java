package com.maximus.filepocessor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Controller {
    private static Controller instance;

    private String rootPath;
    private String outputFiles;

    private List<String> paths;
    private List<String> contents;
    private List<String> processedPaths;

    private List<String> extensions;

    private Controller() {}

    public static Controller getInstance() {
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public void init(String rootPath, String outputFile){
        this.paths = new ArrayList<>();
        this.contents = new ArrayList<>();
        this.processedPaths = new ArrayList<>();
        this.extensions = new ArrayList<>();
        initExtensionList();
        this.rootPath = rootPath;
        this.outputFiles = outputFile;

    }

    public void initExtensionList() {
        try {
            FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/.extensions");
            Scanner scanner = new Scanner(fileReader);
            while(scanner.hasNext()) {
                String string = scanner.nextLine();
                extensions.add(string);
            }
            fileReader.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(extensions.isEmpty()){
            extensions.add("txt");
        }

    }

    public void parseFileTree(){
        FileWalker walker = new FileWalker();
        walker.walk(rootPath);
        Collections.sort(paths);
    }

    public void work(){
        Worker worker = new Worker();
        worker.processFiles();
    }

    public List<String> getPaths() {
        return paths;
    }
    public String getRootPath() {
        return rootPath;
    }

    public boolean isKnownExtension(String extension) {
        return extensions.contains(extension);
    }

    public void setFileProcessed(String name) {
        processedPaths.add(name);
    }

    public boolean isFileProcessed(String name) {
        return processedPaths.contains(name);
    }

    public void appendContents(List<String> content) {
        contents.addAll(content);
    }

    public void saveOutputFile() {
        try {
            FileWriter fileWriter = new FileWriter(outputFiles);
            for(String str : contents){
                fileWriter.write(str + "\n");
            }
            fileWriter.close();

        }catch (IOException ex){
            System.out.println(ex);
        }

    }
}
