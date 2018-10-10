package tools;

public class Math {
	
	public int [][] mulMatrice(int [][]m1,int[][]m2){
		if(m1.length!=m2[0].length) {
			return null;
		}
		int [][]m3=new int[m1.length][m2[0].length];
		for(int i=0;i<m3.length;i++) {
			for(int j=0;i<m3[0].length;j++) {
				m3[i][j]=m1[i][j]*m2[0][0]+m1[i][j+1]*m2[i][0]+m1[2][j+2]*m2[2][0];
			}
		}
		return null;
	}
	
}
