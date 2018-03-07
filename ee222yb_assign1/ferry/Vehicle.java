package ee222yb_assign1.ferry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class Vehicle {
	private static ArrayList<String> allLicensePlates = new ArrayList<>();
	
	// Using public constants instead of getters
	public final String VEHICLE_TYPE; // for printing purposes only
	public final int VEHICLE_FEE;
	public final double VEHICLE_SPACE;
	public final String LICENSE_PLATE;
	
	private ArrayList<Passenger> passengersInVehicle = new ArrayList<>();
	
	/**
	 * Creates a new Vehicle and specified number of passengers
	 * @param type - Vehicle type
	 * @param maxPass - Maximum nr of passengers the vehicle can carry
	 * @param passengers - Nr of passengers seated in vehicle
	 * @param vFee - Vehicle fee on the ferry
	 * @param pFee - Passenger fee for each passenger in the vehicle
	 * @param vSpace - Units of space the vehicle occupies on the ferry
	 */
	public Vehicle(String type, int maxPass, int passengers, int vFee, int pFee, double vSpace) {
		if(passengers > maxPass|| passengers < 1)
			throw new IllegalArgumentException("Illegal number of passengers for vehicle type " + type);
		
		// Creates specified number of passengers
		for(int i = 0; i < passengers; i ++)
			passengersInVehicle.add(new Passenger(pFee));
		
		if(type != "Bicycle") { // Bicycles have no license plates
			String plate = makeLicensePlate();
			while(allLicensePlates.contains(plate)) { // Makes sure it's unique
				plate = makeLicensePlate();
			}
			LICENSE_PLATE = plate;
			allLicensePlates.add(plate);
		}
		else
			LICENSE_PLATE = "";
		
		VEHICLE_TYPE = type;
		VEHICLE_FEE = vFee;
		VEHICLE_SPACE = vSpace;
	}
	
	/**
	 * @return A randomly generated license plate 'LLL NNN' (letters A-Z)
	 */
	private String makeLicensePlate() {
		Random rand = new Random();
		String plateLetters = new String() + (char) (rand.nextInt(26) + 'A') + (char) (rand.nextInt(26) + 'A') + (char) (rand.nextInt(26) + 'A') ;
		String plateNumbers = new String() + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
		
		return plateLetters + " " + plateNumbers;
	}
		
	// Made for encapsulation. Returning a reference to the ArrayList would open up for possibilities to modify the ArrayList. I suppose a cloned list would also be okay to return.
	public Iterator<Passenger> pIterator() {
		return new PassengerIterator();
	}
		
	class PassengerIterator implements Iterator<Passenger> {
		private int index = 0, maxIndex = passengersInVehicle.size();
		
		public boolean hasNext() {
			return index < maxIndex;
		}
		
		public Passenger next() {
			return passengersInVehicle.get(index++);
		}
	}
	
}
