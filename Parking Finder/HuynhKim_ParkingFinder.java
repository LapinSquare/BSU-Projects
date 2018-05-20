import java.util.Scanner; 
import java.util.Random;
import java.text.DecimalFormat;
import java.util.*;

/** 
 * Finds you a random parking spot. Nothin' special.
 * @author Kim Huynh
 * @version 12/13/2017
 */ 

public class HuynhKim_ParkingFinder {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); 
		int carX; 
		int carY; 
		long seed; 
		int time;
		System.out.print("Enter a random number for purchasing purposes : "); 
		seed = in.nextLong( );  
		System.out.println("");
		
		//RANDOM GEN
		Random randomS = new Random(); 
		randomS.setSeed(seed); 
		carX = randomS.nextInt(100);
		carY = randomS.nextInt(100);
		
		//MINUTES
		System.out.print("Enter parking time (minutes) : "); 
		time = in.nextInt();  
		System.out.println(""); 
		if (time>60) { 
			System.out.println("You're staying here for more than an hour? You're crazy! \n"); 
		}
		
		System.out.println("Position of vehicle : X = "+carX+" , Y = "+carY);
		System.out.println("");
		
		//ASSIGN PARKING
		ParkingSpot first = new ParkingSpot ("Lapin Lane", randomS.nextInt(100),randomS.nextInt(100));
		ParkingSpot second = new ParkingSpot ("Krolik Avenue", randomS.nextInt(100),randomS.nextInt(100)); 
		ParkingSpot third = new ParkingSpot ("Contho Crossing", randomS.nextInt(100), randomS.nextInt(100));  
		ParkingSpot fourth = new ParkingSpot ("Kanin Court", randomS.nextInt(100), randomS.nextInt(100)); 
		third.setCostPerInterval(0.30); 
		fourth.setCostPerInterval(0.30); 
		
		//DISTANCE CALCULATIONS (d = DISTANCE, DUH!)
		double d1= Math.abs(first.getLocationX()-carX)+Math.abs(first.getLocationY()-carY);
		double d2= Math.abs(second.getLocationX()-carX)+Math.abs(second.getLocationY()-carY);
		double d3= Math.abs(third.getLocationX()-carX)+Math.abs(third.getLocationY()-carY);
		double d4= Math.abs(fourth.getLocationX()-carX)+Math.abs(fourth.getLocationY()-carY); 
		
		//FORMAT
		int interval = (int) Math.ceil((double)time/10); 
		DecimalFormat fmt = new DecimalFormat("0.00");
		
		System.out.println("Spot 1 : "+first);  
		System.out.println("\t Distance : "+(int)d1);
		System.out.println("\t Total cost : $ "+ fmt.format(interval*first.getCostPerInterval())+"\n");
		
		System.out.println("Spot 2 : "+second); 
		System.out.println("\t Distance : "+(int)d2); 	
		System.out.println("\t Total cost : $ "+ fmt.format(interval*second.getCostPerInterval())+"\n");
		
		System.out.println("Spot 3 : "+third);
		System.out.println("\t Distance : "+(int)d3); 
		System.out.println("\t Total cost : $ "+ fmt.format(interval*third.getCostPerInterval())+"\n");

		System.out.println("Spot 4 : "+fourth);
		System.out.println("\t Distance : "+(int)d4); 
		System.out.println("\t Total cost : $ "+ fmt.format(interval*fourth.getCostPerInterval())+"\n");

		//LAST PRINTOUT
		if (d1<d2 && d1<d3 && d1<d4) { 
			System.out.println("Distance to closest spot : "+(int)d1);
			System.out.println("Closest spot : "+first);
		} 
		else if (d2<d1 && d2<d3 && d2<d4) { 
			System.out.println("Distance to closest spot : "+(int)d2);
			System.out.println("Closest spot : "+second);
		} 
		else if (d3<d2 && d3<d1 && d3<d4) { 
			System.out.println("Distance to closest spot : "+(int)d3);
			System.out.println("Closest spot : "+third);
		} 
		else if (d4<d2 && d4<d1 && d4<d3) { 
			System.out.println("Distance to closest spot : "+(int)d4);
			System.out.println("Closest spot : "+fourth);
		} 
		else { 
			System.out.println("You're not close to any of the spots! Are you on Mars?!");
		}

	}

}

