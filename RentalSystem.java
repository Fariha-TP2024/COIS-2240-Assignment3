import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class RentalSystem {
    private static RentalSystem instance;
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private RentalHistory rentalHistory = new RentalHistory();

    // Private constructor with data loading
    private RentalSystem() {
        loadData();
    }

    // Singleton access method
    public static RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    // ================== Core Functionality ==================
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isEmpty()) {
            System.out.println("Invalid license plate");
            return false;
        }
        if (findVehicleByPlate(vehicle.getLicensePlate()) != null) {
            System.out.println("Vehicle with plate " + vehicle.getLicensePlate() + " already exists");
            return false;
        }
        vehicles.add(vehicle);
        saveVehicle(vehicle);
        return true;
    }

    private void saveVehicle(Vehicle vehicle) {
        // Logic to save vehicle to file can go here
    }

    public boolean addCustomer(Customer customer) {
        if (findCustomerById(customer.getCustomerId()) != null) {
            System.out.println("Customer with ID " + customer.getCustomerId() + " already exists");
            return false;
        }
        customers.add(customer);
        saveCustomer(customer);
        return true;
    }

    private Object findCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	private void saveCustomer(Customer customer) {
        // Logic to save customer to file can go here
    }

    public void rentVehicle(Vehicle vehicle, Customer customer, LocalDate date, double amount) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.AVAILABLE) {
            vehicle.setStatus(Vehicle.VehicleStatus.RENTED);
            RentalRecord record = new RentalRecord(vehicle, customer, date, amount, "RENT");
            rentalHistory.addRecord(record);
            saveRecord(record);
            System.out.println("Vehicle rented to " + customer.getName());
        } else {
            System.out.println("Vehicle is not available for renting.");
        }
    }

    public void returnVehicle(Vehicle vehicle, Customer customer, LocalDate date, double extraFees) {
        if (vehicle.getStatus() == Vehicle.VehicleStatus.RENTED) {
            vehicle.setStatus(Vehicle.VehicleStatus.AVAILABLE);
            RentalRecord record = new RentalRecord(vehicle, customer, date, extraFees, "RETURN");
            rentalHistory.addRecord(record);
            saveRecord(record);
            System.out.println("Vehicle returned by " + customer.getName());
        } else {
            System.out.println("Vehicle is not rented.");
        }
    }

    private void saveRecord(RentalRecord record) {
        // Logic to save rental record to file can go here
    }

    // Add this method to the RentalSystem class
    public void displayVehicles(boolean availableOnly) {
        System.out.println("\nVehicles:");
        System.out.println("| License Plate | Make      | Model     | Year | Status     |");
        System.out.println("------------------------------------------------------------");
        for (Vehicle v : vehicles) {
            if (availableOnly && v.getStatus() == Vehicle.VehicleStatus.AVAILABLE) {
                System.out.printf("| %-13s | %-9s | %-9s | %-4d | %-10s |\n",
                        v.getLicensePlate(), v.getMake(), v.getModel(), v.getYear(), v.getStatus());
            } else if (!availableOnly) {
                System.out.printf("| %-13s | %-9s | %-9s | %-4d | %-10s |\n",
                        v.getLicensePlate(), v.getMake(), v.getModel(), v.getYear(), v.getStatus());
            }
        }
    }

    public void displayAllCustomers() {
        System.out.println("\nRegistered Customers:");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    public void displayRentalHistory() {
        System.out.println("\nRental History:");
        System.out.println("| Vehicle Plate | Customer ID | Date       | Amount | Action |");
        System.out.println("--------------------------------------------------------------");
        for (RentalRecord record : rentalHistory.getRentalHistory()) {
            System.out.printf("| %-13s | %-11d | %-10s | %-6.2f | %-6s |\n",
                    record.getVehicle().getLicensePlate(),
                    record.getCustomer().getCustomerId(),
                    record.getDate(),
                    record.getAmount(),
                    record.getActionType());
        }
    }

    public Vehicle findVehicleByPlate(String plate) {
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(plate)) {
                return v;
            }
        }
        return null;
    }

    public Customer findCustomerById(String customerId2) {
        int customerId;
        try {
            customerId = Integer.parseInt(customerId2); 
        } catch (NumberFormatException e) {
            System.out.println("Invalid customer ID format: " + customerId2);
            return null; 
        }

        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                return c; 
            }
        }
        return null; 
    }

    private void loadData() {
        loadVehicles();
        loadCustomers();
        loadRentalHistory();
    }

    void loadVehicles() {
        File vehicleFile = new File("vehicles.txt");
        if (!vehicleFile.exists()) {
            try {
                vehicleFile.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Error creating vehicles file: " + e.getMessage());
            }
            return; 
        }

        try (Scanner scanner = new Scanner(vehicleFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String licensePlate = parts[0];
                    String make = parts[1];
                    String model = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    vehicles.add(new Car(licensePlate, make, model, year));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Vehicles file not found: " + e.getMessage());
        }
    }

    void loadCustomers() {
        File customerFile = new File("customers.txt");
        if (!customerFile.exists()) {
            try {
                customerFile.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Error creating customers file: " + e.getMessage());
            }
            return; }

        try (Scanner scanner = new Scanner(customerFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int customerId = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    customers.add(new Customer(customerId, name));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customers file not found: " + e.getMessage());
        }
    }

    private void loadRentalHistory() {
        File rentalHistoryFile = new File("rental_history.txt");
        if (!rentalHistoryFile.exists()) {
            try {
                rentalHistoryFile.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Error creating rental history file: " + e.getMessage());
            }
            return; 
        }

        try (Scanner scanner = new Scanner(rentalHistoryFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String vehiclePlate = parts[0];
                    int customerId = Integer.parseInt(parts[1]);
                    LocalDate date = LocalDate.parse(parts[2]);
                    double amount = Double.parseDouble(parts[3]);
                    String actionType = parts[4];

                    Vehicle vehicle = findVehicleByPlate(vehiclePlate);
                    Customer customer = (Customer) findCustomerById(customerId);

                    if (vehicle != null && customer != null) {
                        RentalRecord record = new RentalRecord(vehicle, customer, date, amount, actionType);
                        rentalHistory.addRecord(record);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Rental history file not found: " + e.getMessage());
        }
    }

	public void saveVehicles() {
		// TODO Auto-generated method stub
		
	}

	public void saveCustomers() {
		// TODO Auto-generated method stub
		
	}

	public String getAvailableVehicles() {
		// TODO Auto-generated method stub
		return null;
	}
}

