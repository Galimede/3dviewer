package tools;

import java.util.ArrayList;

import main.Face;
import main.Point;

public class Fonctions {
	/**
	 * Fonction qui multiplie deux matrices entres elles
	 * @param matriceA La premiere des matrices que l'on souhaite multiplier
	 * @param matriceB La deuxieeme matrice que l'on souhaite multiplier
	 * @return le produit de la matriceA par la matriceB
	 */
	public static double[][] multiplier(double[][] matriceA, double[][] matriceB){
		double[][] produitAB;
		int lignes, colonnes;
		if (matriceA==null||matriceB==null||matriceA[0].length != matriceB.length) {
			System.out.println("Erreur multiplication");
			return null;
		}
		if (matriceA.length * matriceA[0].length < matriceB.length * matriceB[0].length) {
			lignes = matriceB.length;
			colonnes = matriceB[0].length;
		} else {
			lignes = matriceA.length;
			colonnes = matriceA[0].length;
		}
		produitAB = new double[lignes][colonnes];
		lignes = 0;
		for (int i = 0; i < matriceA.length; i++) {
			colonnes = 0;
			for (int n = 0; n < matriceB[0].length; n++) {
				double calcul = 0;
				for (int m = 0; m < matriceB.length; m++) {
					calcul += matriceA[i][m] * matriceB[m][n];
				}
				produitAB[lignes][colonnes] =  (calcul/1);
				colonnes++;
			}
			lignes++;
		}
		return produitAB;
	}

	/**
	 * Fonction qui realise la translation d'un point par un vecteur en 3D
	 * @param point La matrice du point de taille 4x1
	 * @param vecteur La matrice du vecteur representant la traslation de taille 3x1
	 * @return la nouvelle matrice du point apres la translation 
	 */
	public static double[][]translation3D(double [][]point,double[]vecteur){
		if(point==null||vecteur==null||point.length!=4||point[0].length!=1||vecteur.length!=3) {
			System.out.println("Erreur translation");
			return null;
		}
		double [][]matV= {{1.0,0.0,0.0,vecteur[0]},{0.0,1.0,0.0,vecteur[1]},{0.0,0.0,1.0,vecteur[2]},{0.0,0.0,0.0,1.0}};
		return multiplier(matV,point);
	}	

	/**
	 *  Retourne l'homothetie 3D d'un certain rapport passe en parametres autour de l'origine
	 *  @param point La matrice du point avant homothetie de taille 4x1
	 *  @param rapport Rapport utilise pour l'homothetie
	 *  @return La nouvelle matrice du point apres l'homothetie   
	 */
	public static double[][] homothetie(double [][]point, double rapport){
		if(point==null||rapport==0.0||point.length!=4||point[0].length!=1) {
			System.out.println("Erreur homothetie");
			return null;
		}
		double[][] homothetieRes=point;
		homothetieRes[0][0]=homothetieRes[0][0]*rapport;
		homothetieRes[1][0]=homothetieRes[1][0]*rapport;
		homothetieRes[2][0]=homothetieRes[2][0]*rapport;
		return homothetieRes;
	}
	public static ArrayList<Point> homothetie(ArrayList<Point> ap, double rapport){
		if(rapport==0.0||ap==null) {
			System.out.println("Erreur homothetie");
			return null;
		}
		for(Point p : ap) {
				p.setX(p.getX()*rapport);
				p.setY(p.getY()*rapport);
				p.setZ(p.getZ()*rapport);
		}
		return ap;
	}


	/**
	 * Realistion la rotation 3D  autour du point (0,0)
	 * @param point La matrice du point avant rotation de taille 4x1
	 * @param angleX Angle en radians de la rotation sur l'axe des X
	 * @param angleY Angle en radians de la rotation sur l'axe des Y
	 * @param angleZ Angle en radians de la rotation sur l'axe des Z
	 * @return La nouvelle matrice du point apres rotation
	 */
	public static double[][] rotation(double[][]point, double angleX, double angleY,  double angleZ) {
		if(point==null||point.length!=4||point[0].length!=1) {
			System.out.println("Erreur rotation");
			return null;
		}
		double cosX =Math.cos(angleX);
		double sinX =Math.sin(angleX);
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
	/**
	 * Fonction qui realise la translation d'une arraylist de face par un vecteur en 3D
	 * @param faces une arraylist de faces 
	 * @param vecteur La matrice du vecteur representant la traslation de taille 3x1
	 * @return la nouvelle arraylist de faces apres la translation 
	 */
	public static ArrayList<Face> translation3D(ArrayList<Face> faces, double[] vecteur) {
		ArrayList<Face> res=new ArrayList<Face>();
		for(Face f : faces) {
			double[][]tmpMat=translation3D(f.getP1(),vecteur);
			Point tmp1=new Point(tmpMat[0][0],tmpMat[1][0],tmpMat[2][0]);
			tmpMat=translation3D(f.getP2(),vecteur);
			Point tmp2=new Point(tmpMat[0][0],tmpMat[1][0],tmpMat[2][0]);
			tmpMat=translation3D(f.getP3(),vecteur);
			Point tmp3=new Point(tmpMat[0][0],tmpMat[1][0],tmpMat[2][0]);
			res.add(new Face(tmp1,tmp2,tmp3));
		}
		return res;
	}
}


