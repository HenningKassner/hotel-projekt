package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea taBuchungen;

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
//Layout
		//Kleines Bild oben links wählen
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(AdminFrame.class.getResource("/parkhotelView.jpg")));
		setTitle("Park Hotel Buchung");
		// EVTL do nothing on close und dann selber joption dialog schreiben
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	// Bilder einrichten
		//Hintergrundbild setzten
		Image background = new ImageIcon(this.getClass().getResource("/hotelGlow.jpeg")).getImage();
		background = background.getScaledInstance(450, 800, java.awt.Image.SCALE_SMOOTH);
				
	//JPanels anlegen
		JPanel panelTop = new JPanel();
		JPanel panelMid = new JPanel();
		JPanel panelBottom = new JPanel();
		
	//JLabel anlegen
		JLabel lblAlleBuchungen = new JLabel();
		
	//JTextArea anlegen
		taBuchungen = new JTextArea();
				
	//JButtons anlegen
		JButton btnExit = new JButton("Exit");		
		JButton btnAbmelden = new JButton("Abmelden");	
		
	//JPanels einrichten		
		panelTop.setBounds(0, 0, 450, 40);
		panelTop.setBackground(new Color(255, 255, 255, 0)); // set transparency to 80
		panelTop.setLayout(null);
		
		panelMid.setBounds(0, 41, 450, 620);		
		panelMid.setBackground(new Color(255, 255, 255, 0));
		panelMid.setLayout(null);
		
		panelBottom.setBounds(0, 661, 450, 40);		
		panelBottom.setBackground(new Color(255, 255, 255, 0));
		panelBottom.setLayout(null);
		
	//JLabels einrichten
		lblAlleBuchungen.setHorizontalAlignment(SwingConstants.CENTER);		
		lblAlleBuchungen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlleBuchungen.setForeground(new Color(255, 255, 255));
		lblAlleBuchungen.setBounds(150, 7, 150, 25);
	
	//JTextArea einrichten
		taBuchungen.setBounds(10, 11, 409, 609);
		taBuchungen.setEditable(false);
		taBuchungen.setColumns(10);	
		
	//JButtons einrichten
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(338, 11, 80, 20);		
		
		btnAbmelden.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbmelden.setBounds(207, 12, 110, 20);
			
	//JPanels setzen
		contentPane.add(panelTop);
		contentPane.add(panelMid);
		contentPane.add(panelBottom);
		
	//JLabels setzen
		panelTop.add(lblAlleBuchungen);
		
	//JButtons setzen
		panelTop.add(btnExit);
		panelTop.add(btnAbmelden);
		
	//JTextArea setzen
			
		panelMid.add(taBuchungen);
				
	// Hintergrund auf Label setzen und Maße einrichten
		JLabel lblBackground = new JLabel();
		lblBackground.setBounds(0, 0, 450, 800);
		lblBackground.setIcon(new ImageIcon(background));
		contentPane.add(lblBackground);
		
	//ActionListener hinzufügen		
		//JButtons
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Sind Sie sicher?");
				if (a == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});		
		
		btnAbmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int a = JOptionPane.showConfirmDialog(null, "Sind Sie sicher?");
				if (a == JOptionPane.YES_OPTION) {
					dispose();
					MainFrame mf = new MainFrame();
					mf.setVisible(true);
				}
			}
		});
	}	
//Methoden
	
	public void setTaBuchungen(String text) {
		taBuchungen.setText(text);
	}
	
}
