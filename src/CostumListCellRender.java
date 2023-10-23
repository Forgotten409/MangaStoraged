import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CostumListCellRender extends JPanel implements ListCellRenderer<Manga> {
    private final JLabel nameLabel = new JLabel();
    private final JLabel chaptersLabel = new JLabel();
    private final JLabel imageLabel = new JLabel();

    private Map<String, ImageIcon> iconCache = new HashMap<>();


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

        ImageIcon icon = null;

        if (iconCache.containsKey(manga.getImagefilepath())) {
            // Jeśli ikona jest już w pamięci podręcznej, użyj jej
            icon = iconCache.get(manga.getImagefilepath());
        } else {
            // Wczytujemy obrazek z pliku lub URLćź
            if (manga.getImagefilepath().startsWith("http://") || manga.getImagefilepath().startsWith("https://")) {
                try {
                    URL imageUrl = new URL(manga.getImagefilepath());
                    icon = new ImageIcon(imageUrl);
                    iconCache.put(manga.getImagefilepath(), icon);
                } catch (IOException e) {
                    // Obsługa błędów, np. obrazek nie może być wczytany z URL
                    e.printStackTrace();
                }
            } else {
                icon = new ImageIcon(manga.getImagefilepath());
                iconCache.put(manga.getImagefilepath(), icon);
            }
        }

        // Jeśli udało się wczytać obrazek, to go skalujemy
        if (icon != null) {
            int width = 150; // Nowa szerokość ikony
            int height = 150; // Nowa wysokość ikony
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImage);
        }

        // Obsługa sytuacji, w której nie udało się wczytać obrazka
        imageLabel.setIcon(icon);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }}
