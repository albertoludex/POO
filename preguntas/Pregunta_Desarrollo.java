package preguntas;

import java.util.ArrayList;

import examenes.Imprimir;


public class Pregunta_Desarrollo extends Pregunta
{
	//ATRIBUTOS

	private int apartados_numero;
	private ArrayList<Apartado> apart;
	private Imprimir print;

	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 * @param preg_txt
	 * @param texto_aclaratorio
	 * @param n_apartados
	 */
	public Pregunta_Desarrollo(String preg_txt, String acl_txt, int num_apart)
	{
		super(preg_txt, acl_txt);
		this.apartados_numero = num_apart;
		
		apart = new ArrayList<Apartado>();
	}

	//Getters
	
	/**
	 * Getter.
	 * @return
	 */
	public int getN_apartados() 
	{
		return apartados_numero;
	}
	
	
	/**
	 * Getter.
	 * @return
	 */
	public ArrayList<Apartado> getApartados()
	{
		return apart;
	}
	
	//Otros métodos
	
	/**
	 * Método que generar un apartado.
	 * @param enun
	 * @param aclar
	 * @param resp
	 */
	public void generarApartado(String enun, String aclar, String resp)
	{
		Apartado a = new Apartado(enun, aclar, resp);
		
		apart.add(a);
	}
	
	
	/**
	 * Método para pasar las respuestas a String
	 * @return
	 */
	private String respuestaToString()
	{
		String r = "";
		
		for(int i=0; i<apartados_numero;i++)
		{
			r = r + ENTER + TABULADOR + Integer.toString(i+1) + POINT + ESPACIO + apart.get(i).getRespuesta() + POINT;
		}
		
		return r;
	}
	
	
	@Override
	public String print_simple()  
	{
		print = new Imprimir();
		
		for(int i=0; i<apartados_numero;i++)
		{
			System.out.print(TABULADOR + (i+1) + POINT + ESPACIO);
			print.Print_Obj_Simp(apart.get(i));
			System.out.println();
		}
		
		return "";
	}

	
	@Override
	public String print_comp() 
	{
		print = new Imprimir();
		
		for(int i=0; i<apartados_numero;i++)
		{
			System.out.print(TABULADOR + (i+1) + POINT + ESPACIO);
			print.Print_Obj_Comp(apart.get(i));
			System.out.println();
		}
		
		return "";
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
