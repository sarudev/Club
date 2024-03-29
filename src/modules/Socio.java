package modules;

import java.util.Map;

public class Socio extends Persona {
	private int idCarnetSocio;
	private double cuota;

	public Socio(String nombre, String apellido, int dni, int edad, int idCarnetSocio, double cuota) throws Exception {
		super(nombre, apellido, dni, edad);
		this.setIdCarnetSocio(idCarnetSocio);
		this.setCuota(cuota);
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

	public Map<String, Object> toHashMap() {
		Map<String, Object> map = super.toHashMap();
		map.put("idCarnetSocio", this.idCarnetSocio);
		map.put("cuota", this.cuota);
		return map;
	}
}
