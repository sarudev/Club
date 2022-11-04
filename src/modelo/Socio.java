package modelo;

public class Socio extends Persona {
	private int idCarnetSocio;
	private double cuota;

	public Socio(String nombre, String apellido, int dni, int edad, int idCarnetSocio, double cuota) {
		super(nombre, apellido, dni, edad);
		setIdCarnetSocio(idCarnetSocio);
		setCuota(cuota);
	}

	public int getIdCarnetSocio() {
		return idCarnetSocio;
	}

	private void setIdCarnetSocio(int idCarnetSocio) {
		this.idCarnetSocio = idCarnetSocio;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

}
