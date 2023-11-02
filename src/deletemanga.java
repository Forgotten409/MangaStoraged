import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class deletemanga {

    LanguageChange languageChange = new LanguageChange();

    RestarAPP restarAPP = new RestarAPP();

    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icone_app.png")));

    JFrame frame = new JFrame("OtakuLibrary");

    JButton button = new JButton(languageChange.messages.getString("button.button"));

    JTextField mangaName = new JTextField(20);

    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();

    JLabel labeldeletemanga = new JLabel(languageChange.messages.getString("label.labeldeletemanga"));

    GUIADDED guiadded = new GUIADDED();


    File originalFile = new File(guiadded.filename);

    JLabel label2 = new JLabel(languageChange.messages.getString("label.label2"));

    JLabel label3 = new JLabel("");






    public void Satrtdeleteprogram(){

        languageChange.readDatalanguage();
        updateButtonLabels();

        frame.setSize(700,200);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);


        frame.setLocationRelativeTo(null);

        frame.setIconImage(icon.getImage());


        frame.getContentPane().add(BorderLayout.CENTER, panel);


        panel.setBackground(Color.darkGray);
        panel.add(labeldeletemanga);
        panel.add(mangaName);
        panel.add(button);
        panel.add(label2);
        frame.getContentPane().add(BorderLayout.SOUTH, panel2);
        panel2.setBackground(Color.darkGray);
        panel2.add(label3);

        label2.setForeground(Color.red);


        frame.setResizable(false);

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



           if (languageChange.languagechanger.equals("pl")){
               label3.setText("<html><font color='green'>" + textname + "</font><p1> zostalo usuniete!!</p1></html>");
               restarAPP.StartResrar();
               System.exit(0);
           }else if (languageChange.languagechanger.equals("en")){
               label3.setText("<html><font color='green'>" + textname + "</font><p1> has been deleted!!</p1></html>");
               restarAPP.StartResrar();
               System.exit(0);

           }

           if (!textname.isEmpty()) {
               mangaName.setText(""); // CLEAR

           }







       }catch (IOException ex) {
           ex.printStackTrace(System.out);
           System.err.println("Błąd podczas usuwania mangi z pliku.");
       }


    }
    public void updateButtonLabels() {
        Locale userLocale = new Locale(languageChange.languagechanger);
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
        button.setText(messages.getString("button.button"));
        labeldeletemanga.setText(messages.getString("label.labeldeletemanga"));
        label2.setText(messages.getString("label.label2"));

    }

}
