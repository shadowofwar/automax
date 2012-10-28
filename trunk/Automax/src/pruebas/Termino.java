package pruebas;

public class Termino{
	
	public static final String LAMBDA = "L";
	
	private String simbolosE;
	private String estado;
	
	public Termino(){
		super();
		simbolosE = LAMBDA;
		estado = LAMBDA;
	}
	
	public Termino(String simbolosE){
		super();
		this.simbolosE = simbolosE;
		this.estado = LAMBDA;
	}
	
	public Termino(String simbolosE, String estado){
		super();
		this.simbolosE = simbolosE;
		this.estado = estado;
	}

	public String getSimbolosE() {
		return simbolosE;
	}

	public void setSimbolosE(String simbolosE) {
		this.simbolosE = simbolosE;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean esSecuenciaNula(){
		if(this.simbolosE.equals(LAMBDA)&&this.estado.equals(LAMBDA))
			return true;
		return false;
	}
	

}
