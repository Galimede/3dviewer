package tools;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import main.Face;
import main.Point;

public class PlyReader {
	private ArrayList<Point> points=new ArrayList<>();
	private ArrayList<Face> faces=new ArrayList<>();
	private int nbPoints;
	private int nbFaces;
	private FileReader fr;  
	private BufferedReader br;

	/**
	 * @return the points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * @return the faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}

	public PlyReader(String path) throws IOException {
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr); 
			lecture();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	private void lecture() throws IOException {
		String actu=br.readLine();
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
					actu=br.readLine();
					if(i<=nbPoints-1) {
						tmp=getCoorPoint(actu);
						System.out.println(tmp[0]+" "+tmp[1]+" "+tmp[2]);
						
						points.add(new Point(tmp[0],tmp[1],tmp[2]));
					}else {
						tmp=getPointFace(actu);
						faces.add(new Face(points.get((int)tmp[0]), points.get((int)tmp[1]), points.get((int)tmp[2])));
					}
				}
			}
			actu=br.readLine();
		}

	}


	private double[] getPointFace(String actu) {
		System.out.println(actu);
		String tmps=actu.substring(2,actu.length());
		double res[]=new double[3];
		int cptSpace=0;
		int idx=0;
		int idxBeg=0;
		int idxEnd=0;
		for(int i=0;i<tmps.length()&&cptSpace<2;i++) {
			if(tmps.charAt(i)==' ') {
				cptSpace++;
				idxEnd=i;
				res[idx]=Double.parseDouble(tmps.substring(idxBeg,idxEnd));
				idxBeg=idxEnd+1;
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
		int idxEnd=0;
		for(int i=0;i<actu.length()&&cptSpace<2;i++) {
			if(actu.charAt(i)==' ') {
				cptSpace++;
				idxEnd=i;
				res[idx]=Double.parseDouble(actu.substring(idxBeg,idxEnd));
				idxBeg=idxEnd+1;
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
		System.out.println(res);
		return Integer.parseInt(res);
	}
}