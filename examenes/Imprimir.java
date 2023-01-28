package examenes;

public class Imprimir {
	/**
	 * Método que sirve para realizar una impresión por pantalla de un objeto (Versión simple).
	 * @param obj Objeto a imprimir.
	 */
	public void Print_Obj_Simp(ImprimirI obj)
	{
		System.out.println(obj.print_simple());
	}
	
	
	/**
	 * Método que sirve para realizar una impresión por pantalla de un objeto (Versión completa).
	 * @param obj Objeto a imprimir.
	 */
	public void Print_Obj_Comp(ImprimirI obj)
	{
		System.out.print(obj.print_comp());
	}
}
