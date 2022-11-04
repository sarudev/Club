package modelo;

public class Profesor extends Persona {
	private int idCarnetProfesor;
	private String actividad;
	private double sueldo;

	public Profesor(String nombre, String apellido, int dni, int edad, int idCarnetProfesor, String actividad,
			double sueldo) {
		super(nombre, apellido, dni, edad);
		setIdCarnetProfesor(idCarnetProfesor);
		setActividad(actividad);
		setSueldo(sueldo);
	}

	public int getIdCarnetProfesor() {
		return idCarnetProfesor;
	}

	private void setIdCarnetProfesor(int idCarnetProfesor) {
		this.idCarnetProfesor = idCarnetProfesor;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
}
