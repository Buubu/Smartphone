package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import smartphone.AffichageContact;


public class AffichageContactTest {

	@Test
	public void testExtraireParties() {
		AffichageContact affichageContact = new AffichageContact();
		
		// String de test simulant un fichier contact
		String infosContact = new String("Pr�nom;Nom;T�l�phone;Email;DateDeNaissance;CheminPhoto");
		
		// Variable accueillant la valeur retourn�e de la m�thode
		String[] recupInfos = new String[2];
		
		// Variables qui r�cup�rent les parties extraites
		String prenom;
		String nom;
		String telephone;
		
		// Utilisation � trois reprsies de la m�thode d'extraction
		recupInfos = affichageContact.extraireParties(infosContact);
		prenom = recupInfos[0];
		infosContact = recupInfos[1];
		
		recupInfos = affichageContact.extraireParties(infosContact);
		nom = recupInfos[0];
		infosContact = recupInfos[1];
		
		recupInfos = affichageContact.extraireParties(infosContact);
		telephone = recupInfos[0];
		infosContact = recupInfos[1];
		
		// V�rification de la valeur de la variable "telephone"
		if(!telephone.equals("T�l�phone")) {
			fail("La m�thode d'extraction des parties ne fonctionne pas.");
		}
	}
}
