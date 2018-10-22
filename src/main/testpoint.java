package main;

public class testpoint {
	public static void main(String []args) {
		Point p=new Point(4.0,5.0,6.0);
		double [][]test=p.getMatrice();
		for(int i=0;i<test.length;i++) {
			for(int j=0;j<test[0].length;j++) {
				System.out.println(test[i][j]);
			}
			System.out.println('\n');
		}
		System.out.println(p.getX()+" "+p.getY()+" "+p.getZ());
	}
}
