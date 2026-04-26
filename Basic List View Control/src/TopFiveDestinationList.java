import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();

        //  Updated destinations with descriptions (User Story Requirement)
        addDestinationNameAndPicture(
            "1. Paris, France - The city of lights known for romance and culture.",
            new ImageIcon(getClass().getResource("/resources/paris.jpg"))
        );

        addDestinationNameAndPicture(
            "2. Bali, Indonesia - A tropical paradise with beaches and temples.",
            new ImageIcon(getClass().getResource("/resources/bali.jpg"))
        );

        addDestinationNameAndPicture(
            "3. Rome, Italy - A historic city full of ancient architecture.",
            new ImageIcon(getClass().getResource("/resources/rome.jpg"))
        );

        addDestinationNameAndPicture(
            "4. Tokyo, Japan - A modern city blending tradition and technology.",
            new ImageIcon(getClass().getResource("/resources/tokyo.jpg"))
        );

        addDestinationNameAndPicture(
            "5. New York, USA - A vibrant city with endless attractions.",
            new ImageIcon(getClass().getResource("/resources/nyc.jpg"))
        );

        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        // Styling
        list.setBackground(new Color(240, 248, 255)); // light blue
        list.setSelectionBackground(new Color(173, 216, 230));

        // Click event (for links)
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int index = list.locationToIndex(evt.getPoint());

                try {
                    if (index == 0) {
                        Desktop.getDesktop().browse(new java.net.URI("https://www.expedia.com/Paris.d179898.Destination-Travel-Guides"));
                    } else if (index == 1) {
                        Desktop.getDesktop().browse(new java.net.URI("https://www.expedia.com/Bali.d602651.Destination-Travel-Guides"));
                    } else if (index == 2) {
                        Desktop.getDesktop().browse(new java.net.URI("https://www.expedia.com/Rome.d179899.Destination-Travel-Guides"));
                    } else if (index == 3) {
                        Desktop.getDesktop().browse(new java.net.URI("https://www.expedia.com/Tokyo.d179900.Destination-Travel-Guides"));
                    } else if (index == 4) {
                        Desktop.getDesktop().browse(new java.net.URI("https://www.expedia.com/New-York.d178293.Destination-Travel-Guides"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(10);
        list.setCellRenderer(renderer);

      
        JLabel nameLabel = new JLabel("Created by Filipe Angelo", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(nameLabel, BorderLayout.SOUTH);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        //  Adds each destination with image
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}