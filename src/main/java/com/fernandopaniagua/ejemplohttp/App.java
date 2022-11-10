package com.fernandopaniagua.ejemplohttp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

	public static void main(String[] args) {
		try {
			String respuesta = ClienteHTTP.getStringFromURL("http://localhost:80/pelicula.json");
			//String respuesta = ClienteHTTP.getStringFromURL("https://www.elpais.es/");	
			Path path = Paths.get("d:/salida.html");
			Files.write(path, respuesta.getBytes());
			System.out.println(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("FIN");
		}
	}

}
