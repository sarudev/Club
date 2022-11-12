package modelo;

import java.util.ArrayList;
import java.util.List;

public class Actividad {
	private int idActividad;
	private String nombre;
	private int cupos;
	private List<DiaHorario> diasYhorarios;
	private List<Profesor> lstProfesores;
	private List<Socio> lstSocios;

	public Actividad(int idActividad, String nombre, int cupos) {
		this.setIdActividad(idActividad);
		this.setNombre(nombre);
		this.setCupos(cupos);
		this.diasYhorarios = new ArrayList<DiaHorario>();
		this.lstProfesores = new ArrayList<Profesor>();
		this.lstSocios = new ArrayList<Socio>();
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

	public List<DiaHorario> getDiasYhorarios() {
		return this.diasYhorarios;
	}

	public List<Profesor> getLstProfesores() {
		return this.lstProfesores;
	}

	public List<Socio> getLstSocios() {
		return this.lstSocios;
	}

	public void agregarDiaYhorario(DiaHorario dYh) throws Exception {
		this.diasYhorarios.add(dYh);
	}

	public void agregarProfesor(Profesor prof) {
		this.lstProfesores.add(prof);
	}

	public Profesor traerProfesor(int id) {
		int i = 0;
		Profesor prof = null;

		while (i < this.lstProfesores.size() && prof == null) {
			if (this.lstProfesores.get(i).getIdCarnetProfesor() == id)
				prof = this.lstProfesores.get(i);
			i++;
		}

		return prof;
	}

	public void eliminarProfesor(int id) throws Exception {
		Profesor prof = this.traerProfesor(id);

		if (prof == null)
			throw new Exception("No existe ese profesor.");

		this.lstProfesores.remove(id);
	}

	public void agregarSocio(Socio socio) throws Exception {
		if (this.lstProfesores.size() == this.cupos)
			throw new Exception("Se alcanzo el limite de cupos.");
		this.lstSocios.add(socio);
	}

	public Socio traerSocio(int id) {
		int i = 0;
		Socio prof = null;

		while (i < this.lstSocios.size() && prof == null) {
			if (this.lstSocios.get(i).getIdCarnetSocio() == id)
				prof = this.lstSocios.get(i);
			i++;
		}

		return prof;
	}

	public void eliminarSocio(int id) throws Exception {
		Socio prof = this.traerSocio(id);

		if (prof == null)
			throw new Exception("No existe ese socio.");

		this.lstSocios.remove(id);
	}

}
