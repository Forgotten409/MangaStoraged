
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GUIADDED{

    JFrame frame2 = new JFrame("Magazyn Mang");

    DefaultListModel<Manga> listModel = new DefaultListModel<>();

    JList<Manga> list = new JList<>(listModel);


    JButton confirmbutton = new JButton("Potwierdz");






    public GUIADDED(){


    }


    public void StartGuiAddedManga(){

        List<Manga> mangaList = new ArrayList<>();

        JLabel namemanga = new JLabel("Name: ");

        JTextField getmanganame = new JTextField(20);

        JLabel chaptermanga = new JLabel("Chapter: ");

        JTextField getchaptermanga = new JTextField(20);

        JLabel picturemanga = new JLabel("Zdjęcie: ");

        JButton picturebutton = new JButton("Wybierz zdjęcie: ");

        JTextField getpicturemanga = new JTextField(20);

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
            String textchapter =getchaptermanga.getText();
            String picture = getpicturemanga.getText();
            if (textname.isEmpty() || textchapter.isEmpty() || picture.isEmpty()){}else {
                mangaList.add(new Manga(textname,textchapter,picture));

                list.setCellRenderer(new CostumListCellRender());


                for (Manga manga : mangaList) {
                    listModel.addElement(manga);
                }
                System.out.println(list);
                System.out.println(mangaList);
                mangaList.clear();  }



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



        frame2.setVisible(true);
    }





    }


