package main;

public class Jugador {
	String nombre;
	String nivel;
	Integer goles_minimos;
	Float goles;
	Float sueldo;
	Float bono;
	Float sueldo_completo;
	String equipo;
	
	public Jugador(String nombre, String nivel, 
			Integer goles_minimos, Float goles, Float sueldo, 
			Float bono, Float sueldo_completo, String equipo) {
		super();
		this.nombre = nombre;
		this.nivel = nivel;
		this.goles_minimos = goles_minimos;
		this.goles = goles;
		this.sueldo = sueldo;
		this.bono = bono;
		this.sueldo_completo = sueldo_completo;
		this.equipo = equipo;
	}
}
