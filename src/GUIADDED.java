
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GUIADDED{


    public String filename = "DataSave.txt";

    JFrame frame2 = new JFrame("Magazyn Mang");

    DefaultListModel<Manga> listModel = new DefaultListModel<>();

    JList<Manga> list = new JList<>(listModel);


    JButton confirmbutton = new JButton("Potwierdz");

    List<Manga> mangaList = new ArrayList<>();






    public GUIADDED(){


    }


    public void StartGuiAddedManga(){



        JLabel namemanga = new JLabel("Name: ");

        JTextField getmanganame = new JTextField(20);

        JLabel chaptermanga = new JLabel("Chapter: ");

        JTextField getchaptermanga = new JTextField(20);

        JLabel picturemanga = new JLabel("Zdjęcie: ");

        JButton picturebutton = new JButton("Wybierz zdjęcie: ");

        JTextField getpicturemanga = new JTextField(20);

        JButton chooseUrlButton = new JButton("Wybierz obrazek z URL");


        frame2.setDefaultCloseOperation(frame2.DISPOSE_ON_CLOSE);

        frame2.setSize(500, 500);


        JPanel panel2 = new JPanel(new GridLayout(3,3,0,10));

        frame2.getContentPane().add(BorderLayout.SOUTH, confirmbutton);

        JPanel column1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel column2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel column3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        column1.add(namemanga);
        column1.add(getmanganame);

        column2.add(chaptermanga);
        column2.add(getchaptermanga);

        column3.add(picturemanga);
        column3.add(getpicturemanga);
        column3.add(picturebutton);
        column3.add(chooseUrlButton);

        panel2.add(column1);
        panel2.add(column2);
        panel2.add(column3);
        frame2.add(panel2);



        panel2.setBackground(Color.darkGray);
        column1.setBackground(Color.darkGray);
        column2.setBackground(Color.darkGray);
        column3.setBackground(Color.darkGray);




        confirmbutton.addActionListener(e -> {
            String textname = getmanganame.getText();
            String textchapter = getchaptermanga.getText();
            String picture = getpicturemanga.getText();
            if (textname.isEmpty() || textchapter.isEmpty() || picture.isEmpty()){}else {
                mangaList.add(new Manga(textname,textchapter,picture));

                list.setCellRenderer(new CostumListCellRender());


                for (Manga manga : mangaList) {
                    listModel.addElement(manga);
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename ,true))) {
                    for (Manga manga : mangaList) {
                        // Format danych w pliku tekstowym - możesz dostosować go do swoich potrzeb
                        String line = manga.getName() + "," + manga.getChapter() + "," + manga.getImagefilepath();
                        writer.write(line);
                        writer.newLine(); // Dodanie nowej linii między rekordami
                    }
                    System.out.println("Dane zostały zapisane do pliku " + filename);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mangaList.clear();


                }


            if (!textname.isEmpty() || textchapter.isEmpty() || picture.isEmpty()) {
                getmanganame.setText(""); // CLEAR
                getchaptermanga.setText(""); // CLEAR
                getpicturemanga.setText(""); // CLEAR
                mangaList.clear();

            }

        });


        picturebutton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame2);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                getpicturemanga.setText(selectedFile.getAbsolutePath());
            }
        });


        chooseUrlButton.addActionListener(e -> {
            String url = JOptionPane.showInputDialog("Podaj URL obrazka:");
            if (url != null && !url.isEmpty()) {
                getpicturemanga.setText(url);
            }});


        frame2.setVisible(true);
    }
public void readData(){
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // Tutaj musisz podzielić linię na części, używając odpowiedniego separatora (np. przecinka)
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String name = parts[0];
                String chapter = parts[1];
                String imagefilepath = parts[2];

                // Teraz masz dane wczytane z pliku, możesz zrobić co chcesz z tymi danymi
                // Na przykład, możesz utworzyć obiekt Manga i dodać go do listy.
                mangaList.add(new Manga(name, chapter, imagefilepath));

                list.setCellRenderer(new CostumListCellRender());

                for (Manga manga : mangaList) {
                    listModel.addElement(manga);
                }
                mangaList.clear();
            }
        }
        System.out.println("Dane zostały wczytane z pliku " + filename);
    } catch (IOException ex) {
        ex.printStackTrace();
    }

}
    }











