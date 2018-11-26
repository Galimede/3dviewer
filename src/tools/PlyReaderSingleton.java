package tools;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.Face;
import main.Point;
/**
 * 
 * @author dejonghg
 *	La classe PlyReader sert à lire les fichiers .ply 
 */
public class PlyReaderSingleton {

	private static PlyReaderSingleton pr=null;

	public PlyReaderSingleton getPlyReader(String s) {
		pr=new PlyReaderSingleton(s);
		return pr;
	}
	
	private PlyReaderSingleton(String path){
		String test=path.substring(path.length()-4,path.length());
		if(test.equals(".ply")) {
			try {
				fr = new FileReader(path);
				br = new BufferedReader(fr); 
				lecture();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		}else{
			System.out.println("Une erreur a été détecté le fichier n'est pas reconnu en tant que .ply");
		}
	}
	
	
	
	private ArrayList<Point> points=new ArrayList<>();
	private ArrayList<Face> faces=new ArrayList<>();
	private int nbPoints;
	private int nbFaces;
	private FileReader fr;  
	private BufferedReader br;

	/**
	 * @return Renvoie l'ArrayList de Point crées à la suite de la lecture du fichiers 
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * @return Renvoie l'ArrayList de Face crées à la suite de la lecture du fichiers
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}
	/**
	 * Initialise un PlyReader et en lance la lecture
	 * @param path L'argument path doit être un chemin relatif ou absolu menant à un fichier .ply
	 */

	/**
	 * Méthode privée à PlyReader qui réalise la lecture du fichier et initialise les points et les faces
	 */
	private void lecture(){
		String actu=null;
		try {
			actu = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean enteteFini=false;
		double[] tmp=new double[3];
		while(actu!=null) {

			if(actu.contains("element vertex")) {
				nbPoints=getNumberValue(actu);
			}

			else if(actu.contains("element face")) {
				nbFaces=getNumberValue(actu);
			}

			else if(actu.contains("end_header")) {
				enteteFini=true;
			}

			if(enteteFini){
				int tmpInt=nbPoints+nbFaces;
				for(int i=0;i<tmpInt;i++) {
					try {
						actu=br.readLine();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(i<=nbPoints-1) {
						tmp=getCoorPoint(actu);
						//	System.out.println(tmp[0]+" "+tmp[1]+" "+tmp[2]);

						points.add(new Point(tmp[0],tmp[1],tmp[2]));
					}else {
						tmp=getPointFace(actu);
						faces.add(new Face(points.get((int)tmp[0]), points.get((int)tmp[1]), points.get((int)tmp[2])));
					}
				}
			}
			try {
				actu=br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Méthode privée qui renvoie les 3 points correspondant à une face
	 * @param actu "actu" est la ligne actuelle du buffer 
	 * @return les 3 points sous forme de 
	 */
	private double[] getPointFace(String actu) {
		//	System.out.println(actu);
		String tmps=actu.substring(2,actu.length());
		double res[]=new double[3];
		int idx=0;
		int idxBeg=0;
		for(int i=0;i<tmps.length()&&idx<2;i++) {
			if(tmps.charAt(i)==' ') {
				res[idx]=Double.parseDouble(tmps.substring(idxBeg,i));
				idxBeg=i+1;
				idx++;
			}
		}
		res[idx]=Double.parseDouble(tmps.substring(idxBeg,tmps.length()));
		return res;
	}

	/**
	 * Sert a récuperer les 3 coordonnées de la ligne actuelle et les renvoie
	 * @param actu est la ligne actuelle du buffer
	 * @return un tableua de double avec les 3 coordonnes d'un futur point
	 */
	private double[] getCoorPoint(String actu) {
		double res[]=new double[3];
		int cptSpace=0;
		int idx=0;
		int idxBeg=0;
		for(int i=0;i<actu.length()&&cptSpace<2;i++) {
			if(actu.charAt(i)==' ') {
				cptSpace++;
				res[idx]=Double.parseDouble(actu.substring(idxBeg,i));
				idxBeg=i+1;
				idx++;
			}
		}
		res[idx]=Double.parseDouble(actu.substring(idxBeg,actu.length()));
		return res;
	}
	/**
	 * Méthode privée qui récupere la valeur correspondant au nombre de face et de point
	 * @param s est une ligne de la forme "element vertex ***" ou "element face ***"
	 * @return un int qui représente la valeur associé à la ligne
	 */
	private int getNumberValue(String s) {
		String res=s;
		while(res.charAt(0)>'9'||res.charAt(0)==' '){
			res=res.substring(1,res.length());
		}
		//System.out.println(res);
		return Integer.parseInt(res);
	}
}

