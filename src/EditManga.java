import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Objects;

public class EditManga extends JFrame {

    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icone_app.png")));
    private JTextField searchField;
    private JTextField chaptersField;

    private JButton saveButton;

    public void EditManga2() {
        searchField = new JTextField(20);
        JLabel label1 = new JLabel("Szukaj Mangi do edycji");
        JButton searchButton = new JButton("Szukaj");

        chaptersField = new JTextField(10);
        JLabel label2 = new JLabel("Edytuj ilosc chapterów");
        chaptersField.setEditable(false); // Pole jest początkowo nieedytowalne

        saveButton = new JButton("Zapisz zmiany");

        // Utwórz interfejs użytkownika
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label1);
        panel.add(searchField);

        panel.add(searchButton);
        panel.add(label2);
        panel.add(chaptersField);
        panel.add(saveButton);
        saveButton.setEnabled(false);
        add(panel);
        panel.setBackground(Color.darkGray);



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mangaName = searchField.getText();
                searchMangaInFile(mangaName);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mangaName = searchField.getText();
                String newChapterCount = chaptersField.getText();
                saveChapterCount(mangaName, newChapterCount);
            }
        });

        setTitle("Edytor Mangi");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Program zostanie wylaczony by zapisac dane", "Potwierdzenie zamknięcia", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0); // Zamknij program
                }
            }
        });
        setLocationRelativeTo(null);
        setIconImage(icon.getImage());
        setResizable(false);

    }

    public void searchMangaInFile(String mangaName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("DataSave.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(mangaName)) {
                    String chapterCount = parts[1];
                    chaptersField.setText(chapterCount);
                    chaptersField.setEditable(true); // Pozwól na edycję ilości chapterów
                    saveButton.setEnabled(true);

                    return; // Zakończ przeszukiwanie po znalezieniu mangi
                }
            }

            // Jeśli nie znaleziono mangi, wyczyść pole edycji ilości chapterów
            chaptersField.setText("");
            chaptersField.setEditable(false);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void saveChapterCount(String mangaName, String newChapterCount) {
        try {
            File inputFile = new File("DataSave.txt");

            File tempFile = getFile(mangaName, newChapterCount, inputFile);

            // Usuń oryginalny plik
            if (inputFile.delete()) {
                // Zmień nazwę tymczasowego pliku na oryginalną nazwę
                if (tempFile.renameTo(inputFile)) {
                    JOptionPane.showMessageDialog(this, "Zapisano zmiany.");
                } else {
                    JOptionPane.showMessageDialog(this, "Błąd podczas zapisu zmian.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Błąd podczas zapisu zmian.");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static File getFile(String mangaName, String newChapterCount, File inputFile) throws IOException {
        File tempFile = new File("DataSaveTemp.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
             BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(mangaName)) {
                    writer.write(mangaName + "," + newChapterCount + "," + parts[2]);
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        return tempFile;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EditManga editManga = new EditManga();
                editManga.setVisible(true);
            }
        });
    }
}
