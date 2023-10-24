import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    String textname = "";

    JLabel label2 = new JLabel(textname);

    Boolean Test1 = false;




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
            e.printStackTrace();
        }
    }}








