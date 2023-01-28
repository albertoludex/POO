package preguntas;


public class Pregunta_V_F extends Pregunta_Test
{
	//ATRIBUTOS
	private String result;
	
	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 * @param texto_pregunta
	 * @param texto_aclaratorio
	 * @param resta
	 * @param result
	 */
	public Pregunta_V_F(String texto_pregunta, String texto_aclaratorio, float resta, String result) 
	{
		super(texto_pregunta, texto_aclaratorio, resta);
		this.result = result;
	}

	//Getters
	
	@Override
	public String getResp() 
	{
		return result;
	}
	
	
	@Override
	public String print_simple()  
	{
		if(aclarar_texto.compareTo("") == 0)
			return preg_txt + TABULADOR + TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos;
		else
			return preg_txt + ESPACIO + AB_PARENT + aclarar_texto + 
					CERR_PARENT + POINT + TABULADOR + TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos;
	}

	
	@Override
	public String print_comp() 
	{
		if(aclarar_texto.compareTo("") == 0)
			return preg_txt + TABULADOR + TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos + TABULADOR + result;
		else
			return preg_txt + ESPACIO + AB_PARENT + aclarar_texto + CERR_PARENT + 
					POINT + TABULADOR + TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos + TABULADOR + result;
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
