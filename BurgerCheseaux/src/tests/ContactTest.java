package tests;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import smartphone.Contact;


public class ContactTest {

	@Test
	public void testCreerContact() throws IOException {
		// Variables d�finissant les attributs d'un contact
		String prenom = "Test";
		String nom = "Cr�ation";
		String telephone = "0270000000";
		String mail = "test.creation@students.hevs.ch";
		String naissance = "01 janvier 1990";
		String cheminPhoto = "";
	
		// Cr�ation de la classe pour r�cup�rer la m�thode
		Contact contact = new Contact(prenom, nom, telephone, mail, naissance, cheminPhoto);
		contact.creerContact();
		
		// Variables du fichier contact
		String nomFichier = "Test_Cr�ation.txt";
		File fichier = new File("stockage/ListeContacts", nomFichier);
		
		// Variables du contenu du fichier contact
		String contenu = "";
		
		FileReader in = new FileReader(fichier);
		BufferedReader bin = new BufferedReader(in);
		
		contenu = bin.readLine();
		bin.close();
		
		if(fichier.exists() == false) {
			fail("La m�thode de cr�ation du contact ne fonctionne pas.");
		}
		
		if(contenu.equals("")) {
			fail("La m�thode de cr�ation du contact ne fonctionne pas.");
		}	
	}
}