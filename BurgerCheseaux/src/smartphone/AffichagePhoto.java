package smartphone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class AffichagePhoto extends JPanel {

	// D�finition des attributs pour la layout de l'affichage
	private GridBagLayout grille = new GridBagLayout();
	private GridBagConstraints contraintes = new GridBagConstraints();
	
	
	// Cr�ation du panel transparent qui contient les diff�rents boutons
	private JPanel panelBoutons = new JPanel();
	
	private ImageIcon iconeGauche = new ImageIcon(new ImageIcon().getClass().getResource("/Fleche_Gauche.png"));
	private Image logoGauche = iconeGauche.getImage().getScaledInstance(65, 30, Image.SCALE_SMOOTH);	
	private JButton flecheGauche = new JButton(new ImageIcon(logoGauche));
	
	private ImageIcon iconeDroite = new ImageIcon(new ImageIcon().getClass().getResource("/Fleche_Droite.png"));
	private Image logoDroite = iconeDroite.getImage().getScaledInstance(65, 30, Image.SCALE_SMOOTH);	
	private JButton flecheDroite = new JButton(new ImageIcon(logoDroite));
	
	private ImageIcon iconePoubelle = new ImageIcon(new ImageIcon().getClass().getResource("/Bouton_Supprimer.png"));
	private Image logoPoubelle = iconePoubelle.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private JButton supprimer = new JButton(new ImageIcon(logoPoubelle));
	
	private JButton retour = new JButton("Retour");
	
	
	// D�finition des attributs concernant l'image de la galerie
	private int index;
	private File[] listeImages;

	private Image imageGalerie;
	private int largeur;
	private int hauteur;
	
	
	public AffichagePhoto(int indice, File[] listeImages) {

		// Passage des valeurs au constructeur
		index = indice;
		this.listeImages = listeImages;

		// R�cup�ration de l'ImageIcon dans une Image pour pouvoir la repeindre
		ImageIcon image = new ImageIcon(listeImages[index].getAbsolutePath());
		imageGalerie = image.getImage();
		largeur = image.getIconWidth();
		hauteur = image.getIconHeight();
	

		// D�finition du format du panel transparent
		panelBoutons.setPreferredSize(new Dimension(500, 750));
		panelBoutons.setBackground(new Color(0, 0, 0, 0));

		
		//Ajout des ActionListener sur les diff�rents boutons
		retour.addActionListener(new Clic_Back());
		flecheDroite.addActionListener(new Clic_Next());
		flecheGauche.addActionListener(new Clic_Previous());
		supprimer.addActionListener(new Clic_Supprimer());
		
		
		// D�finition des dimensions des boutons
		flecheDroite.setPreferredSize(new Dimension(65, 30));
		flecheGauche.setPreferredSize(new Dimension(65, 30));
		retour.setPreferredSize(new Dimension(170, 30));
		supprimer.setPreferredSize(new Dimension(30, 30));

		
		// D�finition des contraintes pour le layout		
		panelBoutons.setLayout(grille);
		contraintes.weighty = 1;
		
		// Bouton "Retour", la grille commence en (0, 0)
		contraintes.gridx = 0;
		contraintes.gridy = 0;		
		// Une seule cellule disponible pour le bouton "Annuler"
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 0, 250, 0);
		panelBoutons.add(retour, contraintes);
		
		// Bouton "Poubelle", position � (0, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 0;	
		// Dernier composant de sa ligne
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 250, 0);
		panelBoutons.add(supprimer, contraintes);
		
		// Bouton de la fl�che gauche, positionnement sur la deuxi�me ligne
		contraintes.gridx = 0;
		contraintes.gridy = 1;	
		// Une seule cellule disponible pour la fl�che � gauche
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(0, 0, 375, 165);	
		panelBoutons.add(flecheGauche, contraintes);
		
		// Bouton de la fl�che droite, positionnement � (1, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 1;	
		// Dernier composant de sa ligne
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 165, 375, 0);	
		panelBoutons.add(flecheDroite, contraintes);

		add(panelBoutons);
	}
	
	
	// M�thode pour repeindre le fond du panel avec l'image de la galerie
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		if (imageGalerie != null) { 
            double hauteur = this.hauteur;
            double largeur = (double) this.largeur;
            int height = 0;
            int width = 0;
            double facteur;
            int vide;
            int centre = 0;
            
            // L'image est au format "Portrait"
            if(hauteur > largeur) {
            	// Recherche du facteur pour le redimensionnement
            	facteur = hauteur/650.0;
            	
            	// La hauteur repr�sente la hauteur compl�te de l'image
            	height = 650;
            	
            	// La largeur est calcul�e en fonction du facteur
            	largeur = largeur/facteur;
            	width = (int) largeur;
            	
            	// L'image doit �tre centr�e sur sa largeur
            	vide = 490-width;
            	centre = vide/2;
            	
            	g.drawImage(imageGalerie, centre, 0, width, height, this);
            }
            
            
            // L'image est au format "Paysage"
            if(largeur > hauteur) {
            	// Recherche du facteur pour le redimensionnement
            	facteur = largeur/490.0;
            	
            	// La largeur repr�sente la largeur compl�te de l'image
            	width = 490;
            	
            	// La hauteur est calcul�e en fonction du facteur
            	hauteur = hauteur/facteur;
            	height = (int) hauteur;
            	
            	// L'image doit �tre centr�e sur sa hauteur
            	vide = 650-height;
            	centre = vide/2;
            	
            	g.drawImage(imageGalerie, 0, centre, width, height, this);
            }
            
            
            // L'image est carr�e
            if(largeur == hauteur) {
            	// L'image prend le maximum de place possible, tout en restant dans le champ du panel
            	width = 490;
            	height = 490;
            	
            	vide = 650-490;
            	centre = vide/2;
            	
            	g.drawImage(imageGalerie, 0, centre, width, height, this);
            }
        } 
    }
	
	
	// D�finition de l'action du bouton de retour
	class Clic_Back implements ActionListener {
		public void actionPerformed(ActionEvent e1) {
			Structure.changePanel(2);
		}
	}
	
	
	// D�finition de l'action du bouton de la fl�che "Next"
	class Clic_Next implements ActionListener {
		public void actionPerformed(ActionEvent e2) {
			// Si l'index est � la fin, il ne doit pas continuer
			if(index == listeImages.length-1) {
				// Ne rien faire
			}
			// Sinon, il montre l'image suivante de la galerie
			else {
				index++;
				Structure.changeImage(6, index, listeImages);
			}
		}
	}
	
	
	// D�finition de l'action du bouton de la fl�che "Previous"
	class Clic_Previous implements ActionListener {
		public void actionPerformed(ActionEvent e3) {
			// Si l'indice est positionn� sur la premi�re image, il n'est pas possible d'aller plus en arri�re
			if(index == 0) {
				// Ne rien faire
			}
			// Sinon, il montre l'image pr�c�dente de la galerie
			else {
				index--;
				Structure.changeImage(6, index, listeImages);
			}
		}
	}
	
	
	// D�finition de l'action du bouton de suppression
	class Clic_Supprimer implements ActionListener {
		public void actionPerformed(ActionEvent e4) {
			File fichier = new File(listeImages[index].getAbsolutePath());
			
			JOptionPane confirmation = new JOptionPane();
			int option = JOptionPane.showOptionDialog(null, "�tes-vous s�r de vouloir supprimer d�finitivement cette image ?",
					"Supprimer l'image", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[] {"Supprimer", "Annuler"}, "Annuler");
			
			if(option == JOptionPane.OK_OPTION) {
				// Suppression de l'image et retour � la galerie
				fichier.delete();
				Structure.refresh();
			}
		}
	} 
}