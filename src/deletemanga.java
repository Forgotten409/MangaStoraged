import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class deletemanga {

    JFrame frame = new JFrame("Usuń Mange");

    JButton button = new JButton("Usuń");

    JTextField mangaName = new JTextField(20);

    JPanel panel = new JPanel();

    JLabel label = new JLabel("Wpisz nazwe mangi do usunięcia(manga zostanie usunieta na zawsze bądz pewny!!");

    GUIADDED guiadded = new GUIADDED();


    File originalFile = new File(guiadded.filename);



    public void Satrtdeleteprogram(){

        frame.setSize(600,600);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);


        frame.getContentPane().add(BorderLayout.CENTER, panel);
        panel.setBackground(Color.darkGray);
        panel.add(label);
        panel.add(mangaName);
        panel.add(button);

        button.addActionListener(e -> {
            changeData();
            });




        frame.setVisible(true);



    }

    public void changeData(){

        String textname = mangaName.getText();
       try (BufferedReader reader = new BufferedReader(new FileReader(originalFile))){
           List<String> lines = new ArrayList<>();
           String line;
           while ((line = reader.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts.length == 3 && parts[0].equals(textname)) {
                   continue;
               }
               lines.add(line);
           }

           try (BufferedWriter writer = new BufferedWriter(new FileWriter(originalFile))){
               for (String updateline : lines){
                   writer.write(updateline);
                   writer.newLine();
               }
           }

           System.out.println("Manga " + textname + " została usunięta z pliku " + guiadded.filename);







       }catch (IOException ex) {
           ex.printStackTrace();
           System.err.println("Błąd podczas usuwania mangi z pliku.");
       }


    }


}
