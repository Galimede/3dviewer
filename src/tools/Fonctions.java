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
		if(vecteur==null||point==null) {
			System.out.println("L'un des deux parametres matrice ou vecteur est nul");
			return null;
		}
		if(vecteur.length!=3) {
			System.out.println("Vecteur non valide un vectuer a 3 dimensions est demandé");
			return null;
		}
		if(point.length!=4||point[0].length!=4) {
			System.out.println("Matrice non valide pour la 3D taille esperer 4x4 taille recus "+point.length+"x"+point[0].length);
			return null;
		}
		double [][]matV= {{1.0,0.0,0.0,vecteur[0]},{0.0,1.0,0.0,vecteur[1]},{0.0,0.0,1.0,vecteur[2]},{0.0,0.0,0.0,1.0}};
		return multiplier(point,matV);
	}

	/**
	 *  Retourne l'homothetie 3D de rapport k autour de l'origine
	 *  @param points
	 *  		Matrice des points
	 *  @param k 
	 *  		Rapport utilisé pour l'homothetie
	 *  @return Un tableau à deux dimensions représentant l'homothétie 3D   
	 */
	public static double[][] homothetie(double [][]points, double k){
		if(points == null) {
			System.out.println("Pas de points fournis");
			return null;
		}
		double[] origine = {0,0,0};
		points = translation3D(points,origine);
		double[][] res = points;
		double[][] homothetie = {  {k,0,0,0},
				{0,k,0,0},
				{0,0,k,0},
				{0,0,0,1}
		};	
		res = multiplier(res,homothetie);
		res = translation3D(res,origine);
		return res;
	}

	/** 
	 * retourne la rotation autour de l'origine
	 * @param points
	 * 		  Matrice des points
	 * @param angleX
	 * 		  Angle de rotation de Y vers Z (En radians)
	 * @param angleY
	 * 		  Angle de rotation de Z vers X (En radians)
	 * @param angleZ
	 * 		  Angle de rotation de x vers Y (En radians)
	 * @return Un tableau à deux dimensions représentant la rotation sur un axe autour de l'origine
	 */
	public static double[][] rotation(double [][]points, double angleX, double angleY,  double angleZ) {
		double[] vecteurToOrigine= {-points[0][3],-points[1][3],-points[2][3]};
		double[] vecteurToPoint= {points[0][3],points[1][3],points[2][3]};
		points=translation3D(points,vecteurToOrigine);
		double cosX = Math.cos(angleX);
		double sinX = Math.sin(angleX);
		double[][] rX = { 
				{1,0,    0,   0},
				{0,cosX,-sinX,0},
				{0,sinX,cosX, 0},
				{0,0,   0,    1}
		};
		double cosY = Math.cos(angleY);
		double sinY = Math.sin(angleY);
		double[][] rY = { 
				{cosY, 0,sinY,0},
				{0,    1,0   ,0}, 
				{-sinY,0,cosY,0},
				{0,    0,0,   1}
		};
		double cosZ = Math.cos(angleZ);
		double sinZ = Math.sin(angleZ);
		double[][] rZ = { 
				{cosZ,-sinZ, 0, 0},
				{sinZ, cosZ, 0, 0},
				{0,    0,    1, 0},
				{0,    0,     0, 1}
		};
		double[][] rotation = multiplier(rZ, rY);
		rotation = multiplier(rotation, rX);  
		return translation3D(multiplier(points,rotation),vecteurToPoint);
	}
}

