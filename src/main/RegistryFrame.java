package main;

import java.awt.Color;
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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RegistryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfPreis;
	private JTextField tfPasswortErneut;
	private JTextField tfPasswort;
	private JTextField tfEmail;
	private JTextField tfTelefon;
	private JTextField tfNachname;
	private JTextField tfVorname;
	private JTextArea taBeschreibungExtras;
	private ArrayList<Integer> anzahlPundZ = new ArrayList<Integer>();
	private Statement st;
	private Connection c;
	private String connectString = "jdbc:mysql://127.0.0.1/parkhotel";
	private String user = "root";
	private String password = "";
	private final ButtonGroup grpPension = new ButtonGroup();
	private LocalDate dateIn = LocalDate.now(), dateOut = LocalDate.now().plusDays(1);
	private int numberPersons = 1, numberDoubleRooms = 0, numberSingleRooms = 1, numberDays = 1, numberDTS;
	private boolean vollpension = true, kinderbett, payTV, flussblick, durchgang, youngFloor;
	private float extras = 20;
	private float gesamtPreis = 140;

	/**
	 * Create the frame.
	 */
	public RegistryFrame(LocalDate start, LocalDate ende, int indexPersonen, int indexZimmer,
			float preis, boolean isVollpension, boolean isHalbpension,
			boolean isKinderbett, boolean isPayTV, boolean isYoungFloor,
			boolean isFlussblick, boolean isDurchgangszimmer, String beschreibung,
			int getNumberPersons, int getNumberDays, int numberDaysTillStart, int getNumberSingleRooms,
			int getNumberDoubleRooms, float getExtras) {
		this.numberPersons = getNumberPersons;
		this.numberDays = getNumberDays;
		this.numberDTS = numberDaysTillStart;
		this.numberSingleRooms = getNumberSingleRooms;
		this.numberDoubleRooms = getNumberDoubleRooms;
		this.extras = getExtras;
		
//Layout
		//Kleines Bild oben links wählen
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(RegistryFrame.class.getResource("/parkhotelView.jpg")));
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
		JLabel lblBuchung = new JLabel("IHRE BUCHUNG");
		JLabel lblErsterTag = new JLabel("Erster Tag");
		JLabel lblLetzterTag = new JLabel("Letzter Tag");
		JLabel lblPersonen = new JLabel("Personen");
		JLabel lblZimmer = new JLabel("Zimmer");
		JLabel lblPreis = new JLabel("Ihr Preis");
		JLabel lblExtras = new JLabel("Extras");
		JLabel lblRegistrierung = new JLabel("Registrierung");
		JLabel lblVorname = new JLabel("Vorname");
		JLabel lblNachname = new JLabel("Nachname");
		JLabel lblTelefon = new JLabel("Telefonnummer");
		JLabel lblEmail = new JLabel("E-Mail Adresse");
		JLabel lblPasswort = new JLabel("neues Passwort");
		JLabel lblPasswortErneut = new JLabel("Passwort erneut");
		JLabel lblAnmeldedaten = new JLabel("Anmeldedaten");
		
	//JTextFields anlegen
		tfPreis = new JTextField("" + gesamtPreis);
		tfPasswortErneut = new JTextField();
		tfPasswort = new JTextField();
		tfTelefon = new JTextField();
		tfEmail = new JTextField();
		tfNachname = new JTextField();
		tfVorname = new JTextField();		
		
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
		JButton btnAbbrechen = new JButton("Abbrechen");
		
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
		lblPreis.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPreis.setBounds(150, 90, 150, 20);
		lblPreis.setForeground(new Color(255, 255, 255));
		
		lblExtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblExtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExtras.setBounds(10, 175, 135, 20);
		lblExtras.setForeground(new Color(255, 255, 255));
		
		lblRegistrierung.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrierung.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistrierung.setBounds(150, 0, 150, 20);
		lblRegistrierung.setForeground(new Color(255, 255, 255, 255));
		
		lblVorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVorname.setBounds(10, 30,150, 20);
		lblVorname.setForeground(new Color(255, 255, 255));
				
		lblNachname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNachname.setBounds(10, 70, 150, 20);
		lblNachname.setForeground(new Color(255, 255, 255));
		
		lblTelefon.setBounds(10, 110, 150, 20);
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefon.setForeground(new Color(255, 255, 255));
		
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 30, 150, 20);
		lblEmail.setForeground(new Color(255, 255, 255));
		
		lblPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPasswort.setBounds(10, 70, 150, 20);
		lblPasswort.setForeground(new Color(255, 255, 255));
		
		lblPasswortErneut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPasswortErneut.setBounds(10, 110, 150, 20);
		lblPasswortErneut.setForeground(new Color(255, 255, 255));
		
		lblAnmeldedaten.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnmeldedaten.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnmeldedaten.setBounds(150, 0, 150, 20);
		lblAnmeldedaten.setForeground(new Color(255, 255, 255, 255));
		
	//JTextFields einrichten
		tfPreis.setHorizontalAlignment(SwingConstants.CENTER);
		tfPreis.setEditable(false);
		tfPreis.setColumns(10);
		tfPreis.setBounds(150, 115, 150, 20);
		tfPreis.setText("" +preis);
		
		tfVorname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfVorname.setColumns(10);
		tfVorname.setBounds(165, 30, 250, 20);
		
		tfNachname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfNachname.setColumns(10);
		tfNachname.setBounds(165, 70, 250, 20);
		
		tfTelefon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfTelefon.setColumns(10);
		tfTelefon.setBounds(165, 110, 250, 20);
		
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tfEmail.setColumns(10);
		tfEmail.setBounds(165, 30, 250, 20);
		
		tfPasswort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPasswort.setColumns(10);
		tfPasswort.setBounds(165, 70, 250, 20);
		
		tfPasswortErneut.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfPasswortErneut.setBounds(165, 110, 250, 20);
		tfPasswortErneut.setColumns(10);		
		
	//JTextArea einrichten
		taBeschreibungExtras.setEditable(false);
		taBeschreibungExtras.setBounds(165, 205, 250, 105);
		taBeschreibungExtras.setColumns(10);		
		taBeschreibungExtras.setText(beschreibung);
		
	//JCheckboxes einrichten
		chckbxKinderbett.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxKinderbett.setBounds(10, 205, 135, 17);
		chckbxKinderbett.setSelected(isKinderbett);
		
		chckbxPayTV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxPayTV.setBounds(10, 227, 135, 17);
		chckbxPayTV.setSelected(isPayTV);
		
		chckbxYoungFloor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxYoungFloor.setBounds(10, 249, 135, 17);
		chckbxYoungFloor.setSelected(isYoungFloor);
		
		chckbxFlussblick.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxFlussblick.setBounds(10, 271, 135, 17);
		chckbxFlussblick.setSelected(isFlussblick);
		
		chckbxDurchgangszimmer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxDurchgangszimmer.setBounds(10, 293, 135, 17);
		chckbxDurchgangszimmer.setSelected(isDurchgangszimmer);
		
	//JRadioButton einrichten
		rdbtnVollpension.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnVollpension.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnVollpension.setBounds(10, 150, 120, 20);
		//Als Standard setzen
		rdbtnVollpension.setSelected(isVollpension);
		
		rdbtnHalbpension.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnHalbpension.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnHalbpension.setBounds(150, 150, 120, 20);
		rdbtnHalbpension.setSelected(isHalbpension);
		
	//JComboboxes einrichten
		//JComboBoxen füllen
		fillNumbers(anzahlPundZ);
		fillComboBox(cbPersonen, anzahlPundZ);
		fillComboBox(cbZimmer, anzahlPundZ);
		
		cbPersonen.setBounds(300, 60, 40, 20);
		cbPersonen.setSelectedIndex(indexPersonen);
		
		cbZimmer.setBounds(370, 60, 40, 20);
		cbZimmer.setSelectedIndex(indexZimmer);
		
	//JButtons einrichten
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(315, 150, 80, 20);
		
		btnRegistrieren.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrieren.setBounds(45, 160, 150, 20);
		
		//10, 110, 150, 20
		btnAbbrechen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbbrechen.setBounds(240, 160, 150, 20);
		
	//DatePickers einrichten
		dpStart.setDate(start);
		dpStart.setLayout(null);
		dpStart.setBounds(10, 60, 120, 20);
		dpStart.getComponentToggleCalendarButton().setBounds(100, 1, 19, 18);
		dpStart.getComponentDateTextField().setBounds(1, 1, 98, 18);
		dpStart.setLayout(null);
		
		dpEnd.setDate(ende);
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
		panelMid.add(lblRegistrierung);
		panelMid.add(lblVorname);
		panelMid.add(lblNachname);
		panelMid.add(lblTelefon);
		panelBottom.add(lblEmail);
		panelBottom.add(lblPasswort);
		panelBottom.add(lblPasswortErneut);
		panelBottom.add(lblAnmeldedaten);
		
	//JTextFields setzen
		panelTop.add(tfPreis);		
		panelMid.add(tfVorname);
		panelMid.add(tfNachname);
		panelMid.add(tfTelefon);
		panelBottom.add(tfEmail);
		panelBottom.add(tfPasswort);
		panelBottom.add(tfPasswortErneut);
		
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
		panelBottom.add(btnAbbrechen);
		panelBottom.add(btnRegistrieren);
		
	//JComboBoxes setzen
		panelTop.add(cbPersonen);
		panelTop.add(cbZimmer);
		
	//JButtons setzen
		panelTop.add(btnExit);
		
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
					} else if (((cbZimmer.getSelectedIndex() + 1) * 2) == (cbPersonen.getSelectedIndex() + 1)) {
						numberSingleRooms = 0;
						numberDoubleRooms = cbZimmer.getSelectedIndex() + 1;
						tfPreis.setText("" + berechnePreis());
					} else {
						if ((cbPersonen.getSelectedIndex() + 1) % (cbZimmer.getSelectedIndex() + 1) == 1) {
							numberSingleRooms = cbZimmer.getSelectedIndex();
							;
							numberDoubleRooms = 1;
							tfPreis.setText("" + berechnePreis());
						} else {
							numberDoubleRooms = (cbPersonen.getSelectedIndex() + 1) % (cbZimmer.getSelectedIndex() + 1);
							numberSingleRooms = (cbZimmer.getSelectedIndex() + 1) - numberDoubleRooms;
							tfPreis.setText("" + berechnePreis());
						}
					}
				}
			}
		});
		
		//JButtons
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Sind Sie sicher?");
				if (a == JOptionPane.YES_OPTION) {
					System.exit(0);
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
					numberDTS = 0;
					tfPreis.setText("" + berechnePreis());
					System.out.println("Tage zum Start: " + numberDTS);
				} else if(dpEnd.getDate().equals(e.getNewDate()) || e.getNewDate().isAfter(dpEnd.getDate())) {
					dateIn = e.getNewDate();
					dpEnd.setDate(e.getNewDate().plusDays(1));
					dateOut = dpEnd.getDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					numberDTS = (int) ChronoUnit.DAYS.between(LocalDate.now(), e.getNewDate());
					tfPreis.setText("" + berechnePreis());
					System.out.println("Tage zum Start: " + numberDTS);
				} else {
					dateIn = e.getNewDate();
					numberDays = (int) ChronoUnit.DAYS.between(dateIn, dateOut);
					numberDTS = (int) ChronoUnit.DAYS.between(LocalDate.now(), e.getNewDate());
					tfPreis.setText("" + berechnePreis());
					System.out.println("Tage zum Start: " + numberDTS);
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
		
		btnRegistrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String vorname = tfVorname.getText();
				String nachname = tfNachname.getText();
				String telefon = tfTelefon.getText();
				String email = tfEmail.getText();
				if (tfVorname.getText().equals("") | (tfVorname.getText().length() < 2)) {
					JOptionPane.showMessageDialog(null, "Bitte einen Vornamen eingeben",
							"Bitte erneut versuchen", JOptionPane.ERROR_MESSAGE);
					tfVorname.setText("");
				} else if (tfNachname.getText().equals("") | (tfNachname.getText().length() < 2)) {
					JOptionPane.showMessageDialog(null, "Bitte einen Nachnamen eingeben",
							"Bitte erneut versuchen", JOptionPane.ERROR_MESSAGE);
					tfNachname.setText("");
				} else if (tfTelefon.getText().equals("") | (tfTelefon.getText().length() < 11) | (tfTelefon.getText().length() > 20)) {
					JOptionPane.showMessageDialog(null, "Bitte eine gültige Telefonnummer eingeben",
							"Bitte erneut versuchen", JOptionPane.ERROR_MESSAGE);
					tfTelefon.setText("");
				} else if (tfEmail.getText().equals("") | (tfEmail.getText().length() < 7)
						| !tfEmail.getText().contains("@")) {
					JOptionPane.showMessageDialog(null, "Bitte eine gültige E-Mail eingeben",
							"Bitte erneut versuchen", JOptionPane.ERROR_MESSAGE);
					tfEmail.setText("");
				} else if (tfPasswort.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bitte ein Passwort eingeben",
							"Bitte erneut versuchen", JOptionPane.ERROR_MESSAGE);
					tfPasswort.setText("");
					tfPasswortErneut.setText("");
				} else if (tfPasswort.getText().length() < 8) {
					JOptionPane.showMessageDialog(null, "Ihr Passwort muss mindestens 8-stellig sein",
							"Bitte erneut versuchen", JOptionPane.ERROR_MESSAGE);
					tfPasswort.setText("");
					tfPasswortErneut.setText("");
				} else if (!tfPasswort.getText().equals(tfPasswortErneut.getText())) {
					JOptionPane.showMessageDialog(null, "Die Passwörter stimmen nicht überein",
							"Bitte erneut versuchen", JOptionPane.ERROR_MESSAGE);
					tfPasswort.setText("");
					tfPasswortErneut.setText("");
				} else {
					String passwort = tfPasswort.getText();
					String sql = "SELECT email FROM login WHERE email = '" + email + "'";
					System.out.println(sql);
					try {
						c = DriverManager.getConnection(connectString, user, password);
						st = c.createStatement();
						ResultSet rs = st.executeQuery(sql);
						if (rs.next()) {
							int a = JOptionPane.showConfirmDialog(null,
									"Diese E-Mail wurde bereits registriert. Passwort per E-Mail senden?");
							if (a == JOptionPane.YES_OPTION) {
								JOptionPane.showMessageDialog(null, "Sie haben Post");
							}
						} else {
							sql = "INSERT INTO login(email, password)" + " VALUES('" + email + "', '" + passwort + "')";
							st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
							ResultSet keys = st.getGeneratedKeys();
							keys.next();
							int id = keys.getInt(1);
							sql = "INSERT INTO kunde(login_ID, vorname, nachname, telefon)" + " VALUES(" + id + ", '"
									+ vorname + "', '" + nachname + "', '" + telefon + "')";
							JOptionPane.showMessageDialog(null, "Vielen Dank für Ihre Registrierung");
							dispose();
							// Noch Konstruktor bauen für user LoggedInFrame(username)
							LoggedInFrame lfi = new LoggedInFrame(dpStart.getDate(), dpEnd.getDate(),
									cbPersonen.getSelectedIndex(), cbZimmer.getSelectedIndex(), berechnePreis(),
									rdbtnVollpension.isSelected(), rdbtnHalbpension.isSelected(), chckbxKinderbett.isSelected(),
									chckbxPayTV.isSelected(), chckbxYoungFloor.isSelected(), chckbxFlussblick.isSelected(),
									chckbxDurchgangszimmer.isSelected(), taBeschreibungExtras.getText(), numberPersons,
									numberDays, numberDaysTillStart, numberSingleRooms, numberDoubleRooms, extras, email);
							lfi.setVisible(true);
							// Zum testen
							System.out.println(sql);
							int number = st.executeUpdate(sql);
							System.out.println(number + " neuen Kunden eingetragen");
						}
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
				}
			}
		});
		
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(lblPasswortErneut, "Sind Sie sicher?");
				if (a == JOptionPane.YES_OPTION) {
					dispose();
					MainFrame mf = new MainFrame();
					mf.setVisible(true);
				}
			}
		});
	
	//Tests
		//Bild Boden evtl weg
//		JLabel lblbottomPic = new JLabel("");
//		lblbottomPic.setBounds(0, 155, 484, 373);
//		Image bottomPic = new ImageIcon(this.getClass().getResource("/parkhotelView.jpg")).getImage();
//		bottomPic = bottomPic.getScaledInstance(484, 375, java.awt.Image.SCALE_SMOOTH);
//		lblbottomPic.setIcon(new ImageIcon(bottomPic));
//		panelMid.add(lblbottomPic);

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
	
	//Getter
	public LocalDate getDateTin() {
		return dateIn;
	}
	
	public LocalDate getDateOut() {
		return dateOut;
	}
	
	public int getNumberPersons() {
		return numberPersons;
	}

	public int getNumberSingleRooms() {
		return numberSingleRooms;
	}
	
	public int getNumberDoubleRooms() {
		return numberDoubleRooms;
	}
	
	public boolean isVollpension() {
		return vollpension;
	}
	
	public boolean isKinderbett() {
		return kinderbett;
	}

	public boolean isPayTV() {
		return payTV;
	}

	public boolean isFlussblick() {
		return flussblick;
	}

	public boolean isDurchgang() {
		return durchgang;
	}

	public boolean isYoungFloor() {
		return youngFloor;
	}
	
	
	//Setter
	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}	

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}

	public void setNumberPersons(int numberPersons) {
		this.numberPersons = numberPersons;
	}

	public void setNumberSingleRooms(int numberSingleRooms) {
		this.numberSingleRooms = numberSingleRooms;
	}

	public void setNumberDoubleRooms(int numberDubleRooms) {
		this.numberSingleRooms = numberDoubleRooms;
	}
	
	public void setVollpension(boolean vollpension) {
		this.vollpension = vollpension;
	}

	public void setKinderbett(boolean kinderbett) {
		this.kinderbett = kinderbett;
	}	

	public void setPayTV(boolean payTV) {
		this.payTV = payTV;
	}	
	
	public void setFlussblick(boolean flussblick) {
		this.flussblick = flussblick;
	}	

	public void setDurchgang(boolean durchgang) {
		this.durchgang = durchgang;
	}	
	
	public void setYoungFloor(boolean youngFloor) {
		this.youngFloor = youngFloor;
	}
}
