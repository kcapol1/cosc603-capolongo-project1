package cosc603.project1;

public class FireDanger {

	private	static double buildupIndex = 3.0;
	private	static double fineFuelMoisture = 99.0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Initialize
		double DRY = 78.0;
		double WET = 50.0;
//		int ISNOW = 1;
  		int ISNOW = 0;
//		double PRECIP = 0.5;
		double PRECIP = 0.1;
		double WIND = 14;
		double IHERB = 1;	// The current herb state of the district
//		double IHERB = 2;
//		double IHERB = 3;
		double DF = 0;
		double FFM = 99;
		double ADFM = 99;
		double GRASS = 5;
		double TIMBER = 10;
		double FLOAD = 0;

		// Check if snow is present

		if(ISNOW > 0)
		{ 	/* There is snow on the ground.  Set the Timber and Grass indexes to zero (0)  */
			GRASS = 0;
			TIMBER = 0;
			
	        // Adjust Buildup Index for precipitation before adding to Drying Factor
			if(PRECIP > 0.1) 
			{	
            	// Adjust Buildup Index
				adjustBuldupIndex(PRECIP);
			}
		} 
		else
		{ 	/* No show on the ground so compute spread indexes and fire load */

			// Calculate Fine Fuel Moisture
			computeFineFuelMoisture(DRY,WET);

            // Find Drying Factor in table
	        DF = findDryingFactor();

	        // Add five (5) percent Fine Fuel Moisture for each Herb State greater than one
	        fineFuelMoisture = fineFuelMoisture + (IHERB - 1.0) * 5.0;				
		
            // Set Fine Fuel Moisture to one (1) if less than one
            if(fineFuelMoisture <= 1)
	        {
            	fineFuelMoisture = 1;
	        }
            
            // Adjust Buildup Index for precipitation before adding to Drying Factor
            if(PRECIP > 0.1)
            {				
            	// Adjust Buildup Index
				adjustBuldupIndex(PRECIP);
            }
            
            // Add Drying Factor to Buildup Index
            buildupIndex = buildupIndex + DF;

            // Calculate Adjusted Fuel Moisture for heavy fuels
            ADFM = 0.9 * fineFuelMoisture + 0.5 + 9.5 * Math.exp(-buildupIndex/50.0);
            
            // Calculate Grass Spread Index
            GRASS = computeSpreadIndex(WIND,fineFuelMoisture);

            // Calculate Timber Spread Index
            TIMBER = computeSpreadIndex(WIND,ADFM);
            
            // Calculate Fire Danger Rating
            FLOAD = computeFireDangerIndex(TIMBER);
		}


        System.out.println();
        System.out.format("Fine Fuel Moisture = %-10.3f%n",fineFuelMoisture);
        System.out.format("Adjusted Fuel Moisture = %-10.3f%n",ADFM);
        System.out.format("Drying Factor = %-10.3f%n",DF);
        System.out.format("Buildup Index = %-10.3f%n",buildupIndex);
        System.out.format("Grass Spread Index = %-10.3f%n",GRASS);
        System.out.format("Timber Spread Index = %-10.3f%n",TIMBER);
        System.out.format("Fire Load Rating = %-10.3f%n",FLOAD);
        
	}


	private static void adjustBuldupIndex(double precipitation) {
		//		BUO=-50.*ALOG(    1.-(1. - EXP(-BUO/50.) ) * EXP(-1.175*(PRECIP-.1) ) )
		buildupIndex = -50.0 * Math.log(1.0 - (1.0 - Math.exp(-1.0 * (buildupIndex/50.0)) * Math.exp( -1.175 * (precipitation - 0.1))));
		//		If BUO < 0 then BUI = 0		
		if(buildupIndex < 0) {
			buildupIndex = 0;
		}
	}
	
	private static void computeFineFuelMoisture(double dryBulbTemp,double wetBulbTemp) {
		double diff = 0;
		int tempInt = 3;
		double[] A = new double[] {-0.185900, -0.85900, -0.05966, -0.07737};
		double[] B = new double[] {30.0, 19.2, 13.8, 22.5};
		double[] rangeDrytoWet = new double[] {4.5, 12.5, 27.5};

		diff=dryBulbTemp-wetBulbTemp;						//			DIF=DRY-WET

		for(int i = 0; i < 3; i++) 							//			For I = 1 to 3 Do
        {
        	if(diff <= rangeDrytoWet[i])					//				If DIF <= C(I) Then
        	{
            	tempInt = i;
        		break;										//					Exit for
        	}												//				End If
        }													//			Next

		fineFuelMoisture = B[tempInt] * Math.exp(A[tempInt] * diff);	//		FFM=B(I)*EXP (A(I)*DIF) 	

        if(fineFuelMoisture < 1)										//			If FFM < 1 Then
        {
        	fineFuelMoisture = 1;										//				FFM = 1
        }													//			End If

	}
	
	private static double findDryingFactor() {
		double value = 0;
		int tempInt = 5;
		double[] D = new double[] {16.0, 10.0, 7.0, 5.0, 4.0, 3.0};

        for(int i = 0; i < 6; i++)
        {
        	if(fineFuelMoisture > D[i])
        	{
        		tempInt = i - 1;
        		break;
        	}
        }

        value = tempInt;	 	

        return value;
	}
	
	private static double computeSpreadIndex(double wind,double fuelMoisture) {
		double value = 0;
		
        // Check if Fuel Moisture is greater than thirty percent
		if(fuelMoisture < 30 )
		{
			if(wind < 14 )
			{
				value = .01312*(wind+6.) * Math.pow(33.-fuelMoisture,1.65) -3.0;
			}
			else
			{
				value = .00918*(wind+14.) * Math.pow(33.-fuelMoisture,1.65) -3.0;
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
	
	public static double computeFireDangerIndex(double timberSpreadIndex) {
		double value = 0;
		// Check if either Timber Spread Index or Buildup Index are zero
		if((timberSpreadIndex > 0) && (buildupIndex > 0))
        {
    		//				FLOAD = 1.75*ALOG10 (TIMBER) + .32*ALOG10( BUO ) – 1.640
        	value = 1.75 * Math.log10(timberSpreadIndex) + 0.32 * Math.log10(buildupIndex ) - 1.640;	
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