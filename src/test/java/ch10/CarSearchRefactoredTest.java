package ch10;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarSearchRefactoredTest {

    CarSearchRefactored carSearch = new CarSearchRefactored();

    @Test
    public void findSportCars_WithNoAddedCars_ReturnsEmptyList() {
        List<Car> foundCars = carSearch.findSportCars();
        assertTrue(foundCars.isEmpty());
    }

    @Test
    public void findSportCars_WithDifferentCars_ReturnsOnlySportCars() {
        Car sportCar = mock(Car.class);
        Car regularCar = mock(Car.class);
        when(sportCar.isSportCar()).thenReturn(true);
        when(regularCar.isSportCar()).thenReturn(false);

        carSearch.addCar(sportCar);
        carSearch.addCar(regularCar);

        List<Car> sportCars = carSearch.findSportCars();
        assertEquals(1, sportCars.size());
        assertEquals(sportCar, sportCars.get(0));
    }

    //Other tests for car search
}