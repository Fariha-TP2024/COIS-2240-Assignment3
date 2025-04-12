public class Vehicle {
    public static final String VehicleStatus = null;
	private String licensePlate;
    private String make;
    private String model;
    private int year;
    private boolean isAvailable;

    public Vehicle(String licensePlate, String make, String model, int year) {
        setLicensePlate(licensePlate);
        this.make = make;
        this.model = model;
        this.year = year;
        this.isAvailable = true;
    }

    private boolean isValidPlate(String plate) {
        return plate != null && plate.matches("[A-Za-z]{3}\\d{3}");
    }

    public void setLicensePlate(String plate) {
        if (!isValidPlate(plate)) {
            throw new IllegalArgumentException("Invalid plate format. Use AAA123.");
        }
        this.licensePlate = plate.toUpperCase();
    }

    // Getters and setters
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public String getLicensePlate() { return licensePlate; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }

	public Short getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}