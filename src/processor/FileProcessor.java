package processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FileProcessor
        implements Runnable {

    // =====================================
    // STORES LOGS
    // =====================================

    public static ArrayList<String>
            operationLogs =
            new ArrayList<>();



    // =====================================
    // VARIABLES
    // =====================================

    private File file;

    private File rootFolder;

    private HashMap<String, HashSet<String>>
            categoryExtensions;



    // =====================================
    // CONSTRUCTOR
    // =====================================

    public FileProcessor(
            File file,
            File rootFolder,
            HashMap<String, HashSet<String>>
                    categoryExtensions
    ) {

        this.file = file;

        this.rootFolder = rootFolder;

        this.categoryExtensions =
                categoryExtensions;
    }



    // =====================================
    // THREAD METHOD
    // =====================================

    @Override
    public void run() {

        try {

            // FILE NAME
            String fileName =
                    file.getName();



            // FILE EXTENSION
            String extension =
                    getExtension(fileName);



            // FIND CATEGORY
            String category =
                    findCategory(extension);



            // CATEGORY FOLDER
            File categoryFolder =
                    new File(
                            rootFolder,
                            category
                    );



            // CREATE FOLDER IF NOT EXISTS
            if(!categoryFolder.exists()) {

                categoryFolder.mkdir();
            }



            // DESTINATION PATH
            Path destination =
                    Paths.get(
                            categoryFolder.getAbsolutePath(),
                            file.getName()
                    );



            // MOVE FILE
            Files.move(
                    file.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );



            // SUCCESS MESSAGE
            String successMessage =
                    "✓ "
                            + file.getName()
                            + " -> "
                            + category;



            // SAVE TO LOG LIST
            operationLogs.add(
                    successMessage
            );



            // SAVE TO LOG FILE
            logOperation(
                    successMessage
            );



            System.out.println(
                    successMessage
            );
        }

        catch(IOException e) {

            // ERROR MESSAGE
            String errorMessage =
                    "✗ "
                            + file.getName()
                            + " could not be moved";



            // SAVE TO LOG LIST
            operationLogs.add(
                    errorMessage
            );



            // SAVE TO LOG FILE
            logOperation(
                    errorMessage
            );



            System.out.println(
                    errorMessage
            );

            e.printStackTrace();
        }
    }



    // =====================================
    // GET FILE EXTENSION
    // =====================================

    private String getExtension(
            String fileName
    ) {

        int dotIndex =
                fileName.lastIndexOf(".");


        // NO EXTENSION
        if(dotIndex == -1) {

            return "";
        }



        return fileName.substring(
                dotIndex + 1
        ).toLowerCase();
    }



    // =====================================
    // FIND CATEGORY
    // =====================================

    private String findCategory(
            String extension
    ) {

        for(String category :
                categoryExtensions.keySet()) {

            HashSet<String> extensions =
                    categoryExtensions.get(
                            category
                    );



            if(extensions.contains(
                    extension
            )) {

                return category;
            }
        }



        // DEFAULT CATEGORY
        return "Unorganized";
    }



    // =====================================
    // SAVE LOG TO FILE
    // =====================================

    private void logOperation(
            String message
    ) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "organizer.log",
                            true
                    );



            writer.write(
                    message + "\n"
            );



            writer.close();
        }

        catch(IOException e) {

            e.printStackTrace();
        }
    }
}