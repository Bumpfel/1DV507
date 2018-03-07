package ee222yb_assign1.ferry;

import java.util.Iterator;
import java.util.ArrayList;

public class StandardFerry implements Ferry {

	// Using public constants instead of getters
	public final int MAX_PASSENGER_SPACE = 200;
	public final int MAX_VEHICLE_SPACE = 40;
	
	private ArrayList<Vehicle> boardedVehicles = new ArrayList<>();
	private ArrayList<Passenger> boardedPassengers = new ArrayList<>();
	private double occupiedSpace;
	private int revenue;
	
	public int countPassengers() {
		return boardedPassengers.size();
	}

	/**
	 * Returns a ceiling rounded integer of occupied space
	 */
	public int countVehicleSpace() {
		return (int) Math.ceil(occupiedSpace);
	}

	public int countMoney() {
		return revenue;
	}

	/**
	 * Embarks passenger on ferry if there is room and passenger is not already on board
	 * Adds fee to revenue
	 * @throws IndexOutofBoundsException if ferry is full
	 * @throws IllegalArgumentException if Passenger is already on board the ferry
	 */
	public void embark(Passenger p) {
		if(!hasRoomFor(p))
			throw new IndexOutOfBoundsException("The ferry is full. Cannot embark any more passengers."); // user can avoid the exception with the hasRoomFor() method
		else if(boardedPassengers.contains(p))
			throw new IllegalArgumentException("Passenger is already on board the ferry.");
		
		boardedPassengers.add(p);
		revenue += p.getPassengerFee();
	}
	
	/**
	 * Embarks the vehicle and its passengers on the ferry
	 * Adds fees to revenue
	 * @throws IndexOutofBoundsException if there is not enough space on ferry to embark Vehicle
	 * @throws IllegalArgumentException if Vehicle is already on board the ferry
	 */
	public void embark(Vehicle v) {
		if(!hasSpaceFor(v))
			throw new IndexOutOfBoundsException("The ferry doesn't have enough space to embark the vehicle " + v.VEHICLE_TYPE + ".");
		else if(boardedVehicles.contains(v))
			throw new IllegalArgumentException("Vehicle is already on board the ferry.");
		
		// Embarks all passengers in the vehicle
		Iterator<Passenger> it = v.pIterator();
		while(it.hasNext()) 
			embark(it.next());
		
		boardedVehicles.add(v);
		revenue += v.VEHICLE_FEE;
		occupiedSpace += v.VEHICLE_SPACE;
	}
	
	public void disembark() {
		boardedPassengers.clear();
		boardedVehicles.clear();
		occupiedSpace = 0;
	}
	
	public boolean hasSpaceFor(Vehicle v) {
		return occupiedSpace + v.VEHICLE_SPACE <= MAX_VEHICLE_SPACE;
	}
	
	public boolean hasRoomFor(Passenger p) {
		return boardedPassengers.size() < MAX_PASSENGER_SPACE;
	}
	
	@Override
	public String toString() {
		Iterator<Vehicle> it = iterator();

		String str = "The ferry has " + boardedVehicles.size() + " vehicles and " + boardedPassengers.size() + " passengers on board."
					+ "There are " + (MAX_VEHICLE_SPACE - countVehicleSpace()) + " units of vehicle space left out of a maximum of " + MAX_VEHICLE_SPACE + ". The ferry has earned " + revenue + " kr.\n"
					+ "Loaded vehicles:";
		while(it.hasNext()) {
			Vehicle v = it.next();
			str += "\n" + v.VEHICLE_TYPE + " " + v.LICENSE_PLATE;
		}
		return str;
	}
	
	public Iterator<Vehicle> iterator() {
		return new VehicleIterator();
	}
	
	class VehicleIterator implements Iterator<Vehicle> {
	
		private int index = 0, maxIndex = boardedVehicles.size();
		
		public VehicleIterator() {
		}

		public boolean hasNext() {
			return index < maxIndex;
		}
		
		public Vehicle next() {
			return boardedVehicles.get(index++);
		}
	}

}
