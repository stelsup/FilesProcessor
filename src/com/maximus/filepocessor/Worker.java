package com.maximus.filepocessor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Worker {


    public void processFiles() {

         for(String path : Controller.getInstance().getPaths()) {
             if(!Controller.getInstance().isFileProcessed(path)) {
                 processFile(path);
             }
        }
    }

    public void processFile(String path) {
        List<String> tempFileContent;

        Controller.getInstance().setFileProcessed(path);
        tempFileContent = readFileContents(path);

        Iterator<String> it = tempFileContent.iterator();

        while(it.hasNext()) {
            String str = it.next();
            if(str.matches("require\\s+'[^']*'")){
                int in = str.indexOf('\'') + 1;
                int out = str.lastIndexOf('\'');
                String tmp = Controller.getInstance().getRootPath() + "/" + str.substring(in, out);
                tmp = tmp.replace('\\', '/');

                if(!Controller.getInstance().isFileProcessed(tmp)){
                    processFile(tmp);
                }
                it.remove();
            }
        }

        Controller.getInstance().appendContents(tempFileContent);

    }

    public List<String> readFileContents(String fileName) {
        List<String> contents = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            while(scanner.hasNext()) {
                String string = scanner.nextLine();
                contents.add(string);
            }
            fileReader.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

}
