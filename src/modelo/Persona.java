package modelo;

public class Persona {
	protected String nombre;
	protected String apellido;
	protected int dni;
	protected int edad;

	public Persona(String nombre, String apellido, int dni, int edad) {
		setNombre(nombre);
		setApellido(apellido);
		setDni(dni);
		setEdad(edad);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

}
