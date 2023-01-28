package preguntas;


public class Pregunta_Teorica extends Pregunta
{
	//ATRIBUTOS
	private String correct;
	
	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 * @param preg_txt
	 * @param aclarar_txt.
	 * @param correcto
	 */
	public Pregunta_Teorica(String preg_txt, String aclarar_txt, String correcto)
	{
		super(preg_txt, aclarar_txt);
		this.correct = correcto;
	}
	
	//Getters
	
	@Override
	public String getResp() 
	{
		return correct;
	}
	
	
	@Override
	public String print_simple()  
	{
		if(aclarar_texto.compareTo("") == 0)
			return preg_txt;
		else
			return preg_txt + ESPACIO + AB_PARENT + aclarar_texto + CERR_PARENT + POINT;
	}

	
	@Override
	public String print_comp() 
	{
		if(aclarar_texto.compareTo("") == 0)
			return preg_txt + ENTER + TABULADOR + EX_PRINT + DOS_PUNTOS + ESPACIO + correct;
		else
			return preg_txt + ESPACIO + AB_PARENT + aclarar_texto + CERR_PARENT + POINT + 
					ENTER + ESPACIO + RESP_PRINT + DOS_PUNTOS + ESPACIO + correct;
	}

	
	@Override
	public String getTxt_preg()  
	{
		return preg_txt;
	}
	
	
	@Override
	public String getTxt_aclar() 
	{
		return aclarar_texto;
	}
}

