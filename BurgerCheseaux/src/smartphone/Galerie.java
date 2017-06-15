package smartphone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import smartphone.ListeContacts.TicTac;


public class Galerie extends JPanel {
	
	// Définition des attributs pour la galerie
	private GridLayout grille = new GridLayout();
	private JPanel galerie = new JPanel();
	private JPanel bandeHaut = new JPanel();
	
	private GridBagLayout positionnement = new GridBagLayout();
	private GridBagConstraints contraintes = new GridBagConstraints();
	
	private JButton boutonPlus = new JButton("+");
	
	private ImageIcon imageRefresh = new ImageIcon(new ImageIcon().getClass().getResource("/Bouton_Refresh.png"));
	private Image logoRefresh = imageRefresh.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
	private JButton boutonActualiser = new JButton(new ImageIcon(logoRefresh));
	
	
	Font policePlus = new Font("Arial", Font.PLAIN, 24);
	
	private File dossierImages = new File ("stockage/Galerie");
	private File[] nomsImages = dossierImages.listFiles();
	
	
	// Création de l'image de fond pour le panel
	private ImageIcon imageFond = new ImageIcon(new ImageIcon().getClass().getResource("/Fond_Panel.jpg"));
	private Image fond = imageFond.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT);
	
	
	public Galerie(){

		// Utilisation de la méthode qui charge les image dans le panel, le layout est défini au même moment (paramètres : taille des images)
		galerie = loadImages(160, 180);
		
		
		// Application du scroll sur le JPanel "galerie"
		JScrollPane scroll = new JScrollPane(galerie);
		scroll.setPreferredSize(new Dimension(490, 570));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		

		// Définition du fond d'écran du panel de la galerie		
		galerie.setBackground(new Color(0, 0, 0, 0));
		
		
		// Ajout des boutons à la bande du haut
		boutonActualiser.setPreferredSize(new Dimension(50, 50));
		boutonActualiser.addActionListener(new Clic_Refresh());
		boutonPlus.setPreferredSize(new Dimension(50, 50));
		boutonPlus.addActionListener(new Clic_Ajouter());
		boutonPlus.setFont(policePlus);
		
		bandeHaut.setLayout(positionnement);
		
		// Définition des contraintes
		// Bouton "Actualiser", position (0, 0)
		contraintes.gridx = 0;
		contraintes.gridy = 0;		
		// Une seule cellule disponible pour le bouton "Actualiser"
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 0, 40);
		bandeHaut.add(boutonActualiser, contraintes);
		
		// Bouton "Ajouter", position (0, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 0;		
		// Dernier composant de sa ligne
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 40, 0, 0);
		bandeHaut.add(boutonPlus, contraintes);

		bandeHaut.setBackground(new Color(0, 0, 0, 0));
		add(bandeHaut);
		
		
		// Ajout du scroll au JPanel de la Galerie
		add(scroll);
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
	
	
	// Méthode qui permet de retourner le dossier des images de la galerie
	protected File[] getdossierImages(){
		return nomsImages;
	}

	
	// Méthode qui permet de modifier le dossier des images de la galerie
	protected void setdossierImage(File [] listeNoms){
		this.nomsImages = listeNoms;
	}
	
	
	// Méthode d'ajout d'images dans le panel GridLayout
	private JPanel loadImages(int largeurImage, int hauteurImage){	
		dossierExist();
		
		return galerie = controleFichiers(largeurImage, hauteurImage);
	}
	
	private void dossierExist() {
		if(dossierImages.exists() && dossierImages.isDirectory()) {
			// Ne rien faire
		}
		else {
			dossierImages.mkdir();
		}
	}
	
	
	// Méthode qui contrôle si les fichiers insérés sont bien des images, sinon il supprime le fichier du dossier
	private JPanel controleFichiers(int largeurImage, int hauteurImage) {
		double calcul;
		int nbrLignes;
		Border border = LineBorder.createGrayLineBorder();
		
		// Définition de la taille de la grille, sur notre grille, rapport de 2 (pour 6 éléments, 3 lignes - pour 8 éléments, 4 lignes, etc...)
		// Calcul du nombre de lignes
		calcul = (dossierImages.list().length)/3.0;
		
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
		
		
		// Définition du layout sur le JPanel
		JPanel panelImages = new JPanel(grille);
		
		
		// Ajout des images
		for(int i=0; i<nomsImages.length; i++) {
			String name = nomsImages[i].getName();
			String nameSubs = name.substring(nomsImages[i].getName().length()-3, nomsImages[i].getName().length());
			
			if(nameSubs.equals("jpg") || nameSubs.equals("png")) {
				JLabel labelImage = new JLabel();
				labelImage = addImage(nomsImages, i, largeurImage, hauteurImage);
				labelImage.setBorder(border);
				panelImages.add(labelImage);
			}
			else {
				nomsImages[i].delete();
			}
		}
		
		// Ajout de cases vides pour positionner les photos de manière propre	
		for(int i=dossierImages.list().length; i<9; i++) {
			JLabel caseVide = new JLabel();
			caseVide.setPreferredSize(new Dimension(120, 90));
			caseVide.setBorder(border);
			panelImages.add(caseVide);
		}	
		
		return panelImages;		
	}
	
	
	// Méthode pour ajouter les images au JPanel de la galerie
	protected JLabel addImage(File[] listeFichiers, int indice, int largeur, int hauteur) {
		ImageIcon imageResized;
		
		ImageIcon image = new ImageIcon(listeFichiers[indice].getAbsolutePath());
		imageResized = resizeImage(image, largeur, hauteur);
		
		JLabel labelImage = new JLabel(imageResized);
		
		//Ajout d'un MouseListener à chaque JLabel, transmission de la position et du tableau des références images
		labelImage.addMouseListener(new Clic_Image(indice, listeFichiers));
		return labelImage;
	}
	
	
	// Définition du MouseListener pour les JLabel
	class Clic_Image implements MouseListener {
		private int indice;
		private File[] listeImages;
		
		
		// Définition du constructeur
		public Clic_Image(int indice, File[] liste) {
			// Passage des valeurs transmises par le MouseListener de chaque image
			this.indice = indice;
			listeImages = liste;
		}
		
	
		public void mouseClicked(MouseEvent e1) {
			// Au clic sur l'image, on change de fenêtre en lui transmettant les paramètres, pour qu'il puisse charger la bonne image
			Structure.changeImage(4, indice, listeImages);
		}
		
		public void mouseEntered(MouseEvent e2) {}
		
		public void mouseExited(MouseEvent e3) {}
		
		public void mousePressed(MouseEvent e4) {}
		
		public void mouseReleased(MouseEvent e5) {}
	}

	
	// Méthode pour le redimensionnement des images
	protected ImageIcon resizeImage(ImageIcon imageIcon, int largeur, int hauteur) {
		
	      Image image = imageIcon.getImage();
	      Image nouvelleImage = image.getScaledInstance(largeur, hauteur, java.awt.Image.SCALE_SMOOTH);
	      imageIcon = new ImageIcon(nouvelleImage);
	      
	      return imageIcon;
	}
	
	
	// Méthode de rafraîchissement de la galerie
	class Clic_Refresh implements ActionListener {
		public void actionPerformed(ActionEvent e6){
			Structure.refresh();
		}
	}
	
	
	// Définition de l'action lors du clic sur le bouton d'ajout
	class Clic_Ajouter implements ActionListener {
		public void actionPerformed(ActionEvent e7){
			// Ouvrir un mini-explorateur Windows (par défaut, dans Documents)
			JFileChooser chooser = new JFileChooser();
			int statut = chooser.showOpenDialog(null);
			
			if (statut == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				
		    	if (file == null) {
		    		return;
		    	}
		    	
		    	// Sauver le chemin de l'image sélectionnée
		    	String cheminFichier = chooser.getSelectedFile().getAbsolutePath();
		    	File source = new File(cheminFichier);
		    	cheminFichier = cheminFichier.substring(cheminFichier.lastIndexOf('\\')+1);
		    	
		    	// Copie du fichier sélectionné dans la galerie
		    	try { 
		    		// Canal d'entrée
		    		FileChannel in = new FileInputStream(source).getChannel();
		    		// Canal de sortie
		    		FileChannel out = new FileOutputStream("stockage/galerie/"+cheminFichier).getChannel();
		    		// Copie depuis le in vers le out
		    		in.transferTo(0, in.size(), out); 	
		    		in.close();
		    		out.close();
		    	} catch (IOException e) { 
		    		JOptionPane erreur = new JOptionPane();
					int option = JOptionPane.showOptionDialog(null, "Une erreur est survenue au moment de la copie de l'image, veuillez réessayer.",
							"Copie interrompue", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
					
					if(option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
						Structure.changePanel(2);
					}
		    	}
			}
		}
	}
}