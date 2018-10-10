package main;

import java.io.IOException;
import java.util.ArrayList;

import tools.PlyReader;

public class test {
	public static void main (String []args) throws IOException {
		PlyReader pr1=new PlyReader("ressources/dolphin.ply");
		Model main=new Model(pr1.getPoints(),pr1.getFaces());
		main.disp();
	}
}
