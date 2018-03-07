package ee222yb_assign1.ferry;

public class FerryMain {

	public static void main(String[] args) {
		try {
			StandardFerry ferry1 = new StandardFerry();

			for(int i = 0; i < 20; i ++)
				ferry1.embark(new Passenger());
			
			ferry1.embark(new Car(3)); // Creates and embarks a car with 3 passengers
			ferry1.embark(new Lorry(1));
			ferry1.embark(new Bus(20));
			ferry1.embark(new Bus(15));
			ferry1.embark(new Bicycle());
			ferry1.embark(new Car(2));
			ferry1.embark(new Car(2));
			
			ferry1.disembark();
			
			ferry1.embark(new Bus(17));
			ferry1.embark(new Lorry(1));
			ferry1.embark(new Lorry(1));
			ferry1.embark(new Bicycle());
			ferry1.embark(new Car(2));
			ferry1.embark(new Car(3));
			ferry1.embark(new Bicycle());
			ferry1.embark(new Bicycle());
			ferry1.embark(new Car(1));
			ferry1.embark(new Car(3));
			ferry1.embark(new Lorry(2));
			ferry1.embark(new Car(1));
			ferry1.embark(new Bicycle());
			ferry1.embark(new Bus(6));
			ferry1.embark(new Car(4));
			
			System.out.println(ferry1.toString());
		}
		catch(IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}
		catch(IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
