package ch10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarSearchRefactored {
    private final List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> findSportCars() {
        return cars.stream()
                .filter(Car::isSportCar)
                .collect(Collectors.toList());
    }
}
