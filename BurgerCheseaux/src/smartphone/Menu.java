package smartphone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JPanel {	
	
		// D�finition de l'attribut pour la grille d'applications
		private GridLayout grille = new GridLayout();
		
		
		// D�finition des attributs pour l'application des contacts
		private ImageIcon iconeContacts = new ImageIcon(new ImageIcon().getClass().getResource("/Bouton_Contacts.png"));
		private Image logoContacts = iconeContacts.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);	
		private JButton boutonContacts = new JButton(new ImageIcon(logoContacts));
		private JLabel nomContacts = new JLabel("Contacts");
		private JPanel caseContacts = new JPanel();
		
		
		// D�finition des attributs pour l'application de la galerie
		private ImageIcon iconeGalerie = new ImageIcon(new ImageIcon().getClass().getResource("/Bouton_Galerie.png"));
		private Image logoGalerie = iconeGalerie.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		private JButton boutonGalerie = new JButton(new ImageIcon(logoGalerie));
		private JLabel nomGalerie = new JLabel("Galerie");
		private JPanel caseGalerie = new JPanel();
		
		
		// Cr�ation de l'image de fond d'�cran du smartphone
		private ImageIcon imageBackground = new ImageIcon(new ImageIcon().getClass().getResource("/Background.png"));
		private Image background = imageBackground.getImage().getScaledInstance(500, 750, Image.SCALE_DEFAULT);
	
		
	public Menu() {
		
		// Cr�ation des variables pour la mise en forme de la grille et des applications
		JPanel[] vide = new JPanel[14];
		Color transparente = new Color(0, 0, 0, 0);
		Dimension bouton = new Dimension(100, 100);
		Font police = new Font("Arial", Font.BOLD, 16);
			
		
		// Disposition de la grille sur l'�cran		
		grille.setColumns(4);
		grille.setRows(4);		
		grille.setHgap(10);
		grille.setVgap(10);		
		this.setLayout(grille);
			
		
		// Cr�ation de l'espace pour l'application des contacts		
		boutonContacts.setPreferredSize(bouton);
		boutonContacts.setBackground(transparente);
		caseContacts.add(boutonContacts);
		caseContacts.add(nomContacts);
		caseContacts.setBackground(transparente);
			
		nomContacts.setFont(police);
		nomContacts.setForeground(Color.WHITE);
		
		
		// Cr�ation de l'espace pour l'application de la galerie
		boutonGalerie.setPreferredSize(bouton);
		boutonGalerie.setBackground(transparente);
		caseGalerie.add(boutonGalerie);
		caseGalerie.add(nomGalerie);
		caseGalerie.setBackground(transparente);
		
		nomGalerie.setFont(police);
		nomGalerie.setForeground(Color.WHITE);
		
		
		// Remplissage de la grille avec les �l�ments voulus (+ �l�ments vides pour une bonne disposition)
		this.add(caseContacts);
		this.add(caseGalerie);
		
		for(int i=0; i<vide.length; i++) {
			vide[i] = new JPanel();
			vide[i].setBackground(transparente);
			this.add(vide[i]);
		}
		
		
		// Ajout des actions aux deux boutons du menu
		Clic_Contacts clicContacts = new Clic_Contacts();
		boutonContacts.addActionListener(clicContacts);
		
		Clic_Galerie clicGalerie = new Clic_Galerie();
		boutonGalerie.addActionListener(clicGalerie);	
	}
	
	
	// Cr�ation des actions des boutons du menu
	class Clic_Contacts implements ActionListener {
		public void actionPerformed(ActionEvent e1) {
			Structure.changePanel(1);
		}
	}
	
	class Clic_Galerie implements ActionListener {
		public void actionPerformed(ActionEvent e2) {
			Structure.changePanel(2);
		}
	}
	
		
	// D�finition du fond d'�cran du smartphone
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(background != null) {
			int height = this.getSize().height;
			int width = this.getSize().width;
			g.drawImage(background, 0, 0, width, height, this);
		}
	}
}