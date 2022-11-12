package modelo;

public class Profesor extends Persona {
	private int idCarnetProfesor;
	private double sueldo;

	public Profesor(String nombre, String apellido, int dni, int edad, int idCarnetProfesor, double sueldo) {
		super(nombre, apellido, dni, edad);
		setIdCarnetProfesor(idCarnetProfesor);
		setSueldo(sueldo);
	}

	public int getIdCarnetProfesor() {
		return idCarnetProfesor;
	}

	private void setIdCarnetProfesor(int idCarnetProfesor) {
		this.idCarnetProfesor = idCarnetProfesor;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
}
