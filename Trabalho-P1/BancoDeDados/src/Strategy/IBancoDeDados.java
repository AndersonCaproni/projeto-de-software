package Strategy;

import java.util.ArrayList;

import Models.Curso;

public interface IBancoDeDados {
	
	public void Cadastrar(Curso curso);
	public ArrayList<Curso> Visualizar();

}
