package pruebas;

import java.util.Vector;

public class DeAFaER5 {

	/**
	 * @param argsytyutu
	 */
	public static void main(String[] args) {
		ExpresionRegular t1 = new ExpresionRegular("A");
		ExpresionRegular t2 = new ExpresionRegular("B");
		ExpresionRegular t3 = new ExpresionRegular("C");
		ExpresionRegular t4 = new ExpresionRegular("Er");
		
		Termino terms[] = new Termino[3];
		terms[0] = new Termino("0","B");
		terms[1] = new Termino("1","B");
		terms[2] = new Termino();
		t1.setTerms(terms);
		
		
		terms = new Termino[3];
		terms[0] = new Termino("0","C");
		terms[1] = new Termino("1","C");
		terms[2] = new Termino();
		t2.setTerms(terms);
		
		terms = new Termino[3];
		terms[0] = new Termino("0","Er");
		terms[1] = new Termino("1","A");
		terms[2] = new Termino();
		t3.setTerms(terms);
		
		terms = new Termino[2];
		terms[0] = new Termino("0","Er");
		terms[1] = new Termino("1","Er");
		t4.setTerms(terms);
		
	
		Vector<ExpresionRegular> ecuaciones = new Vector<ExpresionRegular>();
		ecuaciones.addElement(t1);
		ecuaciones.addElement(t2);
		ecuaciones.addElement(t3);
		ecuaciones.addElement(t4);
		
		
		AutomataFinito af = new AutomataFinito(ecuaciones, "A");
		ExpresionRegular.convertirAFenER(af);

	}

}
