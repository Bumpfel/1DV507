package ee222yb_assign1.ferry;

public class Passenger {
	private int passengerFee = 20; // Default fee for passenger w/o vehicle
	
	public Passenger() {
	}
	
	// Used when creating vehicles
	protected Passenger(int fee) {
		passengerFee = fee;
	}
	
	public int getPassengerFee() {
		return passengerFee;
	}
}
