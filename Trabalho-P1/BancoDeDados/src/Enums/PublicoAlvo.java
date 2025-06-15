package Enums;

public enum PublicoAlvo {
    ADOLESCENTE("Adolescente"),
    ADULTO("Adulto");

    private final String descricao;

    PublicoAlvo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
