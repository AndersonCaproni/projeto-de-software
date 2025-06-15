package SubsistemaFacade;

public class Televisao {
	
	public String ligar() {
		return "Televisão ligada.\n"; 
	}
	
    public String desligar() { 
    	return "Televisão desligada.\n"; 
    }
    
    public String setEntrada(String entrada) { 
    	return "Televisão configurada para a entrada " + entrada + ".\n"; 
    }
}
