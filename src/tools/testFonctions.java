package tools;

public class testFonctions {
	public static void main(String[]args) {
		double [][]m1= {{1.0,0.0,0.0,3.0},{0.0,1.0,0.0,2.0},{0.0,0.0,1.0,1.0},{0.0,0.0,0.0,1.0}};
		double [][]m2= {{2.0,2.0,2.0,2.0},{3.0,3.0,3.0,3.0},{9.0,9.0,9.0,9.0},{4.0,4.0,4.0,4.0}};
		double []vecteur= {100.0,0.0,0.0};
		double[][]m3=Fonctions.multiplier(m1,m2);
	
		m3=Fonctions.translation3D(m1,vecteur);
		
		for(int i=0;i<m3.length;i++) {
			for(int j=0;j<m3[0].length;j++) {
				System.out.println(m3[i][j]);
			}
			System.out.println("\n");
		}
		
	}
}
