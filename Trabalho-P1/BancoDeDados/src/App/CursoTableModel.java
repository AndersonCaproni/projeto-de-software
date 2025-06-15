package App;

import Models.Curso;
import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CursoTableModel extends AbstractTableModel {
    private final List<Curso> cursos;
    private final String[] colunas = {"ID", "Nome", "Carga Horária", "Valor", "Público Alvo"};
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public CursoTableModel(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public int getRowCount() {
        return cursos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Curso curso = cursos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> curso.getId();
            case 1 -> curso.getNome();
            case 2 -> curso.getCargaHoraria() + "h";
            case 3 -> currencyFormatter.format(curso.getValor());
            case 4 -> curso.getPublicoAlvo().getDescricao();
            default -> null;
        };
    }

    public Curso getCursoAt(int rowIndex) {
        return cursos.get(rowIndex);
    }
}
