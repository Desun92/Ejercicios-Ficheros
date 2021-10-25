package es.iestetuan.acv.fictexto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.acv.dao.IAlumno;
import es.iestetuan.acv.dao.vo.Alumno;

public class AlumnoFicheroDAO implements IAlumno {
	
	public Alumno getAlumno(long nia) {
		
		Alumno devolver = new Alumno();
		BufferedReader bfin = null;
		String linea;
		String[] lineaPartida;
		long niaComparacion;
		boolean primeraLinea=true;
		
		try {
			bfin=new BufferedReader(new FileReader("recursos/alumnos-dam2-nuevos.txt"));
			
			while((linea=bfin.readLine())!=null) {
				if(primeraLinea)
					primeraLinea=false;
				else {
					lineaPartida=linea.split(",");
					niaComparacion=Long.parseLong(lineaPartida[0]);
					
					if(niaComparacion==nia) {
						devolver.setNia(niaComparacion);
						devolver.setNombre(lineaPartida[1]);
						devolver.setApellido1(lineaPartida[2]);
						devolver.setApellido2(lineaPartida[3]);
						break;
					}
				}
			}
		}
		
		catch(IOException ioex){
			ioex.printStackTrace();
		}
		
		finally {
			
			try {
				if(bfin!=null)
					bfin.close();
			}
			
			catch(IOException ioex2) {
				ioex2.printStackTrace();
			}
			
		}
		return devolver;
	}
	public List<Alumno> getAlumnos(){
		
		List<Alumno> devolver=new ArrayList<Alumno>();
		BufferedReader bfin = null;
		boolean primeraLinea=true;
		String linea;
		String[] lineaPartida;
		
		
		try{
		
		bfin=new BufferedReader(new FileReader("recursos/alumnos-dam2-nuevos.txt"));
		
		while((linea=bfin.readLine())!=null) {
			if(primeraLinea)
				primeraLinea=false;
			else {
				Alumno alumno = new Alumno();
				lineaPartida=linea.split(",");
				alumno.setNia(Long.parseLong(lineaPartida[0]));
				alumno.setNombre(lineaPartida[1]);
				alumno.setApellido1(lineaPartida[2]);
				alumno.setApellido2(lineaPartida[3]);
				devolver.add(alumno);
			}
		}
		}
		
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return devolver;
	}
	public void guardarAlumnos(List<Alumno> alumnos) {
		
	}
}