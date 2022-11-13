package modules;

import java.util.HashMap;
import java.util.Map;

public class DiaHorario {
  private String dia;
  private int hora;
  private int duracion;

  public DiaHorario(String dia, int hora, int duracion) throws Exception {
    this.setDia(dia);
    this.setHora(hora);
    this.setDuracion(duracion);
  }

  private void setDia(String dia) throws Exception {
    if (dia.contains("=") || dia.contains(",") || dia.contains(".") || dia.contains("{") || dia.contains("}")
        || dia.contains("[") || dia.contains("]") || dia.contains("\""))
      throw new Exception("El dia no puede contener los siguientes caracteres: `={}[]`");
    if (!dia.equalsIgnoreCase("lunes") &&
        !dia.equalsIgnoreCase("martes") &&
        !dia.equalsIgnoreCase("miercoles") &&
        !dia.equalsIgnoreCase("jueves") &&
        !dia.equalsIgnoreCase("viernes") &&
        !dia.equalsIgnoreCase("sabado") &&
        !dia.equalsIgnoreCase("domingo"))
      throw new Exception("Invalid day.");
    this.dia = dia.replace(dia.charAt(0), Character.toUpperCase(dia.charAt(0)));
  }

  private void setHora(int hora) throws Exception {
    if (hora < 0)
      throw new Exception("hora can't be negative.");
    this.hora = hora;
  }

  public void setDuracion(int duracion) throws Exception {
    if (duracion < 0)
      throw new Exception("duracion can't be negative.");
    this.duracion = duracion;
  }

  public String getDia() {
    return dia;
  }

  public int getHora() {
    return hora;
  }

  public int getDuracion() {
    return duracion;
  }

  public boolean isBetween(DiaHorario incluye) {
    boolean sameDay = incluye.getDia().equalsIgnoreCase(this.dia);
    boolean isBetween = incluye.getHora() <= this.hora && this.hora < incluye.getHora() + incluye.getDuracion();

    return sameDay && isBetween;
  }

  public Map<String, Object> toHashMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("dia", "\"" + this.dia + "\"");
    map.put("hora", this.hora);
    map.put("duracion", this.duracion);
    return map;
  }
}
