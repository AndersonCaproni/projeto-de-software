package Context;

import Strategy.IBancoDeDados;
import Models.Curso;
import java.util.ArrayList;

public class CursoService {
    private IBancoDeDados banco;

    public CursoService(IBancoDeDados banco) {
        this.banco = banco;
    }

    public void setBanco(IBancoDeDados banco) {
        this.banco = banco;
    }

    public void Cadastrar(Curso curso) {
        banco.Cadastrar(curso);
    }

    public ArrayList<Curso> Visualizar() {
        return banco.Visualizar();
    }
}
