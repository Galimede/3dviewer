package tools;

public class Math {
	public static double[][] multiplier(double[][] MA, double[][] MB){
		double[][] MC;
		int l, c;
		if (MA.length * MA[0].length < MB.length * MB[0].length) {
			l = MB.length;
			c = MB[0].length;
		} else {
			l = MA.length;
			c = MA[0].length;
		}
		MC = new double[l][c];
		l = 0;
		for (int i = 0; i < MA.length; i++) {
			c = 0;
			for (int n = 0; n < MB[0].length; n++) {
				double calcul = 0;
				for (int m = 0; m < MB.length; m++) {
					calcul += MA[i][m] * MB[m][n];
				}
				MC[l][c] =  (calcul/1);
				c++;
			}
			l++;
		}
		return MC;
	}


	public static double[][]translation3D(double [][]point,double[]vecteur){
		if(vecteur==null||point==null) {
			System.out.println("L'un des deux parametres matrice ou vecteur est nul");
			return null;
		}
		if(vecteur.length!=3) {
			System.out.println("Vecteur non valide ");
			return null;
		}
		if(point.length!=4||point[0].length!=4) {
			System.out.println("Matrice non valide pour la 3D taille esperer 4x4 taille recus "+point.length+"x"+point[0].length);
			return null;
		}
		double [][]matV= {{1,0,0,vecteur[0]},{0,1,0,vecteur[1]},{0,0,1,vecteur[2]},{0,0,0,1}};
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
		double[][] rX = { {0} };
		double[][] rY = { {0} };
		double[][] rZ = { {0} };
		double[][] rotation = multiplier(rZ, rY);
		rotation = multiplier(rotation, rX);
		return rotation;
	}
}

