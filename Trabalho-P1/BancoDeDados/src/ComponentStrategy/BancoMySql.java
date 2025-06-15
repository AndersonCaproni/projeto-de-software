package ComponentStrategy;

import java.sql.*;
import java.util.ArrayList;
import Models.Curso;
import Enums.PublicoAlvo;
import Strategy.IBancoDeDados;

public class BancoMySql implements IBancoDeDados {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
        		"jdbc:mysql://127.0.0.1:3306/strategy?useSSL=false&serverTimezone=UTC",
                "root",
                "" 
        );
    }

    @Override
    public void Cadastrar(Curso curso) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO curso (nome, cargaHoraria, publicoAlvo, valor, descricao) VALUES (?, ?, ?, ?, ?)"
            );

            ps.setString(1, curso.getNome());
            ps.setObject(2, curso.getCargaHoraria(), java.sql.Types.INTEGER);
            ps.setString(3, curso.getPublicoAlvo() != null ? curso.getPublicoAlvo().name() : null);
            ps.setObject(4, curso.getValor(), java.sql.Types.DOUBLE);
            ps.setString(5, curso.getDescricao());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Curso> Visualizar() {
        ArrayList<Curso> cursos = new ArrayList<>();
        try (Connection conn = getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM curso");
            while (rs.next()) {
                Curso curso = new Curso(
                    rs.getInt("Id"),
                    rs.getString("Nome"),
                    rs.getObject("CargaHoraria") != null ? rs.getInt("CargaHoraria") : null,
                    rs.getString("PublicoAlvo") != null ? PublicoAlvo.valueOf(rs.getString("PublicoAlvo")) : null,
                    rs.getObject("Valor") != null ? rs.getDouble("Valor") : null,
                    rs.getString("Descricao")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
