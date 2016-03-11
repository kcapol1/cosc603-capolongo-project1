package cosc603.project1;
import java.util.Scanner;

/**
 * @author Ken Capolongo
 *
 */

public class project1 {
	
	private static FireDangerRatings fireDangerRatings_;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		fireDangerRatings_ = new FireDangerRatings();
		fireDangerRatings_.setDryBulbTemperature(78);
		fireDangerRatings_.setWetBulbTemperature(50);
		fireDangerRatings_.setSnowPresent(false);
		fireDangerRatings_.setPrecipitation(0.1);
		fireDangerRatings_.setWindSpeed(14);
		fireDangerRatings_.setDistrictHerbaceousStage(1);
		fireDangerRatings_.setBuildupIndex(3);
		
		printAll();
		
        int menuSelection;
        double inputMeasurement;
        int inputFlag;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println( );
            System.out.println("National Fire Danger Rating System\n");
            System.out.println("Select from the following:\n");
            System.out.println("\t(1) Dry-bulb reading");
            System.out.println("\t(2) Wet-bulb reading");
            System.out.println("\t(3) Snow");
            System.out.println("\t(4) Preceding 24-hour precipitation");
            System.out.println("\t(5) Current wind speed");
            System.out.println("\t(6) Yesterday's buildup index");
            System.out.println("\t(7) Current herbaceous stage of vegetation");
            System.out.println("\t(q) quit program");
            System.out.print("\nEnter selection: ");
            if (!scanner.hasNextInt()) 
            	break;
            menuSelection = scanner.nextInt();
            switch (menuSelection) {
                case 1: System.out.println("Dry-bulb temperature:");
                	inputMeasurement = scanner.nextDouble();
                	fireDangerRatings_.setDryBulbTemperature(inputMeasurement);
                	break;
                case 2: System.out.println("Wet-bulb temperature:");
                	inputMeasurement = scanner.nextDouble();
                	fireDangerRatings_.setWetBulbTemperature(inputMeasurement);
                    break;
                case 3: System.out.println("Is snow present (1=y/0=n):");
                	inputFlag = scanner.nextInt();
                	if(inputFlag == 1) {
                		fireDangerRatings_.setSnowPresent(true);
                	}
                	else {
                		fireDangerRatings_.setSnowPresent(false);
                	}
                	break;
                case 4: System.out.println("24-hour precipitation (inches):");
                	inputMeasurement = scanner.nextDouble();
            		fireDangerRatings_.setPrecipitation(inputMeasurement);
                    break;
                case 5: System.out.println("Current wind speed (MPH):");
                	inputMeasurement = scanner.nextDouble();
        			fireDangerRatings_.setWindSpeed(inputMeasurement);
                    break;
                case 6: System.out.println("Yesterday's buildup index:");
                	inputMeasurement = scanner.nextDouble();
    				fireDangerRatings_.setBuildupIndex(inputMeasurement);
                    break;
                case 7: System.out.println("Current herbaceous stage of vegetation:");
                	inputMeasurement = scanner.nextDouble();
                	fireDangerRatings_.setDistrictHerbaceousStage(inputMeasurement);
                    break;
                default: menuSelection = 0;
                          break;
            }
            
    		printAll();
            
        } while (menuSelection != 0);
       
        System.out.println("\nDone!");

        scanner.close();

	}
	
	private static void printAll() {
	    System.out.println();
        System.out.format("Fine Fuel Moisture = %-10.3f%n",fireDangerRatings_.getFineFuelMoisture());
        System.out.format("Adjusted Fuel Moisture = %-10.3f%n",fireDangerRatings_.getAdjustedFuelMoisture());
        System.out.format("Drying Factor = %-10.3f%n",fireDangerRatings_.getDryingFactor());
        System.out.format("Buildup Index = %-10.3f%n",fireDangerRatings_.getBuildupIndex());
        System.out.format("Grass Spread Index = %-10.3f%n",fireDangerRatings_.getGrassSpreadIndex());
        System.out.format("Timber Spread Index = %-10.3f%n",fireDangerRatings_.getTimberSpreadIndex());
        System.out.format("Fire Load Rating = %-10.3f%n",fireDangerRatings_.computeFireDangerIndex());
			
	}
	

}