import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;




public class GUI extends Component {


    LanguageChange languageChange = new LanguageChange();




    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icone_app.png")));
    JFrame frame = new JFrame("OtakuLibrary");
    JButton addbutton = new JButton(languageChange.messages.getString("button.addbutton"));

    JLabel label = new JLabel(languageChange.messages.getString("label.label"));
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();

    GUIADDED guiadded = new GUIADDED();

    JPanel rootpanel = new JPanel();

    JButton deletebutton = new JButton(languageChange.messages.getString("button.deletebutton"));


    deletemanga deletemanga = new deletemanga();

    JTextField mangafindName = new JTextField(20);

    JButton addbutton2 = new JButton(languageChange.messages.getString("button.addbutton2"));

    String textname ;

    JLabel label2 = new JLabel(textname);
    JMenuBar menuBar = new JMenuBar();
    JMenu settingsMenu = new JMenu(languageChange.messages.getString("menu.settingsMenu"));
    JMenuItem exportMenuItem = new JMenuItem(languageChange.messages.getString("menuitem.exportMenuItem"));
    JMenuItem importMenuItem = new JMenuItem(languageChange.messages.getString("menuitem.importMenuItem"));
    JMenuItem languagemenu = new JMenuItem(languageChange.messages.getString("menuitem.languagemenu"));

    JMenuItem deletealldata = new JMenuItem(languageChange.messages.getString("menuitem.deletealldata"));

    JMenu Infomenu = new JMenu(languageChange.messages.getString("menu.Infomenu"));

    JMenuItem MEandInfo = new JMenuItem(languageChange.messages.getString("menuitem.MEandInfo"));

    JMenuItem Changelog = new JMenuItem(languageChange.messages.getString("menuitem.Changelog"));

    JButton Editmanga = new JButton(languageChange.messages.getString("button.Editmanga"));

    EditManga editManga1 = new EditManga();

    ChangelogWrite changelogWrite = new ChangelogWrite();


    JScrollPane scrollPane = new JScrollPane(guiadded.list);

    RestarAPP restarAPP = new RestarAPP();

    public GUI() {

    }




    public void StartGui() {



        languageChange.readDatalanguage();

        updateButtonLabels();   //<----- odswiezenie


        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setSize(800, 800);

        frame.setIconImage(icon.getImage());

        frame.setResizable(false);

        frame.setLocationRelativeTo(null);

        panel.add(addbutton);
        panel.add(label);
        panel.add(deletebutton);
        panel.add(Editmanga);

        rootpanel.add(panel);

        frame.add(rootpanel);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);


        frame.setJMenuBar(menuBar);
        menuBar.add(settingsMenu);
        settingsMenu.add(languagemenu);
        settingsMenu.add(deletealldata);
        settingsMenu.add(exportMenuItem);
        settingsMenu.add(importMenuItem);
        exportMenuItem.setForeground(Color.BLACK);
        settingsMenu.setForeground(Color.BLACK);
        importMenuItem.setForeground(Color.BLACK);
        menuBar.add(Infomenu);
        Infomenu.setForeground(Color.BLACK);
        languagemenu.setForeground(Color.BLACK);
        Infomenu.add(MEandInfo);
        Infomenu.add(Changelog);
        menuBar.setBackground(Color.lightGray);

        if (languageChange.languagechanger.equals("pl")){
            textname = ("Szukanie po nazwie!");
            label2 = new JLabel(textname);
        }else if (languageChange.languagechanger.equals("en")){
            textname = ("Search by name!");
            label2 = new JLabel(textname);

        }

        if (languageChange.languagechanger.equals("pl")){
            importMenuItem.setToolTipText("<html><font color='red'>Aplikacja zawsze bedzie sie restartowala przy waznych zmianach.</font></html>");
            ToolTipManager.sharedInstance().setInitialDelay(0);
        }else if (languageChange.languagechanger.equals("en")){
            importMenuItem.setToolTipText("<html><font color='red'>The application will always restart after important changes.</font></html>");
            ToolTipManager.sharedInstance().setInitialDelay(0);

        }

        Changelog.addActionListener(e -> {
            changelogWrite.Changelogstartprogram();

        });

        languagemenu.addActionListener(e -> {
            languageChange.startprogramlanguage();

        });

        deletealldata.addActionListener(e -> {

            if (languageChange.languagechanger.equals("pl")){
                int choice = JOptionPane.showConfirmDialog(null, "<html><font color='red'>Wszystkie dane zostana usuniete. (Uzyj tego, gdy napotkasz problem)</font></html>", "Potwierdzenie usuniecia", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {

                    File folder = new File(restarAPP.appDirectoryPath);

                    if (folder.exists() && folder.isDirectory()) {
                        deleteFolder(folder);
                        System.exit(0); // Zamknij program
                    }




                }

            }else if (languageChange.languagechanger.equals("en")){
                int choice = JOptionPane.showConfirmDialog(null, "<html><font color='red'>All data will be deleted. (Use this when you encounter a problem)</font></html>", "Confirmation of deletion", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {


                    File folder = new File(restarAPP.appDirectoryPath);

                    if (folder.exists() && folder.isDirectory()) {
                        deleteFolder(folder);
                        System.exit(0); // Zamknij program
                    }
                }

            }
        });




        panel.setBackground(Color.GRAY);
        rootpanel.setBackground(Color.DARK_GRAY);
        panel2.setBackground(Color.DARK_GRAY);
        panel2.add(mangafindName);
        panel2.add(addbutton2);
        panel2.add(label2);

        addbutton.addActionListener(e -> guiadded.StartGuiAddedManga());


        guiadded.readData();

        scrollPane.setPreferredSize(new Dimension(600, 600));
        scrollPane.getViewport().getView().setBackground(Color.gray);
        scrollPane.getViewport().getView().setForeground(Color.gray);


        panel2.add(scrollPane);
        frame.setVisible(true);


        frame.setBackground(Color.GRAY);

        deletebutton.addActionListener(e -> {
            deletemanga.Satrtdeleteprogram();

        });


        addbutton2.addActionListener(e -> {
            findMangaName();
        });

        Editmanga.addActionListener(e -> {
            editManga1.EditManga2();
           editManga1.setVisible(true);
        });

        exportMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(); // Tworzenie okna wyboru pliku
            int result = fileChooser.showSaveDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File destinationFile = fileChooser.getSelectedFile();
                Path sourcePath = Paths.get(guiadded.filename);
                Path destinationPath = Paths.get(destinationFile.getAbsolutePath());

                try {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(frame, "Plik DataSave.txt został skopiowany do " + destinationPath);
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                    JOptionPane.showMessageDialog(frame, "Wystąpił błąd podczas kopiowania pliku.");
                }
            }


        });

        importMenuItem.addActionListener(e -> {



            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File appDirectory = new File(guiadded.appDirectoryPath);
                if (!appDirectory.exists()) {
                    // Tworzenie folderu, jeśli nie istnieje
                    if (appDirectory.mkdirs()) {
                        System.out.println("Folder został utworzony.");
                    } else {
                        System.out.println("Nie udało się utworzyć folderu.");
                    }
                }


                File selectedFile = fileChooser.getSelectedFile();
                File dataSaveFile = new File(guiadded.filename);




                // Jeśli plik "DataSave.txt" nie istnieje, to go utwórz
                if (!dataSaveFile.exists()) {
                    try {
                        dataSaveFile.createNewFile();
                    } catch (IOException ex) {
                        ex.printStackTrace(System.out);
                    }
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(dataSaveFile, true))) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Kopiowanie danych z wybranego pliku do pliku "DataSave.txt"
                        writer.write(line);
                        writer.newLine();


                    }
                    Timer timer = getTimer();

                    timer.start();

                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        });


    }

    private Timer getTimer() {
        int delayInSeconds = 2; // Opóźnienie w sekundach
        Timer timer = new Timer(delayInSeconds * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutaj umieść kod, który ma być uruchomiony po opóźnieniu
                System.out.println("Zdarzenie opóźnione o " + delayInSeconds + " sekundy.");
                restarAPP.StartResrar();

                System.exit(0);

            }
        });

        timer.setRepeats(false); // Ustawienie, aby timer działał tylko raz
        return timer;
    }


    public void findMangaName() {
        label2.setText("");
        textname = mangafindName.getText();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(guiadded.filename))) {
            String line;
            int foundIndex = -1; // Indeks znalezionej mangi
            int currentIndex = 0; // Aktualny indeks w liście
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(textname)) {
                    if (languageChange.languagechanger.equals("en")){
                        label2.setText("Found a search named: " + textname);
                        found = true;
                        foundIndex = currentIndex;
                        break; // Jeśli znaleziono, można przerwać pętlę

                    }else if (languageChange.languagechanger.equals("pl")){
                        label2.setText("Znaleziono wyszukiwanie o nazwie: " + textname);
                        found = true;
                        foundIndex = currentIndex;
                        break; // Jeśli znaleziono, można przerwać pętlę
                    }

                }
                currentIndex++;
            }




            if (!found && languageChange.languagechanger.equals("pl")) {
                label2.setText("Nie znaleziono mangi o nazwie: " + textname);
            } else if (!found && languageChange.languagechanger.equals("en")){
                label2.setText("No search named: " + textname);
            }else

            {
                // Przewiń JScrollPane, aby wyświetlić odnalezioną mangę
                JViewport viewport = scrollPane.getViewport();
                int height = guiadded.list.getFixedCellHeight(); // Wysokość jednego elementu na liście
                int visibleRowCount = viewport.getHeight() / height; // Liczba widocznych elementów
                int scrollIndex = Math.max(0, foundIndex - (visibleRowCount / 2)); // Indeks do przewinięcia

                guiadded.list.setSelectedIndex(foundIndex); // Zaznacz odnalezioną mangę
                guiadded.list.ensureIndexIsVisible(foundIndex); // Upewnij się, że odnaleziona manga jest widoczna
                guiadded.list.ensureIndexIsVisible(scrollIndex); // Przewiń do indeksu
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    public void updateButtonLabels() {
        Locale userLocale = new Locale(languageChange.languagechanger);
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);

        deletebutton.setText(messages.getString("button.deletebutton"));
        addbutton.setText(messages.getString("button.addbutton"));
        label.setText(messages.getString("label.label"));
        addbutton2.setText(messages.getString("button.addbutton2"));
        settingsMenu.setText(messages.getString("menu.settingsMenu"));
        exportMenuItem.setText(messages.getString("menuitem.exportMenuItem"));
        importMenuItem.setText(messages.getString("menuitem.importMenuItem"));
        languagemenu.setText(messages.getString("menuitem.languagemenu"));
        Infomenu.setText(messages.getString("menu.Infomenu"));
        MEandInfo.setText(messages.getString("menuitem.MEandInfo"));
        Changelog.setText(messages.getString("menuitem.Changelog"));
        Editmanga.setText(messages.getString("button.Editmanga"));
        deletealldata.setText(messages.getString("menuitem.deletealldata"));





        //odswiezenie wrzystkich napsiuw tutaj hahaha XDD
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }


    }









