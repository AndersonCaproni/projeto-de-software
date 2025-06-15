package SubsistemaFacade;

public class Luzes {
	
    public String ligar() { 
    	return "Luzes acesas.\n"; 
    }
    
    public String desligar() { 
    	return "Luzes apagadas.\n";
    }
    
    public String setNivel(int nivel) {
    	return "Intensidade das luzes ajustada para " + nivel + "%\n"; 
    }
}
