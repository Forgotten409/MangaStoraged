import javax.swing.*;
import java.awt.*;

public class GUIADDED{

    JFrame frame2 = new JFrame("Magazyn Mang");

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> Manganame = new JList<>(listModel);

    JButton confirmbutton = new JButton("Potwierdz");






    public GUIADDED(){


    }


    public void StartGuiAddedManga(){

        JLabel namemanga = new JLabel("Name: ");

        JTextField getmanganame = new JTextField(20);

        frame2.setDefaultCloseOperation(frame2.DISPOSE_ON_CLOSE);

        frame2.setSize(500, 500);



        JPanel rootpanel2 = new JPanel();

        frame2.add(rootpanel2);
        rootpanel2.setBackground(Color.darkGray);
        JPanel panel2 = new JPanel();
        frame2.add(panel2);
        panel2.setBackground(Color.darkGray);
        panel2.add(namemanga);
        panel2.add(getmanganame);
        frame2.getContentPane().add(BorderLayout.SOUTH, confirmbutton);

        confirmbutton.addActionListener(e -> {
            String textname = getmanganame.getText();
            if (!textname.isEmpty()) {
                listModel.addElement(textname);
                getmanganame.setText(""); //czyszczenie

            }
        });



        frame2.setVisible(true);
    }

}
