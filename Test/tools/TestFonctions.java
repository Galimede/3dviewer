package tools;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tools.Fonctions;

class TestFonctions {

	@Test
	void testMultiplier() {
		double [][]m1= {{1.0,0.0,0.0,3.0},{0.0,1.0,0.0,2.0},{0.0,0.0,1.0,1.0},{0.0,0.0,0.0,1.0}};
		double [][]m2= {{2.0,2.0,2.0,2.0},{3.0,3.0,3.0,3.0},{9.0,9.0,9.0,9.0},{4.0,4.0,4.0,4.0}};
		double [][]success= {{2.0,2.0,2.0,14.0},{3.0,3.0,3.0,21.0},{9.0,9.0,9.0,63.0},{4.0,4.0,4.0,28.0}};
		double [][]mauvais = {{1.0},{2.0}};
		assertArrayEquals(success, Fonctions.multiplier(m2, m1));
		assertNull(Fonctions.multiplier(mauvais,m2));
		
	}

	@Test
	void testTranslation3D() {
		double vecteur[] = {1,0,0};
		
		double point[][] = {{1,0,0,3},
							{0,1,0,2},
							{0,0,1,1},
							{0,0,0,1}};
		
		double resEspéré[][] = {{1,0,0,4},
								{0,1,0,2},
								{0,0,1,1},
								{0,0,0,1}} ;
		assertArrayEquals(resEspéré, Fonctions.translation3D(point, vecteur));

	}

	@Test
	void testHomothetie() {
		
		
		fail("Not yet implemented");
	}

	@Test
	void testRotation() {
		fail("Not yet implemented");
	}

}
