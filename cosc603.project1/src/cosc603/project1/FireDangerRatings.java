/**
 * 
 */
package cosc603.project1;

/**
 * Class for computing national fire danger ratings
 * and fire load index
 * 
 * @author Ken Capolongo
 *
 */
public class FireDangerRatings {
	
	/**
	 * 
	 */
	private static double dryBulbTemperature_;
	private static double wetBulbTemperature_;
	private static boolean isSnowPresent_;
	private	static double precipitation_;
	private	static double windSpeed_;
	private	static double districtHerbState_;	// The current herb state of the district
	private	static double buildupIndex_;
	private	static double timberSpreadIndex_;
	private	static double grassSpreadIndex_;
	private	static double fineFuelMoisture_;
	private	static double dryingFactor_;	



	public FireDangerRatings() {
		
	}

	/**
	 * @param dryBulbTemperature
	 * @param wetBulbTemperature
	 * @param isSnowPresent
	 * @param precipitation
	 * @param windSpeed
	 * @param districtHerbState
	 * @param buildupIndex
	 */
	public FireDangerRatings(double	dryBulbTemperature, double wetBulbTemperature, boolean isSnowPresent,
			double precipitation, double windSpeed, int	districtHerbState, double buildupIndex)	{
		setDryBulbTemperature(dryBulbTemperature);
		setWetBulbTemperature(wetBulbTemperature);
		setSnowPresent(isSnowPresent);
		setPrecipitation(precipitation);
		setWindSpeed(windSpeed);
		setDistrictHerbState(districtHerbState);	// The current herb state of the district
		setBuildupIndex(buildupIndex);	
	}
	


	/**
	 * @return the dryBulbTemperature_
	 */
	public static double getDryBulbTemperature() {
		return dryBulbTemperature_;
	}

	/**
	 * @param dryBulbTemperature the dryBulbTemperature_ to set
	 */
	public static void setDryBulbTemperature(double dryBulbTemperature) {
		FireDangerRatings.dryBulbTemperature_ = dryBulbTemperature;
		// Calculate Fine Fuel Moisture
		FireDangerRatings.computeFineFuelMoisture();
	}

	/**
	 * @return the wetBulbTemperature_
	 */
	public static double getWetBulbTemperature() {
		return wetBulbTemperature_;
	}

	/**
	 * @param wetBulbTemperature the wetBulbTemperature_ to set
	 */
	public static void setWetBulbTemperature(double wetBulbTemperature) {
		FireDangerRatings.wetBulbTemperature_ = wetBulbTemperature;
		// Calculate Fine Fuel Moisture
		FireDangerRatings.computeFineFuelMoisture();
	}

	/**
	 * @return the isSnowPresent_
	 */
	public static boolean isSnowPresent() {
		return isSnowPresent_;
	}

	/**
	 * @param isSnowPresent the isSnowPresent_ to set
	 */
	public static void setSnowPresent(boolean isSnowPresent) {
		FireDangerRatings.isSnowPresent_ = isSnowPresent;
	}

	/**
	 * @return the precipitation_
	 */
	public static double getPrecipitation() {
		return precipitation_;
	}

	/**
	 * @param precipitation the precipitation_ to set
	 */
	public static void setPrecipitation(double precipitation) {
		FireDangerRatings.precipitation_ = precipitation;
	}

	/**
	 * @return the windSpeed_
	 */
	public static double getWindSpeed() {
		return windSpeed_;
	}

	/**
	 * @param windSpeed the windSpeed_ to set
	 */
	public static void setWindSpeed(double windSpeed) {
		FireDangerRatings.windSpeed_ = windSpeed;
	}

	/**
	 * @return the districtHerbState_
	 */
	public static double getDistrictHerbState() {
		return districtHerbState_;
	}

	/**
	 * @param districtHerbState the districtHerbState_ to set
	 */
	public static void setDistrictHerbState(double districtHerbState) {
		FireDangerRatings.districtHerbState_ = districtHerbState;
	}

	/**
	 * @return the buildupIndex_
	 */
	public static double getBuildupIndex() {
		return buildupIndex_;
	}

	/**
	 * @param buildupIndex the buildupIndex_ to set
	 */
	public static void setBuildupIndex(double buildupIndex) {
		FireDangerRatings.buildupIndex_ = buildupIndex;
	}
	
	/**
	 * @return the fineFuelMoisture_
	 */
	protected static double getFineFuelMoisture() {
		return fineFuelMoisture_;
	}

	/**
	 * @param fineFuelMoisture the fineFuelMoisture_ to set
	 */
	protected static void setFineFuelMoisture(double fineFuelMoisture) {
		FireDangerRatings.fineFuelMoisture_ = fineFuelMoisture;
	}
	
	/**
	 * @return the timberSpreadIndex_
	 */
	protected static double getTimberSpreadIndex() {
		return timberSpreadIndex_;
	}

	/**
	 * @param timberSpreadIndex the timberSpreadIndex_ to set
	 */
	protected static void setTimberSpreadIndex(double timberSpreadIndex) {
		FireDangerRatings.timberSpreadIndex_ = timberSpreadIndex;
	}
	
	/**
	 * @return the grassSpreadIndex_
	 */
	protected static double getGrassSpreadIndex() {
		return grassSpreadIndex_;
	}

	/**
	 * @param grassSpreadIndex the grassSpreadIndex_ to set
	 */
	protected static void setGrassSpreadIndex(double grassSpreadIndex) {
		FireDangerRatings.grassSpreadIndex_ = grassSpreadIndex;
	}
	
	/**
	 * @return the dryingFactor_
	 */
	protected static double getDryingFactor() {
		return dryingFactor_;
	}

	/**
	 * @param dryingFactor the dryingFactor_ to set
	 */
	protected static void setDryingFactor(double dryingFactor) {
		FireDangerRatings.dryingFactor_ = dryingFactor;
	}
	/**
	 * 
	 */
	private static void adjustBuldupIndex() {
		// Adjust Buildup Index for Precipitation
       double tempValue;

       if(FireDangerRatings.getPrecipitation() > 0.1) {

			//		BUO=-50.*ALOG(    1.-(1. - EXP(-BUO/50.) ) * EXP(-1.175*(precipitation_-.1) ) )
			tempValue = -50.0 * Math.log(1.0 - (1.0 - Math.exp(-1.0 * (FireDangerRatings.getBuildupIndex()/50.0)) * Math.exp( -1.175 * (FireDangerRatings.getPrecipitation() - 0.1))));
 
			//		If BUO < 0 then BUI = 0		
        	if(tempValue < 0)
        		tempValue = 0;
        	
        	FireDangerRatings.setBuildupIndex(tempValue);
        }
	}
	
	/**
	 * 
	 */
	private static void computeFineFuelMoisture() {
		double tempValue;
		double diff = 0;
		int tempInt = 3;
		double[] A = new double[] {-0.185900, -0.85900, -0.05966, -0.07737};
		double[] B = new double[] {30.0, 19.2, 13.8, 22.5};
		double[] rangeDrytoWet = new double[] {4.5, 12.5, 27.5};

		diff = FireDangerRatings.getDryBulbTemperature() - FireDangerRatings.getWetBulbTemperature();

		for (int i = 0; i < 3; i++) {
        	if(diff <= rangeDrytoWet[i]) {
            	tempInt = i;
        		break;
        	}
        }

		tempValue = B[tempInt] * Math.exp(A[tempInt] * diff);	

        if(tempValue < 1)
        	tempValue = 1;
        
        FireDangerRatings.setFineFuelMoisture(tempValue);

	}
	
	/**
	 * @return
	 */
	private static void findDryingFactor() {
		int tempInt = 5;
		double[] D = new double[] {16.0, 10.0, 7.0, 5.0, 4.0, 3.0};

        for (int i = 0; i < 6; i++) {
        	if(FireDangerRatings.getFineFuelMoisture() > D[i]) {
        		tempInt = i - 1;
        		break;
        	}
        }

        FireDangerRatings.setDryingFactor(tempInt);	 	

	}
	
	/**
	 * @param fuelMoisture
	 * @return
	 */
	private static double computeSpreadIndex(double fuelMoisture) {
		double value = 0;
		
        // Check if Fuel Moisture is greater than thirty percent
		if(fuelMoisture < 30 ) {

			if(FireDangerRatings.getWindSpeed() < 14 ) {
				value = .01312 * (FireDangerRatings.getWindSpeed() + 6.) * Math.pow(33.0 - fuelMoisture,1.65) - 3.0;
			}
			else {
				value = .00918 * (FireDangerRatings.getWindSpeed() + 14.) * Math.pow(33.0 - fuelMoisture,1.65) - 3.0;
			}

			if(value < 1 ) 
				value = 1;

			if(value > 99 )
				value = 99;

		}
		else {
			value = 1;
		}
		
		return value;
	}
	

	/**
	 * @return
	 */
	public double computeFireDangerIndex() {
		double fireLoadRating = 0;
		double fineFuelMoisture = 0;
		double buildupIndex = 0;
		double adjustedFuelMoisture = 0;
		double grassSpreadIndex = 0;
		double timberSpreadIndex = 0;
		
		if(FireDangerRatings.isSnowPresent()) { 

			/* There is snow on the ground.  Set the Timber and Grass indexes to zero (0)  */
			FireDangerRatings.setGrassSpreadIndex(0);
			FireDangerRatings.setTimberSpreadIndex(0);
			
	        // Adjust Buildup Index for precipitation_
			FireDangerRatings.adjustBuldupIndex();
		} 
		else { 	
			/* No show on the ground so compute spread indexes and fire load */

            // Find Drying Factor in table
			FireDangerRatings.findDryingFactor();

	        // Add five (5) percent Fine Fuel Moisture for each Herb State greater than one
			fineFuelMoisture = FireDangerRatings.getFineFuelMoisture() + (FireDangerRatings.getDistrictHerbState() - 1.0) * 5.0;				
		
            // Set Fine Fuel Moisture to one (1) if less than one
            if(fineFuelMoisture <= 1)
            	fineFuelMoisture = 1;
            
            FireDangerRatings.setFineFuelMoisture(fineFuelMoisture);
            
            // Adjust Buildup Index for precipitation_ before adding to Drying Factor
			adjustBuldupIndex();
            
            // Add Drying Factor to Buildup Index
			buildupIndex = FireDangerRatings.getBuildupIndex() + FireDangerRatings.getDryingFactor();
			FireDangerRatings.setBuildupIndex(buildupIndex);
			
            // Calculate Adjusted Fuel Moisture for heavy fuels
            adjustedFuelMoisture = 0.9 * FireDangerRatings.getFineFuelMoisture() + 0.5 + 9.5 * Math.exp(-FireDangerRatings.getBuildupIndex() / 50.0);
            
            // Calculate Grass Spread Index
            grassSpreadIndex = FireDangerRatings.computeSpreadIndex(FireDangerRatings.getFineFuelMoisture());
            FireDangerRatings.setGrassSpreadIndex(grassSpreadIndex);

            // Calculate Timber Spread Index
            timberSpreadIndex = FireDangerRatings.computeSpreadIndex(adjustedFuelMoisture);
            FireDangerRatings.setTimberSpreadIndex(timberSpreadIndex);
            
            // Calculate Fire Danger Rating
            // Check if either Timber Spread Index or Buildup Index are zero
            if((FireDangerRatings.getTimberSpreadIndex() > 0) && (FireDangerRatings.getBuildupIndex() > 0)) {

            	//				fireLoadRating = 1.75*ALOG10 (timberSpreadIndex) + .32*ALOG10( BUO ) – 1.640
            	fireLoadRating = 1.75 * Math.log10(FireDangerRatings.getTimberSpreadIndex()) + 0.32 * Math.log10(FireDangerRatings.getBuildupIndex() ) - 1.640;	
 
            	if(fireLoadRating > 0 )
            		fireLoadRating =	Math.pow(10.0,fireLoadRating);
            }
		}
		return fireLoadRating;
	}
}
