package examenes;

import java.util.ArrayList;

import main.ConstantsI;
import preguntas.Pregunta;

public class Asignatura implements ImprimirI, ConstantsI
{
	//ATRIBUTOS
		private String code;
		private String titulo;

		private ArrayList<Pregunta> preguntas;
		private ArrayList<Examenes> examenes;

		
		//MÉTODOS
		
		//Constructor
		
		/**
		 * Constructor.
		 * @param code
		 * @param titulo
		 */
		public Asignatura(String code, String titulo)
		{
			this.code = code;
			this.titulo = titulo;
			
			preguntas = new ArrayList<Pregunta>();
			examenes = new ArrayList<Examenes>();
		}
		
		//Getters
		
		/**
		 * Getter.
		 * @return
		 */
		public String getCode() 
		{
			return code;
		}
		
		
		/**
		 * Getter.
		 * @return
		 */
		public String getTitulo() 
		{
			return titulo;
		}
		
		
		/**
		 * Getter.
		 * @return
		 */
		public ArrayList<Pregunta> getPreguntas()
		{
			return preguntas;
		}
		
		
		/**
		 * Getter.
		 * @return
		 */
		public ArrayList<Examenes> getExamenes()
		{
			return examenes;
		}
		
		//Otros métodos
		
		/**
		 * Método que añade una pregunta al array de preguntas.
		 * @param p.
		 */
		public void addPregunta(Pregunta p)
		{
			preguntas.add(p);
		}
		
		
		/**
		 * Método que añade un examen al array de examenes.
		 * @param ex.
		 */
		public void addExamen(Examenes ex)
		{
			examenes.add(ex);
		}

		
		@Override
		public String print_simple() 
		{
			return this.code;
		}


		@Override
		public String print_comp() 
		{
			return this.code + GUION + this.titulo;
		}
	}

