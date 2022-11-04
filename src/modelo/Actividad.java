package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Actividad {
	private int idActividad;
	private String nombre;
	private int cupos;
	private String turnos;
	private List<LocalDateTime> diasYhorarios;
	private List<Profesor> lstProfesores;
	private List<Socio> lstSocios;

	public Actividad(int idActividad, String nombre, int cupos, String turnos) {
		setIdActividad(idActividad);
		setNombre(nombre);
		setCupos(cupos);
		setTurnos(turnos);
		diasYhorarios = new ArrayList<LocalDateTime>();
		lstProfesores = new ArrayList<Profesor>();
		lstSocios = new ArrayList<Socio>();
	}

	public int getIdActividad() {
		return idActividad;
	}

	private void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCupos() {
		return cupos;
	}

	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	public String getTurnos() {
		return turnos;
	}

	public void setTurnos(String turnos) {
		this.turnos = turnos;
	}

}
