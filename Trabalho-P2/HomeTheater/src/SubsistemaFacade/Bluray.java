package SubsistemaFacade;

public class Bluray {
	
    public String ligar() { 
    	return "Blu-ray Player ligado.\n";
    }
    
    public String desligar() {
    	return "Blu-ray Player desligado.\n";
    }
    
    public String play(String filme) { 
    	return "Reproduzindo o filme: " + filme + ".\n"; 
    }
    
    public String parar() { 
    	return "Blu-ray Player parado.\n"; 
    }
}
