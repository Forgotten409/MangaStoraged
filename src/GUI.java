import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class GUI {

    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icone_app.png")));
    JFrame frame = new JFrame("Magazyn Mang");
    JButton addbutton = new JButton("Dodaj");

    JLabel label = new JLabel("Dodaj mange! Lub usuń!");
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();

    GUIADDED guiadded = new GUIADDED();

    JPanel rootpanel = new JPanel();

    JButton deletebutton = new JButton("Usuń");

    deletemanga deletemanga = new deletemanga();

    JTextField mangafindName = new JTextField(20);

    JButton addbutton2 = new JButton("Szukaj");

    String textname = "Szukanie Mangi po nazwi";

    JLabel label2 = new JLabel(textname);
    JMenuBar menuBar = new JMenuBar();
    JMenu settingsMenu = new JMenu("Ustawienia");
    JMenuItem exportMenuItem = new JMenuItem("Exportuj dane na pulpit!");
    JMenuItem importMenuItem = new JMenuItem("Importuj dane do aplikacji!");
    JMenu Infomenu = new JMenu("Info");

    JMenuItem MEandInfo = new JMenuItem("Stworzony przez: Forgotten409" + " " +
            "Wersja: 1.0 ");










    JScrollPane scrollPane = new JScrollPane(guiadded.list);
    public GUI() {


    }


    public void StartGui() {
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setSize(800, 800);

        frame.setIconImage(icon.getImage());




        panel.add(addbutton);
        panel.add(label);
        panel.add(deletebutton);

        rootpanel.add(panel);

        frame.add(rootpanel);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, panel2);


        frame.setJMenuBar(menuBar);
        menuBar.add(settingsMenu);
        settingsMenu.add(exportMenuItem);
        settingsMenu.add(importMenuItem);
        exportMenuItem.setForeground(Color.BLACK);
        settingsMenu.setForeground(Color.BLACK);
        importMenuItem.setForeground(Color.BLACK);
        menuBar.add(Infomenu);
        Infomenu.setForeground(Color.BLACK);
        Infomenu.add(MEandInfo);

        importMenuItem.setToolTipText("<html><font color='red'>Musisz uruchomić ponownie aplikację po imporcie danych.</font></html>");
        ToolTipManager.sharedInstance().setInitialDelay(0); // Natychmiastowe wyświetlanie




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

        exportMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(); // Tworzenie okna wyboru pliku
            int result = fileChooser.showSaveDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                File destinationFile = fileChooser.getSelectedFile();
                Path sourcePath = Paths.get("DataSave.txt");
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
                File selectedFile = fileChooser.getSelectedFile();
                File dataSaveFile = new File("DataSave.txt");

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
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        });


    }






    public void findMangaName() {
        label2.setText("");
        textname = mangafindName.getText();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("DataSave.txt"))) {
            String line;
            int foundIndex = -1; // Indeks znalezionej mangi
            int currentIndex = 0; // Aktualny indeks w liście
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(textname)) {
                    label2.setText("Znaleziono mangę o nazwie: " + textname);
                    found = true;
                    foundIndex = currentIndex;
                    break; // Jeśli znaleziono, można przerwać pętlę
                }
                currentIndex++;
            }
            if (!found) {
                label2.setText("Nie znaleziono mangi o nazwie: " + textname);
            } else {
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
    }}








