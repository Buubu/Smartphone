package smartphone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Contact {

	// D�finition des attributs d'un contact
	private String prenom;
	private String nom;
	private String telephone;
	private String mail;
	private String naissance;
	private String cheminPhoto;
	
	// D�finition des attributs repr�sentant le fichier de la liste des contacts
	private String pathContacts = "stockage/ListeContacts";
	private File folder = new File(pathContacts);
	
	
	public Contact(String prenom, String nom, String telephone, String mail, String naissance, String cheminPhoto) throws IOException {
		// Test du contenu avant d'affecter les valeurs aux attributs de la classe
		if(prenom.equals("Pr�nom")) {
			this.prenom = "";
		}
		else {
			this.prenom = prenom;
		}
		
		if(nom.equals("Nom")) {
			this.nom = "";
		}
		else {
			this.nom = nom;
		}
		
		if(telephone.equals("N� de t�l�phone")) {
			this.telephone = "";
		}
		else {
			this.telephone = telephone;
		}
		
		if(mail.equals("Email")) {
			this.mail = "";
		}
		else {
			this.mail = mail;
		}
		
		if(naissance.equals("Date de naissance")) {
			this.naissance = "";
		}
		else {
			this.naissance = naissance;
		}
		
		this.cheminPhoto = cheminPhoto;

		
		// Cr�ation du contact avec les contenus exacts
		creerContact();
	}
	
		
	// M�thode de cr�ation du contact dans la liste des contacts, exception jet�e car le test du fichier existant est effectu� au d�marrage du smartphone
	public void creerContact() throws IOException {
		File fichier = new File(folder, prenom+"_"+nom+".txt");
		fichier.createNewFile();
		
		// Ecriture des informations dans le fichier du contact
		FileWriter out = new FileWriter(fichier);
		BufferedWriter bout = new BufferedWriter(out);
		
		bout.write(prenom+";"+nom+";"+telephone+";"+mail+";"+naissance+";"+cheminPhoto);
		bout.close();		
	}	
}