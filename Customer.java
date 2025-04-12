public class Customer {
    private int customerId;  // ID should be numeric
    private String name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public Customer(String customerIdStr, String name) {
        try {
            this.customerId = Integer.parseInt(customerIdStr);  
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Customer ID must be numeric");
        }
        this.name = name;
    }

    public Customer(int customerId2, String name2, String contactInfo) {
		// TODO Auto-generated constructor stub
	}

	// Getters
    public int getCustomerId() { return customerId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("Customer ID: %03d | Name: %s", customerId, name);
    }

	public String getCustomerName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getContactInfo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}