package App;

import javax.swing.SwingUtilities;

import Facade.HomeTheaterFacade;
import SubsistemaFacade.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                AparelhoDeSom som = new AparelhoDeSom();
                Televisao tv = new Televisao();
                Luzes luzes = new Luzes();
                Pipoqueira pipoqueira = new Pipoqueira();
                Bluray dvd = new Bluray();

                HomeTheaterFacade homeTheater = new HomeTheaterFacade(som, tv, luzes, pipoqueira, dvd);

                HomeTheaterInterface gui = new HomeTheaterInterface(homeTheater);
                gui.setVisible(true);
            }
        });
    }
}