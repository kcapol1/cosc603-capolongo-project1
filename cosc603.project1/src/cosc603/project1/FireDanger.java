package cosc603.project1;

public class FireDanger {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Initialize
		double DRY = 0;
		double WET = 0;
		int ISNOW = 0;
		double PRECIP = 0.1;
		double WIND = 14;
		double BUO = 3;
		double IHERB = 1;
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
				BUO = adjustBuldupIndex(BUO,PRECIP);		//				BUO=-50.*ALOG(1.-(1.-EXP (-BUO/50.))*EXP (-1.175*(PRECIP-.1)))
															//				If BUO < 0 then BUI = 0
			}												//			End If
		} 
		else												//		Else
		{							
															//			DIF=DRY-WET
															//			For I = 1 to 3 Do


															//				If DIF <= C(I) Then

															//					Exit for
															//				End If
															//			Next
															//			FFM=B(I)*EXP (A(I)*DIF) 	

	        												//			For I = 1 to 6 Do
															//				If FFM > D(I) Then
															//					DF=I-1
															//					Exit for
															//				End If
															//			Next
											//			If FFM < 1 Then
											//				FFM = 1
											//			End If
											//			FFM = FFM + (IHERB-1) *5
											//			If PRECIP > 0.1 then
											//				BUO=-50.*ALOG(1.-(1.-EXP (-BUI/50.))*EXP (-1.175*(PRECIP-.1)))
											//				If BUO < 0 Then
											//					BUO = 0
											//				End If
											//			End If
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
		}									//	End If


        System.out.println("ISNOW =  " + ISNOW + "\tGRASS =  " + GRASS + "\tTIMBER =  " + TIMBER + "\tGRASS =  " + GRASS + "\tFFM =  " + FFM + "\tBUO =  " + BUO);
        System.out.println();
        
	}


	public static double adjustBuldupIndex(double buldupIndex,double precipitation) {
//		BUO=-50.*ALOG(    1.-(1. - EXP(-BUO/50.) ) * EXP(-1.175*(PRECIP-.1) ) )
//		If BUO < 0 then BUI = 0		
		double value;
		value = -50.0 * Math.log(1.0 - (1.0 - Math.exp(-1.0 * (buldupIndex/50.0)) * Math.exp( -1.175 * (precipitation - 0.1))));
		if(value < 0) {
			value = 0;
		}
		return value;
	}
	
	
}