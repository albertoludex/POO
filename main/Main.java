package main;

import java.util.Scanner;


import examenes.Asignatura;
import examenes.Examenes;
import examenes.Imprimir;
import examenes.Examenes.CONVOCATORIA;
import examenes.Examenes.TIPO_EX;
import persistencia.Persistencia;
import preguntas.Pregunta;
import preguntas.Pregunta_Desarrollo;
import preguntas.Pregunta_Rellenar;
import preguntas.Pregunta_Teorica;
import preguntas.Pregunta_V_F;
import preguntas.Pregunta_Listado;
import pdf.Pdfs;

public class Main implements ConstantsI
{
	//ATRIBUTOS
	private static int opc;
	
	private static Imprimir imprimible;
	private static Scanner scanln;
	private static Pregunta p;
	private static Examenes ex = null;
	private static Persistencia persist = new Persistencia();
	
	private static CONVOCATORIA c;
	private static TIPO_EX t_ex;
	
	
	/**
	 * Main.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		boolean exit = false;
		scanln = new Scanner(System.in);
		Asignatura POO = new Asignatura("POO", "Programación Orientada a Objetos");
		Asignatura DADM = new Asignatura("DADM", "Desarrollo de Aplicaciones para Dispositivos Móviles");
		
		persist.addAsignatura(POO);
		persist.addAsignatura(DADM);
		
		System.out.println(INIC);
		
		while(exit == false)
		{		
			try
			{
				opc = Integer.parseInt(pedirCadena(START_OPS));
			} catch (Exception e){opc = 0;};

			switch (opc)
			{
				case 1:
					persist.loadFromJson(PERSISTENCIA_UBI);
					crearPregunta();
					persist.persistToJson(PERSISTENCIA_UBI);
					break;
				case 2:
					persist.loadFromJson(PERSISTENCIA_UBI);
					crearExamen();
					persist.persistToJson(PERSISTENCIA_UBI);
					break;
				case 3:
					persist.loadFromJson(PERSISTENCIA_UBI);
					verExamen();
					break;
				case 4:
					persist.loadFromJson(PERSISTENCIA_UBI);
					eliminiarEP();
					persist.persistToJson(PERSISTENCIA_UBI);
				case 5:
					exit = true;
					System.out.println(OUT);
					break;
				default:
					System.out.println(ERROR_INVALID_OPC);
					break;
			}
		}
		
		scanln.close();
		System.out.println(END);
	}


	/** 
	 *  cadena por pantalla y devuelve el valor .
	 * @param pregunta
	 * @return
	 */
	private static String pedirCadena(String pregunta)
	{
		System.out.println(pregunta);
		
		return scanln.nextLine();
	}
	
	
	/**
	 *  generar una pregunta.
	 */
	private static void crearPregunta()
	{	
		int selec=0;
		boolean correcto = true;
		imprimible = new Imprimir();
		
		do
		{
			mostrarAsignaturas();

			try
			{
				selec = Integer.parseInt(pedirCadena(ENTER + ASIG_SELECT));
			} catch (Exception e){correcto = false;};
		}while(correcto == false);
		
		try
		{
			opc =  Integer.parseInt(pedirCadena(PRG_OPS));
		} catch (Exception e){opc = 0;};

		switch (opc)
		{
			case 1:
				p = new Pregunta_Teorica(
					pedirCadena(TXT_PREG), 
					pedirCadena(ACLARAR_PREG), 
					pedirCadena(RESP_PREG));
				
				persist.getAsignaturas().get(selec-1).addPregunta(p);
				break;
				
			case 2:	
				persist.getAsignaturas().get(selec-1).addPregunta(elegirPreguntaTest());
				break;
				
			case 3:
				int n;
				
				do 
				{
					try
					{
						n = Integer.parseInt(pedirCadena(NUM_DES_PREG));
					}catch (Exception e) {n = 0;};
					
					if(n < 1)
						System.out.println(ERROR_INVALID_CANTIDAD);
					
				} while(n < 1);
				
				p = new Pregunta_Desarrollo(DESARROLLO, "", n);
				
				for(int i=0; i<((Pregunta_Desarrollo) p).getN_apartados(); i++)
				{
					((Pregunta_Desarrollo) p).generarApartado(pedirCadena(TXT_PREG), 
				    pedirCadena(ACLARAR_PREG),
				    pedirCadena(RESP_PREG));
				}
				
				persist.getAsignaturas().get(selec-1).addPregunta(p);
				break;
				
			case 4:
				p = new Pregunta_Rellenar(
					pedirCadena(TXT_PREG), 
					pedirCadena(ACLARAR_PREG), 
					pedirCadena(HUECOS).charAt(0),
					pedirCadena(TXT_RELLENO));
				
				for(int i=0; i<((Pregunta_Rellenar) p).getN_huecos(); i++)
				{
					((Pregunta_Rellenar) p).addHuecos(pedirCadena(PREG_RELLENO + (i+1) + POINT));
				}
				
				persist.getAsignaturas().get(selec-1).addPregunta(p);
				break;
				
			default:
				System.out.println(ERROR_INVALID_OPC);
				break;
		}
	}
	
	
	/**
	 * a generar un examen.
	 */
	private static void crearExamen()
	{
		int selec = hacerElecciones();
		Asignatura a = persist.getAsignaturas().get(selec-1);
		
		ex = new Examenes(
			 pedirCadena(AUTOR), 
			 a.getTitulo(), 
			 pedirCadena(CURSO), 
			 c, t_ex,
			 Integer.parseInt(pedirCadena(NUM_PREG)));
		
		ex.generarPreguntas(a.getPreguntas());
		
		a.addExamen(ex);
	}
	
	
	/**
	 * visualizar un examen.
	 */
	private static void verExamen()
	{
		int selec=1, cont=0;
		boolean resp = false, correcto = true;
		
		do
		{
			mostrarAsignaturas();

			try
			{
				selec = Integer.parseInt(pedirCadena(NUM_PREG));
			} catch (Exception e){correcto = false;};
			
			if(selec>persist.getAsignaturas().size())
				correcto=false;
			else
				correcto=true;
		}while(correcto == false);
		
		for(int i=0; i<persist.getAsignaturas().get(selec-1).getExamenes().size(); i++)
		{
			imprimible = new Imprimir();

			System.out.print((i+1) + POINT);
			imprimible.Print_Obj_Comp(persist.getAsignaturas().get(selec-1).getExamenes().get(i));
			System.out.println(POINT + ENTER);
			cont++;
		}
		
		try
		{
			opc =  Integer.parseInt(pedirCadena(EX_VER));
		} catch (Exception e){opc = 0;};
		
		if(opc > cont)
			opc=0;

		switch (opc)
		{
			case 0:
				System.out.println(EX_NO + ENTER);
				break;
				
			default:
				if(pedirCadena(EX_RESP).compareTo(OK) == 0)
					resp = true;
				
				ex = persist.getAsignaturas().get(selec-1).getExamenes().get(opc-1);
				
				System.out.println(ENTER + ENTER + ENTER);
				imprimible.Print_Obj_Comp(ex);
				System.out.println(ENTER);
				
				for(int i=0;i<ex.getN_preguntas();i++)
				{
					try
					{
						System.out.print(EX_PRINT + ESPACIO + (i+1) + ESPACIO + AB_PARENT + 
										 ex.getPuntuaciones().get(i) + CERR_PARENT + DOS_PUNTOS + ESPACIO);
						
						if(resp==true)
						{
							imprimible.Print_Obj_Comp(ex.getPreguntas_selec().get(i));
						}
						else
						{
							imprimible.Print_Obj_Simp(ex.getPreguntas_selec().get(i));
						}
						
						System.out.println(ENTER + ENTER);
					}
					catch(IndexOutOfBoundsException e)
					{
						System.out.println(EX_PREG_VACIO);
						i=ex.getN_preguntas();
					}
				}
				
				if(pedirCadena(PDF_CREATE).compareTo(OK) == 0)
				{
					Pdfs.generadorPDF(ex, resp);
					
					System.out.println(ENTER + ENTER);
				}
				break;
		}
	}
	
	
	/**
	 *  elecciones necesarias para generar el examen.
	 */
	private static int hacerElecciones()
	{
		boolean correcto = true;
		int selec = 0;
		imprimible = new Imprimir();
		
		do
		{
			mostrarAsignaturas();

			try
			{
				selec = Integer.parseInt(pedirCadena(ASIG_SELECT));
			} catch (Exception e){correcto = false;};
		}while(correcto == false);
		
		try
		{
			opc = Integer.parseInt(pedirCadena(CONVOC));
		} catch (Exception e){opc = 0;};
		
		switch(opc)
		{
			case 1:
				c = CONVOCATORIA.ENERO;
				break;
				
			case 2:
				c = CONVOCATORIA.JUNIO;
				break;
				
			case 3:
				c = CONVOCATORIA.SEPTIEMBRE;
				break;
				
			case 4:
				c = CONVOCATORIA.DICIEMBRE;
				break;
				
			default:
				System.out.println(ERROR_INVALID_OPC);
				break;
		}
		
		try
		{
			opc = Integer.parseInt(pedirCadena(EX_TYPE));
		} catch (Exception e){opc = 0;};
		
		switch(opc)
		{
			case 1:
				t_ex = TIPO_EX.TEORICO;
				break;
				
			case 2:
				t_ex = TIPO_EX.MIXTO;
				break;
				
			case 3:
				t_ex = TIPO_EX.TEST;
				break;
				
			case 4:
				t_ex = TIPO_EX.PRACTICO;
				break;
				
			default:
				System.out.println(ERROR_INVALID_OPC);
				break;
		}
		
		return selec;
	}

	
	/**
	 *  imprimir por pantalla las asignaturas disponibles.
	 */
	private static void mostrarAsignaturas()
	{
		imprimible = new Imprimir();
		System.out.println(ASIG);
		
		for(int i=0; i<persist.getAsignaturas().size(); i++)
		{
			System.out.print(ENTER + (i+1) + POINT + ESPACIO);
			imprimible.Print_Obj_Comp(persist.getAsignaturas().get(i));
			System.out.println(POINT + "");
		}
		System.out.println();
	}

	
	/**
	 *  el tipo de pregunta test que queremos.
	 * @return
	 */
	private static Pregunta elegirPreguntaTest()
	{
		int op;
		float r;

		try
		{
			op = Integer.parseInt(pedirCadena(PREG_TYPE));
		} catch (Exception e){op = 0;};
		
		try
		{
			r = Integer.parseInt(pedirCadena(PREG_REST_TEST));
		}catch (Exception e) {r = 0;};
		
		switch(op)
		{
			case 1:
				p = new Pregunta_V_F(
					pedirCadena(TXT_PREG), 
					pedirCadena(ACLARAR_PREG),
					r,
					pedirCadena(VF_PREG));
				break;
				
			case 2:
				int n, pos;
				
				do 
				{
					try
					{
						n = Integer.parseInt(pedirCadena(NUM_RESP));
					}catch (Exception e) {n = 0;};
					
					if(n < 1)
						System.out.println(ERROR_INVALID_CANTIDAD);
					
					try
					{
						pos = Integer.parseInt(pedirCadena(POS_RESP));
					}catch (Exception e) {pos = 0;};
					
					if(pos > n || pos < 1)
						System.out.println(ERROR_INVALID_CANTIDAD);	
					
				}while(n < 1 && pos < 1 && pos > n);
				
				p = new Pregunta_Listado(
						pedirCadena(TXT_PREG), 
						pedirCadena(ACLARAR_PREG), 
						r, n, pos);
					
				for(int i=0; i<((Pregunta_Listado) p).getN_resp(); i++)
				{
					((Pregunta_Listado) p).addRespuestas(pedirCadena(TXT_TEST_PREG + (i+1) + POINT));
				}
				break;
			
			default:
				System.out.println(ERROR_INVALID_OPC);
				break;
		}
		
		return p;
	}
	
	
	/**
	 *  eliminar preguntas o exámenes 
	 */
	 private static void eliminiarEP() 
	 {
		 int asig;
	 	imprimible = new Imprimir();
	 	
		 try
		 {
			 opc = Integer.parseInt(pedirCadena(PREG_DELETE));
		 } catch (Exception e){opc = 0;};
		 
		 switch(opc)
		 {
		 	case 1:
				System.out.println(PREGS_PARA_DELETE);
				
				for(int x=0;x<persist.getAsignaturas().size();x++)
				{
					System.out.print(ENTER + (x+1) + POINT + ESPACIO);
					imprimible.Print_Obj_Comp(persist.getAsignaturas().get(x));
					System.out.println(POINT + "");
					System.out.println();
					
					for(int i=0; i<persist.getAsignaturas().get(x).getPreguntas().size(); i++)
					{
						System.out.print(ENTER + TABULADOR + (i+1) + POINT + ESPACIO);
						imprimible.Print_Obj_Simp(persist.getAsignaturas().get(x).getPreguntas().get(i));
					}
					System.out.println();
				}
				
				do
				{
					try
					{
						asig = Integer.parseInt(pedirCadena(ASIG_PARA_DELETE));
					} catch (Exception e){asig = -1;};
					
					if(asig>persist.getAsignaturas().size())
						asig=-1;
					 
					try
					{
						opc = Integer.parseInt(pedirCadena(ASIG_PARA_DELETE));
					} catch (Exception e){opc = -1;};
					
					if(asig<=0 || opc<=0)
						System.out.println(ERROR_INVALID_OPC);
				}while(asig<0);
				
				if(opc>persist.getAsignaturas().get(asig-1).getPreguntas().size())
					System.out.println(ERROR_INVALID_OPC);
	
				if(pedirCadena(ENTER + DELETE1 + persist.getAsignaturas().get(asig-1).getPreguntas().get(opc-1).print_simple()
				+ DELETE2).compareTo(OK) == 0)
				{
					persist.getAsignaturas().get(asig-1).getPreguntas().remove(opc-1);
					System.out.println(PREG_DELETEADA);
				}
				else
					System.out.println(ENTER + PREG_DELETEADA);
		 		break;
		 		
		 	case 2:
		 		System.out.println(EX_DELETE);
		 		
		 		for(int x=0;x<persist.getAsignaturas().size();x++)
				{
					System.out.print(ENTER + (x+1) + POINT + ESPACIO);
					imprimible.Print_Obj_Comp(persist.getAsignaturas().get(x));
					System.out.println(POINT + "");
					System.out.println();
					
					for(int i=0; i<persist.getAsignaturas().get(x).getExamenes().size(); i++)
					{
						System.out.print(ENTER + TABULADOR + (i+1) + POINT + ESPACIO);
						imprimible.Print_Obj_Simp(persist.getAsignaturas().get(x).getExamenes().get(i));
					}
					System.out.println();
				}
		 		
		 		do
				{
					try
					{
						asig = Integer.parseInt(pedirCadena(ASIG_PARA_DELETE));
					} catch (Exception e){asig = -1;};
					
					if(asig>persist.getAsignaturas().size())
						asig=-1;
					 
					try
					{
						opc = Integer.parseInt(pedirCadena(PREG_NUM_DELETE));
					} catch (Exception e){opc = -1;};
					
					if(asig<=0 || opc<=0)
						System.out.println(ERROR_INVALID_OPC);
				}while(asig<0);
				
				if(opc>persist.getAsignaturas().get(asig-1).getExamenes().size())
					System.out.println(ERROR_INVALID_OPC);
				
				if(pedirCadena(ENTER + DELETE1 + persist.getAsignaturas().get(asig-1).getExamenes().get(opc-1).print_simple()
						+ DELETE2).compareTo(OK) == 0)
						{
							persist.getAsignaturas().get(asig-1).getExamenes().remove(opc-1);
							System.out.println(EX_DELETEADO);
						}
						else
							System.out.println(ENTER + EX_NO_DELETEADO);
		 		break;
		 		
		 	default:
		 		System.out.println(NO_DELETE_NADA + ENTER);
		 		break;
		 }
	 }
}