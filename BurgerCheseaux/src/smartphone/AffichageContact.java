package smartphone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class AffichageContact extends JPanel {

	// D�finition des attributs pour la layout de l'affichage
	private GridBagLayout grille = new GridBagLayout();
	private GridBagConstraints contraintes = new GridBagConstraints();
	
	
	// D�finition des attributs repr�sentant tous les composants
	private ImageIcon iconePoubelle = new ImageIcon(new ImageIcon().getClass().getResource("/Bouton_Supprimer.png"));
	private Image logoPoubelle = iconePoubelle.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private JButton supprimer = new JButton(new ImageIcon(logoPoubelle));
	
	private JButton retour = new JButton("Retour");
	private JButton modifier = new JButton("Modifier");
	
	private static JLabel prenom = new JLabel();
	private static JLabel nom = new JLabel();
	private static JLabel telephone = new JLabel();
	private static JLabel mail = new JLabel();
	private static JLabel naissance = new JLabel();
	private static JLabel photo = new JLabel();
	
	private static String nomFichier;
	
	private static ImageIcon photoDefaut = new ImageIcon(new ImageIcon().getClass().getResource("/Contact_Defaut.png"));
	private static Image iconeDefaut = photoDefaut.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	
	
	// D�finition des variables permettant de r�cup�rer toutes les infos du contact
	private static String infosContact;
	private static String partiePrenom;
	private static String partieNom;
	private static String partieTelephone;
	private static String partieMail;
	private static String partieNaissance;
	private static String cheminPhoto;
	
	
	// Cr�ation de l'image de fond pour le panel
	private ImageIcon imageFond = new ImageIcon(new ImageIcon().getClass().getResource("/Fond_Panel.jpg"));
	private Image fond = imageFond.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT);
	

	public AffichageContact() {
		// D�finition du layout sur le JPanel
		setLayout(grille);
		
		
		// D�finition des dimensions des composants
		Dimension boutonChoix = new Dimension(170, 30);
		Dimension boutonDelete = new Dimension(30, 30);
		Dimension taillePhoto = new Dimension(120, 120);
		
		retour.setPreferredSize(boutonChoix);
		modifier.setPreferredSize(boutonChoix);
		supprimer.setPreferredSize(boutonDelete);
		photo.setPreferredSize(taillePhoto);
		
		
		// D�finition des actions des boutons
		// Retour
		Clic_Retour clicRetour = new Clic_Retour();
		retour.addActionListener(clicRetour);
		
		// Supprimer
		Clic_Supprimer clicSupprimer = new Clic_Supprimer();
		supprimer.addActionListener(clicSupprimer);
		
		// Modifier
		Clic_Modifier clicModifier = new Clic_Modifier();
		modifier.addActionListener(clicModifier);
		
		
		// D�fintion du format des JLabel du formulaire
		Font police = new Font("Arial", Font.BOLD, 26);
		
		prenom.setFont(police);
		nom.setFont(police);
		telephone.setFont(police);
		mail.setFont(police);
		naissance.setFont(police);
		 
		
		// D�finition des contraintes pour le layout
		// Bouton "Retour", la grille commence en (0, 0)
		contraintes.gridx = 0;
		contraintes.gridy = 0;		
		// Une seule cellule disponible pour le bouton "Retour"
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 30, 110, 0);
		contraintes.weightx = 0.45;
		add(retour, contraintes);
		
		
		// Bouton "Modifier", positionnement � (0, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 0;	
		// Une seule cellule disponible pour le bouton "Modifier"
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 110, 0);
		contraintes.weightx = 0.45;
		add(modifier, contraintes);
		
		
		// Bouton "Supprimer", positionnement � (0, 2)
		contraintes.gridx = 2;
		contraintes.gridy = 0;	
		// Dernier composant de sa ligne
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 110, 30);
		contraintes.weightx = 0.1;
		add(supprimer, contraintes);
		
		
		// Label contenant la photo, positionnement sur la deuxi�me ligne
		contraintes.gridx = 0;
		contraintes.gridy = 1;	
		// Le label va prendre deux cellules verticalement
		contraintes.gridwidth = 1;
		contraintes.gridheight = 2;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 30, 50, 0);	
		contraintes.weightx = 0.1;
		add(photo, contraintes);
		

		// Composant du champ "Pr�nom", positionnement � (1, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 1;		
		// Le champ ne prend qu'une cellule verticalement, mais est le dernier de sa ligne et s'�tend sur cette derni�re
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 0, 0, 30);
		contraintes.weightx = 0.9;
		add(prenom, contraintes);
		
		
		// Composant du champ "Nom", positionnement sur la troisi�me ligne
		contraintes.gridx = 1;
		contraintes.gridy = 2;	
		// Le champ ne prend qu'une cellule verticalement, mais est le dernier de sa ligne et s'�tend sur cette derni�re
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 0, 0, 30);
		add(nom, contraintes);
		
		
		// Composant du champ du num�ro de t�l�phone, positionnement sur la quatri�me ligne
		contraintes.gridx = 0;
		contraintes.gridy = 3;		
		// Le champ ne prend qu'une cellule verticalement, mais est le seul de sa ligne et s'�tend sur cette derni�re
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 30, 50, 30);
		// On remet � "0" la distribution horizontale
		contraintes.weightx = 1;	
		add(telephone, contraintes);
		
		
		// Composant du champ de l'email, placement sur la cinqui�me ligne
		contraintes.gridx = 0;
		contraintes.gridy = 4;	
		// Le champ ne prend qu'une cellule verticalement, mais est le seul de sa ligne et s'�tend sur cette derni�re
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 30, 50, 30);
		add(mail, contraintes);
		
		
		// Composant du champ de la date de naissance, placement sur la derni�re ligne
		contraintes.gridx = 0;
		contraintes.gridy = 5;
		// Le champ ne prend qu'une cellule verticalement, mais est le seul de sa ligne et s'�tend sur cette derni�re
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 30, 50, 30);
		add(naissance, contraintes);
	}
	

	// M�thode de r�cup�ration du nom de fichier contact
	public static void recupererNomFichier(String path) throws IOException {
		// Sauvegarde du nom de fichier dans une variable "tampon" qui servira � la suppression
		nomFichier = path;
		
		// D�finition du bon fichier contact � consulter
		File folder = new File("stockage/ListeContacts");
		File fichier = new File(folder, path);
		
		// Lecture dans le fichier et enregistrement des informations dans une seule String
		try {
		FileReader in = new FileReader(fichier);
		BufferedReader bin = new BufferedReader(in);
		infosContact = bin.readLine();
		bin.close();
		} catch (FileNotFoundException e) {
			JOptionPane erreur = new JOptionPane();
			int option = JOptionPane.showOptionDialog(null, "Une erreur est survenue au moment de la lecture du fichier contact, veuillez r�essayer.",
					"Affichage interrompu", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			
			if(option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
				Structure.changePanel(1);
			}
		}
		
		// On r�duit la String pour s�parer les diff�rentes informations
		// Partie du pr�nom, r�cup�ration et supression de la String de base		
		String[] recupInfos = extraireParties(infosContact);
		partiePrenom = recupInfos[0];
		infosContact = recupInfos[1];
		
		prenom.setText(partiePrenom);
		dessinerJLabel(prenom);
		
		// Partie du nom, r�cup�ration et supression de la String de base
		recupInfos = extraireParties(infosContact);
		partieNom = recupInfos[0];
		infosContact = recupInfos[1];

		nom.setText(partieNom);
		dessinerJLabel(nom);
		
		// Partie du t�l�phone, r�cup�ration et supression de la String de base
		recupInfos = extraireParties(infosContact);
		partieTelephone = recupInfos[0];
		infosContact = recupInfos[1];
		
		telephone.setText(partieTelephone);
		dessinerJLabel(telephone);
			
		// Partie de l'email, r�cup�ration et supression de la String de base		
		recupInfos = extraireParties(infosContact);
		partieMail = recupInfos[0];
		infosContact = recupInfos[1];
		
		mail.setText(partieMail);
		dessinerJLabel(mail);
		
		// Partie de la date de naissance, r�cup�ration et supression de la String de base
		recupInfos = extraireParties(infosContact);
		partieNaissance = recupInfos[0];
		infosContact = recupInfos[1];
	
		naissance.setText(partieNaissance);
		dessinerJLabel(naissance);
		
		// Partie du chemin de la photo, r�cup�ration
		cheminPhoto = infosContact;
		if(cheminPhoto.equals("null")) {
			cheminPhoto = "Contact_Defaut.png";
		}
		afficherPhoto(cheminPhoto);	
	}
	
	
	// M�thode d'extraction des parties, y compris tests
	public static String[] extraireParties(String infos) {
		String partieExtraite;
		String[] infosModifiees = new String[2];
		
		// Partie � r�cup�rer et supression de la String de base
		partieExtraite = infos.substring(0, (infos.indexOf(';')+1));
		
		// Tests � effectuer si le champ est rest� vide
		if(partieExtraite.equals(";")) {
			infos = infos.substring(infos.indexOf(';')+1);
		}
		else {
			infos = infos.replace(partieExtraite, "");
		}		
		
		if(!partieExtraite.equals("")) {
			partieExtraite = partieExtraite.replace(";", "");
		}
		
		// Ajout des valeurs au tableau
		infosModifiees[0] = partieExtraite;
		infosModifiees[1] = infos;
		
		return infosModifiees;
	}
	
	
	// D�finition du fond d'�cran du panel
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(fond != null) {
			int height = this.getSize().height;
			int width = this.getSize().width;
			g.drawImage(fond, 0, 0, width, height, this);
		}
	}
	
	
	// Cr�ation de l'action du bouton "Retour"
	class Clic_Retour implements ActionListener {
		public void actionPerformed(ActionEvent e1) {
			Structure.changePanel(1);
		}
	}
	
	
	// Cr�ation de l'action du bouton "Supprimer"
	class Clic_Supprimer implements ActionListener {
		public void actionPerformed(ActionEvent e2) {
			File folder = new File("stockage/ListeContacts");
			File fichier = new File(folder, nomFichier);
			
			JOptionPane confirmation = new JOptionPane();
			int option = JOptionPane.showOptionDialog(null, "Cette action est irr�versible, �tes-vous s�r de vouloir supprimer d�finitivement ce contact ?",
					"Supprimer le contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] {"Supprimer", "Annuler"}, "Annuler");
			
			if(option == JOptionPane.OK_OPTION) {
				// Suppression du contact et retour � la liste des contacts
				fichier.delete();
				Structure.changePanel(1);
			}
		}
	}
	
	
	// M�thode de cr�ation des bordures ou non (question esth�tique lors de l'affichage d'un JLabel vide)
	public static void dessinerJLabel(JLabel label) {
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		Border noBorder = BorderFactory.createLineBorder(new Color(0, 0, 0, 0));
		
		if(!label.getText().equals("")) {
			label.setBorder(border);
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
		}
		else {
			label.setOpaque(false);
			label.setBorder(noBorder);
		}
	}
	
	
	// M�thode d'ajout de la photo au contact (photo par d�faut ou non)
	public static void afficherPhoto(String nom) {
		File fichier = new File(nom);
		
		// D�finition des variables pour le calcul de la taille
		double facteur;
		int height = 0;
		int width = 0;
		
		// Calcul de la taille de l'image
		ImageIcon image = new ImageIcon(fichier.getAbsolutePath());
		double largeur = image.getIconWidth();
		double hauteur = image.getIconHeight();

		// Image en "Portrait"
		if(hauteur > largeur) {
			facteur = hauteur/120.0;
			height = 120;
			largeur = largeur/facteur;
			width = (int) largeur;
		}
		
		// Image en "Paysage"
		if(largeur > hauteur) {
			facteur = largeur/120.0;
			width = 120;
			hauteur = hauteur/facteur;
			height = (int) hauteur;
		}
		
		// Image carr�e
		if(largeur == hauteur) {
			height = 120;
			width = 120;
		}
		

		// Ajout de l'image au bouton de la photo
		if(fichier.exists() == true) {
			ImageIcon photoContact = new ImageIcon(fichier.getAbsolutePath());
			Image iconeContact = photoContact.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			photo.setIcon(new ImageIcon(iconeContact));
		}
		else {
			photo.setIcon(new ImageIcon(iconeDefaut));
		}
	}
	
	
	// Cr�ation de l'action du bouton "Modifier"
	class Clic_Modifier implements ActionListener {
		public void actionPerformed(ActionEvent e3) {
			ModificationContact.recupererInfos(nomFichier, partiePrenom, partieNom, partieTelephone, partieMail, partieNaissance, cheminPhoto);
			
			Structure.changePanel(5);		
		}
	}
}