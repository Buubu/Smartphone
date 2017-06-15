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

	// Définition des attributs pour la layout de l'affichage
	private GridBagLayout grille = new GridBagLayout();
	private GridBagConstraints contraintes = new GridBagConstraints();
	
	
	// Définition des attributs représentant tous les composants
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
	
	
	// Définition des variables permettant de récupérer toutes les infos du contact
	private static String infosContact;
	private static String partiePrenom;
	private static String partieNom;
	private static String partieTelephone;
	private static String partieMail;
	private static String partieNaissance;
	private static String cheminPhoto;
	
	
	// Création de l'image de fond pour le panel
	private ImageIcon imageFond = new ImageIcon(new ImageIcon().getClass().getResource("/Fond_Panel.jpg"));
	private Image fond = imageFond.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT);
	

	public AffichageContact() {
		// Définition du layout sur le JPanel
		setLayout(grille);
		
		
		// Définition des dimensions des composants
		Dimension boutonChoix = new Dimension(170, 30);
		Dimension boutonDelete = new Dimension(30, 30);
		Dimension taillePhoto = new Dimension(120, 120);
		
		retour.setPreferredSize(boutonChoix);
		modifier.setPreferredSize(boutonChoix);
		supprimer.setPreferredSize(boutonDelete);
		photo.setPreferredSize(taillePhoto);
		
		
		// Définition des actions des boutons
		// Retour
		Clic_Retour clicRetour = new Clic_Retour();
		retour.addActionListener(clicRetour);
		
		// Supprimer
		Clic_Supprimer clicSupprimer = new Clic_Supprimer();
		supprimer.addActionListener(clicSupprimer);
		
		// Modifier
		Clic_Modifier clicModifier = new Clic_Modifier();
		modifier.addActionListener(clicModifier);
		
		
		// Défintion du format des JLabel du formulaire
		Font police = new Font("Arial", Font.BOLD, 26);
		
		prenom.setFont(police);
		nom.setFont(police);
		telephone.setFont(police);
		mail.setFont(police);
		naissance.setFont(police);
		 
		
		// Définition des contraintes pour le layout
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
		
		
		// Bouton "Modifier", positionnement à (0, 1)
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
		
		
		// Bouton "Supprimer", positionnement à (0, 2)
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
		
		
		// Label contenant la photo, positionnement sur la deuxième ligne
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
		

		// Composant du champ "Prénom", positionnement à (1, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 1;		
		// Le champ ne prend qu'une cellule verticalement, mais est le dernier de sa ligne et s'étend sur cette dernière
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 0, 0, 30);
		contraintes.weightx = 0.9;
		add(prenom, contraintes);
		
		
		// Composant du champ "Nom", positionnement sur la troisième ligne
		contraintes.gridx = 1;
		contraintes.gridy = 2;	
		// Le champ ne prend qu'une cellule verticalement, mais est le dernier de sa ligne et s'étend sur cette dernière
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 0, 0, 30);
		add(nom, contraintes);
		
		
		// Composant du champ du numéro de téléphone, positionnement sur la quatrième ligne
		contraintes.gridx = 0;
		contraintes.gridy = 3;		
		// Le champ ne prend qu'une cellule verticalement, mais est le seul de sa ligne et s'étend sur cette dernière
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 30, 50, 30);
		// On remet à "0" la distribution horizontale
		contraintes.weightx = 1;	
		add(telephone, contraintes);
		
		
		// Composant du champ de l'email, placement sur la cinquième ligne
		contraintes.gridx = 0;
		contraintes.gridy = 4;	
		// Le champ ne prend qu'une cellule verticalement, mais est le seul de sa ligne et s'étend sur cette dernière
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 30, 50, 30);
		add(mail, contraintes);
		
		
		// Composant du champ de la date de naissance, placement sur la dernière ligne
		contraintes.gridx = 0;
		contraintes.gridy = 5;
		// Le champ ne prend qu'une cellule verticalement, mais est le seul de sa ligne et s'étend sur cette dernière
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 30, 50, 30);
		add(naissance, contraintes);
	}
	

	// Méthode de récupération du nom de fichier contact
	public static void recupererNomFichier(String path) throws IOException {
		// Sauvegarde du nom de fichier dans une variable "tampon" qui servira à la suppression
		nomFichier = path;
		
		// Définition du bon fichier contact à consulter
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
			int option = JOptionPane.showOptionDialog(null, "Une erreur est survenue au moment de la lecture du fichier contact, veuillez réessayer.",
					"Affichage interrompu", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
			
			if(option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
				Structure.changePanel(1);
			}
		}
		
		// On réduit la String pour séparer les différentes informations
		// Partie du prénom, récupération et supression de la String de base		
		String[] recupInfos = extraireParties(infosContact);
		partiePrenom = recupInfos[0];
		infosContact = recupInfos[1];
		
		prenom.setText(partiePrenom);
		dessinerJLabel(prenom);
		
		// Partie du nom, récupération et supression de la String de base
		recupInfos = extraireParties(infosContact);
		partieNom = recupInfos[0];
		infosContact = recupInfos[1];

		nom.setText(partieNom);
		dessinerJLabel(nom);
		
		// Partie du téléphone, récupération et supression de la String de base
		recupInfos = extraireParties(infosContact);
		partieTelephone = recupInfos[0];
		infosContact = recupInfos[1];
		
		telephone.setText(partieTelephone);
		dessinerJLabel(telephone);
			
		// Partie de l'email, récupération et supression de la String de base		
		recupInfos = extraireParties(infosContact);
		partieMail = recupInfos[0];
		infosContact = recupInfos[1];
		
		mail.setText(partieMail);
		dessinerJLabel(mail);
		
		// Partie de la date de naissance, récupération et supression de la String de base
		recupInfos = extraireParties(infosContact);
		partieNaissance = recupInfos[0];
		infosContact = recupInfos[1];
	
		naissance.setText(partieNaissance);
		dessinerJLabel(naissance);
		
		// Partie du chemin de la photo, récupération
		cheminPhoto = infosContact;
		if(cheminPhoto.equals("null")) {
			cheminPhoto = "Contact_Defaut.png";
		}
		afficherPhoto(cheminPhoto);	
	}
	
	
	// Méthode d'extraction des parties, y compris tests
	public static String[] extraireParties(String infos) {
		String partieExtraite;
		String[] infosModifiees = new String[2];
		
		// Partie à récupérer et supression de la String de base
		partieExtraite = infos.substring(0, (infos.indexOf(';')+1));
		
		// Tests à effectuer si le champ est resté vide
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
	
	
	// Définition du fond d'écran du panel
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(fond != null) {
			int height = this.getSize().height;
			int width = this.getSize().width;
			g.drawImage(fond, 0, 0, width, height, this);
		}
	}
	
	
	// Création de l'action du bouton "Retour"
	class Clic_Retour implements ActionListener {
		public void actionPerformed(ActionEvent e1) {
			Structure.changePanel(1);
		}
	}
	
	
	// Création de l'action du bouton "Supprimer"
	class Clic_Supprimer implements ActionListener {
		public void actionPerformed(ActionEvent e2) {
			File folder = new File("stockage/ListeContacts");
			File fichier = new File(folder, nomFichier);
			
			JOptionPane confirmation = new JOptionPane();
			int option = JOptionPane.showOptionDialog(null, "Cette action est irréversible, êtes-vous sûr de vouloir supprimer définitivement ce contact ?",
					"Supprimer le contact", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] {"Supprimer", "Annuler"}, "Annuler");
			
			if(option == JOptionPane.OK_OPTION) {
				// Suppression du contact et retour à la liste des contacts
				fichier.delete();
				Structure.changePanel(1);
			}
		}
	}
	
	
	// Méthode de création des bordures ou non (question esthétique lors de l'affichage d'un JLabel vide)
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
	
	
	// Méthode d'ajout de la photo au contact (photo par défaut ou non)
	public static void afficherPhoto(String nom) {
		File fichier = new File(nom);
		
		// Définition des variables pour le calcul de la taille
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
		
		// Image carrée
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
	
	
	// Création de l'action du bouton "Modifier"
	class Clic_Modifier implements ActionListener {
		public void actionPerformed(ActionEvent e3) {
			ModificationContact.recupererInfos(nomFichier, partiePrenom, partieNom, partieTelephone, partieMail, partieNaissance, cheminPhoto);
			
			Structure.changePanel(5);		
		}
	}
}