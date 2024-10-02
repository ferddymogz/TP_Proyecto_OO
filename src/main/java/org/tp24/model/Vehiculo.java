package org.tp24.model;

import java.time.LocalDate;

public record Vehiculo(
        String marca,
        String color,
        String tipo,
        int modelo,
        Double precio,
        LocalDate fechaIngreso,
        LocalDate fechaSalida) {
}
