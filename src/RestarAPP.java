import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RestarAPP {


    public String userHome = System.getProperty("user.home");

    public String folderfilename = ".OtakuLibraryApp";

    public String appDirectoryPath = userHome + File.separator + folderfilename;
    public String filenameBat = appDirectoryPath + File.separator + "start_my_app.bat";

    public void StartResrar(){



        Path appPath =  Paths.get(System.getProperty("user.dir"), "OtakuLibraryApp.exe");;


        // Teraz `appPath` zawiera ścieżkę do katalogu zawierającego plik .jar lub .exe tej aplikacji
        System.out.println("Ścieżka do aplikacji: " + appPath);
        String batFilePath = filenameBat; // Ścieżka do pliku .bat

        for (int i = 0; i < 2; i++) {



        try {







            File appDirectory = new File(appDirectoryPath);
            if (!appDirectory.exists()) {
                // Tworzenie folderu, jeśli nie istnieje
                if (appDirectory.mkdirs()) {
                    System.out.println("Folder został utworzony.");
                } else {
                    System.out.println("Nie udało się utworzyć folderu.");
                }
            }
        // Tworzenie pliku .bat
            File batFile = new File(filenameBat);

            if (!batFile.exists()) {
        try (FileWriter writer = new FileWriter(batFilePath)) {
            writer.write("@echo off\n");
            writer.write("taskkill /F /IM \"your_app.exe\" 2>nul\n");
            writer.write("start \"\" \"" + appPath + "\"\n");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
            }else {
                try(FileWriter writer = new FileWriter(batFilePath, false)){
                    writer.write("@echo off\n");
                    writer.write("taskkill /F /IM \"your_app.exe\" 2>nul\n");
                    writer.write("start \"\" \"" + appPath + "\"\n");
                }catch (IOException ex){
                    ex.printStackTrace(System.out);
                }

                try {
                    // Uruchomienie pliku .bat
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", batFilePath);
                builder.start();
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }}



    }catch (SecurityException ex){
            System.err.println("Brak uprawnień administratora lub nie można uzyskać dostępu do folderu.");}}



}}

