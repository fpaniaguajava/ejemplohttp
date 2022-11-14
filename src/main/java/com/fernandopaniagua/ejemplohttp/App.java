package com.fernandopaniagua.ejemplohttp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {

	public static void main(String[] args) {
		try {
			String respuesta = ClienteHTTP.getStringFromURL("http://localhost:80/pelicula.json");
			//String respuesta = ClienteHTTP.getStringFromURL("https://www.elpais.es/");	
			Path path = Paths.get("d:/salida.html");
			Files.write(path, respuesta.getBytes());
			System.out.println(respuesta);
			procesarJSON(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("FIN");
		}
	}
	
	public static void procesarJSON(String strPelicula) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonPelicula = (JSONObject)parser.parse(strPelicula);
			System.out.println("JSON proceso completo");
			
			String title = (String)jsonPelicula.get("Title");
			String year = (String)jsonPelicula.get("Year");
			String director = (String)jsonPelicula.get("Director");
			System.out.println(title + ":" + year + ":" + director); 
			
			JSONArray ratings = (JSONArray)jsonPelicula.get("Ratings");
			for (Object rating : ratings) {
				String source = (String)((JSONObject)rating).get("Source");
				String value = (String)((JSONObject)rating).get("Value");
				System.out.println("Fuente:" + source + " - Calificación:" + value);
			}
			
			
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}
}
