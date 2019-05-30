package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main 
{
	
	public static void main(String[] args)
	{
		// Diccionario de niveles con valores por defecto
		Hashtable<String, Integer> niveles = new Hashtable<String, Integer>();
		niveles.put("A",5);
		niveles.put("B",10);
		niveles.put("C",15);
		niveles.put("Cuauh",20);
		
		//Cargar JSON en ArrayList
		String ruta;
		try
		{
			ruta = args[0];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			ruta = "./ResuelveFC.json";
		}
		JSONArray jarray = getJSONArray(ruta);
		ArrayList<Jugador> jugadores = getJugadoresFrom(jarray);
		
		//Para calcular el porcentaje de cada equipo, guardaremos los equipos en un diccionario que contenga:
		//<String nombre : [Float golesMinimos, Float golesMarcados]>
		Hashtable<String, Float[]> golesEquipos= new Hashtable<String, Float[]>();
		
		for(Jugador j : jugadores)
		{
			//Cercionarnos de cuales son los goles_mínimos del jugador
			if(j.goles_minimos == null)
			{
				j.goles_minimos = new Integer(niveles.get(j.nivel).toString());
			}
			
			//Si no existe el equipo, no tiene goles. Si existe, guardar los goles
			Float golesMinimos = golesEquipos.get(j.equipo) == null ? 0f : golesEquipos.get(j.equipo)[0];
			Float golesTotales = golesEquipos.get(j.equipo) == null ? 0f : golesEquipos.get(j.equipo)[1];
			Float[] golesEquipo = new Float[] {	golesMinimos + j.goles, 
												golesTotales + j.goles_minimos
												};
			
			golesEquipos.put(j.equipo, golesEquipo);
		}
		
		//Calcular bono de cada jugador
		for(Jugador j : jugadores)
		{
			//porcentaje = golesMarcados / golesMinimos
			float porcentajeEquipo = golesEquipos.get(j.equipo)[0] / golesEquipos.get(j.equipo)[1];
			float porcentajeJugador = j.goles / j.goles_minimos;
			
			float bono = ((porcentajeJugador + porcentajeEquipo) / 2) * j.bono;
			j.sueldo_completo = j.sueldo + bono;
			
			System.out.println(j.nombre+": "+j.sueldo_completo);
		}
	}
	
	/**
	 * Convierte un JSONArray a un ArrayList de tipo Jugadores.
	 * 
	 * Para convertir de JSONObject a Jugador, usamos jobject.get(key).toString().
	 * En el caso de que haya que convertir a Float o Integer, añadimos el parser de dicha clase.
	 * 
	 * En el caso de que el nivel sea null, se añadirá el sueldo_minimo
	 * En nuestro problema, sueldo_completo será siempre null
	 * 
	 * @param jarray	JSONArray a convertir
	 * @return			ArrayList a devolver
	 */
	private static ArrayList<Jugador> getJugadoresFrom(JSONArray jarray) 
	{
		ArrayList<Jugador> jugadores = new ArrayList();
		
		Iterator it =  jarray.iterator();
		while (it.hasNext())
		{
			JSONObject jobject = (JSONObject) it.next();
			
			String nombre = jobject.get("nombre").toString();
			Float goles = Float.parseFloat(jobject.get("goles").toString());
			Float sueldo = Float.parseFloat(jobject.get("sueldo").toString());
			Float bono = Float.parseFloat(jobject.get("bono").toString());
			Float sueldo_completo = null;
			String equipo = jobject.get("equipo").toString();

			//Descubrir cual de los dos es null en el json
			String nivel = null;
			Integer goles_minimos = null;
			try
			{
				goles_minimos = Integer.parseInt(jobject.get("goles_minimos").toString());
			}
			catch(NullPointerException e)
			{
				nivel = jobject.get("nivel").toString();
			}
			
			
			jugadores.add(new Jugador(	nombre, nivel, goles_minimos, goles, sueldo, 
										bono, sueldo_completo, equipo ));
		}
		
		return jugadores;
	}
	
	/**
	 * Convierte fichero a objeto JSONArray. Se recogen los jugadores con el método JSONArray.get()
	 * 
	 * @param ruta	Ruta relativa al fichero .json 
	 * @return		Devuelve el objeto JSONArray
	 */
	private static JSONArray getJSONArray(String ruta) 
	{
		JSONArray jarray = null;
		
		try 
		{

			JSONParser parser = new JSONParser();
			jarray = (JSONArray) parser.parse(new FileReader(ruta));
			
		} 
		catch (IOException | ParseException e) 
		{
			e.printStackTrace();
		}
		
		return jarray;
	}

}
