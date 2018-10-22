package tools;

import static org.junit.jupiter.api.Assertions.*;
import tools.Math;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class MathTest extends TestCase{

	@Test
	void testMultiplier() {
		double[][] matriceA = {{1,2},{3,4}};
		double[][] matriceB = {{4,3},{2,1}};
		double[][] resAttendu = {{8,5},{20,13}};
		//rajouter un cas avec une matrice 2/3 * 2/2 et 2/2 * 3/2
		//System.out.println("" + matriceA[1][0] + matriceA[1][1]);
		assertArrayEquals(resAttendu, Math.multiplier(matriceA,matriceB));
	}
	
	@Test
	void testTranslation3D() {
		double [][]point = {{1,2,3},{3,2,1},{2,3,1},{1,1,1}};
		double[]vecteur  = {1,0,0};
		double[][] resAttendu = {{2,3,4},{3,2,1},{2,3,1},{1,1,1}};
		assertArrayEquals(resAttendu, Math.translation3D(point,vecteur));
	}
	
	@Test
	void testHomotetie() {
		fail("Not yet implemented");
	}

}
