package App;

import Facade.HomeTheaterFacade;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomeTheaterInterface extends JFrame {

    private final HomeTheaterFacade homeTheater;

    private JTextField filmeTextField;
    private JButton assistirButton;
    private JButton pararButton;
    private JTextArea logTextArea;
    private JPanel mainPanel;

    public HomeTheaterInterface(HomeTheaterFacade facade) {
        this.homeTheater = facade;

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());

            UIManager.put("Button.arc", 18);
            UIManager.put("Component.arc", 15);
            UIManager.put("TextComponent.arc", 15);

            UIManager.put("Button.font", new Font("Segoe UI Semibold", Font.PLAIN, 14));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextArea.font", new Font("JetBrains Mono", Font.PLAIN, 13));

            UIManager.put("Component.background", new Color(250, 250, 252));
            UIManager.put("Button.background", new Color(30, 144, 255));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.hoverBackground", new Color(25, 118, 210));
            UIManager.put("TextComponent.background", new Color(245, 245, 245));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("🎬 Controle de Home Theater");
        setSize(620, 460);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        initActions();
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        filmeTextField = new JTextField();
        assistirButton = new JButton("Assistir Filme", createIcon("/Icons/player-play.png", 20, 20));
        pararButton = new JButton("Parar Sessão", createIcon("/Icons/player-pause.png", 20, 20));

        assistirButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pararButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pararButton.setEnabled(false);

        logTextArea = new JTextArea("🎉 Bem-vindo! Digite o nome de um filme e clique em 'Assistir'.");
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        logTextArea.setBackground(new Color(245, 245, 245));
        logTextArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
    }

    private void layoutComponents() {
        JPanel painelControles = new JPanel(new GridBagLayout());
        painelControles.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        painelControles.add(new JLabel("Filme:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        painelControles.add(filmeTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelControles.add(assistirButton, gbc);

        gbc.gridx = 1;
        painelControles.add(pararButton, gbc);

        mainPanel.add(painelControles, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(logTextArea), BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private void initActions() {
        assistirButton.addActionListener(e -> {
            String filme = filmeTextField.getText().trim();

            if (filme.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Por favor, digite o nome de um filme.",
                        "Entrada Inválida",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String log = homeTheater.assistirFilme(filme);
            logTextArea.setText(log);

            assistirButton.setEnabled(false);
            pararButton.setEnabled(true);
        });

        pararButton.addActionListener(e -> {
            String log = homeTheater.pararFilme();
            logTextArea.setText(log);

            assistirButton.setEnabled(true);
            pararButton.setEnabled(false);
            filmeTextField.setText("");
        });
    }

    private ImageIcon createIcon(String relativePath, int width, int height) {
        ImageIcon icon = new ImageIcon("src/" + relativePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
