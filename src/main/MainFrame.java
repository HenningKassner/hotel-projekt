package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfPreis;
	private JTextField tfLogin;
	private JTextField tfPasswort;
	private JTextArea taBeschreibungExtras;
	private ArrayList<Integer> anzahlPundZ = new ArrayList<Integer>();
	private final ButtonGroup grpPension = new ButtonGroup();
	private LocalDate dateIn = LocalDate.now(), dateOut = LocalDate.now().plusDays(1);
	private int numberPersons = 1, numberDoubleRooms = 0, numberSingleRooms = 1, numberDays = 1, numberDaysTillStart;
	private boolean vollpension = true, kinderbett, payTV, flussblick, durchgang, youngFloor;
	private float extras = 20;
	private float gesamtPreis = 140;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
//Layout
		//Kleines Bild oben links wählen
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/parkhotelView.jpg")));
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
		
		//Vorschaubild setzen
		Image zimmerKlein = new ImageIcon(this.getClass().getResource("/parkhotelView.jpg")).getImage();
		zimmerKlein = zimmerKlein.getScaledInstance(450, 140, java.awt.Image.SCALE_SMOOTH);
				
	//JPanels anlegen
		JPanel panelTop = new JPanel();
		JPanel panelMid = new JPanel();
		JPanel panelBottom = new JPanel();
		
	//JLabel anlegen
		JLabel lblVorschaubild = new JLabel("");
		JLabel lblBuchung = new JLabel("IHRE BUCHUNG");
		JLabel lblErsterTag = new JLabel("Erster Tag");
		JLabel lblLetzterTag = new JLabel("Letzter Tag");
		JLabel lblPersonen = new JLabel("Personen");
		JLabel lblZimmer = new JLabel("Zimmer");
		JLabel lblPreis = new JLabel("Ihr Preis");
		JLabel lblExtras = new JLabel("Extras");
		JLabel lblVorschau = new JLabel("Das Park Hotel");		
		JLabel lblDaten = new JLabel("Ihre Daten");
		JLabel lblLogin = new JLabel("E-Mail");
		JLabel lblPasswort = new JLabel("Passwort");
		JLabel lblNichtRegistriert = new JLabel("Noch nicht registreirt?");
		
	//JTextFields anlegen
		tfPreis = new JTextField("" + gesamtPreis);
		tfLogin = new JTextField();
		tfPasswort = new JTextField();
		
	//JTextArea anlegen
		taBeschreibungExtras = new JTextArea();
				
	//JCheckBoxes anlegen
		JCheckBox chckbxKinderbett = new JCheckBox("Kinderbett");
		JCheckBox chckbxPayTV = new JCheckBox("Pay-TV");
		JCheckBox chckbxYoungFloor = new JCheckBox("Young Floor");
		JCheckBox chckbxFlussblick = new JCheckBox("Flussblick");
		JCheckBox chckbxDurchgangszimmer = new JCheckBox("Durchgangszimmer");
		
	//JRadioButtons anlegen
		JRadioButton rdbtnVollpension = new JRadioButton("Vollpension");
		JRadioButton rdbtnHalbpension = new JRadioButton("Halbpension");
		
	//JComboBoxes anlegen
		JComboBox<Integer> cbPersonen = new JComboBox<>(new DefaultComboBoxModel<Integer>());		
		JComboBox<Integer> cbZimmer = new JComboBox<>(new DefaultComboBoxModel<Integer>());
		
	//JButtons anlegen
		JButton btnExit = new JButton("Exit");		
		JButton btnRegistrieren = new JButton("Registrieren");		
		JButton btnAnmelden = new JButton("Anmelden");
		
	//DatePickers anlegen
		DatePicker dpStart = new DatePicker();
		DatePicker dpEnd = new DatePicker();		
		
	//JPanels einrichten		
		panelTop.setBounds(0, 0, 450, 320);
		panelTop.setBackground(new Color(255, 255, 255, 0)); // set transparency to 80
		panelTop.setLayout(null);
		
		panelMid.setBounds(0, 321, 450, 160);		
		panelMid.setBackground(new Color(255, 255, 255, 0));
		panelMid.setLayout(null);
		
		panelBottom.setBounds(0, 481, 450, 320);		
		panelBottom.setBackground(new Color(255, 255, 255, 0));
		panelBottom.setLayout(null);
		
	//JLabels einrichten
		lblVorschaubild.setIcon(new ImageIcon(zimmerKlein));
		lblVorschaubild.setHorizontalAlignment(SwingConstants.CENTER);
		lblVorschaubild.setBounds(0, 20, 450, 140);	
		
		lblBuchung.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuchung.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBuchung.setForeground(new Color(255, 255, 255));
		lblBuchung.setBounds(150, 7, 150, 25);
		
		lblErsterTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblErsterTag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblErsterTag.setBounds(10, 35, 100, 20);
		lblErsterTag.setForeground(new Color(255, 255, 255));
		
		lblLetzterTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetzterTag.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLetzterTag.setBounds(150, 35, 100, 20);
		lblLetzterTag.setForeground(new Color(255, 255, 255));
		
		lblPersonen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersonen.setBounds(267, 35, 100, 20);
		lblPersonen.setForeground(new Color(255, 255, 255));
		
		lblZimmer.setHorizontalAlignment(SwingConstants.CENTER);
		lblZimmer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblZimmer.setBounds(340, 35, 100, 20);
		lblZimmer.setForeground(new Color(255, 255, 255));
		
		lblPreis.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreis.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPreis.setBounds(150, 90, 150, 20);
		lblPreis.setForeground(new Color(255, 255, 255));
		
		lblExtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblExtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExtras.setBounds(10, 175, 135, 20);
		lblExtras.setForeground(new Color(255, 255, 255));
		
		lblVorschau.setHorizontalAlignment(SwingConstants.CENTER);
		lblVorschau.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVorschau.setBounds(150, 0, 150, 20);
		lblVorschau.setForeground(new Color(255, 255, 255, 255));
		
		lblDaten.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaten.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDaten.setBounds(150, 10, 150, 20);
		lblDaten.setForeground(new Color(255, 255, 255));
		
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLogin.setBounds(10, 35, 170, 20);
		lblLogin.setForeground(new Color(255, 255, 255));
		
		lblPasswort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPasswort.setBounds(190, 35, 105, 20);
		lblPasswort.setForeground(new Color(255, 255, 255));
		
		lblNichtRegistriert.setHorizontalAlignment(SwingConstants.CENTER);
		lblNichtRegistriert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNichtRegistriert.setBounds(50, 95, 200, 20);
		lblNichtRegistriert.setForeground(new Color(255, 255, 255));
		
		//Bilder hinzufügen
		panelMid.add(lblVorschaubild);
		
	//JTextFields einrichten
		tfPreis.setHorizontalAlignment(SwingConstants.CENTER);
		tfPreis.setEditable(false);
		tfPreis.setColumns(10);
		tfPreis.setBounds(150, 115, 150, 20);
		
		tfLogin.setColumns(10);
		tfLogin.setBounds(10, 60, 170, 20);		
		
		tfPasswort.setColumns(10);
		tfPasswort.setBounds(190, 60, 105, 20);
		
	//JTextArea einrichten
		taBeschreibungExtras.setEditable(false);
		taBeschreibungExtras.setBounds(165, 205, 250, 105);
		taBeschreibungExtras.setColumns(10);		
		
	//JCheckboxes einrichten
		chckbxKinderbett.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxKinderbett.setBounds(10, 205, 135, 17);
		
		chckbxPayTV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxPayTV.setBounds(10, 227, 135, 17);
		
		chckbxYoungFloor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxYoungFloor.setBounds(10, 249, 135, 17);
		
		chckbxFlussblick.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxFlussblick.setBounds(10, 271, 135, 17);
		
		chckbxDurchgangszimmer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxDurchgangszimmer.setBounds(10, 293, 135, 17);
		
	//JRadioButton einrichten
		rdbtnVollpension.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnVollpension.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnVollpension.setBounds(10, 150, 120, 20);
		//Als Standard setzen
		rdbtnVollpension.setSelected(true);
		
		rdbtnHalbpension.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnHalbpension.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnHalbpension.setBounds(150, 150, 120, 20);
		
	//JComboboxes einrichten
		//JComboBoxen füllen
		fillNumbers(anzahlPundZ);
		fillComboBox(cbPersonen, anzahlPundZ);
		fillComboBox(cbZimmer, anzahlPundZ);
		
		cbPersonen.setBounds(300, 60, 40, 20);
		
		cbZimmer.setBounds(370, 60, 40, 20);
		
	//JButtons einrichten
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(315, 150, 80, 20);		
		
		btnRegistrieren.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrieren.setBounds(250, 95, 120, 20);
		
		btnAnmelden.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnmelden.setBounds(310, 60, 110, 20);
		
	//DatePickers einrichten
		dpStart.setDate(LocalDate.now());
		dpStart.setLayout(null);
		dpStart.setBounds(10, 60, 120, 20);
		dpStart.getComponentToggleCalendarButton().setBounds(100, 1, 19, 18);
		dpStart.getComponentDateTextField().setBounds(1, 1, 98, 18);
		dpStart.setLayout(null);
		
		dpEnd.setDate(LocalDate.now().plusDays(1));
		dpEnd.setLayout(null);
		dpEnd.setBounds(150, 60, 120, 20);
		dpEnd.getComponentToggleCalendarButton().setBounds(100, 1, 19, 18);
		dpEnd.getComponentDateTextField().setBounds(1, 1, 98, 18);
		dpEnd.setLayout(null);	
			
	//JPanels setzen
		contentPane.add(panelTop);
		contentPane.add(panelMid);
		contentPane.add(panelBottom);
		
	//JLabels setzen
		panelTop.add(lblBuchung);
		panelTop.add(lblErsterTag);	
		panelTop.add(lblLetzterTag);
		panelTop.add(lblPersonen);
		panelTop.add(lblZimmer);
		panelTop.add(lblPreis);		
		panelTop.add(lblExtras);		
		panelMid.add(lblVorschau);
		panelBottom.add(lblDaten);		
		panelBottom.add(lblLogin);		
		panelBottom.add(lblPasswort);		
		panelBottom.add(lblNichtRegistriert);
		
	//JTextFields setzen
		panelTop.add(tfPreis);
		panelBottom.add(tfLogin);
		panelBottom.add(tfPasswort);
		
	//JTextArea setze
		panelTop.add(taBeschreibungExtras);
		
	//JCheckboxes setzen
		panelTop.add(chckbxKinderbett);
		panelTop.add(chckbxPayTV);
		panelTop.add(chckbxYoungFloor);
		panelTop.add(chckbxFlussblick);
		panelTop.add(chckbxDurchgangszimmer);
		
	//JRadioButtons setzen
		panelTop.add(rdbtnVollpension);
		panelTop.add(rdbtnHalbpension);
		
	//JComboBoxes setzen
		panelTop.add(cbPersonen);
		panelTop.add(cbZimmer);
		
	//JButtons setzen
		panelTop.add(btnExit);
		panelBottom.add(btnRegistrieren);
		panelBottom.add(btnAnmelden);
		
	//DatePickers setzen
		panelTop.add(dpStart);
		panelTop.add(dpEnd);
		
	// Hintergrund auf Label setzen und Maße einrichten
		JLabel lblBackground = new JLabel();
		lblBackground.setBounds(0, 0, 450, 800);
		lblBackground.setIcon(new ImageIcon(background));
		contentPane.add(lblBackground);
	
	//Buttongruppierungen festlegen
		grpPension.add(rdbtnVollpension);
		grpPension.add(rdbtnHalbpension);
		
	//ActionListener hinzufügen
		//JCheckBoxes
		chckbxKinderbett.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxKinderbett.isSelected()) {
					kinderbett = true;
					extras += 5;
					tfPreis.setText("" + berechnePreis());
					beschreibenKinderbett();
				} else {
					kinderbett = false;
					extras -= 5;
					tfPreis.setText("" + berechnePreis());
					taBeschreibungExtras.setText("");
				}
			}
		});		
		
		chckbxPayTV.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxPayTV.isSelected()) {
					payTV = true;
					extras += 10;
					tfPreis.setText("" + berechnePreis());
					beschreibenPayTV();
				} else {
					payTV = false;
					extras -= 10;
					tfPreis.setText("" + berechnePreis());
					taBeschreibungExtras.setText("");
				}
			}
		});		
		
		chckbxYoungFloor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxYoungFloor.isSelected()) {
					youngFloor = true;
					extras += 5;
					tfPreis.setText("" + berechnePreis());
					beschreibenYoungFloor();
				} else {
					youngFloor = false;
					extras -= 5;
					tfPreis.setText("" + berechnePreis());
					taBeschreibungExtras.setText("");
				}
			}
		});		
		
		chckbxFlussblick.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxFlussblick.isSelected()) {
					flussblick = true;
					extras += 20;
					tfPreis.setText("" + berechnePreis());
					beschreibenFlussblick();
				} else {
					flussblick = false;
					extras -= 20;
					tfPreis.setText("" + berechnePreis());
					taBeschreibungExtras.setText("");
				}
			}
		});	
		
		chckbxDurchgangszimmer.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxDurchgangszimmer.isSelected()) {
					durchgang = true;
					extras += 10;
					tfPreis.setText("" + berechnePreis());
					beschreibenDurchgang();
				} else {
					durchgang = false;
					extras -= 10;
					tfPreis.setText("" + berechnePreis());
					taBeschreibungExtras.setText("");
				}
			}
		});	
		
		//JRadioButtons
		rdbtnVollpension.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!rdbtnVollpension.isSelected()) {
					vollpension = false;
					extras -= 20;
					tfPreis.setText("" + berechnePreis());
				}
			}
		});		
		
		rdbtnHalbpension.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!rdbtnHalbpension.isSelected()) {
					vollpension = true;
					extras += 20;
					tfPreis.setText("" + berechnePreis());
				}
			}
		});
		
		//JComboBoxes
		cbPersonen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				numberPersons = cbPersonen.getSelectedIndex() + 1;

				// mehr cases oder different approach
				switch (cbPersonen.getSelectedIndex() + 1) {
				case 1:
					cbZimmer.setSelectedIndex(0);
					tfPreis.setText("" + berechnePreis());
					break;
				case 2:
					cbZimmer.setSelectedIndex(1);
					tfPreis.setText("" + berechnePreis());
					// cbZimmer.remove(0);
					break;
				case 3:
					cbZimmer.setSelectedIndex(2);
					tfPreis.setText("" + berechnePreis());
					break;
				case 4:
					cbZimmer.setSelectedIndex(3);
					tfPreis.setText("" + berechnePreis());
					break;
				case 5:
					cbZimmer.setSelectedIndex(4);
					tfPreis.setText("" + berechnePreis());
					break;
				case 6:
					cbZimmer.setSelectedIndex(5);
					tfPreis.setText("" + berechnePreis());
					break;
				case 7:
					cbZimmer.setSelectedIndex(6);
					tfPreis.setText("" + berechnePreis());
					break;
				case 8:
					cbZimmer.setSelectedIndex(7);
					tfPreis.setText("" + berechnePreis());
					break;
				case 9:
					cbZimmer.setSelectedIndex(8);
					tfPreis.setText("" + berechnePreis());
					break;
				case 10:
					cbZimmer.setSelectedIndex(9);
					tfPreis.setText("" + berechnePreis());
					break;
				case 11:
					cbZimmer.setSelectedIndex(10);
					tfPreis.setText("" + berechnePreis());
					break;
				case 12:
					cbZimmer.setSelectedIndex(11);
					tfPreis.setText("" + berechnePreis());
					break;
				case 13:
					cbZimmer.setSelectedIndex(12);
					tfPreis.setText("" + berechnePreis());
					break;
				case 14:
					cbZimmer.setSelectedIndex(13);
					tfPreis.setText("" + berechnePreis());
					break;
				case 15:
					cbZimmer.setSelectedIndex(14);
					tfPreis.setText("" + berechnePreis());
					break;
				case 16:
					cbZimmer.setSelectedIndex(15);
					tfPreis.setText("" + berechnePreis());
					break;
				case 17:
					cbZimmer.setSelectedIndex(16);
					tfPreis.setText("" + berechnePreis());
					break;
				case 18:
					cbZimmer.setSelectedIndex(17);
					tfPreis.setText("" + berechnePreis());
					break;
				case 19:
					cbZimmer.setSelectedIndex(18);
					tfPreis.setText("" + berechnePreis());
					break;
				case 20:
					cbZimmer.setSelectedIndex(19);
					tfPreis.setText("" + berechnePreis());
					break;
				default:
					break;
				}
			}
		});
		
		cbZimmer.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Tester
				if (cbPersonen.getSelectedIndex() < cbZimmer.getSelectedIndex()) {
					JOptionPane.showMessageDialog(cbZimmer, "Mindestens eine Person pro Zimmer");
					if (cbPersonen.getSelectedIndex() == 0) {
						cbZimmer.setSelectedIndex(0);
						tfPreis.setText("" + berechnePreis());
					} else {
						cbZimmer.setSelectedIndex(cbPersonen.getSelectedIndex());
						tfPreis.setText("" + berechnePreis());
					}
					// Eventuell optionen wegnehmen
				} else if (((cbZimmer.getSelectedIndex() + 1) * 2) < (cbPersonen.getSelectedIndex() + 1)) {
					JOptionPane.showMessageDialog(cbZimmer, "Nicht geüngend Zimmer");
					cbZimmer.setSelectedIndex(cbPersonen.getSelectedIndex() / 2);
					tfPreis.setText("" + berechnePreis());
				} else {
					if (cbZimmer.getSelectedIndex() == cbPersonen.getSelectedIndex()) {
						numberSingleRooms = cbZimmer.getSelectedIndex() + 1;
						numberDoubleRooms = 0;
						tfPreis.setText("" + berechnePreis());
						System.out.println("Einzel: " + numberSingleRooms + ", Doppel: " + numberDoubleRooms);

					} else if (((cbZimmer.getSelectedIndex() + 1) * 2) == (cbPersonen.getSelectedIndex() + 1)) {
						numberSingleRooms = 0;
						numberDoubleRooms = cbZimmer.getSelectedIndex() + 1;
						tfPreis.setText("" + berechnePreis());
						System.out.println("Einzel: " + numberSingleRooms + ", Doppel: " + numberDoubleRooms);

					} else {
						if ((cbPersonen.getSelectedIndex() + 1) % (cbZimmer.getSelectedIndex() + 1) == 1) {
							numberSingleRooms = cbZimmer.getSelectedIndex();
							numberDoubleRooms = 1;
							tfPreis.setText("" + berechnePreis());
							System.out.println("Einzel: " + numberSingleRooms + ", Doppel: " + numberDoubleRooms);
						} else {
							numberDoubleRooms = (cbPersonen.getSelectedIndex() + 1) % (cbZimmer.getSelectedIndex() + 1);
							numberSingleRooms = (cbZimmer.getSelectedIndex() + 1) - numberDoubleRooms;
							tfPreis.setText("" + berechnePreis());
							System.out.println("Einzel: " + numberSingleRooms + ", Doppel: " + numberDoubleRooms);

						}
					}
				}
			}
		});
		
		//JButtons
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnExit, "Sind Sie sicher?");
				if (a == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});		
		
		btnRegistrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegistryFrame regf = new RegistryFrame(dpStart.getDate(), dpEnd.getDate(),
						cbPersonen.getSelectedIndex(), cbZimmer.getSelectedIndex(), berechnePreis(),
						rdbtnVollpension.isSelected(), rdbtnHalbpension.isSelected(), chckbxKinderbett.isSelected(),
						chckbxPayTV.isSelected(), chckbxYoungFloor.isSelected(), chckbxFlussblick.isSelected(),
						chckbxDurchgangszimmer.isSelected(), taBeschreibungExtras.getText(), numberPersons,
						numberDays, numberDaysTillStart, numberSingleRooms, numberDoubleRooms, extras);
				regf.setVisible(true);
			}
		});
	
		btnAnmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfLogin.getText().equals("admin") & tfPasswort.getText().equals("root")) {
					dispose();
					AdminFrame admin = new AdminFrame();
					admin.setTaBuchungen(Database.adminInfo().toString());
					admin.setVisible(true);
					
				} else {
					if(Database.checkLogin(tfLogin.getText(), tfPasswort.getText())) {
						dispose();
						LoggedInFrame lif = new LoggedInFrame(dpStart.getDate(), dpEnd.getDate(),
								cbPersonen.getSelectedIndex(), cbZimmer.getSelectedIndex(), berechnePreis(),
								rdbtnVollpension.isSelected(), rdbtnHalbpension.isSelected(), chckbxKinderbett.isSelected(),
								chckbxPayTV.isSelected(), chckbxYoungFloor.isSelected(), chckbxFlussblick.isSelected(),
								chckbxDurchgangszimmer.isSelected(), taBeschreibungExtras.getText(), numberPersons,
								numberDays, numberDaysTillStart, numberSingleRooms, numberDoubleRooms, extras, tfLogin.getText());
						lif.setTaZusammenfassung(Database.kundeBuchungen(tfLogin.getText()).toString());
						lif.setVisible(true);
						lif.setTitle("Buchungen für : " + tfLogin.getText());
					} else {
						JOptionPane.showMessageDialog(null, "Falsche E-Mail oder falsches Passwort",
								"Bitte erneut versuchen", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//DatePickers
		dpStart.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent e) {
				if (e.getNewDate().isBefore(LocalDate.now())) {
					JOptionPane.showMessageDialog(null, "Bitte wählen Sie einen Tag ab dem heutigen aus.");
					dpStart.setDate(LocalDate.now());
					dateIn = dpStart.getDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					numberDaysTillStart = 0;
					tfPreis.setText("" + berechnePreis());
					System.out.println("Tage zum Start: " + numberDaysTillStart);
				} else if(dpEnd.getDate().equals(e.getNewDate()) || e.getNewDate().isAfter(dpEnd.getDate())) {
					dateIn = e.getNewDate();
					dpEnd.setDate(e.getNewDate().plusDays(1));
					dateOut = dpEnd.getDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					numberDaysTillStart = (int) ChronoUnit.DAYS.between(LocalDate.now(), e.getNewDate());
					tfPreis.setText("" + berechnePreis());
					System.out.println("Tage zum Start: " + numberDaysTillStart);
				} else {
					dateIn = e.getNewDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					numberDaysTillStart = (int) ChronoUnit.DAYS.between(LocalDate.now(), e.getNewDate());
					tfPreis.setText("" + berechnePreis());
					System.out.println("Tage zum Start: " + numberDaysTillStart);
				}
			}
		});
		
		dpEnd.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent e) {
				if (e.getNewDate().isBefore(dpStart.getDate()) | e.getNewDate().equals(dpStart.getDate())) {
					JOptionPane.showMessageDialog(dpEnd, "Der letzte Tag muss nach dem ersten sein.");
					dpEnd.setDate(dpStart.getDate().plusDays(1));
					dateOut = dpEnd.getDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					tfPreis.setText("" + berechnePreis());
				} else if (dpStart.getDate().plusDays(31).isBefore(e.getNewDate())) {
					JOptionPane.showMessageDialog(dpEnd, "Sie können maximal 30 Übernachtungen buchen");
					dpEnd.setDate(dpStart.getDate().plusDays(1));
					dateOut = dpEnd.getDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					tfPreis.setText("" + berechnePreis());
				} else {
					dateOut = e.getNewDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					tfPreis.setText("" + berechnePreis());
				}
			}
		});
			
	}
	
//Methoden
	//JComboBoxen Füller
	// Maximale Anzahl von Personen und Zimmern festlegen und in Array schreiben
	public ArrayList<Integer> fillNumbers(ArrayList<Integer> al) {
		for (int i = 1; i < 21; i++) {
			al.add(i);
		}
		return al;
	}

	// Füllen der ComboBox
	public void fillComboBox(JComboBox<Integer> jcb, ArrayList<Integer> al) {
		for (int i = 0; i < al.size(); i++) {
			jcb.addItem(al.get(i));
		}
	}
	
	// Errechnen des Gesamtpreises
	public float berechnePreis() {
		gesamtPreis = (numberDoubleRooms * 180 + numberDoubleRooms * extras + numberSingleRooms * 120 + numberSingleRooms * extras) * numberDays;
		System.out.println(extras);
		return gesamtPreis;
	}
	
	//Füllen Beschreibung der Extras
	public void beschreibenKinderbett() {
		taBeschreibungExtras.setText("Gerne stellen wir Ihnen ein hochwertiges,\nkindgerechtes Zustellbett zur Verfügung.");
	}
	
	public void beschreibenPayTV() {
		taBeschreibungExtras.setText("Erleben Sie das volle Fernseherlebnis\nmit der Freischaltung von Netflix und Sky\nFilm, Serie und Sport.");
	}
	public void beschreibenYoungFloor() {
		taBeschreibungExtras.setText("Keine Lust auf alte Knacker? Dann buche\nunsere exklusive 2. Etage nur für junge\nLeute! Alle über 21 müssen draussen bleiben.");
	}
	
	public void beschreibenFlussblick() {
		taBeschreibungExtras.setText("Genießen Sie unsere höchste Etage mit\natemberaubendem Flussblick von ganz oben.");
	}
	
	public void beschreibenDurchgang() {
		taBeschreibungExtras.setText("Wenn Sie gerne ihr Zimmer erweitern wollen,\num näher bei der Familie zu sein, stellen wir\nIhnen gerne ein Zimmer mit Durchgangstür\nzur Verfügung.");
	}
}
