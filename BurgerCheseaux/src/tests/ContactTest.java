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
		// Variables définissant les attributs d'un contact
		String prenom = "Test";
		String nom = "Création";
		String telephone = "0270000000";
		String mail = "test.creation@students.hevs.ch";
		String naissance = "01 janvier 1990";
		String cheminPhoto = "";
	
		// Création de la classe pour récupérer la méthode
		Contact contact = new Contact(prenom, nom, telephone, mail, naissance, cheminPhoto);
		contact.creerContact();
		
		// Variables du fichier contact
		String nomFichier = "Test_Création.txt";
		File fichier = new File("stockage/ListeContacts", nomFichier);
		
		// Variables du contenu du fichier contact
		String contenu = "";
		
		FileReader in = new FileReader(fichier);
		BufferedReader bin = new BufferedReader(in);
		
		contenu = bin.readLine();
		bin.close();
		
		if(fichier.exists() == false) {
			fail("La méthode de création du contact ne fonctionne pas.");
		}
		
		if(contenu.equals("")) {
			fail("La méthode de création du contact ne fonctionne pas.");
		}	
	}
}