package cosc603.project1;

public class FireDanger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Start
//		Initialize subroutine
//		FFM=99
//		ADMF=99
//		DF=0
//		FLOAD=0
//		A(1)=-0.185900
//		A(2)=-0.85900
//		A(3)=-0.05966
//		A(4)=-0.07737
//		B(1)=30.0
//		B(2)=19.2
//		B(3)=13.8
//		B(4)=22.5
//		C(1)=4.5
//		C(2)=12.5
//		C(3)=27.5
//		D(1)=16.0
//		D(2)=10.0
//		D(3)=7.0
//		D(4)=5.0
//		D(5)=4.0
//		D(6)=3.0

//		If ISNOW > 0 Then
//			GRASS=0
//			TIMBER=0
//			If PRECIP > 0.1 then 
//		BUO=-50.*ALOG(1.-(1.-EXP (-BUI/50.))*EXP (-1.175*(PRECIP-.1)))
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
