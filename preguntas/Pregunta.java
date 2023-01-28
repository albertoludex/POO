package preguntas;

import examenes.ImprimirI;
import main.ConstantsI;

public abstract class Pregunta implements ImprimirI, ConstantsI
{
	//ATRIBUTOS

	 protected String preg_txt;

	 protected String aclarar_texto;
	
	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 * @param texto_pregunta
	 * @param texto_aclaratorio
	 */
	public Pregunta(String texto_pregunta, String texto_aclaratorio)
	{
		this.preg_txt = texto_pregunta;
		this.aclarar_texto = texto_aclaratorio;
	}
	
	//Getters
	
	/**
	 * Getter.
	 * @return
	 */
	public abstract String getTxt_preg();
	
	
	/**
	 * Getter.
	 * @return
	 */
	public abstract String getTxt_aclar();

	
	/**
	 * Getter.
	 * @return
	 */
	public abstract String getResp();
}
