package pruebas;

public class Transformation {
	
	public static void main(String arg[]){
		ExpresionRegular t1 = new ExpresionRegular("A");
		ExpresionRegular t2 = new ExpresionRegular("B");
		
		Termino terms[] = new Termino[2];
		terms[0] = new Termino("0","A");
		terms[1] = new Termino("1","B");
		t1.setTerms(terms);
		
		terms = new Termino[2];
		terms[0] = new Termino("0","B");
		terms[1] = new Termino("1","B");
		t2.setTerms(terms);
		
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println("-----ARDEN");
		ExpresionRegular t3 = ExpresionRegular.arden(t1);
		ExpresionRegular t4 = ExpresionRegular.arden(t2);
		System.out.println(t3.toString());
		System.out.println(t4.toString());
		System.out.println("-----ELIMINAR ESTADOS DE ERROR");
		ExpresionRegular t5 = t3;
		ExpresionRegular t6 = t4;
		if(t3.esEstadoDeError()){
			t6 = ExpresionRegular.eliminarEstado(t4, t3.getEstado());
		}
		if(t4.esEstadoDeError()){
			t5 = ExpresionRegular.eliminarEstado(t3, t4.getEstado());
		}
		System.out.println(t5.toString());
		System.out.println(t6.toString());
		
		System.out.println("-----ENSAYO REEMPLAZO");
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println("-----REEMPLAZAR B EN A");
		ExpresionRegular t7 = ExpresionRegular.reemplazarAenB(t2, t1);
		System.out.println(t7.toString());
		System.out.println(t2.toString());
		System.out.println("-----REEMPLAZAR A EN B");
		ExpresionRegular t8 = ExpresionRegular.reemplazarAenB(t1, t2);
		System.out.println(t1.toString());
		System.out.println(t8.toString());

		
	}

}
