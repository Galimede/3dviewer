package main;

import java.io.IOException;
import java.util.ArrayList;

import tools.PlyReader;

public class test {
	public static void main(String[]args) throws IOException {
		PlyReader pr=new PlyReader("ressources/dolphin.ply");
		ArrayList<Point> tmp=pr.getPoints();
		ArrayList<Face>tmp2=pr.getFaces();
		for(Point p : tmp) {
			System.out.println(p.toString());
		}
		
		for(Face f : tmp2) {
			System.out.println(f.toString());
		}
		
	}
}
