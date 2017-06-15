package tests;

import static org.junit.Assert.*;
import javax.swing.ImageIcon;
import org.junit.Test;
import smartphone.Galerie;


public class GalerieTest {

	@Test
	public void testResizeImage() {
		// Cr�ation de la galerie pour acc�der � la m�thode
		Galerie galerie = new Galerie();
		
		// Cr�ation de l'image et de la variable qui accueillera la nouvelle image (choix volontaire d'une image rectangulaire)
		ImageIcon image = new ImageIcon(new ImageIcon().getClass().getResource("/Fond_Panel.jpg"));
		ImageIcon imageResized;
		
		// D�finition des variables de la taille de l'image choisie
		int hauteur = image.getIconHeight();
		int largeur = image.getIconWidth();
		
		// Redimension de l'image de base
		imageResized = galerie.resizeImage(image, 120, 120);
		
		// D�finition des variables de la taille de la nouvelle image
		int newHauteur = imageResized.getIconHeight();
		int newLargeur = image.getIconWidth();
		
		// Test
		if(hauteur == newHauteur && largeur == newLargeur) {
			fail("La m�thode de redimensionnement de l'image ne fonctionne pas.");
		}
	}
}