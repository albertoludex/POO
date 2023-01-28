package preguntas;

import java.util.ArrayList;


public class Pregunta_Listado extends Pregunta_Test
{
	//ATRIBUTOS
	private int num_resp;
	private int posicion_ok;
	
	private ArrayList<String> respuestas;
	
	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 * @param preg_txts
	 * @param aclar_txto
	 * @param menos
	 * @param respuesta_num
	 * @param pos_ok
	 */
	public Pregunta_Listado(String preg_txts, String aclar_txto, float menos, int respuesta_num, int pos_ok) 
	{
		super(preg_txts, aclar_txto, menos);
		this.num_resp = respuesta_num;
		this.posicion_ok = pos_ok;
		
		respuestas = new ArrayList<String>();
	}

	//Getters
	
	/**
	 * Getter.
	 * @return
	 */
	public ArrayList<String> getRespuestas() 
	{
		return respuestas;
	}
	
	
	/**
	 * Getter.
	 * @return
	 */
	public int getN_resp() 
	{
		return num_resp;
	}
	
	
	/**
	 * Getter
	 * @return
	 */
	public int getPos_correcta() 
	{
		return posicion_ok;
	}
	
	
	public void addRespuestas(String respuesta) 
	{
		respuestas.add(respuesta);
	}
	
	
	/**
	 * Método que formatea el array de respuestas en un string para la impresión.
	 * @return
	 */
	private String respuestaToString()
	{
		String r = "";
		
		for(int i=0; i<num_resp;i++)
		{
			r = r + ENTER + TABULADOR + Integer.toString(i+1) + POINT + ESPACIO + respuestas.get(i) + POINT;
		}
		
		return r;
	}

	
	@Override
	public String print_simple()  
	{
		if(aclarar_texto.compareTo("") == 0)
			return preg_txt + TABULADOR + TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos + respuestaToString();
		else			
			return preg_txt + AB_PARENT + aclarar_texto + CERR_PARENT + POINT + 
					TABULADOR + TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos + respuestaToString();
	}

	
	@Override
	public String print_comp() 
	{
		if(aclarar_texto.compareTo("") == 0)
			return preg_txt + TABULADOR + TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos + respuestaToString() +
				   ENTER + ENTER + RESP_PRINT + DOS_PUNTOS + ESPACIO + respuestas.get(posicion_ok);
		else
			return preg_txt + AB_PARENT + aclarar_texto + CERR_PARENT + POINT + TABULADOR + 
					TEST_RESTA_PREG + DOS_PUNTOS + ESPACIO + menos + respuestaToString() + ENTER + 
					RESP_PRINT + DOS_PUNTOS + ESPACIO + respuestas.get(posicion_ok-1);
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
	
	
	@Override
	public String getResp() 
	{
		return respuestaToString();
	}
}
