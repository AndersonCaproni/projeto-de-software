package App;

import ComponentStrategy.BancoMySql;
import ComponentStrategy.BancoSqlServer;
import Context.CursoService;
import Strategy.IBancoDeDados;
import javax.swing.*;

public class Program {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        IBancoDeDados banco = escolherBanco();
        if (banco == null) {
            System.out.println("Nenhum banco de dados selecionado. Encerrando.");
            return;
        }

        CursoService context = new CursoService(banco);

        SwingUtilities.invokeLater(() -> {
            CursoAppUI app = new CursoAppUI(context);
            app.setVisible(true);
        });
    }

    private static IBancoDeDados escolherBanco() {
        Object[] options = {"MySQL", "SQL Server"};
        int choice = JOptionPane.showOptionDialog(null,
                "Por favor, escolha o banco de dados para iniciar:",
                "Seleção de Banco de Dados",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                return new BancoMySql();
            case 1:
                return new BancoSqlServer();
            default:
                return null;
        }
    }
}