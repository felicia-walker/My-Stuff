package pxc.puzzles;

import java.util.*;

public class TheOtherFamousCube {

	private enum Color {W,R,B,O,G,Y};
	private enum Direction {Clockwise, Counterclockwise, Normal, Flip};
	private enum Face {Top, Left, Front, Right, Back, Bottom};
	
	private static final int cm_NumberOfFaces = 6;
	private static final int cm_CubeSize = 3;

	private static Color[][][] m_Cube = new Color[cm_NumberOfFaces][cm_CubeSize][cm_CubeSize];
	
// --------------------------------------------------------------------------------------
	private static void InitCube() {
		for (int rowIndex = 0; rowIndex < cm_CubeSize; rowIndex++) {
			Arrays.fill(m_Cube[Face.Top.ordinal()][rowIndex],Color.R);
			Arrays.fill(m_Cube[Face.Left.ordinal()][rowIndex],Color.B);
			Arrays.fill(m_Cube[Face.Front.ordinal()][rowIndex],Color.W);
			Arrays.fill(m_Cube[Face.Right.ordinal()][rowIndex],Color.G);
			Arrays.fill(m_Cube[Face.Back.ordinal()][rowIndex],Color.Y);
			Arrays.fill(m_Cube[Face.Bottom.ordinal()][rowIndex],Color.O);
		}
	}
	
	private static void PrintCubeFace(int pFaceIndex) {
		
		System.out.println("Face " + (pFaceIndex+1) + ":");
		for (int rowIndex = 0; rowIndex < cm_CubeSize; rowIndex++) {
			for (int colIndex = 0; colIndex < cm_CubeSize; colIndex++) {
				System.out.print(m_Cube[pFaceIndex][rowIndex][colIndex].toString() + " ");
			}
				
			System.out.println();
		}
		
		System.out.println();
	}
	
	private static void PrintCube(){
		
		for (int faceIndex = 0; faceIndex < cm_NumberOfFaces; faceIndex++) 
			PrintCubeFace(faceIndex);
	}

// --------------------------------------------------------------------------------------
	
	private static void TempToBottom(int pDstRow,
									 Color[] pTempRow) {
		
		m_Cube[Face.Bottom.ordinal()][2 - pDstRow] = pTempRow.clone();

	}

	private static void TempToTop(int pDstRow,
			 					  Color[] pTempRow) {

		m_Cube[Face.Top.ordinal()][pDstRow][0] = pTempRow[2];
		m_Cube[Face.Top.ordinal()][pDstRow][1] = pTempRow[1];
		m_Cube[Face.Top.ordinal()][pDstRow][2] = pTempRow[0];

	}

	private static void TopToLeft(int pIndex) {
		
		m_Cube[Face.Left.ordinal()][0][pIndex] = m_Cube[Face.Top.ordinal()][pIndex][2];
		m_Cube[Face.Left.ordinal()][1][pIndex] = m_Cube[Face.Top.ordinal()][pIndex][1];
		m_Cube[Face.Left.ordinal()][2][pIndex] = m_Cube[Face.Top.ordinal()][pIndex][0];
	
	}

	private static void BottomToLeft(int pIndex) {
		
		m_Cube[Face.Left.ordinal()][0][2 - pIndex] = m_Cube[Face.Bottom.ordinal()][pIndex][0];
		m_Cube[Face.Left.ordinal()][1][2 - pIndex] = m_Cube[Face.Bottom.ordinal()][pIndex][1];
		m_Cube[Face.Left.ordinal()][2][2 - pIndex] = m_Cube[Face.Bottom.ordinal()][pIndex][2];
	
	}

	private static void TopToRight(int pIndex,
								   Direction pDirection) {
		
		if (pDirection == Direction.Clockwise) {
			m_Cube[Face.Right.ordinal()][0][2 - pIndex] = m_Cube[Face.Top.ordinal()][pIndex][2];
			m_Cube[Face.Right.ordinal()][1][2 - pIndex] = m_Cube[Face.Top.ordinal()][pIndex][1];
			m_Cube[Face.Right.ordinal()][2][2 - pIndex] = m_Cube[Face.Top.ordinal()][pIndex][0];
		
		} else {
			m_Cube[Face.Top.ordinal()][pIndex][0] = m_Cube[Face.Right.ordinal()][2][2 - pIndex];
			m_Cube[Face.Top.ordinal()][pIndex][1] = m_Cube[Face.Right.ordinal()][1][2 - pIndex];
			m_Cube[Face.Top.ordinal()][pIndex][2] = m_Cube[Face.Right.ordinal()][0][2 - pIndex];
			
		}
	
	}

	private static void BottomToRight(int pIndex,
					   				  Direction pDirection) {
		
		if (pDirection == Direction.Counterclockwise) {
			m_Cube[Face.Right.ordinal()][0][pIndex] = m_Cube[Face.Bottom.ordinal()][pIndex][2];
			m_Cube[Face.Right.ordinal()][1][pIndex] = m_Cube[Face.Bottom.ordinal()][pIndex][1];
			m_Cube[Face.Right.ordinal()][2][pIndex] = m_Cube[Face.Bottom.ordinal()][pIndex][0];
		
		} else {
			m_Cube[Face.Bottom.ordinal()][pIndex][0] = m_Cube[Face.Right.ordinal()][2][pIndex];
			m_Cube[Face.Bottom.ordinal()][pIndex][1] = m_Cube[Face.Right.ordinal()][1][pIndex];
			m_Cube[Face.Bottom.ordinal()][pIndex][2] = m_Cube[Face.Right.ordinal()][0][pIndex];
		
		}
		
}

//	 --------------------------------------------------------------------------------------
	private static void ColumnClone(int pSrcFace,
								    int pSrcCol,
								    int pDstFace,
								    int pDstCol,
								    Direction pFlip) {

		if (pFlip == Direction.Normal) {
			m_Cube[pDstFace][0][pDstCol] = m_Cube[pSrcFace][0][pSrcCol];
			m_Cube[pDstFace][1][pDstCol] = m_Cube[pSrcFace][1][pSrcCol];
			m_Cube[pDstFace][2][pDstCol] = m_Cube[pSrcFace][2][pSrcCol];
		} else {
			m_Cube[pDstFace][0][pDstCol] = m_Cube[pSrcFace][2][pSrcCol];
			m_Cube[pDstFace][1][pDstCol] = m_Cube[pSrcFace][1][pSrcCol];
			m_Cube[pDstFace][2][pDstCol] = m_Cube[pSrcFace][0][pSrcCol];			
		}
	}

	private static Color[] ColumnClone(int pSrcFace,
		    						   int pSrcCol,
		    						   Direction pFlip) {

		Color[] TmpCol = new Color[cm_CubeSize];
		
		if (pFlip == Direction.Normal) {
			TmpCol[0] = m_Cube[pSrcFace][0][pSrcCol];
			TmpCol[1] = m_Cube[pSrcFace][1][pSrcCol];
			TmpCol[2] = m_Cube[pSrcFace][2][pSrcCol];
		} else {
			TmpCol[0] = m_Cube[pSrcFace][2][pSrcCol];
			TmpCol[1] = m_Cube[pSrcFace][1][pSrcCol];
			TmpCol[2] = m_Cube[pSrcFace][0][pSrcCol];			
		}
		
		return TmpCol;
	}

	private static void ColumnClone(Color[] pTmpCol,
								    int pSrcFace,
								    int pSrcCol,
				   					Direction pFlip) {
	
		if (pFlip == Direction.Normal) {
			m_Cube[pSrcFace][0][pSrcCol] = pTmpCol[0];
			m_Cube[pSrcFace][1][pSrcCol] = pTmpCol[1];
			m_Cube[pSrcFace][2][pSrcCol] = pTmpCol[2];
		} else {
			m_Cube[pSrcFace][0][pSrcCol] = pTmpCol[2];
			m_Cube[pSrcFace][1][pSrcCol] = pTmpCol[1];
			m_Cube[pSrcFace][2][pSrcCol] = pTmpCol[1];
		}
	
	}
	
	private static void RotateFace(int pFaceIndex, 
								   Direction pDirection) {
		
		Color[][] TmpFace = new Color[cm_CubeSize][cm_CubeSize];
		
		if (pDirection == Direction.Clockwise) {
			TmpFace[0][0] = m_Cube[pFaceIndex][2][0];
			TmpFace[0][1] = m_Cube[pFaceIndex][1][0];
			TmpFace[0][2] = m_Cube[pFaceIndex][0][0];
			TmpFace[1][0] = m_Cube[pFaceIndex][2][1];
			TmpFace[1][1] = m_Cube[pFaceIndex][1][1];
			TmpFace[1][2] = m_Cube[pFaceIndex][0][1];
			TmpFace[2][0] = m_Cube[pFaceIndex][2][2];
			TmpFace[2][1] = m_Cube[pFaceIndex][1][2];
			TmpFace[2][2] = m_Cube[pFaceIndex][0][2];
						
		} else {
			TmpFace[0][0] = m_Cube[pFaceIndex][0][2];
			TmpFace[0][1] = m_Cube[pFaceIndex][1][2];
			TmpFace[0][2] = m_Cube[pFaceIndex][2][2];
			TmpFace[1][0] = m_Cube[pFaceIndex][0][1];
			TmpFace[1][1] = m_Cube[pFaceIndex][1][1];
			TmpFace[1][2] = m_Cube[pFaceIndex][2][1];
			TmpFace[2][0] = m_Cube[pFaceIndex][0][0];
			TmpFace[2][1] = m_Cube[pFaceIndex][1][0];
			TmpFace[2][2] = m_Cube[pFaceIndex][2][0];
		}
		
		m_Cube[pFaceIndex] = TmpFace;
	}
	
//	 --------------------------------------------------------------------------------------
	private static void F() {
		
		Color[] TmpColor = new Color[cm_CubeSize];
		TmpColor = ColumnClone(Face.Left.ordinal(),2,Direction.Normal);
		
		RotateFace(Face.Front.ordinal(),Direction.Clockwise);

		BottomToLeft(0);
		BottomToRight(0,Direction.Clockwise);
		TopToRight(2,Direction.Clockwise);
		TempToTop(2,TmpColor);

	}
	
	private static void FPrime() {
		
		Color[] TmpColor = new Color[cm_CubeSize];
		TmpColor = ColumnClone(Face.Left.ordinal(),2,Direction.Normal);
		
		RotateFace(Face.Front.ordinal(),Direction.Counterclockwise);

		TopToLeft(2);
		TopToRight(2,Direction.Counterclockwise);
		BottomToRight(0,Direction.Counterclockwise);
		TempToBottom(2,TmpColor);

	}
	
	private static void B() {
		
		Color[] TmpColor = new Color[cm_CubeSize];
		TmpColor = ColumnClone(Face.Left.ordinal(),0,Direction.Normal);
		
		RotateFace(Face.Back.ordinal(),Direction.Counterclockwise);

		TopToLeft(0);
		TopToRight(0,Direction.Counterclockwise);
		BottomToRight(2,Direction.Counterclockwise);
		TempToBottom(0,TmpColor);

	}
	
	private static void BPrime() {
		
		Color[] TmpColor = new Color[cm_CubeSize];
		TmpColor = ColumnClone(Face.Left.ordinal(),0,Direction.Normal);
		
		RotateFace(Face.Back.ordinal(),Direction.Clockwise);

		BottomToLeft(2);
		BottomToRight(2,Direction.Clockwise);
		TopToRight(0,Direction.Clockwise);
		TempToTop(0,TmpColor);

	}
	
	private static void L() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Front.ordinal(),0,Direction.Normal);
		
		RotateFace(Face.Left.ordinal(),Direction.Clockwise);

		ColumnClone(Face.Top.ordinal(),0,Face.Front.ordinal(),0,Direction.Normal);
		ColumnClone(Face.Back.ordinal(),2,Face.Top.ordinal(),0,Direction.Flip);
		ColumnClone(Face.Bottom.ordinal(),0,Face.Back.ordinal(),2,Direction.Flip);
		ColumnClone(TmpCol,Face.Bottom.ordinal(),0,Direction.Normal);
	}
	
	private static void LPrime() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Front.ordinal(),0,Direction.Normal);
		
		RotateFace(Face.Left.ordinal(),Direction.Counterclockwise);

		ColumnClone(Face.Bottom.ordinal(),0,Face.Front.ordinal(),0,Direction.Normal);
		ColumnClone(Face.Back.ordinal(),2,Face.Bottom.ordinal(),0,Direction.Flip);
		ColumnClone(Face.Top.ordinal(),0,Face.Back.ordinal(),2,Direction.Flip);
		ColumnClone(TmpCol,Face.Top.ordinal(),0,Direction.Normal);
	}
	
	private static void R() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Front.ordinal(),2,Direction.Normal);
		
		RotateFace(Face.Right.ordinal(),Direction.Clockwise);

		ColumnClone(Face.Bottom.ordinal(),2,Face.Front.ordinal(),2,Direction.Normal);
		ColumnClone(Face.Back.ordinal(),0,Face.Bottom.ordinal(),2,Direction.Flip);
		ColumnClone(Face.Top.ordinal(),2,Face.Back.ordinal(),0,Direction.Flip);
		ColumnClone(TmpCol,Face.Top.ordinal(),2,Direction.Normal);
	}
	
	private static void RPrime() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Front.ordinal(),2,Direction.Normal);
		
		RotateFace(Face.Right.ordinal(),Direction.Counterclockwise);

		ColumnClone(Face.Top.ordinal(),2,Face.Front.ordinal(),2,Direction.Normal);
		ColumnClone(Face.Back.ordinal(),0,Face.Top.ordinal(),2,Direction.Flip);
		ColumnClone(Face.Bottom.ordinal(),2,Face.Back.ordinal(),0,Direction.Flip);
		ColumnClone(TmpCol,Face.Bottom.ordinal(),2,Direction.Normal);
	}
	
	private static void D() {
		
		Color[] TmpRow = new Color[cm_CubeSize];
		TmpRow = m_Cube[Face.Left.ordinal()][2].clone();
		
		RotateFace(Face.Bottom.ordinal(),Direction.Clockwise);

		m_Cube[Face.Left.ordinal()][2] = m_Cube[Face.Back.ordinal()][2].clone();
		m_Cube[Face.Back.ordinal()][2] = m_Cube[Face.Right.ordinal()][2].clone();
		m_Cube[Face.Right.ordinal()][2] = m_Cube[Face.Front.ordinal()][2].clone();
		m_Cube[Face.Front.ordinal()][2] = TmpRow;
	}
	
	private static void DPrime() {
		
		Color[] TmpRow = new Color[cm_CubeSize];
		TmpRow = m_Cube[Face.Left.ordinal()][2].clone();
		
		RotateFace(Face.Top.ordinal(),Direction.Counterclockwise);

		m_Cube[Face.Left.ordinal()][2] = m_Cube[Face.Front.ordinal()][2].clone();
		m_Cube[Face.Front.ordinal()][2] = m_Cube[Face.Right.ordinal()][2].clone();
		m_Cube[Face.Right.ordinal()][2] = m_Cube[Face.Back.ordinal()][2].clone();
		m_Cube[Face.Back.ordinal()][2] = TmpRow;
	}

	private static void U() {
		
		Color[] TmpRow = new Color[cm_CubeSize];
		TmpRow = m_Cube[Face.Left.ordinal()][0].clone();
		
		RotateFace(Face.Top.ordinal(),Direction.Clockwise);

		m_Cube[Face.Left.ordinal()][0] = m_Cube[Face.Front.ordinal()][0].clone();
		m_Cube[Face.Front.ordinal()][0] = m_Cube[Face.Right.ordinal()][0].clone();
		m_Cube[Face.Right.ordinal()][0] = m_Cube[Face.Back.ordinal()][0].clone();
		m_Cube[Face.Back.ordinal()][0] = TmpRow;
	}

	private static void UPrime() {
		
		Color[] TmpRow = new Color[cm_CubeSize];
		TmpRow = m_Cube[Face.Left.ordinal()][0].clone();
		
		RotateFace(Face.Top.ordinal(),Direction.Counterclockwise);

		m_Cube[Face.Left.ordinal()][0] = m_Cube[Face.Back.ordinal()][0].clone();
		m_Cube[Face.Back.ordinal()][0] = m_Cube[Face.Right.ordinal()][0].clone();
		m_Cube[Face.Right.ordinal()][0] = m_Cube[Face.Front.ordinal()][0].clone();
		m_Cube[Face.Front.ordinal()][0] = TmpRow;
	}

	private static void X() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Left.ordinal(),1,Direction.Normal);
		
		FPrime();

		TopToLeft(1);
		TopToRight(1,Direction.Counterclockwise);
		BottomToRight(1,Direction.Counterclockwise);
		TempToBottom(1,TmpCol);
		
		B();
	}
	
	private static void XPrime() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Left.ordinal(),1,Direction.Normal);
		
		F();

		BottomToLeft(1);
		BottomToRight(1,Direction.Clockwise);
		TopToRight(1,Direction.Clockwise);
		TempToTop(1,TmpCol);
		
		BPrime();
	}

	private static void Y() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Front.ordinal(),1,Direction.Normal);
		
		L();

		ColumnClone(Face.Top.ordinal(),1,Face.Front.ordinal(),1,Direction.Normal);
		ColumnClone(Face.Back.ordinal(),1,Face.Top.ordinal(),1,Direction.Flip);
		ColumnClone(Face.Bottom.ordinal(),1,Face.Back.ordinal(),1,Direction.Flip);
		ColumnClone(TmpCol,Face.Bottom.ordinal(),1,Direction.Normal);
		
		RPrime();
	}

	private static void YPrime() {
		
		Color[] TmpCol = new Color[cm_CubeSize];
		TmpCol = ColumnClone(Face.Front.ordinal(),1,Direction.Normal);
		
		LPrime();

		ColumnClone(Face.Bottom.ordinal(),1,Face.Front.ordinal(),1,Direction.Normal);
		ColumnClone(Face.Back.ordinal(),1,Face.Bottom.ordinal(),1,Direction.Flip);
		ColumnClone(Face.Top.ordinal(),1,Face.Back.ordinal(),1,Direction.Flip);
		ColumnClone(TmpCol,Face.Top.ordinal(),1,Direction.Normal);
		
		R();
	}
	
	private static void Z() {
		
		Color[] TmpRow = new Color[cm_CubeSize];
		TmpRow = m_Cube[Face.Left.ordinal()][1].clone();
		
		D();

		m_Cube[Face.Left.ordinal()][1] = m_Cube[Face.Back.ordinal()][1].clone();
		m_Cube[Face.Back.ordinal()][1] = m_Cube[Face.Right.ordinal()][1].clone();
		m_Cube[Face.Right.ordinal()][1] = m_Cube[Face.Front.ordinal()][1].clone();
		m_Cube[Face.Front.ordinal()][1] = TmpRow;
		
		UPrime();
	}

	private static void ZPrime() {
		
		Color[] TmpRow = new Color[cm_CubeSize];
		TmpRow = m_Cube[Face.Left.ordinal()][1].clone();
		
		DPrime();

		m_Cube[Face.Left.ordinal()][1] = m_Cube[Face.Front.ordinal()][1].clone();
		m_Cube[Face.Front.ordinal()][1] = m_Cube[Face.Right.ordinal()][1].clone();
		m_Cube[Face.Right.ordinal()][1] = m_Cube[Face.Back.ordinal()][1].clone();
		m_Cube[Face.Back.ordinal()][1] = TmpRow;
		
		U();
	}
	
	// --------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
			String strMoves1 = "ZZuBURBBr";
			String strMoves2 = "RBBrubDLLUUDDRRuD";
			String strMoves3 = "UbulBBLdRDr";
			String strMoves4 = "RDrRdrDlBBLUBuX";
			String strMoves5 = "YYRRLL";
			String strMoves6 = "lUURZZRdrDzuDRdUZr";
			String strMoves = strMoves1 + strMoves2+ strMoves3 + strMoves4 + strMoves5 + strMoves6;
			
			InitCube();
					
			for (char chrMove : strMoves.toCharArray()) {
				switch (chrMove) {
					case 'Y' : Y(); break;
					case 'y' : YPrime(); break;
					case 'Z' : Z(); break;
					case 'z' : ZPrime(); break;
					case 'L' : L(); break;
					case 'l' : LPrime(); break;
					case 'R' : R(); break;
					case 'r' : RPrime(); break;
					case 'U' : U(); break;
					case 'u' : UPrime(); break;
					case 'D' : D(); break;
					case 'd' : DPrime(); break;
					case 'B' : B(); break;
					case 'b' : BPrime(); break;
					case 'F' : F(); break;
					case 'f' : FPrime(); break;
					case 'X' : X(); break;
					case 'x' : XPrime(); break;
					default : break;
				}
			}
			
			PrintCube();
	}

}
