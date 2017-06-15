package smartphone;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Structure extends JFrame {
	
	// Définition des bandes autour de l'écran
	private JPanel bandeHaut = new JPanel(new BorderLayout());
	private JPanel bandeBas = new JPanel();
	private JPanel bordGauche = new JPanel();
	private JPanel bordDroite = new JPanel();
	
	
	// Création du bouton de retour au menu
	private ImageIcon iconeMenu = new ImageIcon(new ImageIcon().getClass().getResource("/Bouton_Menu.png"));
	private Image logoMenu = iconeMenu.getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH);	
	private JButton boutonMenu = new JButton(new ImageIcon(logoMenu));
	
	
	// Définition des variables pour le changement d'écran
	private static CardLayout collectionEcrans = new CardLayout();
	private static String[] liste = {"Menu", "Contacts", "Galerie", "AjoutContact", "AffichageContact", "ModificationContact", "AffichagePhoto"};
	private static JPanel ecran = new JPanel();
	private JPanel menu = new Menu();
	private JPanel listeContacts = new ListeContacts();
	private JPanel galerie = new Galerie();
	private JPanel ajoutContact = new AjoutContact();
	private JPanel affichageContact = new AffichageContact();
	private JPanel modificationContact = new ModificationContact();
	
	
	// Création de l'horloge du smartphone
	private DateFormat formatDate = new SimpleDateFormat("HH:mm");
	private Calendar maintenant = Calendar.getInstance();	
	// ajout d'espaces [3] dans la JLabel, pour que l'horloge ne soit pas collée à droite à cause du BorderLayout
	private JLabel time = new JLabel(formatDate.format(maintenant.getTime()) + "   ");
	// définition d'une seconde en millisecondes
	private int wait = 1000;
	
	
	// Création de l'image des paramètres
	private ImageIcon iconeParametres = new ImageIcon(new ImageIcon().getClass().getResource("/Parametres_Systeme.png"));
	private Image logoParametres = iconeParametres.getImage().getScaledInstance(100, 30, Image.SCALE_SMOOTH);	 
	private JLabel parametres = new JLabel(new ImageIcon(logoParametres));
		

	public Structure() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Définition de la taille des bandes
		Dimension tailleBande = new Dimension(500, 50);
		Dimension tailleBord = new Dimension(5, 750);
		
		
		// Création de la bande du haut
		GridBagLayout positionnementHaut = new GridBagLayout();
		GridBagConstraints contraintes = new GridBagConstraints();
		
		bandeHaut.setPreferredSize(tailleBande);
		bandeHaut.setBackground(Color.BLACK);
		bandeHaut.setLayout(positionnementHaut);
		
		Font police = new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 16);
		time.setFont(police);
		time.setForeground(Color.WHITE);
		
		TicTac tictac = new TicTac();
		Timer timer = new Timer(wait, tictac);
		timer.start();
		
		// Définition des contraintes pour le layout
		// Image des paramètres, la grille commence en (0, 0)
		contraintes.gridx = 0;
		contraintes.gridy = 0;		
		// Une seule cellule disponible pour le JLabel contenant l'image
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 0, 0);
		contraintes.weightx = 0.975;
		bandeHaut.add(parametres, contraintes);
		
		// Horloge, positionnement à (0, 1)
		contraintes.gridx = 1;
		contraintes.gridy = 0;		
		// Une seule cellule disponible pour l'horloge
		contraintes.gridwidth = GridBagConstraints.REMAINDER;
		contraintes.gridheight = 1;		
		// Alignement et marges (ordre : dessus, gauche, dessous et droite)
		contraintes.anchor = GridBagConstraints.EAST;
		contraintes.insets = new Insets(0, 0, 0, 0);
		contraintes.weightx = 0.025;
		bandeHaut.add(time, contraintes);
		

		// Création de la bande du bas
		bandeBas.setPreferredSize(tailleBande);
		bandeBas.setBackground(Color.BLACK);
		
		Dimension tailleMenu = new Dimension(100, 40);
		boutonMenu.setPreferredSize(tailleMenu);
		boutonMenu.setBorderPainted(false);
		
		Clic_RetourMenu clicMenu = new Clic_RetourMenu();
		boutonMenu.addActionListener(clicMenu);
		bandeBas.add(boutonMenu);
		
		
		// Création des deux bords
		bordGauche.setPreferredSize(tailleBord);
		bordGauche.setBackground(Color.BLACK);
		bordDroite.setPreferredSize(tailleBord);
		bordDroite.setBackground(Color.BLACK);
		
			
		// Ajout des composants dans la fenêtre
		add(bandeHaut, BorderLayout.NORTH);
		add(bandeBas, BorderLayout.SOUTH);
		add(bordGauche, BorderLayout.WEST);
		add(bordDroite, BorderLayout.EAST);

		
		// Ajout de la collection d'écrans à la structure
		ecran.setLayout(collectionEcrans);
		ecran.add(menu, liste[0]);
		ecran.add(listeContacts, liste[1]);
		ecran.add(galerie, liste[2]);
		ecran.add(ajoutContact, liste[3]);
		ecran.add(affichageContact, liste[4]);
		ecran.add(modificationContact, liste[5]);
		add(ecran, BorderLayout.CENTER);		
    }

		
	// Méthode de changement des écrans en fonction de leur numéro
	public static void changePanel(int a) {
		collectionEcrans.show(ecran, liste[a]);
	}
	
	
	// Méthode d'affichage du Panel "AffichagePhoto"
	public static void changeImage(int a, int indice, File[] listImage){
		JPanel photo = new AffichagePhoto(indice, listImage);
		ecran.add(photo, liste[6]);
		collectionEcrans.show(ecran, liste[a]);
	}
	
	
	// Méthode de rafraîchissement de la galerie
	public static void refresh(){
		JPanel galerie = new Galerie();
		ecran.add(galerie, liste[2]);
		collectionEcrans.show(ecran, liste[2]);
	}
	
	
	// Création de l'action du bouton de retour au menu
	class Clic_RetourMenu implements ActionListener {
		public void actionPerformed(ActionEvent e1) {
			Structure.changePanel(0);
		}
	}
	
	
	// Création de l'action de changement d'heure
	class TicTac implements ActionListener {
		public void actionPerformed(ActionEvent e2) {
			Calendar maintenant = Calendar.getInstance();
			// ajout d'espaces [3] dans la JLabel, pour que l'horloge ne soit pas collée à droite à cause du BorderLayout
			time.setText(formatDate.format(maintenant.getTime()) + "   ");
		}
	}
}