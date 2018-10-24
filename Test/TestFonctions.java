import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tools.Fonctions;

class TestFonctions {

	@Test
	void testMultiplier() {
		double [][]m1= {{1.0,0.0,0.0,3.0},{0.0,1.0,0.0,2.0},{0.0,0.0,1.0,1.0},{0.0,0.0,0.0,1.0}};
		double [][]m2= {{2.0,2.0,2.0,2.0},{3.0,3.0,3.0,3.0},{9.0,9.0,9.0,9.0},{4.0,4.0,4.0,4.0}};
		double [][]m3=Fonctions.multiplier(m1,m2);
		double [][]succes= {{2.0,2.0,2.0,14.0},{3.0,3.0,3.0,21.0},{9.0,9.0,9.0,63.0},{4.0,4.0,4.0,28.0}};
		for(int i=0;i<m3.length;i++) {
			for(int j=0;j<m3[0].length;j++) {
				assertEquals(m3[i][j],succes[i][j]);
			}
		}
	}

	@Test
	void testTranslation3D() {
		fail("Not yet implemented");
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
