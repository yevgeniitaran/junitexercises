package ch10;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarSearchTest {

    CarSearch carSearch = new CarSearch();

    @Test
    public void findSportCars_WithNoAddedCars_ReturnsEmptyList() {
        List<Car> foundCars = carSearch.findSportCars();
        assertTrue(foundCars.isEmpty());
    }

    @Test
    public void findSportCars_WithDifferentCars_ReturnsOnlySportCars() {
        Car sportCar = mock(Car.class);
        Car regularCar = mock(Car.class);
        Engine sportEngine = mock(Engine.class);
        Engine regularEngine = mock(Engine.class);
        Manufacturer sportManufacturer = mock(Manufacturer.class);
        Manufacturer regularManufacturer = mock(Manufacturer.class);
        when(sportManufacturer.getName()).thenReturn("Ferrari");
        when(regularManufacturer.getName()).thenReturn("Manufacturer");
        when(sportEngine.getNbOfCylinders()).thenReturn(7);
        when(regularEngine.getNbOfCylinders()).thenReturn(4);
        when(sportCar.getEngine()).thenReturn(sportEngine);
        when(sportCar.getManufacturer()).thenReturn(sportManufacturer);
        when(sportCar.getColor()).thenReturn(Color.RED);
        when(regularCar.getManufacturer()).thenReturn(regularManufacturer);
        when(regularCar.getEngine()).thenReturn(regularEngine);
        when(regularCar.getColor()).thenReturn(Color.RED);

        carSearch.addCar(sportCar);
        carSearch.addCar(regularCar);

        List<Car> sportCars = carSearch.findSportCars();
        assertEquals(1, sportCars.size());
        assertEquals(sportCar, sportCars.get(0));
    }

    //Other tests for car search
}