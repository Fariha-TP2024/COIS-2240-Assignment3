import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

public class VehicleRentalTest {
    private RentalSystem rentalSystem;
    private Vehicle vehicle;
    private Customer customer;

    @BeforeEach
    void setUp() {
        rentalSystem = RentalSystem.getInstance();
        vehicle = new Vehicle("ABC123", "Toyota", "Camry", 2023);
        customer = new Customer(1, "John Doe");
    }

    @Test
    void testLicensePlateValidation() {
        // Valid plates
        assertDoesNotThrow(() -> new Vehicle("AAA100", "Honda", "Civic", 2020));
        assertDoesNotThrow(() -> new Vehicle("ZZZ999", "Ford", "Focus", 2021));

        // Invalid plates
        assertThrows(IllegalArgumentException.class, 
            () -> new Vehicle("", "Invalid", "Car", 2022));
        assertThrows(IllegalArgumentException.class, 
            () -> new Vehicle("ZZZ99", "Invalid", "Car", 2022));
    }

    @Test
    void testRentAndReturnVehicle() {
        assertTrue(vehicle.isAvailable());
        assertTrue(rentalSystem.rentVehicle(vehicle, customer, LocalDate.now(), 5.0));
        assertFalse(vehicle.isAvailable());
        assertFalse(rentalSystem.rentVehicle(vehicle, customer, LocalDate.now(), 5.0));
        assertTrue(rentalSystem.returnVehicle(vehicle, customer, LocalDate.now(), 100.0));
        assertTrue(vehicle.isAvailable());
        assertFalse(rentalSystem.returnVehicle(vehicle, customer, LocalDate.now(), 100.0));
    }

    @Test
    void testSingletonRentalSystem() throws Exception {
        Constructor<RentalSystem> constructor = RentalSystem.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        RentalSystem instance1 = RentalSystem.getInstance();
        RentalSystem instance2 = RentalSystem.getInstance();
        assertSame(instance1, instance2);
    }
}