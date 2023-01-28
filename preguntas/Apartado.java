package preguntas;

import examenes.ImprimirI;
import main.ConstantsI;

public class Apartado implements ImprimirI, ConstantsI
{
	//ATRIBUTOS
	private String preg_txt;
	private String aclar_txt;
	private String resp;
	
	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 * @param preg_txt
	 * @param aclarar_txt
	 * @param resp
	 */
	public Apartado(String preg_txt, String aclarar_txt, String resp) 
	{
		this.preg_txt = preg_txt;
		this.aclar_txt = aclarar_txt;
		this.resp = resp;
	}

	//Getters
	
	/**
	 * Getter.
	 * @return
	 */
	public String getTexto_pregunta() 
	{
		return preg_txt;
	}

	
	/**
	 * Getter.
	 * @return
	 */
	public String getTexto_aclaratorio()
	{
		return aclar_txt;
	}
	
	
	/**
	 * Getter.
	 * @return
	 */
	public String getRespuesta()
	{
		return resp;
	}

	
	@Override
	public String print_simple()  
	{
		if(aclar_txt.compareTo("") == 0)
			return preg_txt;
		else
			return preg_txt + AB_PARENT + aclar_txt + CERR_PARENT + POINT;
	}

	
	@Override
	public String print_comp() 
	{
		if(aclar_txt.compareTo("") == 0)
			return preg_txt + ENTER + RESP_PRINT + DOS_PUNTOS + ESPACIO + resp;
		else
			return preg_txt + AB_PARENT + aclar_txt + CERR_PARENT + POINT + ENTER +
					RESP_PRINT + DOS_PUNTOS + ESPACIO + resp;
	}
}
