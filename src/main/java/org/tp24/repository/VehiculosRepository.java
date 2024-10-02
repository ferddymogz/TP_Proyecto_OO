package org.tp24.repository;

import org.tp24.model.Vehiculo;

import java.util.List;

public interface VehiculosRepository {
    List<Vehiculo> buscar(String marca, String color);
    List<Vehiculo> buscar(Double precio);
    List<Vehiculo> buscar(int modelo);
    void agregarVehiculo(Vehiculo vehiculo);

}
