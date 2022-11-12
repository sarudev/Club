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
		this.setNombre(nombre);
		this.setCapacidad(capacidad);
		this.setDireccion(direccion);
		this.lstProfesores = new ArrayList<Profesor>();
		this.lstSocios = new ArrayList<Socio>();
		this.lstActividades = new ArrayList<Actividad>();
		this.lstAlquileres = new ArrayList<Alquiler>();
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

	public List<Profesor> getLstProfesores() {
		return this.lstProfesores;
	}

	public List<Socio> getLstSocios() {
		return this.lstSocios;
	}

	public List<Actividad> getLstActividades() {
		return this.lstActividades;
	}

	public List<Alquiler> getLstAlquileres() {
		return this.lstAlquileres;
	}

	public Profesor agregarProfesor(String nombre, String apellido, int dni, int edad, double sueldo) {
		int id = this.lstProfesores.size();

		Profesor prof = new Profesor(nombre, apellido, dni, edad, id, sueldo);

		this.lstProfesores.add(prof);

		return prof;
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

	public Profesor traerProfesor(String nombre, String apellido) {
		int i = 0;
		Profesor prof = null;

		while (i < this.lstProfesores.size() && prof == null) {
			if (this.lstProfesores.get(i).getNombre().equals(nombre)
					&& this.lstProfesores.get(i).getApellido().equals(apellido))
				prof = this.lstProfesores.get(i);
			i++;
		}

		return prof;
	}

	public Profesor eliminarProfesor(int id) throws Exception {
		Profesor prof = this.traerProfesor(id);

		if (prof == null)
			throw new Exception("No existe ese profesor.");

		this.lstProfesores.remove(id);

		return prof;
	}

	public Socio agregarSocio(String nombre, String apellido, int dni, int edad, int idCarnetSocio, double cuota) {
		int id = this.lstSocios.size();

		Socio soc = new Socio(nombre, apellido, dni, edad, id, cuota);

		this.lstSocios.add(soc);

		return soc;
	}

	public Socio traerSocio(int id) {
		int i = 0;
		Socio soc = null;

		while (i < this.lstSocios.size() && soc == null) {
			if (this.lstSocios.get(i).getIdCarnetSocio() == id)
				soc = this.lstSocios.get(i);
			i++;
		}

		return soc;
	}

	public Socio traerSocio(String nombre, String apellido) {
		int i = 0;
		Socio soc = null;

		while (i < this.lstSocios.size() && soc == null) {
			if (this.lstSocios.get(i).getNombre().equals(nombre)
					&& this.lstSocios.get(i).getApellido().equals(apellido))
				soc = this.lstSocios.get(i);
			i++;
		}

		return soc;
	}

	public Socio eliminarSocio(int id) throws Exception {
		Socio soc = this.traerSocio(id);

		if (soc == null)
			throw new Exception("No existe ese profesor.");

		this.lstSocios.remove(id);

		return soc;
	}

	public Actividad agregarActividad(String nombre, int cupos) {
		int id = this.lstActividades.size();

		Actividad act = new Actividad(id, nombre, cupos);

		this.lstActividades.add(act);

		return act;
	}

	public DiaHorario agregarDiaYhorario(Actividad act, String dia, int hora, int duracion) throws Exception {
		DiaHorario dYh = new DiaHorario(dia, hora, duracion);
		int i = 0;

		while (i < this.lstActividades.size()) {
			int k = 0;
			if (!this.lstActividades.get(i).getDiasYhorarios().isEmpty()) {
				List<DiaHorario> actDyH = this.lstActividades.get(i).getDiasYhorarios();
				while (k < actDyH.size()) {
					if (dYh.isBetween(actDyH.get(k))) {
						throw new Exception("Invalid dia y hora");
					}
					k++;
				}
			}
			i++;
		}

		act.agregarDiaYhorario(dYh);

		return dYh;
	}
}
