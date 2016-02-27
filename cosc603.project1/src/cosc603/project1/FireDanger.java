package cosc603.project1;

public class FireDanger {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Initialize
		double DRY = 78.0;
		double WET = 50.0;
//		int ISNOW = 1;
  		int ISNOW = 0;
		double PRECIP = 0.5;
//		double PRECIP = 0.1;
		double WIND = 14;
		double BUO = 3;
		double IHERB = 1;	// The current herb state of the district
//		double IHERB = 2;
//		double IHERB = 3;
		double DF = 0;
		double FFM = 99;
		double ADMF = 99;
		double GRASS = 0;
		double TIMBER = 0;
		double FLOAD = 0;

		if(ISNOW > 0)										//		If ISNOW > 0 Then
		{	
			GRASS = 0;										//			GRASS=0
			TIMBER = 0;										//			TIMBER=0
			if(PRECIP > 0.1)								//			If PRECIP > 0.1 then 
			{	
															//				BUO=-50.*ALOG(1.-(1.-EXP (-BUO/50.))*EXP (-1.175*(PRECIP-.1)))
				BUO = adjustBuldupIndex(BUO,PRECIP);		//				If BUO < 0 then BUI = 0

			}												//			End If
		} 
		else												//		Else
		{/* No show on the ground so compute spread indexes and fire load */

			// Calculate Fine Fuel Moisture
			FFM = computeFineFuelMoisture(DRY,WET);

            // Find Drying Factor in table
	        DF = findDryingFactor(FFM);

	        // Add five (5) percent Fine Fuel Moisture for each Herb State greater than one
	        FFM = FFM + (IHERB - 1.0) * 5.0;				
		
            // Set Fine Fuel Moisture to one (1) if less than one
            if(FFM <= 1)
	        {
	        	FFM = 1;
	        }
	        
            if(PRECIP > 0.1)								//			If PRECIP > 0.1 then
            {				
            												//				BUO=-50.*ALOG(1.-(1.-EXP (-BUI/50.))*EXP (-1.175*(PRECIP-.1)))
															//				If BUO < 0 Then
															//					BUO = 0
            	BUO = adjustBuldupIndex(BUO,PRECIP);		//				End If
            }												//			End If
            												
            												//			BUO = BUO + DF
															//			ADFM = .9*FFM + .5 + 9.5*EXP ( -BUO/50.)
															//
															//			If ADFM < 30 Then 
															//				If WIND < 14 Then 			(Line 19)
															//					TIMBER = .01312*(WIND+6.) * (33.-ADFM**1.65 – 3.
															//					GRASS = .01312*(WIND+6.) * (33.-FFM**1.65 – 3.
															//					If TIMBER < 1 Then
															//						TIMBER=1
															//						If GRASS < 1 Then
															//							GRASS =1
															//						End If
															//					End If
															//				Else
															//					TIMBER = .00918*(WIND+14.) * (33.-ADFM)**1.65 – 3.
															//					GRASS = .00918*(WIND+14.) * (33.-FFM)**1.65 – 3.
															//					If GRASS > 99 Then
															//						GRASS =99
															//					End If
															//					If TIMBER > 99 Then
															//						TIMBER =99
															//					End If
															//				End If
															//			Else
															//				If FFM < 30 Then			(Line 16)
															//					TIMBER=1			(Line 18)
															//					If WIND < 14 Then
															//						GRASS = .01312*(WIND+6.) * (33.-FFM)**1.65 – 3.
															//						If TIMBER < 1 Then
															//							TIMBER=1
															//						End If
															//						If GRASS < 1 Then
															//							GRASS =1
															//						End If
															//					Else
															//						GRASS = .00918*(WIND+14.) * (33.-FFM)**1.65 – 3.
															//						If GRASS > 99 Then
															//							GRASS =99
															//						End If
															//						If TIMBER > 99 Then
															//							TIMBER =99
															//						End If
															//					End If
															//				Else
															//					GRASS =1
															//					TIMBER=1
															//				End If
															//			End If
															//			
															//
															//		If TIMBER > 0 Then
															//			If BUO > 0 Then
															//				FLOAD = 1.75*ALOG10 (TIMBER) + .32*ALOG10( BUO ) – 1.640
															//				If FLOAD <= 0 Then
															//					FLOAD = 0
															//				Else
															//					FLOAD = 10. ** FLOAD
															//				End If
															//			End If
		}													//	End If


        System.out.println();
        System.out.format("Fine Fuel Moisture = %-10.3f%n",FFM);
        System.out.format("Drying Factor = %-10.3f%n",DF);
        System.out.format("Buildup Index = %-10.3f%n",BUO);
        
	}


	public static double adjustBuldupIndex(double buldupIndex,double precipitation) {
		double value;

		//		BUO=-50.*ALOG(    1.-(1. - EXP(-BUO/50.) ) * EXP(-1.175*(PRECIP-.1) ) )
		value = -50.0 * Math.log(1.0 - (1.0 - Math.exp(-1.0 * (buldupIndex/50.0)) * Math.exp( -1.175 * (precipitation - 0.1))));

		//		If BUO < 0 then BUI = 0		
		if(value < 0) {
			value = 0;
		}
		return value;
	}
	
	public static double computeFineFuelMoisture(double dryBulbTemp,double wetBulbTemp) {
		double value = 0;
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

        value = B[tempInt] * Math.exp(A[tempInt] * diff);	//		FFM=B(I)*EXP (A(I)*DIF) 	

        if(value < 1)										//			If FFM < 1 Then
        {
        	value = 1;										//				FFM = 1
        }													//			End If

        return value;
	}
	
	public static double findDryingFactor(double fineFuelMoisture) {
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
}