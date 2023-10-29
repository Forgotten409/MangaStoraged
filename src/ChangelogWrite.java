import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ChangelogWrite {

    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icone_app.png")));

    JFrame frame = new JFrame("Changelog");

    JPanel panel = new JPanel();
    String Text = "<html><h1>Wersja 1.1</h1>" +
            "<font color='green'>-Dodano mozliwosc zminay ilosci czapterow,</font><br/" +
            "<font color='green'>-Dodano Changelog,</font><br/" +
            "<font color='green'>-Napra malych blędów,</font><br/" +
            "<font color='green'>-dodanie potwierdzenie wyjscia w edytuj,</font><br/" +
            "<font color='green'>-Wylaczenie zmiany rozmiaru calej apliackji,</font><br/" +
            "<font color='green'>-Dodanie plik DataSave do aplikacji,</font><br/" +
            "<font color='green'>-Dodano przypomnienie o usunieciu mangi,</font><br/" +
            "<font color='green'>-I mniejsze zmiany,</font><br/</html>";
    JLabel label = new JLabel(Text);







    public void Changelogstartprogram(){
        frame.setSize(500,300);

        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);

        frame.setIconImage(icon.getImage());

        frame.add(panel);
        panel.add(label);
        panel.setBackground(Color.darkGray);

        frame.setResizable(false);

        frame.setVisible(true);


    }
}
