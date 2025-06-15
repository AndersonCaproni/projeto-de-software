package SubsistemaFacade;

public class AparelhoDeSom {
	
	public String ligar() { 
		return "Amplificador ligado.\n"; 
	}
	
    public String desligar() {
    	return "Amplificador desligado.\n"; 
    }
    
    public String setTipoSom(String tipo) {
    	return "Amplificador configurado para som " + tipo + ".\n"; 
    }
    
    public String setVolume(int level) { 
    	return "Volume do amplificador ajustado para " + level + ".\n";
    }
}
