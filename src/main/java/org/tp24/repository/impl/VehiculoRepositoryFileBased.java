package org.tp24.repository.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tp24.model.Vehiculo;
import org.tp24.repository.VehiculosRepository;

public class VehiculoRepositoryFileBased implements VehiculosRepository {

	protected static final Logger logger = LoggerFactory.getLogger(VehiculoRepositoryFileBased.class);

	private List<Vehiculo> vehiculos = null;

	public VehiculoRepositoryFileBased(){
		vehiculos =  leerVehiculos();
	}

	private List<Vehiculo> leerVehiculos(){
		Path rutaArchivo = Paths.get( "./data/vehiculos.txt" );

		logger.info("Iniciando el proceso de lectura del archivo de vehiculos: " + rutaArchivo.toString());

		try (BufferedReader reader = Files.newBufferedReader( rutaArchivo )) {
			List<Vehiculo> vehiculosLeidos =  reader.lines().map( this::build ).collect(Collectors.toCollection(ArrayList::new));
			logger.info("Lectura de vehiculo finalizada. Se leyeron {} vehiculos.", vehiculosLeidos.size());
			return vehiculosLeidos;
		}catch (IOException e) {
			throw new RuntimeException( e );
		}

	}

	private Vehiculo build(String text){

		String[] vehiculoArray = text.split( "," );
		return new Vehiculo(vehiculoArray[0].trim(),
							vehiculoArray[1].trim(),
							vehiculoArray[2].trim(),
							Integer.parseInt(vehiculoArray[3].trim()),
							Double.parseDouble( vehiculoArray[4].trim()),
							LocalDate.parse(vehiculoArray[5].trim()),
							LocalDate.parse(vehiculoArray[6].trim()));
	}

	@Override
	public List<Vehiculo> buscar(String marca, String color) {
		return vehiculos.stream()
				.filter(vehiculo -> vehiculo.marca().equalsIgnoreCase(marca) &&
						vehiculo.color().equalsIgnoreCase(color))
				.toList();
	}

	@Override
	public List<Vehiculo> buscar(Double precio) {
		return vehiculos.stream()
				.filter(vehiculo -> vehiculo.precio() >= precio)
				.toList();
	}

	@Override
	public List<Vehiculo> buscar(int modelo) {
		return vehiculos.stream()
				.filter(vehiculo -> vehiculo.modelo() == modelo)
				.toList();
	}

	@Override
	public void agregarVehiculo(Vehiculo vehiculo) {
		Path rutaArchivo = Paths.get("./data/vehiculosIngresados.txt");

		logger.info("Agregando un nuevo cliente al archivo: " + vehiculo);

		try (BufferedWriter writer = Files.newBufferedWriter(rutaArchivo, java.nio.file.StandardOpenOption.APPEND)) {
			writer.write(vehiculoToString(vehiculo));
			writer.newLine();
		} catch (IOException e) {
			logger.error("Error al escribir el vehiculo en el archivo", e);
			throw new RuntimeException(e);
		}

		vehiculos.add(vehiculo);
	}

	private String vehiculoToString(Vehiculo vehiculo) {
		return vehiculo.marca() + "," +
				vehiculo.color() + "," +
				vehiculo.tipo() + "," +
				vehiculo.modelo() + "," +
				vehiculo.precio() + "," +
				vehiculo.fechaIngreso() + "," +
				vehiculo.fechaSalida();
	}


}
