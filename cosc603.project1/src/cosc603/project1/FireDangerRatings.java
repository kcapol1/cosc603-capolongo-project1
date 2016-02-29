package cosc603.project1;

// TODO: Auto-generated Javadoc
/**
 * Class for computing national fire danger ratings.
 * and fire load index. It computes buildup index,
 * fire-load index, and various spread indexes.
 * This is a rewrite of the original code, which
 * follows an equation computation method instead
 * of a table lookup scheme.  The computation method
 * was chosen due to memory limitations at the time
 * the original program was created.  The code was
 * listed in the US Forest Service research note 
 * NC-79 Computer Calculation of Fire Danger, 
 * authored by William A.&nbsp; Main, Forest 
 * Research Technician, US Forest Service.
 * 
 * 
 * @author Ken Capolongo
 *
 */
public class FireDangerRatings {
	
	/** The dry-bulb temperature reading. */
	private static double dryBulbTemperature_;
	
	/** The wet-bulb temperature reading. */
	private static double wetBulbTemperature_;
	
	/** The flag to indicate if snow is on the ground. */
	private static boolean isSnowPresent_;
	
	/** The preceding 24 hour precipitation in inches. */
	private	static double precipitation_;
	
	/** The current wind speed in miles per hour. */
	private	static double windSpeed_;
	
	/** The current herbaceous stage of vegetation. */
	private	static double districtHerbState_;	// The current herb state of the district
	
	/** The yesterday's buildup index. */
	private	static double buildupIndex_;
	
	/** The timber spread index. */
	private	static double timberSpreadIndex_;
	
	/** The grass spread index. */
	private	static double grassSpreadIndex_;
	
	/** The fine fuel moisture. */
	private	static double fineFuelMoisture_;



	/**
	 * Class constructor.
	 */
	public FireDangerRatings() {
		
	}

	/**
	 * Class constructor initializing inputs to compute
	 * fire danger ratings.
	 * 
	 * @param dryBulbTemperature  	dry-bulb temperature reading
	 * @param wetBulbTemperature	wet-bulb temperature reading
	 * @param isSnowPresent 		flag to indicate if snow is on the ground
	 * @param precipitation			preceding 24 hour precipitation in inches
	 * @param windSpeed				current wind speed in miles per hour
	 * @param districtHerbState		current herbaceous stage of vegetation
	 * 								1=cured, 2=transition, 3=green
	 * @param buildupIndex			yesterday's buildup index
	 */
	public FireDangerRatings(double	dryBulbTemperature, double wetBulbTemperature, boolean isSnowPresent,
			double precipitation, double windSpeed, int	districtHerbState, double buildupIndex)	{
		setDryBulbTemperature(dryBulbTemperature);
		setWetBulbTemperature(wetBulbTemperature);
		setSnowPresent(isSnowPresent);
		setPrecipitation(precipitation);
		setWindSpeed(windSpeed);
		setDistrictHerbState(districtHerbState);
		setBuildupIndex(buildupIndex);	
	}
	


	/**
	 * Gets the dry bulb temperature.
	 *
	 * @return dryBulbTemperature_
	 */
	public static double getDryBulbTemperature() {
		return dryBulbTemperature_;
	}

	/**
	 * Sets the dry bulb temperature.
	 * Also calculates the fine fuel moisture 
	 *
	 * @param dryBulbTemperature the dryBulbTemperature_ to set
	 */
	public static void setDryBulbTemperature(double dryBulbTemperature) {
		FireDangerRatings.dryBulbTemperature_ = dryBulbTemperature;
		// Calculate Fine Fuel Moisture
		FireDangerRatings.computeFineFuelMoisture();
	}

	/**
	 * Gets the wet bulb temperature.
	 *
	 * @return the wetBulbTemperature_
	 */
	public static double getWetBulbTemperature() {
		return wetBulbTemperature_;
	}

	/**
	 * Sets the wet bulb temperature.
	 * Also calculates the fine fuel moisture 
	 *
	 * @param wetBulbTemperature the wetBulbTemperature_ to set
	 */
	public static void setWetBulbTemperature(double wetBulbTemperature) {
		FireDangerRatings.wetBulbTemperature_ = wetBulbTemperature;
		// Calculate Fine Fuel Moisture
		FireDangerRatings.computeFineFuelMoisture();
	}

	/**
	 * Checks if is snow present.
	 *
	 * @return the isSnowPresent_
	 */
	public static boolean isSnowPresent() {
		return isSnowPresent_;
	}

	/**
	 * Sets the snow present.
	 *
	 * @param isSnowPresent the isSnowPresent_ to set
	 */
	public static void setSnowPresent(boolean isSnowPresent) {
		FireDangerRatings.isSnowPresent_ = isSnowPresent;
	}

	/**
	 * Gets the precipitation.
	 *
	 * @return the precipitation_
	 */
	public static double getPrecipitation() {
		return precipitation_;
	}

	/**
	 * Sets the precipitation.
	 *
	 * @param precipitation the precipitation_ to set
	 */
	public static void setPrecipitation(double precipitation) {
		FireDangerRatings.precipitation_ = precipitation;
	}

	/**
	 * Gets the wind speed.
	 *
	 * @return the windSpeed_
	 */
	public static double getWindSpeed() {
		return windSpeed_;
	}

	/**
	 * Sets the wind speed.
	 *
	 * @param windSpeed the windSpeed_ to set
	 */
	public static void setWindSpeed(double windSpeed) {
		FireDangerRatings.windSpeed_ = windSpeed;
	}

	/**
	 * Gets the district herb state.
	 *
	 * @return the districtHerbState_
	 */
	public static double getDistrictHerbState() {
		return districtHerbState_;
	}

	/**
	 * Sets the district herb state.
	 *
	 * @param districtHerbState the districtHerbState_ to set
	 */
	public static void setDistrictHerbState(double districtHerbState) {
		FireDangerRatings.districtHerbState_ = districtHerbState;
	}

	/**
	 * Gets the buildup index.
	 *
	 * @return the buildupIndex_
	 */
	public static double getBuildupIndex() {
		return buildupIndex_;
	}

	/**
	 * Sets the buildup index.
	 *
	 * @param buildupIndex the buildupIndex_ to set
	 */
	public static void setBuildupIndex(double buildupIndex) {
		FireDangerRatings.buildupIndex_ = buildupIndex;
	}
	
	/**
	 * Gets the fine fuel moisture.
	 *
	 * @return the fineFuelMoisture_
	 */
	protected static double getFineFuelMoisture() {
		return fineFuelMoisture_;
	}

	/**
	 * Sets the fine fuel moisture.
	 *
	 * @param fineFuelMoisture the fineFuelMoisture_ to set
	 */
	protected static void setFineFuelMoisture(double fineFuelMoisture) {
		FireDangerRatings.fineFuelMoisture_ = fineFuelMoisture;
	}
	
	/**
	 * Gets the timber spread index.
	 *
	 * @return the timberSpreadIndex_
	 */
	protected static double getTimberSpreadIndex() {
		return timberSpreadIndex_;
	}

	/**
	 * Sets the timber spread index.
	 *
	 * @param timberSpreadIndex the timberSpreadIndex_ to set
	 */
	protected static void setTimberSpreadIndex(double timberSpreadIndex) {
		FireDangerRatings.timberSpreadIndex_ = timberSpreadIndex;
	}
	
	/**
	 * Gets the grass spread index.
	 *
	 * @return the grassSpreadIndex_
	 */
	protected static double getGrassSpreadIndex() {
		return grassSpreadIndex_;
	}

	/**
	 * Sets the grass spread index.
	 *
	 * @param grassSpreadIndex the grassSpreadIndex_ to set
	 */
	protected static void setGrassSpreadIndex(double grassSpreadIndex) {
		FireDangerRatings.grassSpreadIndex_ = grassSpreadIndex;
	}
	
	/**
	 * Adjust buldup index.
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
	 * Compute fine fuel moisture.
	 */
	private static void computeFineFuelMoisture() {
		double tempValue;
		double diff = 0;
		int tempInt = 3;
		final double A [] = {-0.185900, -0.85900, -0.05966, -0.07737};
		final double B [] = {30.0, 19.2, 13.8, 22.5};
		final double rangeDrytoWet [] = {4.5, 12.5, 27.5};

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
	 * Find drying factor.
	 *
	 * @return the int
	 */
	private static int findDryingFactor() {
		int tempInt = 5;
		final double D [] = {16.0, 10.0, 7.0, 5.0, 4.0, 3.0};

        for (int i = 0; i < 6; i++) {
        	if(FireDangerRatings.getFineFuelMoisture() > D[i]) {
        		tempInt = i - 1;
        		break;
        	}
        }

        return tempInt;	 	

	}
	
	/**
	 * Compute spread index.
	 *
	 * @param fuelMoisture the fuel moisture
	 * @return the double
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
	 * Compute fire danger index.
	 *
	 * @return the double
	 */
	public double computeFireDangerIndex() {
		double fireLoadRating = 0;
		double fineFuelMoisture = 99;
		double buildupIndex = 0;
		double adjustedFuelMoisture = 99;
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

	        // Add five (5) percent Fine Fuel Moisture for each Herb State greater than one
			fineFuelMoisture = FireDangerRatings.getFineFuelMoisture() + (FireDangerRatings.getDistrictHerbState() - 1.0) * 5.0;				
            FireDangerRatings.setFineFuelMoisture(fineFuelMoisture);
            
            // Adjust Buildup Index for precipitation_ before adding to Drying Factor
            FireDangerRatings.adjustBuldupIndex();
            
            // Add Drying Factor to Buildup Index
			buildupIndex = FireDangerRatings.getBuildupIndex() + FireDangerRatings.findDryingFactor();
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
