package cosc603.project1;

// TODO: Auto-generated Javadoc
/**
 * Class for computing national fire danger ratings. and fire load index. It
 * computes buildup index, fire-load index, and various spread indexes. This is
 * a rewrite of the original code, which follows an equation computation method
 * instead of a table lookup scheme. The computation method was chosen due to
 * memory limitations at the time the original program was created. The code was
 * listed in the US Forest Service research note NC-79 Computer Calculation of
 * Fire Danger, authored by William A.&nbsp; Main, Forest Research Technician,
 * US Forest Service.
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
	private static double precipitation_;

	/** The current wind speed in miles per hour. */
	private static double windSpeed_;

	/** The current herbaceous stage of vegetation. */
	private static double districtHerbaceousStage_; // The current herb state of
													// the district

	/** The yesterday's buildup index. */
	private static double buildupIndex_;

	/** The timber spread index. */
	private static double timberSpreadIndex_;

	/** The grass spread index. */
	private static double grassSpreadIndex_;

	/** The fine fuel moisture. */
	private static double fineFuelMoisture_;

	/** The adjusted fuel moisture. */
	private static double adjustedFuelMoisture_;

	/** The drying factor. */
	private static double dryingFactor_;

	/**
	 * Class constructor.
	 */
	public FireDangerRatings() {
		FireDangerRatings.initialize();
	}

	/**
	 * Class constructor initializing inputs to compute fire danger ratings.
	 * 
	 * @param dryBulbTemperature
	 *            dry-bulb temperature reading
	 * @param wetBulbTemperature
	 *            wet-bulb temperature reading
	 * @param isSnowPresent
	 *            flag to indicate if snow is on the ground
	 * @param precipitation
	 *            preceding 24 hour precipitation in inches
	 * @param windSpeed
	 *            current wind speed in miles per hour
	 * @param districtHerbaceousStage
	 *            current herbaceous stage of vegetation 1=cured, 2=transition,
	 *            3=green
	 * @param buildupIndex
	 *            yesterday's buildup index
	 */
	public FireDangerRatings(double dryBulbTemperature,
			double wetBulbTemperature, boolean isSnowPresent,
			double precipitation, double windSpeed,
			int districtHerbaceousStage, double buildupIndex) {
		FireDangerRatings.initialize();
		FireDangerRatings.dryBulbTemperature_ = dryBulbTemperature;
		FireDangerRatings.wetBulbTemperature_ = wetBulbTemperature;
		FireDangerRatings.isSnowPresent_ = isSnowPresent;
		FireDangerRatings.precipitation_ = precipitation;
		FireDangerRatings.windSpeed_ = windSpeed;
		FireDangerRatings.districtHerbaceousStage_ = districtHerbaceousStage;
		FireDangerRatings.buildupIndex_ = buildupIndex;
	}

	
	private static void initialize() {
		FireDangerRatings.dryBulbTemperature_ = 70;
		FireDangerRatings.wetBulbTemperature_ = 60;
		FireDangerRatings.fineFuelMoisture_= 99;
		FireDangerRatings.adjustedFuelMoisture_= 99;
		FireDangerRatings.dryingFactor_= 0;
		FireDangerRatings.isSnowPresent_ = false;
		FireDangerRatings.precipitation_ = 0.1;
		FireDangerRatings.windSpeed_ = 14;
		FireDangerRatings.districtHerbaceousStage_ = 1;
		FireDangerRatings.buildupIndex_ = 5;
	}
	
	/**
	 * Gets the dry bulb temperature.
	 *
	 * @return dryBulbTemperature_
	 */
	public double getDryBulbTemperature() {
		return dryBulbTemperature_;
	}

	/**
	 * Sets the dry bulb temperature. Also calculates the fine fuel moisture
	 *
	 * @param dryBulbTemperature
	 *            the dryBulbTemperature_ to set
	 */
	public void setDryBulbTemperature(double dryBulbTemperature) {
		FireDangerRatings.dryBulbTemperature_ = dryBulbTemperature;
	}

	/**
	 * Gets the wet bulb temperature.
	 *
	 * @return the wetBulbTemperature_
	 */
	public double getWetBulbTemperature() {
		return wetBulbTemperature_;
	}

	/**
	 * Sets the wet bulb temperature. Also calculates the fine fuel moisture
	 *
	 * @param wetBulbTemperature
	 *            the wetBulbTemperature_ to set
	 */
	public void setWetBulbTemperature(double wetBulbTemperature) {
		FireDangerRatings.wetBulbTemperature_ = wetBulbTemperature;
	}

	/**
	 * Checks if is snow present.
	 *
	 * @return the isSnowPresent_
	 */
	public boolean isSnowPresent() {
		return isSnowPresent_;
	}

	/**
	 * Sets the snow present.
	 *
	 * @param isSnowPresent
	 *            the isSnowPresent_ to set
	 */
	public void setSnowPresent(boolean isSnowPresent) {
		FireDangerRatings.isSnowPresent_ = isSnowPresent;
	}

	/**
	 * Gets the precipitation.
	 *
	 * @return the precipitation_
	 */
	public double getPrecipitation() {
		return precipitation_;
	}

	/**
	 * Sets the precipitation.
	 *
	 * @param precipitation
	 *            the precipitation_ to set
	 */
	public void setPrecipitation(double precipitation) {
		FireDangerRatings.precipitation_ = precipitation;
	}

	/**
	 * Gets the wind speed.
	 *
	 * @return the windSpeed_
	 */
	public double getWindSpeed() {
		return windSpeed_;
	}

	/**
	 * Sets the wind speed.
	 *
	 * @param windSpeed
	 *            the windSpeed_ to set
	 */
	public void setWindSpeed(double windSpeed) {
		FireDangerRatings.windSpeed_ = windSpeed;
	}

	/**
	 * Gets the district herb state.
	 *
	 * @return the districtHerbaceousStage_
	 */
	public double getDistrictHerbaceousStage() {
		return districtHerbaceousStage_;
	}

	/**
	 * Sets the district herb state.
	 *
	 * @param districtHerbaceousStage
	 *            the districtHerbaceousStage_ to set
	 */
	public void setDistrictHerbaceousStage(double districtHerbaceousStage) {
		FireDangerRatings.districtHerbaceousStage_ = districtHerbaceousStage;
	}

	/**
	 * Gets the buildup index.
	 *
	 * @return the buildupIndex_
	 */
	public double getBuildupIndex() {
		return buildupIndex_;
	}

	/**
	 * Sets the buildup index.
	 *
	 * @param buildupIndex
	 *            the buildupIndex_ to set
	 */
	public void setBuildupIndex(double buildupIndex) {
		FireDangerRatings.buildupIndex_ = buildupIndex;
	}

	/**
	 * Gets the fine fuel moisture.
	 *
	 * @return the fineFuelMoisture_
	 */
	protected double getFineFuelMoisture() {
		return fineFuelMoisture_;
	}

	/**
	 * Sets the fine fuel moisture.
	 *
	 * @param fineFuelMoisture
	 *            the fineFuelMoisture_ to set
	 */
	protected void setFineFuelMoisture(double fineFuelMoisture) {
		FireDangerRatings.fineFuelMoisture_ = fineFuelMoisture;
	}

	/**
	 * Gets the timber spread index.
	 *
	 * @return the timberSpreadIndex_
	 */
	protected double getTimberSpreadIndex() {
		return timberSpreadIndex_;
	}

	/**
	 * Sets the timber spread index.
	 *
	 * @param timberSpreadIndex
	 *            the timberSpreadIndex_ to set
	 */
	protected void setTimberSpreadIndex(double timberSpreadIndex) {
		FireDangerRatings.timberSpreadIndex_ = timberSpreadIndex;
	}

	/**
	 * Gets the grass spread index.
	 *
	 * @return the grassSpreadIndex_
	 */
	protected double getGrassSpreadIndex() {
		return grassSpreadIndex_;
	}

	/**
	 * Sets the grass spread index.
	 *
	 * @param grassSpreadIndex
	 *            the grassSpreadIndex_ to set
	 */
	protected void setGrassSpreadIndex(double grassSpreadIndex) {
		FireDangerRatings.grassSpreadIndex_ = grassSpreadIndex;
	}
	
	/**
	 * @return the dryingFactor_
	 */
	public double getDryingFactor() {
		return dryingFactor_;
	}

	/**
	 * @param dryingFactor_ the dryingFactor_ to set
	 */
	public void setDryingFactor(double dryingFactor) {
		FireDangerRatings.dryingFactor_ = dryingFactor;
	}
	
	/**
	 * @return the adjustedFuelMoisture_
	 */
	public double getAdjustedFuelMoisture() {
		return adjustedFuelMoisture_;
	}

	/**
	 * @param adjustedFuelMoisture_ the adjustedFuelMoisture_ to set
	 */
	public static void setAdjustedFuelMoisture(double adjustedFuelMoisture) {
		FireDangerRatings.adjustedFuelMoisture_ = adjustedFuelMoisture;
	}

	
	/**
	 * Adjust buldup index for Precipitation.
	 */
	private static void adjustBuldupIndex() {
		double tempValue;

		if (FireDangerRatings.precipitation_ > 0.1) {

			tempValue = -50.0
					* Math.log(1.0 - (1.0 - Math.exp(-1.0
							* (FireDangerRatings.buildupIndex_ / 50.0))
							* Math.exp(-1.175
									* (FireDangerRatings.precipitation_ - 0.1))));

			if (tempValue < 0)
				tempValue = 0;

			FireDangerRatings.buildupIndex_ = tempValue;
		}
	}

	/**
	 * Compute fine fuel moisture.
	 */
	private static void computeFineFuelMoisture() {
		double tempValue = tempValue();
		FireDangerRatings.fineFuelMoisture_ = tempValue;

	}

	private static double tempValue() {
		double tempValue;
		double diff = 0;
		int tempInt = 3;
		final double[] A = { -0.185900, -0.85900, -0.05966, -0.07737 };
		final double[] B = { 30.0, 19.2, 13.8, 22.5 };
		final double[] rangeDrytoWet = { 4.5, 12.5, 27.5 };
		diff = FireDangerRatings.dryBulbTemperature_
				- FireDangerRatings.wetBulbTemperature_;
		for (int i = 0; i < 3; i++) {
			if (diff <= rangeDrytoWet[i]) {
				tempInt = i;
				break;
			}
		}
		tempValue = B[tempInt] * Math.exp(A[tempInt] * diff);
		if (tempValue < 1)
			tempValue = 1;
		return tempValue;
	}

	/**
	 * Find drying factor.
	 *
	 * @return the int
	 */
	public static int findDryingFactor() {
		int tempInt = 5;
		final double [] D = { 16.0, 10.0, 7.0, 5.0, 4.0, 3.0 };

		for (int i = 0; i < 6; i++) {
			if (FireDangerRatings.fineFuelMoisture_ > D[i]) {
				tempInt = i - 1;
				break;
			}
		}

		return tempInt;

	}

	/**
	 * Compute spread index.
	 *
	 * @param fuelMoisture
	 *            the fuel moisture
	 * @return the double
	 */
	private static double computeSpreadIndex(double fuelMoisture) {
		double value = 0;

		// Check if Fuel Moisture is greater than thirty percent
		if (fuelMoisture < 30) {

			if (FireDangerRatings.windSpeed_ < 14) {
				value = .01312 * (FireDangerRatings.windSpeed_ + 6.)
						* Math.pow(33.0 - fuelMoisture, 1.65) - 3.0;
			} else {
				value = .00918 * (FireDangerRatings.windSpeed_ + 14.)
						* Math.pow(33.0 - fuelMoisture, 1.65) - 3.0;
			}

			if (value < 1)
				value = 1;

			if (value > 99)
				value = 99;

		} else {
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

		if (FireDangerRatings.isSnowPresent_) {

			/*
			 * There is snow on the ground. Set the Timber and Grass indexes to
			 * zero (0)
			 */
			FireDangerRatings.grassSpreadIndex_ = 0;
			FireDangerRatings.timberSpreadIndex_ = 0;

			// Adjust Buildup Index for precipitation_
			FireDangerRatings.adjustBuldupIndex();
			
		} else {
			/* No show on the ground so compute spread indexes and fire load */

			// Calculate Fine Fuel Moisture
			FireDangerRatings.computeFineFuelMoisture();
		
			FireDangerRatings.findDryingFactor();
			
			// Add five (5) percent Fine Fuel Moisture for each Herb State
			// greater than one
			FireDangerRatings.fineFuelMoisture_ = FireDangerRatings.fineFuelMoisture_
					+ (FireDangerRatings.districtHerbaceousStage_ - 1.0) * 5.0;

			// Adjust Buildup Index for precipitation_ before adding to Drying
			// Factor
			FireDangerRatings.adjustBuldupIndex();

			// Add Drying Factor to Buildup Index
			FireDangerRatings.buildupIndex_ = FireDangerRatings.buildupIndex_
					+ FireDangerRatings.dryingFactor_;

			// Calculate Adjusted Fuel Moisture for heavy fuels
			FireDangerRatings.adjustedFuelMoisture_ = 0.9 * FireDangerRatings.fineFuelMoisture_
					+ 0.5 + 9.5
					* Math.exp(-FireDangerRatings.buildupIndex_ / 50.0);

			// Calculate Grass Spread Index
			FireDangerRatings.grassSpreadIndex_ = FireDangerRatings
					.computeSpreadIndex(FireDangerRatings.fineFuelMoisture_);

			// Calculate Timber Spread Index
			FireDangerRatings.timberSpreadIndex_ = FireDangerRatings
					.computeSpreadIndex(FireDangerRatings.adjustedFuelMoisture_);

			// Calculate Fire Danger Rating
			// Check if either Timber Spread Index or Buildup Index are zero
			if ((FireDangerRatings.timberSpreadIndex_ > 0)
					&& (FireDangerRatings.buildupIndex_ > 0)) {

				// fireLoadRating = 1.75*ALOG10 (timberSpreadIndex) +
				// .32*ALOG10( BUO ) – 1.640
				fireLoadRating = 1.75
						* Math.log10(FireDangerRatings.timberSpreadIndex_)
						+ 0.32 * Math.log10(FireDangerRatings.buildupIndex_)
						- 1.640;

				if (fireLoadRating > 0)
					fireLoadRating = Math.pow(10.0, fireLoadRating);
			}
		}
		
		return fireLoadRating;
	}


	
}
