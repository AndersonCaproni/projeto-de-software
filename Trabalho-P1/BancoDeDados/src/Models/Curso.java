package Models;

import Enums.PublicoAlvo;

public class Curso {
	
	private Integer id;
    private String nome;
    private Integer cargaHoraria;
    private PublicoAlvo publicoAlvo;
    private Double valor;
    private String descricao;

    public Curso() {
        this.nome = "";
        this.descricao = "";
        this.cargaHoraria = 0;
        this.valor = 0.0;
        this.publicoAlvo = PublicoAlvo.values()[0];
    }
    
    public Curso(Integer id, String nome, Integer cargaHoraria, PublicoAlvo publicoAlvo, Double valor, String descricao) {
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.publicoAlvo = publicoAlvo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public PublicoAlvo getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(PublicoAlvo publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
