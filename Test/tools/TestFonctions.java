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
		double [][]valeursFausses= {{4.0,2.0,2.0,7.0},{42.0,3.0,3.0,21.0},{9.0,9.0,9.0,63.0},{4.0,4.0,4.0,28.0}};
		double [][]mauvais = {{1.0},{2.0}};
		assertArrayEquals(success, Fonctions.multiplier(m2, m1));
		assertFalse(valeursFausses.equals(Fonctions.multiplier(m1,m2)));
		assertNull(Fonctions.multiplier(null,m2));
		assertNull(Fonctions.multiplier(m2,null));
		assertNull(Fonctions.multiplier(mauvais,m2));
		assertNull(Fonctions.multiplier(m2,mauvais));

	}

	@Test
	void testTranslation3D() {
		double vecteur[] = {1,0,0};
		double mauvaisV[] = {1,0,0,4};
		double [][]mauvaisP = {{1.0},{2.0}};
		double point[][] = {{1,0,0,3},
							{0,1,0,2},
							{0,0,1,1},
							{0,0,0,1}};

		double resEspere[][] = {{1,0,0,4},
								{0,1,0,2},
								{0,0,1,1},
								{0,0,0,1}} ;
		assertArrayEquals(resEspere, Fonctions.translation3D(point, vecteur));
		assertNull(Fonctions.translation3D(null, null));
		assertNull(Fonctions.translation3D(null, vecteur));
		assertNull(Fonctions.translation3D(point, null));
		assertNull(Fonctions.translation3D(mauvaisP, null));
		assertNull(Fonctions.translation3D(point,mauvaisV));
	}

	@Test
	void testHomothetie() {
		double point[][] = {{1,0,0,3},
							{0,1,0,2},
							{0,0,1,1},
							{0,0,0,1}};
		double k=2.0;
		double res[][] = {{1,0,0,6},
							{0,1,0,4},
							{0,0,1,2},
							{0,0,0,1}};
		double [][]mauvais = {{1.0},{2.0}};
		assertArrayEquals(res, Fonctions.homothetie(point, k));
		assertNull(Fonctions.homothetie(null, k));
		assertNull(Fonctions.homothetie(mauvais,k));
		assertNull(Fonctions.homothetie(point,0.0));
	}

	@Test
	void testRotation() {
		//Impossible de prevoir la valeur rendu par la fonction rotation a cause de l'arrondie de pi en radiant
	}
}

