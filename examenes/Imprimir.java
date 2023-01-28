package examenes;

public class Imprimir {
	/**
	 * M�todo que sirve para realizar una impresi�n por pantalla de un objeto (Versi�n simple).
	 * @param obj Objeto a imprimir.
	 */
	public void Print_Obj_Simp(ImprimirI obj)
	{
		System.out.println(obj.print_simple());
	}
	
	
	/**
	 * M�todo que sirve para realizar una impresi�n por pantalla de un objeto (Versi�n completa).
	 * @param obj Objeto a imprimir.
	 */
	public void Print_Obj_Comp(ImprimirI obj)
	{
		System.out.print(obj.print_comp());
	}
}
