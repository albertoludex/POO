package pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import examenes.Examenes;
import main.ConstantsI;
import preguntas.Pregunta;
import preguntas.Pregunta_Desarrollo;

public class Pdfs implements ConstantsI
{
	
	public static void generadorPDF(Examenes ex, boolean resp)
	{
        int aux = 0;
		Chunk pregunta, respuesta, apartado, resp_apartado;
		
		Pregunta p;
		
		ArrayList<Chunk> preg = new ArrayList<Chunk>();
		ArrayList<Chunk> reps = new ArrayList<Chunk>();
		ArrayList<Chunk> apart = new ArrayList<Chunk>();
		ArrayList<Chunk> apart_resp = new ArrayList<Chunk>();
		
		try 
		{
	        OutputStream file = new FileOutputStream(new File(PDF_EXTENSION + ex.getAsignatura() + BARRA_BAJA + 
	        										 ex.getCurso() + BARRA_BAJA + ex.getConvocatoria() + PDF_EXTENSION));
	        Document document = new Document();
	        PdfWriter.getInstance(document, file);
        

			

			//Insertar tabla
		    PdfPTable tabla = new PdfPTable(2);

            PdfPCell celda1 = new PdfPCell (new Paragraph (DB_ALUM));
            celda1.setColspan (2);
            celda1.setHorizontalAlignment (Element.ALIGN_CENTER);
            celda1.setPadding (10.0f);
            celda1.setBackgroundColor (new BaseColor (255, 255, 255));
		    
		    PdfPCell celda2 = new PdfPCell (new Paragraph (DB_NAME));
		    celda2.setColspan (1);
		    celda2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		    celda2.setPadding (10.0f);
			    
		    PdfPCell celda3 = new PdfPCell (new Paragraph (DB_APELLIDO));
		    celda3.setColspan (1);
		    celda3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		    celda3.setPadding (10.0f);
		    
		    PdfPCell celda4 = new PdfPCell (new Paragraph (DB_DNI));
		    celda4.setColspan (1);
		    celda4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		    celda4.setPadding (10.0f);
		    
		    PdfPCell celda5 = new PdfPCell (new Paragraph (DB_CORREO));
		    celda5.setColspan (1);
		    celda5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		    celda5.setPadding (10.0f);
			    
		    
			    tabla.addCell(celda1);
			    tabla.addCell(celda2);
			    tabla.addCell("					");
			    tabla.addCell(celda3);
			    tabla.addCell("					");
			    tabla.addCell(celda4);
		    tabla.addCell("					");
		    tabla.addCell(celda5);
		    tabla.addCell("					");
		    tabla.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
		    tabla.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS								          
		    
			 //Texto con formato	
			Chunk b1 = new Chunk(ASIGN + ex.getAsignatura(), 
								            new Font(FontFamily.HELVETICA, 14, Font.BOLD));
			
			Chunk b2 = new Chunk(FECHA + ex.getF_realizacion() + CONVOCA + 
											ex.getConvocatoria() + TABULADOR + ex.getCurso(), 
											new Font(FontFamily.HELVETICA, 14, Font.BOLD));
			
			Chunk b3 = new Chunk(PROF + ex.getAutor(), 
											new Font(FontFamily.HELVETICA, 14, Font.BOLD));
			
			for(int i=0;i<ex.getN_preguntas();i++)
			{
				try 
				{
					p = ex.getPreguntas_selec().get(i);
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println(ERROR_EXAMEN_SIN_PREGUNTAS);
					break;
				}
				
				if(p instanceof Pregunta_Desarrollo)
				{
					pregunta = new Chunk(EX_PRINT + ESPACIO + (i+1) + ESPACIO + AB_PARENT + 
							 ex.getPuntuaciones().get(i) + CERR_PARENT + DOS_PUNTOS + ESPACIO, 
							 new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(150, 0, 0)));
					preg.add(pregunta);
					
					for(int x=0;x<((Pregunta_Desarrollo) p).getN_apartados();x++)
					{
						apartado = new Chunk(TABULADOR + TABULADOR + TABULADOR + APART_PRINT + ESPACIO + (x+1) + POINT + ESPACIO + 
								 ((Pregunta_Desarrollo) p).getApartados().get(x).getTexto_pregunta() + 
								 ESPACIO + ((Pregunta_Desarrollo) p).getApartados().get(x).getTexto_aclaratorio(), 
								 new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(60, 0, 0)));
						
						if(resp == true)
						{
							resp_apartado = new Chunk(TABULADOR + TABULADOR + TABULADOR + TABULADOR + RESP_PRINT + DOS_PUNTOS + ESPACIO + 
													 ((Pregunta_Desarrollo) p).getApartados().get(x).getRespuesta(), 
													 new Font(FontFamily.HELVETICA, 12, Font.BOLD));
							apart_resp.add(resp_apartado);
						}
						apart.add(apartado);
					}
				}
				else
				{
					pregunta = new Chunk(EX_PRINT + ESPACIO + (i+1) + ESPACIO + AB_PARENT + 
							 ex.getPuntuaciones().get(i) + CERR_PARENT + DOS_PUNTOS + ESPACIO + 
							 p.getTxt_preg() + ESPACIO + p.getTxt_aclar(), 
							 new Font(FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(150, 0, 0)));
					
					if(resp == true)
					{	
						respuesta = new Chunk(TABULADOR + TABULADOR + TABULADOR + TABULADOR + RESP_PRINT + DOS_PUNTOS + ESPACIO + 
											  p.getResp(), new Font(FontFamily.HELVETICA, 12, Font.BOLD));
						reps.add(respuesta);
					}
					preg.add(pregunta);
				}
			}
			
			//Insertar todo en el documento
	        document.open();		       
	
	        document.add(Chunk.NEWLINE);
	        
	        document.add(new Paragraph(ENTER));
	        document.add(b1);
	        document.add(new Paragraph(ENTER));
	        document.add(b2);
	        document.add(new Paragraph(ENTER));
	        document.add(b3);
	        document.add(tabla); 
	        document.add(Chunk.NEWLINE);				    
	         
	        document.newPage();
	        
	        for(int i=0;i<preg.size();i++)
	        {
	        	try 
				{
					p = ex.getPreguntas_selec().get(i);
				}
				catch(IndexOutOfBoundsException e)
				{
					System.out.println(ERROR_EXAMEN_SIN_PREGUNTAS);
					break;
				}
	        	
	        	document.add(preg.get(i));
	        	
	        	if(p instanceof Pregunta_Desarrollo)
	        	{
	        		aux++;
	        		for(int x=0;x<((Pregunta_Desarrollo) p).getN_apartados();x++)
	        		{
	        			document.add(new Paragraph(ENTER));
	        			document.add(apart.get(x));
		        		
		        		if(resp==true)
				        {  	
			        		document.add(new Paragraph(ENTER));
				 	        document.add(apart_resp.get(x));
				        }     
	        		}  		
	        	}
	        	else
	        	{
	        		if(resp==true)
			        {  	
		        		document.add(new Paragraph(ENTER));
			 	        document.add(reps.get(i-aux));
			        }
	        	}
	        	document.add(new Paragraph(ENTER));
	        }

	        document.close();
	        file.close();
	        
	        System.out.println(PDF_CORRECTO);
	  } 
	  catch (Exception e) 
	  {
	      e.printStackTrace();
	      System.out.println(COD_ERROR_PDF_INVALIDO);
	  }
	}
}