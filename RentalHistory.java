import java.util.ArrayList;
import java.util.List;

public class RentalHistory {
    private List<RentalRecord> rentalRecords = new ArrayList<>();

    public RentalHistory() {
    }

    public void addRecord(RentalRecord record) {
        this.rentalRecords.add(record);
    }

    public List<RentalRecord> getRentalHistory() {
        return this.rentalRecords;
    }

    public List<RentalRecord> getRentalRecordsByCustomer(String customerName) {
        List<RentalRecord> result = new ArrayList<>();
        for (RentalRecord record : this.rentalRecords) {
            if (record.getCustomer().getName().toLowerCase().contains(customerName.toLowerCase())) {
                result.add(record);
            }
        }
        return result;
    }

    public List<RentalRecord> getRentalRecordsByVehicle(String licensePlate) {
        List<RentalRecord> result = new ArrayList<>();
        for (RentalRecord record : this.rentalRecords) {
            if (record.getVehicle().getLicensePlate().equalsIgnoreCase(licensePlate)) {
                result.add(record);
            }
        }
        return result;
    }

    public void displayRecords() {
        if (this.rentalRecords.isEmpty()) {
            System.out.println("No rental records available.");
        } else {
            System.out.println("Rental History:");
            System.out.println("| Vehicle Plate | Customer ID | Date       | Amount | Action |");
            for (RentalRecord record : this.rentalRecords) {
                System.out.println(record);
            }
        }
    }
}


