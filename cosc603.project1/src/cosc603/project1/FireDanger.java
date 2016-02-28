package cosc603.project1;

/**
 * @author Ken Capolongo
 *
 */

public class FireDanger {

	
	
	/**
	 * Inputs
	 */
	private static double dryBulbTemperature_ = 78.0;
	private	static double buildupIndex_ = 3.0;
	private	static double fineFuelMoisture_ = 99.0;
	private	static double adjustedFuelMoisture_ = 99.0;
	private	static double precipitation_ = 0.1;
	private	static double districtHerbState_ = 1;	// The current herb state of the district
	private static boolean isSnowPresent_ = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Initialize
		double wetBulbTemperature = 50.0;
		double windSpeed = 14;
		double dryingFactor = 0;
		double grassSpreadIndex = 5;
		double timberSpreadIndex = 10;
		double fireLoadRating = 0;

		// Check if snow is present

		if(isSnowPresent_)
		{ 	/* There is snow on the ground.  Set the Timber and Grass indexes to zero (0)  */
			grassSpreadIndex = 0;
			timberSpreadIndex = 0;
			
	        // Adjust Buildup Index for precipitation_
			adjustBuldupIndex();
		} 
		else
		{ 	/* No show on the ground so compute spread indexes and fire load */

			// Calculate Fine Fuel Moisture
			computeFineFuelMoisture(dryBulbTemperature_,wetBulbTemperature);

            // Find Drying Factor in table
	        dryingFactor = findDryingFactor();

	        // Add five (5) percent Fine Fuel Moisture for each Herb State greater than one
	        fineFuelMoisture_ = fineFuelMoisture_ + (districtHerbState_ - 1.0) * 5.0;				
		
            // Set Fine Fuel Moisture to one (1) if less than one
            if(fineFuelMoisture_ <= 1)
	        {
            	fineFuelMoisture_ = 1;
	        }
            
            // Adjust Buildup Index for precipitation_ before adding to Drying Factor
			adjustBuldupIndex();
            
            // Add Drying Factor to Buildup Index
            buildupIndex_ = buildupIndex_ + dryingFactor;

            // Calculate Adjusted Fuel Moisture for heavy fuels
            adjustedFuelMoisture_ = 0.9 * fineFuelMoisture_ + 0.5 + 9.5 * Math.exp(-buildupIndex_/50.0);
            
            // Calculate Grass Spread Index
            grassSpreadIndex = computeSpreadIndex(windSpeed,fineFuelMoisture_);

            // Calculate Timber Spread Index
            timberSpreadIndex = computeSpreadIndex(windSpeed,adjustedFuelMoisture_);
            
            // Calculate Fire Danger Rating
            fireLoadRating = computeFireDangerIndex(timberSpreadIndex);
		}


        System.out.println();
        System.out.format("Fine Fuel Moisture = %-10.3f%n",fineFuelMoisture_);
        System.out.format("Adjusted Fuel Moisture = %-10.3f%n",adjustedFuelMoisture_);
        System.out.format("Drying Factor = %-10.3f%n",dryingFactor);
        System.out.format("Buildup Index = %-10.3f%n",buildupIndex_);
        System.out.format("Grass Spread Index = %-10.3f%n",grassSpreadIndex);
        System.out.format("Timber Spread Index = %-10.3f%n",timberSpreadIndex);
        System.out.format("Fire Load Rating = %-10.3f%n",fireLoadRating);
        
	}


	private static void adjustBuldupIndex() {
		// Adjust Buildup Index for precipitation_
       if(precipitation_ > 0.1)
        {						//		BUO=-50.*ALOG(    1.-(1. - EXP(-BUO/50.) ) * EXP(-1.175*(precipitation_-.1) ) )
        	buildupIndex_ = -50.0 * Math.log(1.0 - (1.0 - Math.exp(-1.0 * (buildupIndex_/50.0)) * Math.exp( -1.175 * (precipitation_ - 0.1))));
        	//		If BUO < 0 then BUI = 0		
        	if(buildupIndex_ < 0) {
        		buildupIndex_ = 0;
        	}
        }
	}
	
	private static void computeFineFuelMoisture(double dryBulbTemp,double wetBulbTemp) {
		double diff = 0;
		int tempInt = 3;
		double[] A = new double[] {-0.185900, -0.85900, -0.05966, -0.07737};
		double[] B = new double[] {30.0, 19.2, 13.8, 22.5};
		double[] rangeDrytoWet = new double[] {4.5, 12.5, 27.5};

		diff = dryBulbTemp-wetBulbTemp;						//			DIF=dryBulbTemperature-wetBulbTemperature

		for(int i = 0; i < 3; i++) 							//			For I = 1 to 3 Do
        {
        	if(diff <= rangeDrytoWet[i])					//				If DIF <= C(I) Then
        	{
            	tempInt = i;
        		break;										//					Exit for
        	}												//				End If
        }													//			Next

		fineFuelMoisture_ = B[tempInt] * Math.exp(A[tempInt] * diff);	//		FFM=B(I)*EXP (A(I)*DIF) 	

        if(fineFuelMoisture_ < 1)										//			If FFM < 1 Then
        {
        	fineFuelMoisture_ = 1;										//				FFM = 1
        }													//			End If

	}
	
	private static double findDryingFactor() {
		double value = 0;
		int tempInt = 5;
		double[] D = new double[] {16.0, 10.0, 7.0, 5.0, 4.0, 3.0};

        for(int i = 0; i < 6; i++)
        {
        	if(fineFuelMoisture_ > D[i])
        	{
        		tempInt = i - 1;
        		break;
        	}
        }

        value = tempInt;	 	

        return value;
	}
	
	private static double computeSpreadIndex(double windSpeed,double fuelMoisture) {
		double value = 0;
		
        // Check if Fuel Moisture is greater than thirty percent
		if(fuelMoisture < 30 )
		{
			if(windSpeed < 14 )
			{
				value = .01312*(windSpeed+6.) * Math.pow(33.-fuelMoisture,1.65) -3.0;
			}
			else
			{
				value = .00918*(windSpeed+14.) * Math.pow(33.-fuelMoisture,1.65) -3.0;
			}
			if(value < 1 )
			{
				value = 1;
			}
			if(value > 99 )
			{
				value = 99;
			}
		}
		else
		{
			value = 1;
		}
		
		return value;
	}
	
	private static double computeFireDangerIndex(double timberSpreadIndex) {
		double value = 0;
		// Check if either Timber Spread Index or Buildup Index are zero
		if((timberSpreadIndex > 0) && (buildupIndex_ > 0))
        {
    		//				fireLoadRating = 1.75*ALOG10 (timberSpreadIndex) + .32*ALOG10( BUO ) – 1.640
        	value = 1.75 * Math.log10(timberSpreadIndex) + 0.32 * Math.log10(buildupIndex_ ) - 1.640;	
        	if(value > 0 )
        	{
        		value =	Math.pow(10.0,value);
        	}	
        	else
        	{
        		value = 0;
        	}        	
        }
		return value;
	}

}