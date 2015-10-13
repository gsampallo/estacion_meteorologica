package gsampallo.serverClima.data;

public class Clima {

	private java.util.Date fecha;
	private String hora;
	private String horaSensor;
	
	private int humedad;
	private double temperatura;
	private double temperatura2;
	private int lluvia;
	public java.util.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getHoraSensor() {
		return horaSensor;
	}
	public void setHoraSensor(String horaSensor) {
		this.horaSensor = horaSensor;
	}
	public int getHumedad() {
		return humedad;
	}
	public void setHumedad(int humedad) {
		this.humedad = humedad;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public double getTemperatura2() {
		return temperatura2;
	}
	public void setTemperatura2(double temperatura2) {
		this.temperatura2 = temperatura2;
	}
	public int getLluvia() {
		return lluvia;
	}
	public void setLluvia(int lluvia) {
		this.lluvia = lluvia;
	}
	
}
