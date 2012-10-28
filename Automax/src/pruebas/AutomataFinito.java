package pruebas;

import java.util.Vector;

public class AutomataFinito {
	
	private Vector<ExpresionRegular> ecuaciones;
	private String estadoInicial;
	
	
	
	public AutomataFinito() {
		super();
	}

	public AutomataFinito(Vector<ExpresionRegular> ecuaciones) {
		super();
		this.ecuaciones = ecuaciones;
	}
	
	public AutomataFinito(String estadoInicial) {
		super();
		this.estadoInicial = estadoInicial;
	}

	public AutomataFinito(Vector<ExpresionRegular> ecuaciones,
			String estadoInicial) {
		super();
		this.ecuaciones = ecuaciones;
		this.estadoInicial = estadoInicial;
	}

	public Vector<ExpresionRegular> getEcuaciones() {
		return ecuaciones;
	}

	public void setEcuaciones(Vector<ExpresionRegular> ecuaciones) {
		this.ecuaciones = ecuaciones;
	}

	public String getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(String estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	
	public String toString(){
		if(ecuaciones == null || ecuaciones.size()==0) return "";
		String resultado = "";
		for(int i=0; i<ecuaciones.size(); i++){
			resultado = resultado + ecuaciones.elementAt(i).toString();
			if((i+1)<ecuaciones.size()) resultado = resultado +"\n";
		}
		return resultado;
	}

}
