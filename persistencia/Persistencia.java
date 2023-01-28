package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import examenes.Asignatura;
import main.ConstantsI;

public class Persistencia implements ConstantsI
{
	//ATRIBUTOS
	private Gson gson;
	private File file;
	
	private ArrayList<Asignatura> asignaturas;
	
	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 */
	public Persistencia()
	{
		asignaturas = new ArrayList<Asignatura>();
	}
	
	//Getters
	
	public ArrayList<Asignatura> getAsignaturas()
	{
		return asignaturas;
	}
	
	
	//Otros métodos
	
	/**
	 * Método para añadir una asignatura al array de Asignaturas.
	 * @param a
	 */
	public void addAsignatura(Asignatura a)
	{
		asignaturas.add(a);
	}
	
	
	/**
	 * Método que sirve para serializar el array de asignaturas y cargar en el archivo JSON.
	 * @param Ruta del fichero donde será guardado.
	 * @throws FileNotFoundException
	 */
    public void persistToJson(String rutaFichero) 
    {  	
    	gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
			
        String asignaturasSerializadas = gson.toJson(asignaturas);

        try
        {//Intentamos abrir el fichero de texto
            file = new File(rutaFichero);
            FileOutputStream fop = new FileOutputStream(file);
       	 	OutputStreamWriter osw = new OutputStreamWriter(fop);
            
            osw.write(asignaturasSerializadas);		//Escribimos la información
            osw.close();							//Y cerramos el fichero de texto
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
	 * Método que sirve para deserializar el array de asignaturas y cargar desde el archivo JSON.
	 * @param Ruta del fichero de donde se extraerá la información.
     */
    public void loadFromJson(String rutaFichero) 
    {
    	//Obtener  del fichero de texto
        String cadena = "";

        try 
        {//Componemos una cadena de texto a partir de varias lineas de archivo, donde estan guardados los datos de los usuarios
            file = new File(rutaFichero);
            FileInputStream fis = new FileInputStream(file);
            
            if (fis != null) 
            {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String linea = "";
                StringBuilder sb = new StringBuilder();
                
                while ((linea = br.readLine()) != null) 
                {
                    sb.append(linea);		//Vamos leyendo linea a linea del texto y los vamos concatenando en un StringBuilder
                }
                isr.close();
                cadena = sb.toString();		//El StringBuilder lo transformamos a string
            }
        } 
        catch (Exception e) 
        {//Si hay algún error, lo mostramos en el Log
            e.printStackTrace();
        }
        if (!cadena.isEmpty()) 
        {
        	gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
			
    		Type listType = new TypeToken<ArrayList<Asignatura>>(){}.getType();
    		
    		asignaturas = gson.fromJson(cadena.split("\\n")[0], listType);    
        } 
    }
}

