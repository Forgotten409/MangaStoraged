import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CostumListCellRender extends JPanel implements ListCellRenderer<Manga> {
    private JLabel nameLabel = new JLabel();
    private JLabel chaptersLabel = new JLabel();
    private JLabel imageLabel = new JLabel();

    public CostumListCellRender() {
        setLayout(new BorderLayout());
        add(nameLabel, BorderLayout.CENTER);
        add(chaptersLabel, BorderLayout.EAST);
        add(imageLabel, BorderLayout.WEST);


        // Ustawienia wyglądu
        setOpaque(true);
        setBorder(new EmptyBorder(15, 15, 15, 15)); // Odstęp od obramowania

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Manga> list, Manga manga, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        nameLabel.setText(" Tytuł: " + manga.getName());
        chaptersLabel.setText("Przeczytane do: " + manga.getChapter());

        // Wczytywanie obrazka z pliku
        ImageIcon icon = new ImageIcon(manga.getImagefilepath());
        // Skalowanie ikony do ustalonego rozmiaru
        int width = 150; // Nowa szerokość ikony
        int height = 150; // Nowa wysokość ikony
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        imageLabel.setIcon(icon);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }



}
