package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Alquiler {
	private int idAlquiler;
	private double precio;
	private List<LocalDateTime> diasYhorarios;

	public Alquiler(int idAlquiler, double precio) {
		setIdAlquiler(idAlquiler);
		setPrecio(precio);
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
