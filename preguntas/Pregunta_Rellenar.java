package preguntas;

import java.util.ArrayList;


public class Pregunta_Rellenar extends Pregunta
{
	//ATRIBUTOS
	private String respuesta_f;
	private int num_huecos = 0;
	private char espacios;
	
	private ArrayList<String> espacio;
	
	
	//MÉTODOS

	//Constructor
	
	/**
	 * Constructor.
	 * @param txt_pregu
	 * @param aclarar_txt
	 * @param f_resp
	 * @param n_huecos
	 */
	public Pregunta_Rellenar(String txt_pregu, String aclarar_txt, char hueco, String f_resp)
	{
		super(txt_pregu, aclarar_txt);
		
		this.respuesta_f = f_resp;
		this.espacios = hueco;
		
		for(int i=0; i<f_resp.length();i++)
		{
			if(f_resp.charAt(i) == hueco)
			{
				num_huecos++;
			}
		}
		
		espacio = new ArrayList<String>();
	}
	
	//Getters
	
	/**
	 * Getter.
	 * @return
	 */
	public int getN_huecos() 
	{
		return num_huecos;
	}
	
	
	/**
	 * Getter.
	 * @return
	 */
	public int getHueco() 
	{
		return espacios;
	}
	
	
	/**
	 * Getter.
	 * @return
	 */
	public ArrayList<String> getHuecos()
	{
		return espacio;
	}
	
	
	/**
	 * Getter.
	 * @return
	 */
	public String getF_resp() 
	{
		return respuesta_f;
	}
	
	
	public void addHuecos(String hueco)
	{
		espacio.add(hueco);
	}
	
	
	/**
	 * Método para dar un formato correcto a la impresión de las respuestas.
	 * @return
	 */
	private String respuestaToString()
	{
		String r = "";
		
		for(int i=0; i<num_huecos;i++)
		{
			r = r + ENTER + TABULADOR + (i+1) + POINT + ESPACIO + espacio.get(i) + POINT;
		}
		
		return r;
	}

	
	@Override
	public String print_simple()  
	{
		if(aclarar_texto.compareTo("") == 0)
			return  preg_txt + TABULADOR + respuesta_f;
		else
			return  preg_txt + ESPACIO + AB_PARENT + aclarar_texto + CERR_PARENT + DOS_PUNTOS + TABULADOR + respuesta_f;
	}

	
	@Override
	public String print_comp() 
	{
		if(aclarar_texto.compareTo("") == 0)
			return preg_txt + TABULADOR + respuesta_f + ENTER + TABULADOR + EX_PRINT + 
				   DOS_PUNTOS + ESPACIO + ENTER + respuestaToString();
		else
			return preg_txt + ESPACIO + AB_PARENT + aclarar_texto + CERR_PARENT + DOS_PUNTOS 
					+ TABULADOR + respuesta_f + ENTER + TABULADOR + RESP_PRINT + DOS_PUNTOS + ESPACIO + ENTER + respuestaToString();
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

