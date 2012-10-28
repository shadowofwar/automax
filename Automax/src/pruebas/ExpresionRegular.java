package pruebas;

import java.util.Vector;

public class ExpresionRegular {
	
	private String estado;
	private Termino terms[];
	private boolean estadoDeError;
		
	public ExpresionRegular(){
		super();
		this.estadoDeError = false;
	}
	
	public ExpresionRegular(String estado){
		super();
		this.estado = estado;
		this.estadoDeError = false;
	}
	
	public ExpresionRegular(Termino terms[]){
		super();
		this.terms = terms;
		this.estadoDeError = false;
	}
	
	public ExpresionRegular(String estado, Termino terms[]){
		super();
		this.estado = estado;
		this.terms = terms;
		this.estadoDeError = false;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Termino[] getTerms() {
		return terms;
	}

	public void setTerms(Termino[] terms) {
		this.terms = terms;
	}
	
	public String toString(){
		String resultado = this.estado + " = ";
		if(this.estadoDeError) {
			resultado = resultado + "ERROR";
			return resultado;
		}
		if(this.terms.length == 0) resultado = resultado + Termino.LAMBDA;
		
		Termino[] terminos = this.terms;
		String adicion;
		for(int i=0; i<terminos.length; i++){
			if(terminos[i].esSecuenciaNula()){
				adicion = Termino.LAMBDA;
			}
			else{
				if(terminos[i].getEstado().equals(Termino.LAMBDA)){
					adicion = terminos[i].getSimbolosE();
				}
				else{
					adicion = terminos[i].getSimbolosE() + terminos[i].getEstado();
				}
			}
			if((i+1)<terminos.length) adicion = adicion + "+";
			resultado = resultado + adicion;
		}
		return resultado;
	}
	
	public boolean esEstadoDeError(){
		return this.estadoDeError;
	}
	
	
	
	public boolean isEstadoDeError() {
		return estadoDeError;
	}

	public void setEstadoDeError(boolean estadoDeError) {
		this.estadoDeError = estadoDeError;
	}

	public static ExpresionRegular arden(ExpresionRegular er){
		Termino t;
		Vector<Termino> a = new Vector<Termino>();
		Vector<Termino> b = new Vector<Termino>();
		for(int i=0; i<er.getTerms().length; i++){
			t = er.getTerms()[i];
			if(t.getEstado().equals(er.getEstado())){
				a.addElement(t);
			}
			else{
				b.addElement(t);
			}
		}
		
		if(a.isEmpty()){
			return er;
		}
		if(b.isEmpty()){
			ExpresionRegular error = new ExpresionRegular(er.getEstado());
			error.setEstadoDeError(true);
			return error;
		}
		
		String alfa = "";
		for(int i=0; i<a.size(); i++){
			alfa = alfa + ((Termino)a.elementAt(i)).getSimbolosE();
			if((i+1)<a.size()) alfa = alfa + "+";
		}
		if(a.size()==1 && alfa.length()<2){
			alfa = alfa +"*";
		}
		else{
			alfa = "("+alfa+")*";
		}
		
		Termino bTerms[] = new Termino[b.size()];
		for(int i=0; i<b.size(); i++){
			bTerms[i] = (Termino)b.elementAt(i);
		}
		
		
		ExpresionRegular bER = new ExpresionRegular(er.getEstado(), bTerms);
		ExpresionRegular resultado = multiplicar(alfa, bER);
		return resultado;
	}
	
	public static ExpresionRegular multiplicar(String sEntrada, ExpresionRegular er){
		ExpresionRegular resultado = new ExpresionRegular(er.getEstado());
		Termino[] terminos = new Termino[er.getTerms().length];
		for(int i=0; i<terminos.length; i++){
			if(er.getTerms()[i].esSecuenciaNula()){
				terminos[i] = new Termino(sEntrada);
			}
			else{
				terminos[i] = new Termino(sEntrada+er.getTerms()[i].getSimbolosE(), er.getTerms()[i].getEstado());
			}
		}
		resultado.setTerms(terminos);
		return resultado;
	}
	
	public static ExpresionRegular eliminarEstado(ExpresionRegular er, String estado){
		ExpresionRegular resultado = new ExpresionRegular(er.getEstado());
		Vector<Termino> estadosFinales = new Vector<Termino>();
		for(int i=0; i<er.getTerms().length; i++){
			if(!er.getTerms()[i].getEstado().equals(estado)){
				estadosFinales.addElement(er.getTerms()[i]);
			}
		}
		Termino[] terminos;
		if(estadosFinales.size()==0){
			terminos = new Termino[1];
			terminos[0] = new Termino();
		}
		else{
			terminos = new Termino[estadosFinales.size()];
			for(int i=0; i<estadosFinales.size(); i++){
				Termino t = (Termino)estadosFinales.elementAt(i);
				terminos[i] = new Termino(t.getSimbolosE(), t.getEstado());
			}
		}
		resultado.setTerms(terminos);
		return resultado;
	}
	
	
	
	public static ExpresionRegular reemplazarAenB(ExpresionRegular a, ExpresionRegular b){
		ExpresionRegular resultado = new ExpresionRegular(b.getEstado());
		Vector<Termino> estadosFinales = new Vector<Termino>();
		for(int i=0; i<b.getTerms().length; i++){
			if(b.getTerms()[i].getEstado().equals(a.getEstado())){
				Termino[] aux = multiplicar(b.getTerms()[i].getSimbolosE(), a).getTerms();
				for(int k=0; k<aux.length;k++){
					estadosFinales.addElement(aux[k]);
				}
			}
			else{
				estadosFinales.addElement(b.getTerms()[i]);
			}
		}

		if(estadosFinales.size()==0){
			return b;
		}
		
		Termino[] terminos = new Termino[estadosFinales.size()];
		for(int i=0; i<estadosFinales.size();i++){
			terminos[i] = estadosFinales.elementAt(i);
		}
		resultado.setTerms(terminos);
		
		
		return resultado;
	}
	
	public static ExpresionRegular convertirAFenER(AutomataFinito af){
		
		
		Vector<ExpresionRegular> ecuaciones = af.getEcuaciones();
		int pasos = 1;
		do{
			System.out.println("\n");
			System.out.println("----------------------------PASO "+pasos);
			AutomataFinito pasosAF = new AutomataFinito(ecuaciones, "A");
			System.out.println("***Ecuaciones");
			System.out.println(pasosAF.toString());
			
			ecuaciones = ardenEcuaciones(ecuaciones);
			
			pasosAF = new AutomataFinito(ecuaciones, "A");
			System.out.println("***Despues de ARDEN");
			System.out.println(pasosAF.toString());
			
			ecuaciones = eliminarEstadosDeError(ecuaciones);
			
			pasosAF = new AutomataFinito(ecuaciones, "A");
			System.out.println("***Despues de eliminar estados de error");
			System.out.println(pasosAF.toString());
			
			int e =ExpresionRegular.ecuacionConMenosTerminos(ecuaciones);
			ExpresionRegular ecAReemplazar = ecuaciones.elementAt(e);
			ecuaciones.removeElementAt(e);
			ecuaciones = reemplazarAenEcuaciones(ecAReemplazar, ecuaciones);
			
			pasosAF = new AutomataFinito(ecuaciones, "A");
			System.out.println("***Despues de reemplazar "+ecAReemplazar.getEstado()+" en todas");
			System.out.println(pasosAF.toString());
			pasos++;
			
		}while(ecuaciones.size()>1);
		
		ExpresionRegular resultado = arden(ecuaciones.elementAt(0));
		System.out.println("\nRESULTADO");
		System.out.println(ExpresionRegular.mostrarResultado(resultado));
		
		return resultado;
	}

	private static int ecuacionConMenosTerminos(
			Vector<ExpresionRegular> ecuaciones) {
		int resultado = 1;
		int minTerms = 50;
		for(int i=0; i<ecuaciones.size();i++){
			if(i!=0){
				if(ecuaciones.elementAt(i).getTerms().length<minTerms){
					resultado = i;
					minTerms = ecuaciones.elementAt(i).getTerms().length;
				}
			}
		}
		return resultado;
	}

	private static Vector<ExpresionRegular> reemplazarAenEcuaciones(
			ExpresionRegular elementAt, Vector<ExpresionRegular> ecuaciones) {
		Vector<ExpresionRegular> resultado = new Vector<ExpresionRegular>();
		for(int i=0; i<ecuaciones.size(); i++){
			resultado.addElement(reemplazarAenB(elementAt, ecuaciones.elementAt(i)));
		}
		return resultado;
	}

	private static Vector<ExpresionRegular> eliminarEstadosDeError(
			Vector<ExpresionRegular> ecuaciones) {
		Vector<ExpresionRegular> resultado = ecuaciones;
		int i = 0;
		while(i<resultado.size()){
			if(resultado.elementAt(i).esEstadoDeError()){
				ExpresionRegular estadoDeError = resultado.elementAt(i);
				resultado.removeElementAt(i);
				Vector<ExpresionRegular> temp = new Vector<ExpresionRegular>();
				for(int j=0; j<resultado.size();j++){
					temp.addElement(ExpresionRegular.eliminarEstado(resultado.elementAt(j), estadoDeError.getEstado()));
				}
				resultado = temp;
				i = 0;
			}
			else{
				i++;
			}
		}
		return resultado;
	}

	private static Vector<ExpresionRegular> ardenEcuaciones(
			Vector<ExpresionRegular> ecuaciones) {
		Vector<ExpresionRegular> resultado = new Vector<ExpresionRegular>();
		for(int i=0; i<ecuaciones.size(); i++){
			if(i==0){
				resultado.addElement(ecuaciones.elementAt(i));
			}
			else{
				resultado.addElement(arden(ecuaciones.elementAt(i)));
			}
		}
		return resultado;
	}
	
	public static String mostrarResultado(ExpresionRegular er){
		String resultado = er.estado + " = ";
		if(er.estadoDeError) {
			resultado = resultado + "ERROR";
			return resultado;
		}
		if(er.terms.length == 0) resultado = resultado + Termino.LAMBDA;
		
		Termino[] terminos = er.terms;
		String adicion;
		for(int i=0; i<terminos.length; i++){
			if(terminos[i].esSecuenciaNula()){
				adicion = Termino.LAMBDA;
			}
			else{
				if(terminos[i].getEstado().equals(Termino.LAMBDA)){
					adicion = terminos[i].getSimbolosE();
				}
				else{
					adicion = terminos[i].getSimbolosE() + terminos[i].getEstado();
				}
			}
			if((i+1)<terminos.length) adicion = adicion + "+\n";
			resultado = resultado + adicion;
		}
		return resultado;
	}
	
}


