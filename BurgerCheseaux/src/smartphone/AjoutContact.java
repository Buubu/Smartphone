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


public class AjoutContact extends JPanel {
	
	// Définition des attributs pour le layout du formulaire
	private GridBagLayout grille = new GridBagLayout();
	private GridBagConstraints contraintes = new GridBagConstraints();
	
	// Définition des attributs représentant tous les composants
	private static JButton ajouterPhoto = new JButton("Photo");
	private JButton annuler = new JButton("Annuler");
	private JButton enregistrer = new JButton("Enregistrer");	
	private JTextField prenom = new JTextField("Prénom");
	private JTextField nom = new JTextField("Nom");
	private JTextField telephone = new JTextField("N° de téléphone");
	private JTextField mail = new JTextField("Email");
	private JTextField naissance = new JTextField("Date de naissance");
	private static String cheminPhoto = "";
	
	// Création de l'image de fond pour le panel
	private ImageIcon imageFond = new ImageIcon(new ImageIcon().getClass().getResource("/Fond_Panel.jpg"));
	private Image fond = imageFond.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT);
	
	// Attributs de la liste de contacts
	private String pathContacts = "stockage/ListeContacts";
	private File folder = new File(pathContacts);
	
		
	public AjoutContact() {
		
		// Application de notre layout sur le panel
		setLayout(grille);
		
		
		// Définition de tous les champs
		// Prénom
		Clic_Formulaire clicFormulairePrenom = new Clic_Formulaire(prenom);
		prenom.addFocusListener(clicFormulairePrenom);
		
		// Nom
		Clic_Formulaire clicFormulaireNom = new Clic_Formulaire(nom);
		nom.addFocusListener(clicFormulaireNom);
		
		// Numéro de téléphone
		Clic_Formulaire clicFormulaireTel = new Clic_Formulaire(telephone);
		telephone.addFocusListener(clicFormulaireTel);
			
		// Email
		Clic_Formulaire clicFormulaireMail = new Clic_Formulaire(mail);
		mail.addFocusListener(clicFormulaireMail);
		
		// Date de naissance
		Clic_Formulaire clicFormulaireDate = new Clic_Formulaire(naissance);
		naissance.addFocusListener(clicFormulaireDate);
		
		
		// Définition des dimensions des composants
		Dimension boutonPhoto = new Dimension(120, 120);
		Dimension boutonChoix = new Dimension(170, 30);
		
		annuler.setPreferredSize(boutonChoix);
		enregistrer.setPreferredSize(boutonChoix);
		ajouterPhoto.setPreferredSize(boutonPhoto);
		
		
		// Définition de l'action du bouton "Annuler"
		Clic_Annuler clicAnnuler = new Clic_Annuler();
		annuler.addActionListener(clicAnnuler);
		
		
		// Définition de l'action du bouton "Photo"
		Clic_Photo clicPhoto = new Clic_Photo();
		ajouterPhoto.addActionListener(clicPhoto);
		
		
		// Défintion du format des JTextField du formulaire
		Font police = new Font("Arial", Font.PLAIN, 18);
		
		prenom.setFont(police);
		nom.setFont(police);
		telephone.setFont(police);
		mail.setFont(police);
		naissance.setFont(police);	
		ajouterPhoto.setFont(police);
		
		
		// Définition des contraintes pour le layout		
		// Bouton "Annuler", la grille commence en (0, 0)
		contraintes.gridx = 0;
		contraintes.gridy = 0;		
		// Une seule cellule disponible pour le bouton "Annuler"
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 30, 120, 0);
		add(annuler, contraintes);
		
		
		// Bouton "Enregistrer", positionnement à (0, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 0;	
		// Dernier composant de sa ligne
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 120, 30);
		add(enregistrer, contraintes);
		
		
		// Bouton de l'ajout d'une photo, positionnement sur la deuxième ligne
		contraintes.gridx = 0;
		contraintes.gridy = 1;	
		// Le bouton va prendre deux cellules verticalement
		contraintes.gridwidth = 1;
		contraintes.gridheight = 2;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 30, 50, 0);	
		contraintes.weightx = 0.1;
		add(ajouterPhoto, contraintes);
		
		
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

		
		// Définition de l'action du bouton "Enregistrer"
		Clic_Enregistrer clicEnregistrer = new Clic_Enregistrer(prenom, nom, telephone, mail, naissance);
		enregistrer.addActionListener(clicEnregistrer);
	}
	
	
	// Méthode permettant la modification du bouton depuis la classe "ChoixPhoto"
	protected static void setJButton(ImageIcon image){
		ajouterPhoto.setIcon(image);
		ajouterPhoto.setText("");
	}
	
	
	// Création de l'action de la souris sur les champs de formulaire
	class Clic_Formulaire implements FocusListener {
		private JTextField field;
		private String contenu;
				
		// Définition du constructeur pour l'adapter à tous les champs du formulaire
		public Clic_Formulaire(JTextField field) {
			this.field = field;
			contenu = field.getText();
		}

		// Au clic, si le contenu n'est plus celui de base, il reste affiché. Sinon, il s'efface pour que l'on puisse saisir nos informations
		public void focusGained(FocusEvent e1) {
			if(field.getText().equals(contenu)) {
				field.setText("");	
			}
			else {}			
		}
		
		// Lorsque l'on quitte le champ, s'il est vide, le texte par défaut revient. Sinon, le nouveau texte reste inscrit
		public void focusLost(FocusEvent e2) {
			if(field.getText().equals("")) {
				field.setText(contenu);
			}
			else {
				field.setText(field.getText());
			}				
		}
	}
	
	
	// Création de l'action du bouton "Annuler"
	class Clic_Annuler implements ActionListener {
		public void actionPerformed(ActionEvent e3) {
			JOptionPane confirmation = new JOptionPane();
			int option = JOptionPane.showOptionDialog(null, "Toutes les modifications seront annulées.", 
					"Ignorer les modifications", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] {"Ignorer", "Annuler"}, "Ignorer");
			
			if(option == JOptionPane.OK_OPTION) {
				// Remise à "zéro" du contenu des JTextField
				prenom.setText("Prénom");
				nom.setText("Nom");
				telephone.setText("N° de téléphone");
				mail.setText("Email");
				naissance.setText("Date de naissance");
				
				Structure.changePanel(1);
			}
		}
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
	
	
	// Méthode de remise à zéro des JTextField
	private void resetTextField() {
		prenom.setText("Prénom");
		nom.setText("Nom");
		telephone.setText("N° de téléphone");
		mail.setText("Email");
		naissance.setText("Date de naissance");
		setJButton(null);
		ajouterPhoto.setText("Photo");
	}
	
	
	// Création de l'action du bouton "Enregistrer" au moment du clic
	class Clic_Enregistrer implements ActionListener {
		private JTextField prenom;
		private JTextField nom;
		private JTextField telephone;
		private JTextField mail;
		private JTextField naissance;
		private String cheminPhoto = "";
		
		
		// Définition du constructeur pour récupérer les contenus saisis
		public Clic_Enregistrer(JTextField prenom, JTextField nom, JTextField telephone, JTextField mail, JTextField naissance) {
			this.prenom = prenom;
			this.nom = nom;
			this.telephone = telephone;
			this.mail = mail;
			this.naissance = naissance;
		}
		
		
		// Récupération du contenu des champs (ATTENTION, au moment du clic pour enregistrer les éventuelles saisies utilisateur)
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
			if(contenuPrenom.equals("Prénom") && contenuNom.equals("Nom")) {
				JOptionPane refus = new JOptionPane();
				int option = JOptionPane.showOptionDialog(null, "Vous devez au moins saisir le prénom ou le nom.", "Erreur contact",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
			}
			else {
				// Détermination du nom du fichier qui va être créé
				String partiePrenom;
				String partieNom;
				
				if(contenuPrenom.equals("Prénom")) {
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
				if(fichier.exists() == true) {
					JOptionPane attention = new JOptionPane();
					int option = JOptionPane.showOptionDialog(null, "Ce contact existe déjà.", "Erreur contact", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
					
					if(option == JOptionPane.OK_OPTION || option == JOptionPane.CANCEL_OPTION) {
						// Remise à "zéro" du contenu des JTextField
						resetTextField();
						
						Structure.changePanel(1);
					}
				}
				else {
					// Gestion d'une exception au cas où le contact ne pourrait pas se créer, simple fenêtre d'erreur demandant de réessayer
					try {
						Contact contact = new Contact(contenuPrenom, contenuNom, contenuTelephone, contenuMail, contenuNaissance, cheminPhoto);
					} catch (IOException e) {
						JOptionPane erreur = new JOptionPane();
						int option = JOptionPane.showOptionDialog(null, "Une erreur est survenue au moment de l'enregistrement, veuillez réessayer.", 
								"Enregistrement interrompu", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
						
						if(option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
							// Remise à "zéro" du contenu des JTextField
							resetTextField();
							
							Structure.changePanel(3);
						}
					}

					JOptionPane confirmation = new JOptionPane();
					int option = JOptionPane.showOptionDialog(null, "Contact enregistré.", "Nouveau contact",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
					
					if(option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
						// Remise à "zéro" du contenu des JTextField
						resetTextField();
						
						Structure.changePanel(1);
					}
				}		
			}
		}			
	}
	
	
	// Création de l'action du bouton d'ajout d'une photo
	class Clic_Photo implements ActionListener {
		private GridLayout grille = new GridLayout(); 
		private Galerie galerie = new Galerie();

		private File folder = new File("stockage/Galerie");
		private double calcul;
		private int nbrLignes;
		
		private Border border = LineBorder.createGrayLineBorder();
		

		public void actionPerformed(ActionEvent e5) {
			// Définition de la taille de la grille, sur notre grille, rapport de 2 (pour 6 éléments, 3 lignes - pour 8 éléments, 4 lignes, etc...)
			// Calcul du nombre de lignes
			calcul = (folder.list().length)/2.0;

			BigDecimal arrondi = new BigDecimal(calcul);
			arrondi = arrondi.setScale(0, BigDecimal.ROUND_CEILING);
			nbrLignes = arrondi.intValue();

			// Test pour que le nombre de lignes soit au moins égal à 3 (question esthétique)
			if(nbrLignes < 3) {
				grille.setRows(3);
			}
			else {
				grille.setRows(nbrLignes);
			}
			
			// Utilisation de la méthode qui va chercher le dossier d'images
			File[] listeImages = galerie.getdossierImages();
			
			// Définition du format du JPanel
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
			
			// Ajout de cases vides pour positionner les photos de manière propre	
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
	
	
	// Définition de l'action du clic sur les images lors de l'ajout d'une photo
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
			ImageIcon image = new ImageIcon(listeImages[indice].getAbsolutePath());
			ImageIcon imageDimension = galerie.resizeImage(image, 120, 120);
			
			AjoutContact.recupererNomImage(listeImages[indice].getAbsolutePath());
			AjoutContact.setJButton(imageDimension);
		}
		
		public void mouseEntered(MouseEvent e7) {}

		public void mouseExited(MouseEvent e8) {}

		public void mousePressed(MouseEvent e9) {}

		public void mouseReleased(MouseEvent e10) {}
	}
	
	
	// Méthode de récupération du nom de la photo sélectionnée
	public static void recupererNomImage(String nom){
		cheminPhoto = nom;
	}
	
	
	// Méthode de transmission du nom
	public String getCheminPhoto() {
		return cheminPhoto;
	}
}