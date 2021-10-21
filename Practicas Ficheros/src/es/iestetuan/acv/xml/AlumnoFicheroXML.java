package es.iestetuan.acv.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.iestetuan.acv.dao.IAlumno;
import es.iestetuan.acv.dao.vo.Alumno;

public class AlumnoFicheroXML implements IAlumno {

	public Alumno getAlumno(long nia) {
		
		Alumno devolver = new Alumno();
		
		try {
		
			File fichero = new File("recursos/alumnos-dam2-nuevos.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fichero);
			doc.getDocumentElement().normalize();
			NodeList listaAlumnos = doc.getElementsByTagName("alumno");
			
			for(int i=0; i<listaAlumnos.getLength(); i++) {
				
				Node nodoAlumno = listaAlumnos.item(i);
				Element alumno = (Element) nodoAlumno;
					
					String id = alumno.getAttribute("id");
					long niaComparar=Long.parseLong(id);
					
					if(niaComparar == nia) {
						NodeList contenidoNombre = alumno.getElementsByTagName("nombre");
						String nombreAlumno = contenidoNombre.item(0).getTextContent();
						
						NodeList contenidoApellido1 = alumno.getElementsByTagName("apellido1");
						String apellido1Alumno = contenidoApellido1.item(0).getTextContent();
						
						NodeList contenidoApellido2 = alumno.getElementsByTagName("apellido2");
						String apellido2Alumno = contenidoApellido2.item(0).getTextContent();
						
						devolver.setNombre(nombreAlumno);
						devolver.setApellido1(apellido1Alumno);
						devolver.setApellido2(apellido2Alumno);
						
						break;
					}
					
				
			}
		
		}
		
		catch(ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		catch(SAXException e) {
			e.printStackTrace();
		}
		
		return devolver;
	}
	public List<Alumno> getAlumnos(){
		
		ArrayList<Alumno> listaAlumnosDevolver = new ArrayList<Alumno>();
		
		try {
			
				File fichero = new File("recursos/alumnos-dam2-nuevos.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fichero);
				doc.getDocumentElement().normalize();
				NodeList listaAlumnos = doc.getElementsByTagName("alumno");
				
				for(int i=0; i<listaAlumnos.getLength(); i++) {
					
					Alumno alumnoAñadir = new Alumno();
					
					Node nodoAlumno = listaAlumnos.item(i);
					Element alumno = (Element) nodoAlumno;
					
					String id = alumno.getAttribute("id");
					long nia = Long.parseLong(id);
					
					NodeList contenidoNombre = alumno.getElementsByTagName("nombre");
					String nombre = contenidoNombre.item(0).getTextContent();
					
					NodeList contenidoApellido1 = alumno.getElementsByTagName("apellido1");
					String apellido1 = contenidoApellido1.item(0).getTextContent();
					
					NodeList contenidoApellido2 = alumno.getElementsByTagName("apellido2");
					String apellido2 = contenidoApellido2.item(0).getTextContent();
					
					alumnoAñadir.setNia(nia);
					alumnoAñadir.setNombre(nombre);
					alumnoAñadir.setApellido1(apellido1);
					alumnoAñadir.setApellido2(apellido2);
					
					listaAlumnosDevolver.add(alumnoAñadir);
					
				}
			}	
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlumnosDevolver;
	}
	
}