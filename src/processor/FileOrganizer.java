package processor;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileOrganizer {

    // CATEGORY -> EXTENSIONS
    private HashMap<String, HashSet<String>>
            categoryExtensions;



    // CONSTRUCTOR
    public FileOrganizer(
            HashMap<String, HashSet<String>>
                    categoryExtensions
    ) {

        this.categoryExtensions =
                categoryExtensions;
    }



    // =====================================
    // ORGANIZE FILES
    // =====================================

    public void organizeFiles(
            File selectedFolder
    ) {

        // INVALID FOLDER
        if(selectedFolder == null ||
                !selectedFolder.exists()) {

            System.out.println(
                    "Invalid Folder"
            );

            return;
        }



        // GET FILES
        File[] files =
                selectedFolder.listFiles();



        if(files == null) {
            return;
        }



        // THREAD POOL
        ExecutorService executor =
                Executors.newFixedThreadPool(4);



        // PROCESS FILES
        for(File file : files) {

            // SKIP DIRECTORIES
            if(file.isDirectory()) {
                continue;
            }



            executor.execute(

                    new FileProcessor(
                            file,
                            selectedFolder,
                            categoryExtensions
                    )
            );
        }



        // STOP THREADS
        executor.shutdown();
    }
}