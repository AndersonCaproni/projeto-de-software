package App;

import Enums.PublicoAlvo;
import Models.Curso;

import javax.swing.*;
import java.awt.*;

public class CursoDialog extends JDialog {
    private JTextField txtNome, txtDescricao;
    private JSpinner spinnerCargaHoraria, spinnerValor;
    private JComboBox<PublicoAlvo> cmbPublicoAlvo;
    private Curso curso;
    private boolean confirmado;

    private static final Color PRIMARY_COLOR = new Color(66, 133, 244);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color FONT_COLOR = new Color(33, 33, 33);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);

    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 13);

    public CursoDialog(Frame owner, Curso curso) {
        super(owner, true);
        this.curso = (curso == null) ? new Curso() : curso;

        setTitle("Cadastro de Curso");
        setSize(500, 500);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        JPanel cardPanel = new JPanel(new BorderLayout(0, 0));
        cardPanel.setBackground(CARD_COLOR);
        cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        cardPanel.add(createFormPanel(), BorderLayout.CENTER);

        add(cardPanel, BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 0, 12));
        formPanel.setBackground(CARD_COLOR);

        txtNome = new JTextField(curso.getNome());
        txtDescricao = new JTextField(curso.getDescricao());
        spinnerCargaHoraria = new JSpinner(new SpinnerNumberModel(curso.getCargaHoraria(), 0, 1000, 1));
        spinnerValor = new JSpinner(new SpinnerNumberModel(curso.getValor(), 0.0, 10000.0, 0.1));
        cmbPublicoAlvo = new JComboBox<>(PublicoAlvo.values());
        cmbPublicoAlvo.setSelectedItem(curso.getPublicoAlvo());
        cmbPublicoAlvo.setFont(FIELD_FONT);
        cmbPublicoAlvo.setBackground(Color.WHITE);
        cmbPublicoAlvo.setForeground(FONT_COLOR);
        cmbPublicoAlvo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR),
            BorderFactory.createEmptyBorder(10, 8, 10, 8)
        ));
        cmbPublicoAlvo.setPreferredSize(new Dimension(0, 40));

        formPanel.add(createFormField("Nome do Curso", txtNome));
        formPanel.add(createFormField("Descrição", txtDescricao));
        formPanel.add(createFormField("Carga Horária (h)", spinnerCargaHoraria));
        formPanel.add(createFormField("Valor (R$)", spinnerValor));
        formPanel.add(createFormField("Público Alvo", cmbPublicoAlvo));

        return formPanel;
    }

    private JPanel createFormField(String labelText, JComponent component) {
        JPanel fieldPanel = new JPanel(new BorderLayout(5, 5));
        fieldPanel.setBackground(CARD_COLOR);

        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        label.setForeground(FONT_COLOR);
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 3, 0));

        component.setFont(FIELD_FONT);
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));

        fieldPanel.add(label, BorderLayout.NORTH);
        fieldPanel.add(component, BorderLayout.CENTER);
        return fieldPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 12));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(BUTTON_FONT);
        btnCancelar.setBackground(new Color(240, 240, 240));
        btnCancelar.setForeground(new Color(90, 90, 90));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                BorderFactory.createEmptyBorder(6, 18, 6, 18)
        ));
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(e -> dispose());

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(BUTTON_FONT);
        btnSalvar.setBackground(PRIMARY_COLOR);
        btnSalvar.setForeground(new Color(0, 0, 0));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBorder(BorderFactory.createEmptyBorder(6, 22, 6, 22));
        btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(e -> onSave());

        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnSalvar);
        return buttonPanel;
    }

    private void onSave() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome do curso é obrigatório.", "Campo Inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        curso.setNome(txtNome.getText().trim());
        curso.setDescricao(txtDescricao.getText().trim());
        curso.setCargaHoraria((Integer) spinnerCargaHoraria.getValue());
        curso.setValor(((Number) spinnerValor.getValue()).doubleValue());
        curso.setPublicoAlvo((PublicoAlvo) cmbPublicoAlvo.getSelectedItem());

        confirmado = true;
        dispose();
    }

    public Curso getCurso() {
        return confirmado ? curso : null;
    }
}
