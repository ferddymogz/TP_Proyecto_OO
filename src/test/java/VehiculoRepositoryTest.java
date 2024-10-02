import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tp24.model.Vehiculo;
import org.tp24.repository.impl.VehiculoRepositoryFileBased;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class VehiculoRepositoryTest {

	protected static final Logger logger = LoggerFactory.getLogger(VehiculoRepositoryTest.class);

	VehiculoRepositoryFileBased vehiculosRepository = new VehiculoRepositoryFileBased();

	@Test
	void cuando_se_buscan_vehiculos_por_marca_y_color_se_devuelven_resultados_exitosamente() {

		var vehiculos = vehiculosRepository.buscar( "mazda", "azul");
		assertNotNull( vehiculos, "vehiculos es NULL, falla el test");
		assertFalse( vehiculos.isEmpty(), "vehiculos está vacio, falla el test");
		assertEquals( 2, vehiculos.size(), "Se esperaba encontrar 2 resultados que coincidan con la busqueda, falla el test" );
	}

	@Test
	void cuando_se_buscan_vehiculos_por_modelo_se_devuelven_resultados_exitosamente() {
		int modelo = 2020;
		var vehiculos =  vehiculosRepository.buscar(modelo );
		assertNotNull( vehiculos, "vehiculos es NULL, falla el test");
		assertFalse( vehiculos.isEmpty(), "vehiculos está vacio, falla el test");

		logger.info("Lectura de vehiculo finalizada. Se encontraron {} vehiculos de modelo {}.", vehiculos.size(), modelo);
	}

	@Test
	void cuando_se_buscan_vehiculos_por_precio_se_devuelven_resultados_exitosamente() {
		Double precio = 45000000D;
		var vehiculos =  vehiculosRepository.buscar(precio);
		assertNotNull( vehiculos, "vehiculos es NULL, falla el test");
		assertFalse( vehiculos.isEmpty(), "vehiculos está vacio, falla el test");

		logger.info("Lectura de vehiculo finalizada. Se encontraron {} vehiculos con precio mayor o igual a {}.", vehiculos.size(), precio);
	}

	@Test
	void cuando_se_agrega_un_vehiculo_nuevo_al_archivo_vehiculosIngresados() {
		Vehiculo nuevoVehiculo = new Vehiculo("Tesla", "rojo", "Automovil", 2023, 999999999999D, LocalDate.of(2023, 5, 15) , LocalDate.of(2024,6,12));
		vehiculosRepository.agregarVehiculo(nuevoVehiculo);

	}
}