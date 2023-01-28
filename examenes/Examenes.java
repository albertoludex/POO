package examenes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


import main.ConstantsI;
import preguntas.Pregunta;
import preguntas.Pregunta_Desarrollo;
import preguntas.Pregunta_Rellenar;
import preguntas.Pregunta_Test;
import preguntas.Pregunta_Teorica;

public class Examenes implements ConstantsI, ImprimirI
{
	//ATRIBUTOS

		private String asignatura;
		private int n_preguntas;
		private String autor;
		private String curso;
		private Date f_realizacion = Calendar.getInstance().getTime(); 
		private String n_asignatura;
		
		private static final Random rand = new Random();
		
		private ArrayList<Pregunta> preguntas_selec;
		private ArrayList<Float> puntuaciones;
		
		private CONVOCATORIA convocatoria;
		private TIPO_EX tipo_examen;
		
		
		//ENUMS
			/**
			 * Enumeración de convocatorias.
			 *
			 */
			public enum CONVOCATORIA{ENERO, JUNIO, SEPTIEMBRE, DICIEMBRE}		
			/**
			 * Enumeración de tipos de examen.
			 *
			 */
			public enum TIPO_EX{TEORICO, MIXTO, TEST, PRACTICO}

		
		//MÉTODOS
			
		//Constructor
		
		/**
		 * Constructor.
		 * @param autor
		 * @param asignatura
		 * @param curso
		 * @param convocatoria
		 * @param tipo_examen
		 * @param n_preguntas
		 */
		public Examenes(String autor, String asignatura, String curso, CONVOCATORIA convocatoria, TIPO_EX tipo_examen, int n_preguntas)
		{
			this.autor = autor;
			this.n_asignatura = asignatura;
			this.curso = curso;
			this.convocatoria = convocatoria;
			this.tipo_examen = tipo_examen;
			this.n_preguntas = n_preguntas;
		}
		
		//Getters
		
		/**
		 * Getter
		 * @return
		 */
		public int getN_preguntas() 
		{
			return n_preguntas;
		}
		
		
		/**
		 * Getter.
		 * @return
		 */
		public String getAsignatura() 
		{
			return asignatura;
		}

		
		/**
		 * Getter.
		 * @return
		 */
		public String getAutor() 
		{
			return autor;
		}

		
		/**
		 * Getter.
		 * @return
		 */
		public String getCurso() 
		{
			return curso;
		}

		
		/**
		 * Getter.
		 * @return
		 */
		public String getF_realizacion() 
		{
			return DATE_FORMAT.format(f_realizacion);
		}

		
		/**
		 * Getter.
		 * @return
		 */
		public TIPO_EX getTipo_examen()
		{
			return tipo_examen;
		}
		
		
		/**
		 * Getter.
		 * @return
		 */
		public CONVOCATORIA getConvocatoria() 
		{
			return convocatoria;
		}
		
		
		/**
		 * Getter.
		 * @return
		 */
		public ArrayList<Pregunta> getPreguntas_selec()
		{
			return preguntas_selec;
		}
		
		
		/**
		 * Getter.
		 * @return
		 */
		public ArrayList<Float> getPuntuaciones()
		{
			return puntuaciones;
		}
		
		
		//Otros métodos
		
		/**
		 * Método que genera las preguntas del examen y que asigna las notas de cada pregunta.
		 * @param preguntas
		 */
		public void generarPreguntas(ArrayList<Pregunta> preguntas)
		{
			Pregunta p;
			preguntas_selec = new ArrayList<Pregunta>();
			puntuaciones = new ArrayList<Float>();

			switch(tipo_examen)
			{
				case TEORICO:
					for(int i=0; i<n_preguntas;)
					{
						p = preguntas.get(rand.nextInt(preguntas.size()));
						
						if( p instanceof Pregunta_Teorica)
						{
							preguntas_selec.add(p);
							i++;
						}
					}
					break;
					
				case MIXTO:
					int cont = 0;
					
					for(int i=0; i<n_preguntas; i++)
					{	
						p = preguntas.get(rand.nextInt(preguntas.size()));
						
						if(p instanceof Pregunta_Desarrollo && cont==0)
						{
							cont++;
							preguntas_selec.add(p);
						}
						else
							preguntas_selec.add(p);
					}
					break;
					
				case TEST:			
					for(int i=0; i<n_preguntas;)
					{
						p = preguntas.get(rand.nextInt(preguntas.size()));
						
						if(p instanceof Pregunta_Test || p instanceof Pregunta_Rellenar)
						{
							preguntas_selec.add(p);
							i++;
						}
					}
					break;
					
				case PRACTICO:
					for(int i=0; i<n_preguntas;)
					{
						p = preguntas.get(rand.nextInt(preguntas.size()));
						
						if(p instanceof Pregunta_Desarrollo)
						{
							preguntas_selec.add(p);
							i++;
						}
					}
					break;
			}
			
			for(int x=0; x<preguntas_selec.size(); x++)
			{
				if(tipo_examen.compareTo(TIPO_EX.MIXTO) == 0)
				{
					if(preguntas_selec.get(x) instanceof Pregunta_Desarrollo)
					{
						if(n_preguntas == 1)
							puntuaciones.add((float) 10);
						else
							puntuaciones.add((float) 4);
					}
					else
					{
						if(n_preguntas == 1)
							puntuaciones.add((float) 10);
						else
						puntuaciones.add((float) (6/(float) (n_preguntas-1)));
					}
				}
				else
				{
					puntuaciones.add((float) (10/(float) n_preguntas));
				}
			}
		}

		
		@Override
		public String print_simple() 
		{
			return this.n_asignatura + GUION + this.curso + GUION + this.convocatoria;
		}

		
		@Override
		public String print_comp() 
		{
			return ESPACIO + DATE_FORMAT.format(this.f_realizacion) + GUION + this.autor + GUION + this.n_asignatura + GUION + 
				   this.convocatoria + GUION + this.curso + GUION + this.n_preguntas;
		}

}
