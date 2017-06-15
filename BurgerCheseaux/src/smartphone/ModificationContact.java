package smartphone;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import smartphone.AjoutContact.ChoixPhoto;


public class ModificationContact extends JPanel {

	// D�finition des attributs pour la layout de l'affichage
	private GridBagLayout grille = new GridBagLayout();
	private GridBagConstraints contraintes = new GridBagConstraints();
	
	
	// D�finition des attributs repr�sentant tous les composants
	private JButton annuler = new JButton("Annuler");
	private JButton enregistrer = new JButton("Enregistrer");	
	private static JButton photo = new JButton();
	private static JTextField prenom = new JTextField("Pr�nom");
	private static JTextField nom = new JTextField("Nom");
	private static JTextField telephone = new JTextField("N� de t�l�phone");
	private static JTextField mail = new JTextField("Email");
	private static JTextField naissance = new JTextField("Date de naissance");
	private static String pathPhoto;
	private static String ancienFichier;
	
	
	// Cr�ation de l'image de fond pour le panel
	private ImageIcon imageFond = new ImageIcon(new ImageIcon().getClass().getResource("/Fond_Panel.jpg"));
	private Image fond = imageFond.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT);
		
	
	public ModificationContact() {
		
		// Application de notre layout sur le panel
		setLayout(grille);
		
		
		// D�finition de tous les champs
		// Pr�nom
		Clic_Formulaire clicFormulairePrenom = new Clic_Formulaire(this.prenom);
		this.prenom.addFocusListener(clicFormulairePrenom);
		
		// Nom
		Clic_Formulaire clicFormulaireNom = new Clic_Formulaire(this.nom);
		this.nom.addFocusListener(clicFormulaireNom);
		
		// Num�ro de t�l�phone
		Clic_Formulaire clicFormulaireTel = new Clic_Formulaire(this.telephone);
		this.telephone.addFocusListener(clicFormulaireTel);
			
		// Email
		Clic_Formulaire clicFormulaireMail = new Clic_Formulaire(this.mail);
		this.mail.addFocusListener(clicFormulaireMail);
		
		// Date de naissance
		Clic_Formulaire clicFormulaireDate = new Clic_Formulaire(this.naissance);
		this.naissance.addFocusListener(clicFormulaireDate);
		
		
		// D�finition des dimensions des composants
		Dimension boutonPhoto = new Dimension(120, 120);
		Dimension boutonChoix = new Dimension(170, 30);
				
		annuler.setPreferredSize(boutonChoix);
		enregistrer.setPreferredSize(boutonChoix);
		photo.setPreferredSize(boutonPhoto);
		
		Clic_Photo clicPhoto = new Clic_Photo();
		photo.addActionListener(clicPhoto);

		
		// D�finition de l'action du bouton "Annuler"
		Clic_Annuler clicAnnuler = new Clic_Annuler();
		annuler.addActionListener(clicAnnuler);
		
		
		// D�fintion du format des JTextField du formulaire de modification
		Font police = new Font("Arial", Font.BOLD, 26);
		
		prenom.setFont(police);
		nom.setFont(police);
		telephone.setFont(police);
		mail.setFont(police);
		naissance.setFont(police);	
	
				
		// D�finition des contraintes pour le layout		
		// Bouton "Annuler", la grille commence en (0, 0)
		contraintes.gridx = 0;
		contraintes.gridy = 0;		
		// Une seule cellule disponible pour le bouton "Annuler"
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 30, 110, 0);
		add(annuler, contraintes);
		
		
		// Bouton "Enregistrer", positionnement � (0, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 0;	
		// Dernier composant de sa ligne
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 110, 30);
		add(enregistrer, contraintes);
		
		
		// Bouton contenant la photo, positionnement sur la deuxi�me ligne
		contraintes.gridx = 0;
		contraintes.gridy = 1;	
		// Le bouton va prendre deux cellules verticalement
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
		
		
		// D�finition de l'action du bouton "Enregistrer"
		Clic_Enregistrer clicEnregistrer = new Clic_Enregistrer(prenom, nom, telephone, mail, naissance, pathPhoto);
		enregistrer.addActionListener(clicEnregistrer);
	}
	
	
	// D�finition du fond d'�cran du panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(fond != null) {
			int height = this.getSize().height;
			int width = this.getSize().width;
			g.drawImage(fond, 0, 0, width, height, this);
		}
	}
	
	
	// Cr�ation de l'action de la souris sur les champs de formulaire
	class Clic_Formulaire implements FocusListener {
		private JTextField field;
		private String contenu;
				
		// D�finition du constructeur pour l'adapter � tous les champs du formulaire
		public Clic_Formulaire(JTextField field) {
			this.field = field;
			contenu = field.getText();
		}

		// Au clic, si le contenu n'est plus celui de base, il reste affich�. Sinon, il s'efface pour que l'on puisse saisir nos informations
		public void focusGained(FocusEvent e1) {
			if(field.getText().equals(contenu)) {
				field.setText("");	
			}
			else {}			
		}
		
		// Lorsque l'on quitte le chant, s'il est vide, le texte par d�faut revient. Sinon, le nouveau texte reste inscrit
		public void focusLost(FocusEvent e2) {
			if(field.getText().equals("")) {
				field.setText(contenu);
			}
			else {
				field.setText(field.getText());
			}				
		}
	}
	
	
	// M�thode de r�cup�ration des diff�rentes informations � partir de l'affichage du contact
	public static void recupererInfos(String nomFichier, String partiePrenom, String partieNom, String partieTelephone, String partieMail, 
			String partieNaissance, String cheminPhoto) {
		// R�cup�ration du nom de fichier en cas de modification ult�rieure
		ancienFichier = nomFichier;
		
		// Remplissage des JTextField avec les bons �l�ments (s'ils sont connus), sinon par d�faut
		if(!partiePrenom.equals("")) {
			prenom.setText(partiePrenom);
		}
				
		if(!partieNom.equals("")) {
			nom.setText(partieNom);
		}

		if(!partieTelephone.equals("")) {
			telephone.setText(partieTelephone);
		}
		
		if(!partieMail.equals("")) {
			mail.setText(partieMail);
		}
		
		if(!partieNaissance.equals("")) {
			naissance.setText(partieNaissance);
		}
		
		if(!cheminPhoto.equals("")) {
			if(cheminPhoto.equals("null")) {
				ImageIcon iconeContact = new ImageIcon(new ImageIcon().getClass().getResource("/Contact_Defaut.png"));
				Image imageContact = iconeContact.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
//				pathPhoto = cheminPhoto;
				photo.setIcon(new ImageIcon(imageContact));
			}
			ImageIcon iconeContact = new ImageIcon(cheminPhoto);
			Image imageContact = iconeContact.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
			pathPhoto = cheminPhoto;
			photo.setIcon(new ImageIcon(imageContact));
		}
		else {
			ImageIcon iconeContact = new ImageIcon(new ImageIcon().getClass().getResource("/Contact_Defaut.png"));
			Image imageContact = iconeContact.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
//			pathPhoto = cheminPhoto;
			photo.setIcon(new ImageIcon(imageContact));
		}
	}
	
	
	// Cr�ation de l'action du bouton "Annuler"
	class Clic_Annuler implements ActionListener {
		public void actionPerformed(ActionEvent e3) {
			JOptionPane confirmation = new JOptionPane();
			int option = JOptionPane.showOptionDialog(null, "Toutes les modifications seront annul�es.", 
					"Ignorer les modifications", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] {"Ignorer", "Annuler"}, "Ignorer");
			
			if(option == JOptionPane.OK_OPTION) {
				Structure.changePanel(4);
			}
		}
	}
	
	
	// Cr�ation de l'action du bouton "Enregistrer" au moment du clic
	class Clic_Enregistrer implements ActionListener {
		private JTextField prenom;
		private JTextField nom;
		private JTextField telephone;
		private JTextField mail;
		private JTextField naissance;
		private String cheminPhoto = "";
		private File folder = new File("stockage/ListeContacts");
		
		
		// D�finition du constructeur pour r�cup�rer les contenus saisis
		public Clic_Enregistrer(JTextField prenom, JTextField nom, JTextField telephone, JTextField mail, JTextField naissance, String cheminPhoto) {
			this.prenom = prenom;
			this.nom = nom;
			this.telephone = telephone;
			this.mail = mail;
			this.naissance = naissance;
			this.cheminPhoto = cheminPhoto;
		}
		
		
		// R�cup�ration du contenu des champs (ATTENTION, au moment du clic pour enregistrer les �ventuelles saisies utilisateur)
		public void actionPerformed(ActionEvent e4) {
			String contenuPrenom = prenom.getText();
			String contenuNom = nom.getText();
			String contenuTelephone = telephone.getText();
			String contenuMail = mail.getText();
			String contenuNaissance = naissance.getText();
			
			if(getCheminPhoto() == "") {
				this.cheminPhoto = "/Contact_Defaut.png";
			}
			else {
				this.cheminPhoto = getCheminPhoto();
			}
			
			
			// Test afin de savoir quelle action lancer au moment du clic sur "Enregistrer"
			if(contenuPrenom.equals("Pr�nom") && contenuNom.equals("Nom")) {
				JOptionPane refus = new JOptionPane();
				int option = JOptionPane.showOptionDialog(null, "Vous devez au moins saisir le pr�nom ou le nom.", "Erreur contact",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
			}
			else {
				// D�termination du nom du fichier qui va �tre cr��
				String partiePrenom;
				String partieNom;
				
				if(contenuPrenom.equals("Pr�nom")) {
					partiePrenom = "";
				}
				else {
					partiePrenom = contenuPrenom;
				}
				
				if(contenuNom.equals("Nom")) {
					partieNom = "";
				}
				else {
					partieNom = contenuNom;
				}
				
				String nomFichier = new String(partiePrenom+"_"+partieNom+".txt");
				File fichier = new File(folder, nomFichier);
				
				JOptionPane avertissement = new JOptionPane();
				int option1 = JOptionPane.showOptionDialog(null, "�tes-vous s�r de vouloir apporter des modifications � ce contact ?", "Modification de contact",
						JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
				
				if(option1 == JOptionPane.OK_OPTION) {
					// Suppression de l'ancien fichier de contact
					File ancien = new File(folder, ancienFichier);
					ancien.delete();
					
					// Gestion d'une exception au cas o� le contact ne pourrait pas se cr�er, simple fen�tre d'erreur demandant de r�essayer
					try {
						Contact contact = new Contact(contenuPrenom, contenuNom, contenuTelephone, contenuMail, contenuNaissance, cheminPhoto);
					} catch (IOException e) {
						JOptionPane erreur = new JOptionPane();
						int option = JOptionPane.showOptionDialog(null, "Une erreur est survenue au moment de l'enregistrement, veuillez r�essayer.", 
								"Enregistrement interrompu", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
						
						if(option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
							// Retour sur le contact pr�alablement affich�
							Structure.changePanel(4);
						}
					}

					JOptionPane confirmation = new JOptionPane();
					int option2 = JOptionPane.showOptionDialog(null, "Contact modifi�.", "Modification contact",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
					if(option2 == JOptionPane.OK_OPTION || option2 == JOptionPane.CLOSED_OPTION) {
						// Retour � la liste des contacts						
						Structure.changePanel(1);
					}
				}
			}
		}
	}
	
	
	// Cr�ation de l'action du bouton d'ajout d'une photo
		class Clic_Photo implements ActionListener {
			private GridLayout grille = new GridLayout(); 
			private Galerie galerie = new Galerie();

			private File folder = new File("stockage/Galerie");
			private double calcul;
			private int nbrLignes;
			
			private Border border = LineBorder.createGrayLineBorder();
			

			public void actionPerformed(ActionEvent e5) {
				// D�finition de la taille de la grille, sur notre grille, rapport de 2 (pour 6 �l�ments, 3 lignes - pour 8 �l�ments, 4 lignes, etc...)
				// Calcul du nombre de lignes
				calcul = (folder.list().length)/2.0;

				BigDecimal arrondi = new BigDecimal(calcul);
				arrondi = arrondi.setScale(0, BigDecimal.ROUND_CEILING);
				nbrLignes = arrondi.intValue();

				// Test pour que le nombre de lignes soit au moins �gal � 3 (question esth�tique)
				if(nbrLignes < 3) {
					grille.setRows(3);
				}
				else {
					grille.setRows(nbrLignes);
				}
				
				// Utilisation de la m�thode qui va chercher le dossier d'images
				File[] listeImages = galerie.getdossierImages();
				
				// D�finition du format du JPanel
			    JPanel ecran = new JPanel();
			    ecran.setLayout(grille);
			    		    
			    // Ajout des images
				for(int i=0; i<listeImages.length; i++) {
					ImageIcon image = new ImageIcon(listeImages[i].getAbsolutePath());
					ImageIcon imageDimension = galerie.resizeImage(image, 90, 90);
					
					JLabel caseImage = new JLabel(imageDimension);
					caseImage.setPreferredSize(new Dimension(120, 90));
					caseImage.setBorder(border);
					ChoixPhoto choixPhoto = new ChoixPhoto(i, listeImages);
					caseImage.addMouseListener(choixPhoto);
					ecran.add(caseImage);
				}
				
				// Ajout de cases vides pour positionner les photos de mani�re propre	
				for(int i=folder.list().length; i<6; i++) {
					JLabel caseVide = new JLabel();
					caseVide.setPreferredSize(new Dimension(120, 90));
					caseVide.setBorder(border);
					ecran.add(caseVide);
				}	
			    
				// Ajout du scroll sur la liste de photos
			    JScrollPane scroll = new JScrollPane(ecran);
				scroll.setPreferredSize(new Dimension(260, 300));
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.getVerticalScrollBar().setUnitIncrement(16);
			    
			    JOptionPane.showMessageDialog(null, scroll, "Galerie",JOptionPane.QUESTION_MESSAGE);
			}			
		}
		
		
		// D�finition de l'action du clic sur les images lors de l'ajout d'une photo
		class ChoixPhoto implements MouseListener {
			private int indice;
			private File[] listeImages;
			private Galerie galerie = new Galerie();
			
			public ChoixPhoto(int i, File[] f) {
				// Passage des valeurs transmises par le MouseListener de chaque image
				indice = i;
				listeImages = f;
			}
			
			public void mouseClicked(MouseEvent e6) {
				setJButton(null);
				
				ImageIcon image = new ImageIcon(listeImages[indice].getAbsolutePath());
				ImageIcon imageDimension = galerie.resizeImage(image, 120, 120);
				
				ModificationContact.recupererNomImage(listeImages[indice].getAbsolutePath());
				setJButton(imageDimension);
			}
			
			public void mouseEntered(MouseEvent e7) {}

			public void mouseExited(MouseEvent e8) {}

			public void mousePressed(MouseEvent e9) {}

			public void mouseReleased(MouseEvent e10) {}
		}
		
		
		// M�thode de r�cup�ration du nom de la photo s�lectionn�e
		public static void recupererNomImage(String nom){
			pathPhoto = nom;
		}
		
		
		// M�thode permettant la modification du bouton depuis la classe "ChoixPhoto"
		protected static void setJButton(ImageIcon image){
			photo.setIcon(image);
			photo.setText("");
		}
		
		
		// M�thode de transmission du nom
		public String getCheminPhoto() {
			return pathPhoto;
		}
}