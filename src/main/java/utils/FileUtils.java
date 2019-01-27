package utils;

import java.io.File;

public class FileUtils {

    /**
     * Moves file from one location to another.
     * Deletes file from destination if it already exists, before moving
     * @param file file to move
     * @param destination pathname to move file to
     * @return was move successful
     */
    public static boolean moveFile(File file, String destination){

        File existingFile = new File(destination);
        if(existingFile.exists()){
            existingFile.delete();
        }

        return file.renameTo(new File(destination));
    }
}
