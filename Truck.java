public class Truck extends Vehicle implements Rentable {
    private int cargoCapacity;

        public Truck(String licensePlate, String make, String model, int year, int cargoCapacity) {
            super(licensePlate, make, model, year);
            this.cargoCapacity = cargoCapacity;
        }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getInfo() {
        return super.toString() + " | Cargo Capacity: " + cargoCapacity + "kg";
    }

    @Override
    public void rentVehicle() {
        setStatus(VehicleStatus.RENTED);
        System.out.println("Truck " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(VehicleStatus.AVAILABLE);
        System.out.println("Truck " + getLicensePlate() + " has been returned.");
    }
}
