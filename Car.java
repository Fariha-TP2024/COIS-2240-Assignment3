public class Car extends Vehicle implements Rentable {

    public Car(String licensePlate, String make, String model, int year) {
        super(licensePlate, make, model, year);
    }

    @Override
    public String getInfo() {
        return "Car - " + super.getInfo();
    }

    @Override
    public void rentVehicle() {
        if (isAvailable()) {
            setStatus(VehicleStatus.RENTED);
            System.out.println("Car " + getLicensePlate() + " has been rented.");
        } else {
            System.out.println("Car " + getLicensePlate() + " is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (!isAvailable()) {
            setStatus(VehicleStatus.AVAILABLE);
            System.out.println("Car " + getLicensePlate() + " has been returned.");
        } else {
            System.out.println("Car " + getLicensePlate() + " is already available.");
        }
    }
}

