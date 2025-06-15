package App;

import Context.CursoService;
import Models.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import ComponentStrategy.BancoMySql;
import ComponentStrategy.BancoSqlServer;

public class CursoAppUI extends JFrame {
	private final CursoService cursoService;
    private JTable tabelaCursos;
    private CursoTableModel tableModel;

    private static final Color PRIMARY_COLOR = new Color(30, 136, 229);
    private static final Color BACKGROUND_COLOR = new Color(250, 250, 250);
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Color FONT_COLOR = new Color(33, 33, 33);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);
    private static final Color HEADER_BACKGROUND = new Color(245, 245, 245);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font TABLE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font TABLE_HEADER_FONT = new Font("Segoe UI", Font.BOLD, 15);

    public CursoAppUI(CursoService cursoService) {
        this.cursoService = cursoService;

        setTitle("Gerenciador de Cursos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);
        mainPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        add(mainPanel);
        atualizarTabela();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(HEADER_BACKGROUND);
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));

        JLabel titleLabel = new JLabel("🎓 Painel de Cursos");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel dbSwitcherPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        dbSwitcherPanel.setBackground(HEADER_BACKGROUND);

        JLabel dbLabel = new JLabel("Fonte de Dados:");
        dbLabel.setFont(TABLE_FONT);
        dbLabel.setForeground(FONT_COLOR);

        JComboBox<String> dbComboBox = new JComboBox<>(new String[]{"SqlServer", "MySQL"});
        dbComboBox.setFont(TABLE_FONT);
        dbComboBox.setBackground(Color.WHITE);
        dbComboBox.setForeground(PRIMARY_COLOR.darker());
        dbComboBox.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        dbComboBox.setFocusable(false);
        dbComboBox.addActionListener(e -> {
            String selected = (String) dbComboBox.getSelectedItem();
            if ("MySQL".equals(selected)) {
                cursoService.setBanco(new BancoMySql());
            } else {
                cursoService.setBanco(new BancoSqlServer());
            }
            atualizarTabela();
        });

        dbSwitcherPanel.add(dbLabel);
        dbSwitcherPanel.add(dbComboBox);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(dbSwitcherPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JScrollPane createTablePanel() {
        tabelaCursos = new JTable();
        tabelaCursos.setFillsViewportHeight(true);
        tabelaCursos.setRowHeight(36);
        tabelaCursos.setFont(TABLE_FONT);
        tabelaCursos.setForeground(FONT_COLOR);
        tabelaCursos.setGridColor(BORDER_COLOR);
        tabelaCursos.setSelectionBackground(PRIMARY_COLOR);
        tabelaCursos.setSelectionForeground(Color.WHITE);
        tabelaCursos.setShowHorizontalLines(false);
        tabelaCursos.setShowVerticalLines(false);
        tabelaCursos.setIntercellSpacing(new Dimension(0, 0));
        tabelaCursos.setRowMargin(0);

        JTableHeader header = tabelaCursos.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(PRIMARY_COLOR.darker());
        header.setFont(TABLE_HEADER_FONT);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        renderer.setHorizontalAlignment(SwingConstants.LEFT);
        tabelaCursos.setDefaultRenderer(Object.class, renderer);

        JScrollPane scrollPane = new JScrollPane(tabelaCursos);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        scrollPane.getViewport().setBackground(Color.WHITE);

        return scrollPane;
    }

    private JPanel createFooterPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton btnCadastrar = createStyledButton("Cadastrar Novo Curso", PRIMARY_COLOR);
        btnCadastrar.addActionListener(e -> cadastrarCurso());

        buttonPanel.add(btnCadastrar);
        return buttonPanel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void atualizarTabela() {
        try {
            List<Curso> cursos = cursoService.Visualizar();
            tableModel = new CursoTableModel(cursos);
            tabelaCursos.setModel(tableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Falha ao conectar ao banco de dados.\nVerifique suas credenciais.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadastrarCurso() {
        CursoDialog dialog = new CursoDialog(this, null);
        dialog.setVisible(true);
        Curso novoCurso = dialog.getCurso();
        if (novoCurso != null) {
            cursoService.Cadastrar(novoCurso);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Curso cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
