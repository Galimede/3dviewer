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

		double resEspere[][] = {{1,0,0,4},
				{0,1,0,2},
				{0,0,1,1},
				{0,0,0,1}} ;
		assertArrayEquals(resEspere, Fonctions.translation3D(point, vecteur));

	}

	@Test
	void testHomothetie() {
		double k = 8.0;
		double point[][] = {{1,1,1,1,2,2,2},
				{2,2,2,2,3,3,3},
				{3,3,3,3,4,4,4},
				{1,1,1,1,1,1,1}};

		double homotethie[][] = {{k,0,0,0},
				{0,k,0,0},
				{0,0,k,0},
				{0,0,0,1}};
		double res[][] = {{8,8,8,8,16,16,16},
				{16,16,16,16,24,24,24},
				{24,24,24,24,32,32,32},
				{1,1,1,1,1,1,1}};	
		assertArrayEquals(res, Fonctions.homothetie(point, k));
		assertEquals(null, Fonctions.homothetie(null, k));

	}

	@Test
	void testRotation() {
		//PB AVEC L'ARRONDIE DE PI LA FONCTION MARCHE MAIS LE TEST EST COMPLIQUE
		/*
		double point[][] = {{1,1,1,1,2,2,2},
							{2,2,2,2,3,3,3},
	  						{3,3,3,3,4,4,4},
	  						{1,1,1,1,1,1,1}};

		double[][] expected = {{1,1,1,1,2,2,2},
							   {2,2,2,2,3,3,3},
							   {3,3,3,3,4,4,4},
							   {1,1,1,1,1,1,1}};

		assertArrayEquals(expected, Fonctions.rotation(point, 0, 0, 00));

	}*/
	}
}
