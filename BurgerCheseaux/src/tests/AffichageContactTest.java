package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import smartphone.AffichageContact;


public class AffichageContactTest {

	@Test
	public void testExtraireParties() {
		AffichageContact affichageContact = new AffichageContact();
		
		// String de test simulant un fichier contact
		String infosContact = new String("Prénom;Nom;Téléphone;Email;DateDeNaissance;CheminPhoto");
		
		// Variable accueillant la valeur retournée de la méthode
		String[] recupInfos = new String[2];
		
		// Variables qui récupèrent les parties extraites
		String prenom;
		String nom;
		String telephone;
		
		// Utilisation à trois reprsies de la méthode d'extraction
		recupInfos = affichageContact.extraireParties(infosContact);
		prenom = recupInfos[0];
		infosContact = recupInfos[1];
		
		recupInfos = affichageContact.extraireParties(infosContact);
		nom = recupInfos[0];
		infosContact = recupInfos[1];
		
		recupInfos = affichageContact.extraireParties(infosContact);
		telephone = recupInfos[0];
		infosContact = recupInfos[1];
		
		// Vérification de la valeur de la variable "telephone"
		if(!telephone.equals("Téléphone")) {
			fail("La méthode d'extraction des parties ne fonctionne pas.");
		}
	}
}
