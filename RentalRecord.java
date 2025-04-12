import java.time.LocalDate;

public class RentalRecord {
    private Vehicle vehicle;
    private Customer customer;
    private LocalDate date;
    private double amount;
    private String actionType; // "RENT" or "RETURN"

    public RentalRecord(Vehicle vehicle, Customer customer, LocalDate date, double amount, String actionType) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.date = date;
        this.amount = amount;
        this.actionType = actionType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getActionType() {
        return actionType;
    }

    @Override
    public String toString() {
        return String.format("| %-13s | %-11d | %-10s | %-6.2f | %-6s |", 
                             vehicle.getLicensePlate(), customer.getCustomerId(), date, amount, actionType);
    }
}
