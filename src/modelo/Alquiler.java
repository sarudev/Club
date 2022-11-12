package modelo;

import java.util.List;

public class Alquiler {
	private int idAlquiler;
	private String nombre;
	private double precio;
	private boolean alquilado;
	private DiaHorario diaYhorarios;

	public Alquiler(int idAlquiler, double precio) {
		this.setIdAlquiler(idAlquiler);
		this.setPrecio(precio);
	}

	public int getIdAlquiler() {
		return idAlquiler;
	}

	private void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
