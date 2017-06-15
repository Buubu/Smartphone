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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;


public class ListeContacts extends JPanel {
	
	// D�finition des attributs repr�sentant tous les composants
	private JTextField recherche = new JTextField("Recherche");
	private JButton ajouter = new JButton("+");
	
	private ImageIcon iconeLoupe = new ImageIcon(new ImageIcon().getClass().getResource("/Loupe.png"));
	private Image logoLoupe = iconeLoupe.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	private JButton loupe = new JButton(new ImageIcon(logoLoupe));
	
	
	// D�finition des attributs pour la liste des contacts
	private JPanel liste = new JPanel();
	private GridLayout grille = new GridLayout();
	private JScrollPane scroll = new JScrollPane(liste);

	private String pathContacts = "stockage/ListeContacts";
	private File folder = new File(pathContacts);
	
	
	// Cr�ation de l'image de fond pour le panel
	private ImageIcon imageFond = new ImageIcon(new ImageIcon().getClass().getResource("/Fond_Panel.jpg"));
	private Image fond = imageFond.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT);
	
	
	// D�finition des attributs pour le layout de l'�cran
	private GridBagLayout positionnement = new GridBagLayout();
	private GridBagConstraints contraintes = new GridBagConstraints();
	
	
	// D�fintion de l'attribut qui sera transmis lors de l'affichage du contact
	private String affichageContact;
	
	
	public ListeContacts() {
		
		// V�rification du r�pertoire des contacts	
		if(folder.exists() == false) {
			folder.mkdir();
		}
		
		
		// D�finition des dimensions des composants et du fond pour la recherche
		Dimension bouton = new Dimension(50, 50);
		Dimension champ = new Dimension(200, 50);
		
		loupe.setPreferredSize(bouton);
		ajouter.setPreferredSize(bouton);
		recherche.setPreferredSize(champ);
		

		// Cr�ation de la grille pour la liste des contacts
		grille.setColumns(1);	
		
		// Test pour un affichage propre dans le GridLayout
		int nbrLignes = folder.list().length;
		
		if(nbrLignes<10) {
			grille.setRows(10);
		}
		else {
			grille.setRows(nbrLignes);
		}
		
		liste.setLayout(grille);
		
		
		// D�finition des actions des composants
		Clic_Ajout clicAjout = new Clic_Ajout();
		ajouter.addActionListener(clicAjout);
		
		Clic_Recherche clicRecherche = new Clic_Recherche();
		recherche.addFocusListener(clicRecherche);
		
		
		// Insertion de la liste des contacts dans le panel une premi�re fois au d�marrage
		String[] listeNoms = folder.list();
		Font policeContacts = new Font("Arial", Font.BOLD, 26);
		
		for(int i=0; i<listeNoms.length; i++) {
			String nom = listeNoms[i].replace("_", " ");
			nom = nom.replace(".txt", "");
			JButton contact = new JButton(nom);
			Clic_Contact clicContact = new Clic_Contact(contact);
			contact.addActionListener(clicContact);
			contact.setFont(policeContacts);
			liste.add(contact);
		}	
		// Puis rafra�chissement chaque seconde pour avoir la liste � jour
		// D�finition d'une seconde en millisecondes
		int wait = 1000;
		
		TicTac tictac = new TicTac();
		Timer timer = new Timer(wait, tictac);
		timer.start();
		
		
		// D�finition de l'action de la recherche
		Clic_Loupe clicLoupe = new Clic_Loupe(timer);
		loupe.addActionListener(clicLoupe);
		

		// D�finition du format du JTextField de la recherche et � l'ajout
		Font policeRecherche = new Font("Arial", Font.PLAIN, 18);
		recherche.setFont(policeRecherche);
		
		Font policeAjout = new Font("Arial", Font.PLAIN, 24);
		ajouter.setFont(policeAjout);
		
		
		// D�finition du format du scroll sur la liste des contacts
		scroll.setPreferredSize(new Dimension(200, 420));
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		

		// Application de notre layout sur le panel de l'�cran
		setLayout(positionnement);
		
		
		// D�finition des contraintes pour le layout
		// Barre de recherche, la grille commence en (0, 0)
		contraintes.gridx = 0;
		contraintes.gridy = 0;	
		// Une seule cellule disponible pour la barre de recherche
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;	
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.BASELINE_LEADING;
		contraintes.insets = new Insets(0, 30, 80, 0);
		add(recherche, contraintes);
		
		
		// Bouton de la loupe, positionnement � (0, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 0;	
		// Une seule cellule disponible pour la loupe
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.BASELINE;
		contraintes.insets = new Insets(0, 0, 80, 0);
		add(loupe, contraintes);
		
		
		// Bouton d'ajout d'un nouveau contact, positionnement � (0, 2)
		contraintes.gridx = 2;
		contraintes.gridy = 0;	
		// Ce composant est le dernier de sa ligne
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 80, 30);
		contraintes.weightx = 0.1;
		add(ajouter, contraintes);
		
	
		// Liste des contacts, positionnement � la deuxi�me ligne
		contraintes.gridx = 0;
		contraintes.gridy = 1;	
		// Le composant est le dernier de sa ligne et de sa colonne et peut donc s'�tendre
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(0, 30, 30, 30);
		// On remet � "0" la distribution horizontale	
		contraintes.weightx = 1;
		add(scroll, contraintes);
		
		scroll.requestFocusInWindow();
	}
	
	
	// D�finition de l'action du bouton "+" pour ajouter un contact
	class Clic_Ajout implements ActionListener {
		public void actionPerformed(ActionEvent e1) {
			Structure.changePanel(3);
		}
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
	
	
	// Cr�ation de l'action de la souris sur le champ de recherche
	class Clic_Recherche implements FocusListener {
		
		// Au clic, si le contenu n'est plus celui de base, il reste affich�. Sinon, il s'efface pour que l'on puisse saisir nos informations
		public void focusGained(FocusEvent e2) {
			if(recherche.getText().equals("Recherche")) {
				recherche.setText("");	
			}
			else {}			
		}
		
		// Lorsque l'on quitte le champ, s'il est vide, le texte par d�faut revient. Sinon, le nouveau texte reste inscrit
		public void focusLost(FocusEvent e3) {
			if(recherche.getText().equals("")) {
				recherche.setText("Recherche");
			}
			else {
				recherche.setText(recherche.getText());
			}				
		}
	}
	
	
	// Cr�ation de l'action lors du clic sur la loupe de recherche
	class Clic_Loupe implements ActionListener {
		String[] listeFichiers;
		String contenu;
		String partiePrenom;
		String partieNom;
		String reset = "Recherche";
		Timer initial;
		
		
		// D�finition du constructeur en passant le timer initial
		public Clic_Loupe(Timer timer) {
			initial = timer;
		}
		
		
		public void actionPerformed(ActionEvent e4) {
			// Stopper le timer initial pour ne plus que le rafra�chissement ait lieu durant la recherche
			initial.stop();
						
			// R�cup�ration du contenu de la recherche
			contenu = recherche.getText();
						
			// Tester la recherche, recherche de contact ou recherche vide et r�initialisation
			if(contenu.equals(reset)) {
				initial.start();
			}
			else {
				// Cr�ation d'un tableau pour la recherche des contacts
				ArrayList<String> listeRecherche = new ArrayList<String>();

				// R�cup�ration de tous les noms de fichiers contacts et initialisation du compteur
				listeFichiers = folder.list();

				for(int i=0; i<listeFichiers.length; i++) {
					partiePrenom = listeFichiers[i].substring(0, listeFichiers[i].indexOf('_'));
					partieNom = listeFichiers[i].substring(listeFichiers[i].indexOf('_')+1, listeFichiers[i].indexOf('.'));
					
					if((partiePrenom.toLowerCase()).startsWith(contenu.toLowerCase()) || (partieNom.toLowerCase()).startsWith(contenu.toLowerCase())) {
						listeRecherche.add(new String(partiePrenom + " " + partieNom));
					}
					else {}
				}
				
				// Effacement de la liste de contacts initiale			
				liste.removeAll();
				
				// R�insertion du layout sur le panel, en recalculant le nombre de lignes
				int nbrLignes = listeRecherche.size();
				
				if(nbrLignes<10) {
					grille.setRows(10);
				}
				else {
					grille.setRows(nbrLignes);
				}
			
				liste.setLayout(grille);
				
				// Affichage de la liste de recherche � l'�cran
				Font policeContacts = new Font("Arial", Font.BOLD, 26);
			
				for(int i=0; i<listeRecherche.size(); i++) {
					JButton contact = new JButton(listeRecherche.get(i));
					Clic_Contact clicContact = new Clic_Contact(contact);
					contact.addActionListener(clicContact);
					contact.setFont(policeContacts);
					liste.add(contact);
				}
				
				liste.validate();
				liste.repaint();
			}
		}
	}
	
	
	// Cr�ation de l'action lors du clic sur un contact de la liste
	class Clic_Contact implements ActionListener {
		private JButton contact;
		private String nomContact;
		
		// Constructeur de la classe qui r�cup�re le bouton affect� par l'action
		public Clic_Contact(JButton bouton) {
			contact = bouton;
		}
		
		public void actionPerformed(ActionEvent e5) {
			nomContact = contact.getText();
			if(nomContact.indexOf(' ') == 0 || nomContact.indexOf(' ') > 0) {
				nomContact = nomContact.replace(" ", "_");
			}
			else {
				nomContact.concat("_");
			}
			nomContact = nomContact.concat(".txt");
			affichageContact = nomContact;
			
			try {
				AffichageContact.recupererNomFichier(affichageContact);
			} catch (IOException e) {
				System.out.println(affichageContact);
				JOptionPane erreur = new JOptionPane();
				int option = JOptionPane.showOptionDialog(null, "Une erreur est survenue lors de l'acc�s au contact, veuillez r�essayer.",
						"Acc�s interrompu", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);	
			}
			
			Structure.changePanel(4);			
		}
	}
	
	
	// Cr�ation du rafra�chissement de la liste des contacts
	class TicTac implements ActionListener {		
		public void actionPerformed(ActionEvent e6) {
			liste.removeAll();
			
			// R�insertion du layout sur le panel, en recalculant le nombre de lignes
			int nbrLignes = folder.list().length;
			
			if(nbrLignes<10) {
				grille.setRows(10);
			}
			else {
				grille.setRows(nbrLignes);
			}
		
			liste.setLayout(grille);
			
			// Red�finition de la liste � afficher
			String[] listeNoms = folder.list();
			Font policeContacts = new Font("Arial", Font.BOLD, 26);
			
			for(int i=0; i<listeNoms.length; i++) {
				String nom = listeNoms[i].replace("_", " ");
				nom = nom.replace(".txt", "");
				JButton contact = new JButton(nom);
				Clic_Contact clicContact = new Clic_Contact(contact);
				contact.addActionListener(clicContact);
				contact.setFont(policeContacts);
				liste.add(contact);
			}
			
			liste.validate();
			liste.repaint();
		}
	}
}