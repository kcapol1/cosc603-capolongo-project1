package cosc603.project1;

public class FireDanger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Initialize
		int ISNOW = 0;
		double PRECIP = 0.1;
		double DRY = 0;
		double WET = 0;
		double WIND = 14;
		double IHERB = 0;
		double BUO = 3;
		double FFM = 99;
		double ADMF = 99;
		double DF = 0;
		double FLOAD = 0;

		// declares an array of doubles
		double[] A;
		double[] B;
		double[] C;
		double[] D;

        // allocates memory for 10 doubles
        A = new double[4];
        B = new double[4];
        C = new double[3];
        D = new double[6];
           
        // initialize first element
        A[0] = -0.185900;
        // initialize second element
        A[1] = -0.85900;
        // and so forth
        A[2] = -0.05966;
        A[3] = -0.07737;
        B[0] = 30.0;
        B[1] = 19.2;
        B[2] = 13.8;
        B[3] = 22.5;
        C[0] = 4.5;
        C[1] = 12.5;
        C[2] = 27.5;

		D[0]=16.0;
		D[1]=10.0;
		D[2]=7.0;
		D[3]=5.0;
		D[4]=4.0;
		D[5]=3.0;

//		If ISNOW > 0 Then
//			GRASS=0
//			TIMBER=0
//			If PRECIP > 0.1 then 
//		BUO=-50.*ALOG(1.-(1.-EXP (-BUO/50.))*EXP (-1.175*(PRECIP-.1)))
//		If BUO < 0 then BUI = 0
//			End If
//		Else
//			DIF-DRY-WET
//			For I = 1 to 3 Do
//				If DIF <= C(I) Then
//		Exit for
//		End If
//			Next
//			FFM=B(I)*EXP (A(I)*DIF) 	
//			For I = 1 to 6 Do
//				If FFM > D(I) Then
//					DF=I-1
//					Exit for
//				End If
//			Next
//		If FFM < 1 Then
//			FFM = 1
//		End If
//		FFM = FFM + (IHERB-1) *5
//			If PRECIP > 0.1 then
//		BUO=-50.*ALOG(1.-(1.-EXP (-BUI/50.))*EXP (-1.175*(PRECIP-.1)))
//			If BUO < 0 Then
//				BUO = 0
//			End If
//			BUO = BUO + DF
//		End If
//		ADFM = .9*FFM + .5 + 9.5*EXP ( -BUO/50.)
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
//		End If
//				Else
//					GRASS =1
//					TIMBER=1
//				End If
//			End If
//			
//
//			If TIMBER > 0 Then
//				If BUO > 0 Then
//					FLOAD = 1.75*ALOG10 (TIMBER ) + .32*ALOG10( BUO ) – 1.640
//					If FLOAD < 0 Then
//						FLOAD = 0
//					End If
//					FLOAD = 10. ** FLOAD
//		End If

	}

}
