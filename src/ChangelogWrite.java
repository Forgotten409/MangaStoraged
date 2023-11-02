import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ChangelogWrite {

    LanguageChange languageChange = new LanguageChange();


    ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/icone_app.png")));

    JFrame frame = new JFrame("OtakuLibrary");

    JPanel panel = new JPanel();

    JLabel label = new JLabel("");
    JLabel label2 = new JLabel("");
    JLabel label3 = new JLabel("");








    public void Changelogstartprogram(){

        languageChange.readDatalanguage();
        frame.setSize(500,600);

        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);

        frame.setIconImage(icon.getImage());

        frame.add(panel);
        panel.add(label);
        panel.add(label2);
        panel.add(label3);

        panel.setBackground(Color.darkGray);

        frame.setResizable(false);

        frame.setLocationRelativeTo(null);


        if (languageChange.languagechanger.equals("pl")){
            label.setText("<html><h1>Wersja 1.1</h1>" +
                    "<font color='green'>-Dodano mozliwosc zmiany ilosci rozdziałow,</font><br/" +
                    "<font color='green'>-Dodano dziennik zmian,</font><br/" +
                    "<font color='green'>-Naprawa malych bledów,</font><br/" +
                    "<font color='green'>-dodanie potwierdzenie wyjscia w edytuj,</font><br/" +
                    "<font color='green'>-Wylaczenie zmiany rozmiaru calej aplikacji,</font><br/" +
                    "<font color='green'>-Dodano przypomnienie o usunieciu,</font><br/" +
                    "<font color='green'>-I mniejsze zmiany,</font><br/</html>");

        }else if (languageChange.languagechanger.equals("en")){
            System.out.println(languageChange.languagechanger);
            label.setText("<html><h1>Version 1.1</h1>" +
                   "<font color='green'>-Added the ability to change the number of chapters,</font><br/" +
                   "<font color='green'>-Changelog added,</font><br/" +
                  "<font color='green'>-Fixing small bugs,</font><br/" +
                   "<font color='green'>-adding exit confirmation in edit,</font><br/" +
                    "<font color='green'>-Disabling resizing of the entire application,</font><br/" +
                    "<font color='green'>-Added deletion reminder,</font><br/" +
                    "<font color='green'>-And smaller changes,</font><br/</html>");



        }


        if (languageChange.languagechanger.equals("pl")){
            label2.setText("<html><h1>Wersja 1.2</h1>" +
                    "<font color='green'>-Dodanie restart aplikacji,</font><br/" +
                    "<font color='green'>-Dodano obraz (brak lub nie dziala),</font><br/" +
                    "<font color='green'>-przetlumaczenie calej aplikacji na angielski,</font><br/" +
                    "<font color='green'>-Dodanie możliwosci zmiany jezyka,</font><br/" +
                    "<font color='green'>-Od teraz pliki nie tworza sie na pulpicie,</font><br/" +
                    "<font color='green'>-Naprawiono male bledy,</font><br/" +
                    "<font color='green'>-I inne male zmiany,</font><br/" +
                    "<font color='green'>-Poprawiono tekst  calej aplikacji,</font><br/</html>");

        }else if (languageChange.languagechanger.equals("en")){
            System.out.println(languageChange.languagechanger);
            label2.setText("<html><h1>Version 1.2</h1>" +
                    "<font color='green'>-Adding an application restart,</font><br/" +
                    "<font color='green'>-Image added (missing or not working),</font><br/" +
                    "<font color='green'>-translating the entire application into English,</font><br/" +
                    "<font color='green'>-adding the ability to change the language,</font><br/" +
                    "<font color='green'>-From now on, files are no longer created on the desktop,</font><br/" +
                    "<font color='green'>-Fixed small bugs,</font><br/" +
                    "<font color='green'>-And other small changes,</font><br/</html>" +
                    "<font color='green'>-The text of the entire application has been corrected,</font><br/");



        }
        if (languageChange.languagechanger.equals("pl")){
            label3.setText("<html><h1>Wersja 1.2b</h1>" +
                    "<font color='green'>-Naprawiono blod z restartem,</font><br/" +
                    "<font color='green'>-Naprawion blod z importem danych,</font><br/" +
                    "<font color='green'>-Dodanie wyczyszczenie wszystkich danych,</font><br/");

        }else if (languageChange.languagechanger.equals("en")){
            System.out.println(languageChange.languagechanger);
            label3.setText("<html><h1>Version 1.2b</h1>" +
                    "<font color='green'>-Fixed a bug with restarting,</font><br/" +
                    "<font color='green'>-Fixed a bug with data import,</font><br/" +
                    "<font color='green'>-Adding clearing all data,</font><br/");



        }

        frame.setVisible(true);


    }
}
