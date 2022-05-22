package com.maximus.filepocessor;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;


public class FileWalker {

    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                //System.out.println( "Dir:" + f.getAbsoluteFile());
            }
            else {
                //System.out.println( "File:" + f.getAbsoluteFile() );
                try{
                    if(isTextFile(f.getName()))
                    {
                        String fPath = f.getCanonicalPath();
                        fPath = fPath.replace('\\', '/');
                        Controller.getInstance().getPaths().add(fPath);
                    }
                }catch (IOException ex){
                    System.out.println(ex);
                }

            }
        }
    }

    public boolean isTextFile (String name){
        String extension = name.substring(name.lastIndexOf(".") + 1);
        return Controller.getInstance().isKnownExtension(extension);
    }

}
