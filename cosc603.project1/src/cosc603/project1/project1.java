package cosc603.project1;

/**
 * @author Ken Capolongo
 *
 */

public class project1 {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double fireDangerIndex;
		FireDangerRatings fireDangerRatings = new FireDangerRatings(78.0,50.0,false,0.1,14.0,1,3.0);
		fireDangerIndex = fireDangerRatings.computeFireDangerIndex();
        System.out.println();
        System.out.format("Fine Fuel Moisture = %-10.3f%n",FireDangerRatings.getFineFuelMoisture());
//        System.out.format("Adjusted Fuel Moisture = %-10.3f%n",adjustedFuelMoisture_);
        System.out.format("Drying Factor = %-10.3f%n",FireDangerRatings.getDryingFactor());
        System.out.format("Buildup Index = %-10.3f%n",FireDangerRatings.getBuildupIndex());
        System.out.format("Grass Spread Index = %-10.3f%n",FireDangerRatings.getGrassSpreadIndex());
        System.out.format("Timber Spread Index = %-10.3f%n",FireDangerRatings.getTimberSpreadIndex());
        System.out.format("Fire Load Rating = %-10.3f%n",fireDangerIndex);

       
	}

}