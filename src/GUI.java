import javax.swing.*;
import java.awt.*;

public class GUI{
    JFrame frame = new JFrame("Magazyn Mang");
    JButton addbutton = new JButton("Dodaj");

    JLabel label = new JLabel("Dodaj mange!");
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();

    GUIADDED guiadded = new GUIADDED();

    JPanel rootpanel = new JPanel();


public GUI(){


}


public void StartGui() {
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    frame.setSize(800, 800);


    panel.add(addbutton);
    panel.add(label);

    rootpanel.add(panel);

    frame.add(rootpanel);
    frame.getContentPane().add(BorderLayout.SOUTH, panel);
    frame.getContentPane().add(BorderLayout.CENTER, panel2);

    panel.setBackground(Color.GRAY);
    rootpanel.setBackground(Color.DARK_GRAY);
    panel2.setBackground(Color.DARK_GRAY);



    addbutton.addActionListener(e -> guiadded.StartGuiAddedManga());


    guiadded.readData();
    JScrollPane scrollPane = new JScrollPane(guiadded.list);
    scrollPane.setPreferredSize(new Dimension(600,600));
    scrollPane.getViewport().getView().setBackground(Color.gray);
    scrollPane.getViewport().getView().setForeground(Color.gray);



    panel2.add(scrollPane);
    frame.setBackground(Color.GRAY);




    frame.setVisible(true);
}}







