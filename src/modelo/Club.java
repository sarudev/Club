package modelo;

import java.util.ArrayList;
import java.util.List;

public class Club {
	private String nombre;
	private int capacidad;
	private String direccion;
	private List<Profesor> lstProfesores;
	private List<Socio> lstSocios;
	private List<Actividad> lstActividades;
	private List<Alquiler> lstAlquileres;

	public Club(String nombre, int capacidad, String direccion) {
		setNombre(nombre);
		setCapacidad(capacidad);
		setDireccion(direccion);
		lstProfesores = new ArrayList<Profesor>();
		lstSocios = new ArrayList<Socio>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
