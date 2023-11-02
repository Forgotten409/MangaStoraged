import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;


public class LanguageChange{

    RestarAPP restarAPP = new RestarAPP();

    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icone_app.png")));

    JFrame frame = new JFrame("OtakuLibrary");

    JPanel panel = new JPanel();

    JButton button = new JButton("PL");
    JButton button2 = new JButton("EN");


    String languagechanger = "en";
    Locale userLocale = new Locale(languagechanger);
    ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);

    public String userHome = System.getProperty("user.home");

    public String folderfilename = ".OtakuLibraryApp";

    public String appDirectoryPath = userHome + File.separator + folderfilename;
    public String filenameLanguage = appDirectoryPath + File.separator + "LanguageSaveChoice.txt";



    public void startprogramlanguage(){




        frame.setSize(200,100);

        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);

        frame.setIconImage(icon.getImage());

        frame.add(panel);

        panel.setBackground(Color.darkGray);

        frame.setResizable(false);

        frame.setLocationRelativeTo(null);

        panel.add(button);
        panel.add(button2);

        button.addActionListener(e -> {
            languagechanger = "pl";
            savedatalanguage();
            restarAPP.StartResrar();
            System.exit(0);

        });

        button2.addActionListener(e -> {

            languagechanger = "en";
            savedatalanguage();
            restarAPP.StartResrar();
            System.exit(0);

        });


        frame.setVisible(true);

    }
    public void savedatalanguage(){

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

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameLanguage ,false))) {

            // Format danych w pliku tekstowym - możesz dostosować go do swoich potrzeb
            String line = languagechanger;
            writer.write(line);
            writer.newLine(); // Dodanie nowej linii między rekordami

            System.out.println("Dane zostały zapisane do pliku " + filenameLanguage);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }catch (SecurityException ex){System.err.println("Brak uprawnień administratora lub nie można uzyskać dostępu do folderu.");}}

    public void readDatalanguage(){

        File dataSaveFile = new File(filenameLanguage);
        if (dataSaveFile.exists()){try (BufferedReader reader = new BufferedReader(new FileReader(filenameLanguage))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tutaj musisz podzielić linię na części, używając odpowiedniego separatora (np. przecinka)
                String[] parts = line.split(",");
                if (parts.length == 1) {

                    languagechanger = parts[0];




                }
            }
            System.out.println("Dane zostały wczytane z pliku " + filenameLanguage);

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }}


    }


    }





