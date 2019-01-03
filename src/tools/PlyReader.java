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
 *	La classe PlyReader sert a lire les fichiers .ply 
 */
public class PlyReader {
	private ArrayList<Point> points=new ArrayList<>();
	private ArrayList<Face> faces=new ArrayList<>();
	private int nbPoints;
	private int nbFaces;
	private FileReader fr;  
	private BufferedReader br;

	/**
	 * Renvoie la liste des points contenu dans le fichier .ply
	 * @return Une ArrayList de Point
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * Renvir la liste des faces contenu dans le fichier .ply
	 * @return Une ArrayList de Face
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}
	/**
	 * Initialise un PlyReader et en lance la lecture
	 * @param path L'argument path doit etre un chemin relatif ou absolu menant a  un fichier .ply
	 */
	public PlyReader(String path){
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
			System.out.println("Une erreur a ete detecte le fichier n'est pas reconnu en tant que .ply");
		}
	}

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
	private double[] getPointFace(String actu) {
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
	private int getNumberValue(String s) {
		String res=s;
		while(res.charAt(0)>'9'||res.charAt(0)==' '){
			res=res.substring(1,res.length());
		}
		return Integer.parseInt(res);
	}
}