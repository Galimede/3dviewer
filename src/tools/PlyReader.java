package tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import main.Face;
import main.Point;
import main.Segment;
/*
 * @author Mathieu DEGAND - 01/10/2018
 * University Of Lille
 */
public class PlyReader {

	private List<Point> points;
	private List<Face> faces;
	private List<Segment> segments;
	private int nbPoints;
	private int nbFaces;

	private FileReader fr;  
	private BufferedReader br;

	public PlyReader(String path) {
		try {
			fr = new FileReader(path);
			setNbPoints();
			setnbFaces();
			br = new BufferedReader(fr);    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void setNbPoints() {
		String res="";
		try {
			res=br.readLine();
			while(!res.contains("element vertex")) {
				res=br.readLine();
			}
			res=res.substring(res.lastIndexOf(" ")+1, res.length());
			nbPoints=Integer.parseInt(res);
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}

	public void setnbFaces() {
		String res="";
		try {
			res=br.readLine();
			while(!res.contains("element face")) {
				res=br.readLine();
			}
			res=res.substring(res.lastIndexOf(" ")+1, res.length());
			nbFaces=Integer.parseInt(res);
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}




	public static void main(String[] args) throws IOException {
		FileReader fr=new FileReader("/home/infoetu/degandm/git/ProjetMode2018-M3/ressources/dolphin.ply");   
		BufferedReader br=new BufferedReader(fr);   
		int i;    
		while((i=br.read())!=-1){  
			System.out.print((char)i);  
		}  	
	}
}