package com.maximus.filepocessor;

public class FileProcessorLauncher {


    public static void main(String[] args) {
        String rootPath = args[0];
        String outputFileName = args[1];

        Controller.getInstance().init(rootPath, outputFileName);
        Controller.getInstance().parseFileTree();
        Controller.getInstance().work();
        Controller.getInstance().saveOutputFile();


    }
}
