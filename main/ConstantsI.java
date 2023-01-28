package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import persistencia.RuntimeTypeAdapterFactory;
import preguntas.Pregunta;
import preguntas.Pregunta_Desarrollo;
import preguntas.Pregunta_Rellenar;
import preguntas.Pregunta_Teorica;
import preguntas.Pregunta_Listado;
import preguntas.Pregunta_V_F;


public interface ConstantsI {
	//CONSTANTES
	
		//Inicio
		  static final String GUION =
		  "-";
		  static final String ENTER =
		"\n";
		  static final String TABULADOR =
		"\t";
		  static final String POINT =
		".";
		  static final String DOS_PUNTOS =
		  ":";
		  static final String ESPACIO =
		  " ";
		  static final String AB_PARENT =
		  "(";
		  static final String CERR_PARENT =
		  ")";
		  static final String BARRA_BAJA =
		  "_";
		  static final String OK =
		  "si";	
		  static final String INIC = 
		"BIENVENIDO\n\n\n";
		  static final String PRG_OPS = 
		"\t\tDiga el tipo de pregunta que quiere\n\n1. Teórica. \n2. Tipo test. \n3. Desarrollo de código. \n4. Rellenar.";
		  static final String OUT =
		"\nfuera\n";
		  
		  static final String END =
		"\n\nFIN.\n";
		  static final String START_OPS = 
		"\t\tQue vamos a hacer\n\n1. Crear Pegunta. \n2. Crear examen. \n3. Ver exámenes guardados. \n4. Borrar exámenes/preguntas. \n5. Salir.";
		  static final String PERSISTENCIA_UBI =
		"./files/persistencia.txt";
		  static final String PREG_DELETE =
		  "\npara eliminar pregunta pulse 1, para examen creado pulse 2,  para salir 'ENTER'.";
		  static final String PREGS_PARA_DELETE =
		  "Para eliminar: ";
		  static final String ASIG_PARA_DELETE =
	      "\nAsignatura de la pregunta a eliminar:";
		  static final String ASIG_PARA_DELETE2 =
		  "\nEscriba la asignatura del examen que desea eliminar:";
		  static final String PREG_NUM_DELETE =
		  "\nNumero de pregunta a eliminar: ";
		  static final String DELETE1 =
		  "Eliminando ";
		  static final String DELETE2 =
	      " ¿seguro? Escriba 'si' si está seguro de ello.";
		  static final String PREG_DELETEADA =
		  "eliminada.";
		  static final String PREG_NO_DELETEADA =
		  "No eliminada.";
		  static final String EX_DELETE =
		  "Examenes para eliminar: ";
		  static final String EX_CHOOSE_DELETE =
		  "\nNumero de examen a borrar: ";
		  static final String EX_DELETEADO =
		  "Eliminado.";
		  static final String EX_NO_DELETEADO =
		  "No eliminado.";
		  static final String NO_DELETE_NADA =
		  "no borrar nada.";
		  
		 //Preguntas
		  static final String TXT_PREG =
		"Texto de pregunta:";
		  static final String ACLARAR_PREG =
		"texto para aclarar dudas. Si no presione ENTER:";
		  static final String RESP_PREG =
		"Pon la respuesta correcta:";
		  static final String PREG_TYPE =
		"¿Di el tipo de pregunta?\n\n1. Verdadero/Falso. \n2. Listado de opciones.";
		  static final String PREG_REST_TEST =
		"Valor de una mala. Si no  resta, introduzca '0':";
		  static final String VF_PREG =
		"Verdadero o falso. :";
		  static final String NUM_RESP =
		"Numero de respuesta:";
		  static final String POS_RESP =
		" respuesta correcta. (Ej: si la correcta es la a), introduzca 1:";
		  static final String TXT_TEST_PREG =
		"Posible respuesta ";
		  static final String NUM_DES_PREG =
		"numero de apartados:";
		  static final String HUECOS_NUM_RELL =
		"Introduzca el número de huecos para rellenar que tendrá la pregunta:";
		  static final String TXT_RELLENO =
		"Introduzca la frase para rellenarse, introduciendo un '?' en hueco:";
		  static final String PREG_RELLENO =
		"respuesta hueco ";
		  static final String TEST_RESTA_PREG =
		  "Resta";
		  static final String DESARROLLO =
		  "desarollor";
		  static final String HUECOS =
		  "caracter para hueco, por ejemplo, '?' á '_'";
		  
		//Examenes
		  static final String AUTOR =
		"Autor:";
		  static final String CURSO =
		"Curso:";
		  static final String NUM_PREG =
		"nº preguntas:";
		  static final String ASIG =
		"Asignaturas disponibles: ";
		  static final String ASIG_SELECT =
		"Selecciona una :";
		  static final String CONVOC =
		"convocatoria. \n\n1.ENERO \n2.JUNIO \n3.SEPTIEMBRE \n4.DICIEMBRE";
		  static final String EX_TYPE =
		"tipo de examen. \n\n1. TEÓRICO. \n2. MIXTO. \n3. TEST. \n4. PRÁCTICO";
		
		  static final String EX_VER =
		"cual quieres ver. Si no desea visualizar ninguno, ponga '0'.";
		  static final String EX_NO =
		"no desea ver ninguno.";
		  static final String EX_RESP =
		"ver respuesta con examenes Introduzca 'si' si lo desea o cualquier otra cosa si lo desea ver sin respuestas.";
		  static final String EX_PREG_VACIO =
		  "\nexamen sin preguntas, cree uno.\n\n";
		  static final String EX_PRINT =
		  "Pregunta";
		  static final String RESP_PRINT =
		  "Respuesta";
		  static final String APART_PRINT =
		  "Apartado";
		  static final String PDF_CREATE =
		  "\n\nExamen en PDF, escriba 'si', si no, pulse ENTER";
		  
		//PDF

		  static final String PDF_RUTE =
		  "./files/Examen_";
		  static final String PDF_EXTENSION =
		  ".pdf";
		  static final String DB_ALUM =
		  "Datos Alumno";
		  static final String DB_NAME =
		  "Nombre:";
		  static final String DB_APELLIDO =
		  "Apellidos:";
		  static final String DB_DNI =
		  "DNI:";
		  static final String DB_CORREO =
		  "Correo electrónico:";
		  static final String PDF_CORRECTO =
		  "PDF correctamente\n";
		  static final String ASIGN =
		  "Asignatura: ";
		  static final String PROF =
		  "Profesor: ";
		  static final String FECHA =
		  "Fecha: ";
		  static final String CONVOCA =
		  "\t\t\t\t\tExamen convocatoria ";
		  
		//Persistencia
		  static final RuntimeTypeAdapterFactory<Pregunta> runtimeTypeAdapterFactory = 
		  RuntimeTypeAdapterFactory.of(Pregunta.class, "tipo")
	      .registerSubtype(Pregunta_Desarrollo.class, Pregunta_Desarrollo.class.getName())
	      .registerSubtype(Pregunta_Listado.class, Pregunta_Listado.class.getName())
	      .registerSubtype(Pregunta_Teorica.class, Pregunta_Teorica.class.getName())
	      .registerSubtype(Pregunta_V_F.class, Pregunta_V_F.class.getName())
	      .registerSubtype(Pregunta_Rellenar.class, Pregunta_Rellenar.class.getName());
		
		//Cod. Errores
		
		  static final int COD_ERROR_INVALID_OPC = 1;
		  static final int COD_ERROR_INVALID_CANTIDAD = 2;
		  static final int COD_ERROR_EXAMEN_SIN_PREGUNTAS = 3;
		  static final int COD_ERROR_PDF_INVALIDO = 4;
			
		//Errores
		
		  static final String ERROR_INVALID_OPC = 
		"\nERROR: invalido. Por favor, introduzca una opción valida. (Código #" + COD_ERROR_INVALID_OPC + ")\n";
		  static final String ERROR_INVALID_CANTIDAD = 
		"\nERROR: cantidad invalida. Por favor, introduzca una opción valida. (Código #" + COD_ERROR_INVALID_CANTIDAD + ")\n";
		  static final String ERROR_EXAMEN_SIN_PREGUNTAS = 
		  "\nERROR: no hay preguntas en el examen, por favor, vuelva a crearlo. (Código #" + COD_ERROR_EXAMEN_SIN_PREGUNTAS + ")\n";
		  static final String ERROR_PDF_INVALIDO = 
		  "\nERROR: PDF incorrecto, por favor, vuelva a crearlo. (Código #" + COD_ERROR_PDF_INVALIDO + ")\n";
		  
		  //Formatos
		  static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
}
