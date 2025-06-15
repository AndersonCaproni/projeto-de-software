package Facade;

import SubsistemaFacade.*;

public class HomeTheaterFacade {

    private AparelhoDeSom som;
    private Televisao tv;
    private Luzes luzes;
    private Pipoqueira pipoqueira;
    private Bluray dvd;

    public HomeTheaterFacade(AparelhoDeSom som, Televisao tv, Luzes luzes, Pipoqueira pipoqueira, Bluray dvd) {
        this.som = som;
        this.tv = tv;
        this.luzes = luzes;
        this.pipoqueira = pipoqueira;
        this.dvd = dvd;
    }

    public String assistirFilme(String filme) {
        StringBuilder log = new StringBuilder();
        log.append("Preparando tudo para assistir ao filme...\n");
        log.append("----------------------------------------------------------\n");
        log.append(pipoqueira.ligar());
        log.append(pipoqueira.estourar());
        log.append(luzes.setNivel(10));
        log.append(tv.ligar());
        log.append(tv.setEntrada("HDMI 1"));
        log.append(som.ligar());
        log.append(som.setTipoSom("ambiente"));
        log.append(som.setVolume(15));
        log.append(dvd.ligar());
        log.append(dvd.play(filme));
        return log.toString();
    }

    public String pararFilme() {
        StringBuilder log = new StringBuilder();
        log.append("\nFinalizando a sessão de cinema...\n");
        log.append("--------------------------------------------------\n");
        log.append(pipoqueira.desligar());
        log.append(luzes.ligar());
        log.append(tv.desligar());
        log.append(som.desligar());
        log.append(dvd.parar());
        log.append(dvd.desligar());
        return log.toString();
    }
}