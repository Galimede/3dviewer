package tools;

public class Fonctions {
	public static double[][] multiplier(double[][] A, double[][] B){
		double[][] AB;
		int l, c;
		if (A[0].length != B.length) {
			return null;
		}
		if (A.length * A[0].length < B.length * B[0].length) {
			l = B.length;
			c = B[0].length;
		} else {
			l = A.length;
			c = A[0].length;
		}
		AB = new double[l][c];
		l = 0;
		for (int i = 0; i < A.length; i++) {
			c = 0;
			for (int n = 0; n < B[0].length; n++) {
				double calcul = 0;
				for (int m = 0; m < B.length; m++) {
					calcul += A[i][m] * B[m][n];
				}
				AB[l][c] =  (calcul/1);
				c++;
			}
			l++;
		}
		return AB;
	}


	public static double[][]translation3D(double [][]point,double[]vecteur){
		double [][]matV= {{1.0,0.0,0.0,vecteur[0]},{0.0,1.0,0.0,vecteur[1]},{0.0,0.0,1.0,vecteur[2]},{0.0,0.0,0.0,1.0}};
		return multiplier(matV,point);
	}

	/**
	 *  Retourne l'homothetie 3D de rapport k autour de l'origine
	 *  @param point
	 *  		Matrice des points
	 *  @param rapport 
	 *  		Rapport utilisé pour l'homothetie
	 *  @return Un tableau à deux dimensions représentant l'homothétie 3D   
	 */
	public static double[][] homothetie(double [][]point, double rapport){
		if(point == null) {
			System.out.println("Pas de points fournis");
			return null;
		}
		double[][] homothetie = {  
				{rapport,0,0,0},
				{0,rapport,0,0},
				{0,0,rapport,0},
				{0,0,0,1}
		};	
		return multiplier(homothetie,point);
	}

	/** 
	 * retourne la rotation autour de l'origine
	 * @param point
	 * 		  Matrice des points
	 * @param angleX
	 * 		  Angle de rotation de Y vers Z (En radians)
	 * @param angleY
	 * 		  Angle de rotation de Z vers X (En radians)
	 * @param angleZ
	 * 		  Angle de rotation de x vers Y (En radians)
	 * @return Un tableau à deux dimensions représentant la rotation sur un axe autour de l'origine
	 */
	public static double[][] rotation(double[][]point, double angleX, double angleY,  double angleZ) {
		double cosX =Math.cos(Math.toRadians(22.5));
		double sinX =Math.sin(Math.toRadians(22.5));
		double zero=0.0;
		double un=1.0;
		double[][] rX = { 
				{un,zero,zero,zero},
				{zero,cosX,-sinX,zero},
				{zero,sinX,cosX, zero},
				{zero,zero,zero,un}
		};
		double cosY = Math.cos(angleY);
		double sinY = Math.sin(angleY);
		double[][] rY = { 
				{cosY,zero,sinY,zero},
				{zero,un,zero,zero}, 
				{-sinY,zero,cosY,zero},
				{zero,zero,zero,un}
		};
		double cosZ = Math.cos(angleZ);
		double sinZ = Math.sin(angleZ);
		double[][] rZ = { 
				{cosZ,-sinZ,zero,zero},
				{sinZ, cosZ,zero,zero},
				{zero,zero,un,zero},
				{zero,zero,zero,un}
		};
		double[][] rotation = multiplier(rZ, rY);
		rotation = multiplier(rotation, rX);  
		return multiplier(rotation,point);
	}
	public static double[][]eclairage(){
		return null;
	}
	public static double[][]lissage(){
		return null;
	}
}

