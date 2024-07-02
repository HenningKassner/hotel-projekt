package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Database {
	private static Statement st;
	private static Connection c;
	private static String connectString = "jdbc:mysql://127.0.0.1/parkhotel";
	private static String user = "root";
	private static String password = "";
	
	//Connection zu Datenbank erstellen
	private static void getConnected() {
		try {
			c = DriverManager.getConnection(connectString, user, password);
			st = c.createStatement();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Login prüfen
	public static boolean checkLogin(String username, String userPassword) {
		getConnected();
		try {
			PreparedStatement pst = (PreparedStatement) c.prepareStatement(
					"SELECT email, password" + " FROM login" + " WHERE email = ? AND password = ?");
			pst.setString(1, username);
			pst.setString(2, userPassword);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;				
			} else {
				return false;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}
	
	//Bei erfolgreichem Login User-Daten abrufen
	public static Kunde getUserData(String email) {
		getConnected();
		try {
			String sql = "SELECT kunde_ID, vorname, nachname, telefon"
					+ " FROM kunde "
					+ "JOIN login ON login.login_ID = kunde.login_ID"
					+ " WHERE email = '" + email +  "';";
			ResultSet rs = st.executeQuery(sql);
	
			while(rs.next()) {
				int kunde_ID = rs.getInt("kunde_ID");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				String telefon = rs.getString("telefon");
				
				Kunde kd = new Kunde(kunde_ID, vorname, nachname, telefon, email);				
				return kd;
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Prüfen was für raum
	public static String welcherRaum(boolean doppel, boolean hatYoungFloor, boolean hatFlussblick) {
		if(doppel & hatYoungFloor & hatFlussblick) {
			return "2. Etage, Flussblick, doppel";
		} else if(!doppel & hatYoungFloor & hatFlussblick) {
			return "2. Etage, Flussblick, einzel";
		} else if(doppel & hatYoungFloor) {
			return "2. Etage, doppel";			
		} else if(!doppel & hatYoungFloor) {
			return "2. Etage, einzel";			
		} else if(doppel & hatFlussblick) {
			return "Freie Etage, Flussblick, doppel";			
		} else if(!doppel & hatFlussblick) {
			return "Freie Etage, Flussblick, einzel";			
		} else if(doppel) {
			return "Freie Etage, doppel";			
		} else if(!doppel) {
			return "Freie Etage, einzel";			
		} else {
			return "2. Etage, doppel";			
		}
	}
	//Check ob Raum verfügbar
	public static String anfrageRaum(String welcherRaum) {
		switch(welcherRaum) {
		case "2. Etage, Flussblick, doppel":
			return "SELECT raum_ID FROM raum WHERE"
					+ " raum_ID BETWEEN 240 AND 251 AND groesse = 1 AND belegt = 0;";
		case "2. Etage, Flussblick, einzel":
			return  "SELECT raum_ID FROM raum WHERE"
					+ " raum_ID BETWEEN 240 AND 251 AND groesse = 0 AND belegt = 0;";
		case "2. Etage, doppel":
			return "SELECT raum_ID FROM raum WHERE"
					+ " raum_ID BETWEEN 201 AND 240 AND groesse = 1 AND belegt = 0;";
		case "2. Etage, einzel":
			return "SELECT raum_ID FROM raum WHERE"
					+ " raum_ID BETWEEN 201 AND 240 AND groesse = 1 AND belegt = 0;";
		case "Freie Etage, Flussblick, doppel":
			return "SELECT raum_ID from raum WHERE"
					+ " raum_ID BETWEEN 140 AND 151 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 340 AND 351 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 440 AND 451 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 540 AND 551 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 640 AND 651 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 725 AND 730 AND groesse = 1 AND belegt = 0;";
		case "Freie Etage, Flussblick, einzel":
			return "SELECT raum_ID from raum WHERE"
					+ " raum_ID BETWEEN 140 AND 151 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 340 AND 351 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 440 AND 451 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 540 AND 551 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 640 AND 651 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 725 AND 730 AND groesse = 0 AND belegt = 0;";
		case "Freie Etage, doppel":
			return "SELECT raum_ID from raum WHERE"
					+ " raum_ID BETWEEN 101 AND 139 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 301 AND 339 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 401 AND 439 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 501 AND 539 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 601 AND 639 AND groesse = 1 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 701 AND 724 AND groesse = 1 AND belegt = 0;";
		case "Freie Etage, einzel":
			return "SELECT raum_ID from raum WHERE"
					+ " raum_ID BETWEEN 101 AND 139 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 301 AND 339 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 401 AND 439 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 501 AND 539 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 601 AND 639 AND groesse = 0 AND belegt = 0 OR"
					+ " raum_ID BETWEEN 701 AND 724 AND groesse = 0 AND belegt = 0;";
		default:
			return "";
		}
		
	}
	
	//Zimmer auswählen
	public static int holeRaum(String sqlStatement) {
		getConnected();
		try {	
			ResultSet rs = st.executeQuery(sqlStatement);
			if(rs.next()) {
				int raumNummer = rs.getInt(1);
				return raumNummer;
			} else {
				JOptionPane.showMessageDialog(null, "Leider ist kein Zimmer für diese Kriterien mehr frei.");
				return 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//Zimmer blocken
	public static void blockeRaum(int raumNummer) {
		getConnected();
		String sql = "UPDATE raum SET belegt = 1 WHERE raum_ID = " + raumNummer + ";";
		
		try {
			st.executeUpdate(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Update Datenbank Buchung
	public static int updateDatenbankBuchung(String email, int raum) {
		getConnected();
		Kunde kd = getUserData(email);
		String sql = "INSERT INTO buchung(kunde_ID, raum_ID) VALUES(" + kd.getKunde_ID() + ", " + raum + ");";
			
		try {
			st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet keys = st.getGeneratedKeys();
			keys.next();
			int key = keys.getInt(1);
			return key;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//Update Datenbank Raumzeiten
	public static void updateRaumzeiten(int raum, int days, int tillDays) {
		getConnected();		
		if(tillDays != 0) {
			String sql = "UPDATE buchung SET date_IN = date_IN + INTERVAL " + tillDays + " DAY"
					+ " WHERE raum_ID = " + raum + ";";	
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			sql = "UPDATE buchung SET date_OUT = date_IN + INTERVAL + " + days + " DAY"
					+ " WHERE raum_ID = " + raum + ";";
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} else {
			String sql = "UPDATE buchung SET date_OUT = date_OUT + INTERVAL " + days + " DAY"
					+ " WHERE raum_ID = " + raum + ";";
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	//Update Datenbank Extras
	public static void updateExtras(int id, boolean vollpension, boolean kinderbett, boolean payTV, boolean youngFloor, boolean flussblick, boolean durchgang) {
		getConnected();
		String sql = "INSERT INTO buchungextras (buchung_ID, extras_ID)"
				+ " VALUES ";	
		if(kinderbett) {
			sql += "(" + id + ", 1)";
			System.out.println(sql);
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			sql = "INSERT INTO buchungextras (buchung_ID, extras_ID)"
					+ " VALUES ";
		}
		if(payTV) {
			sql += "(" + id + ", 2)";
			System.out.println(sql);
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			sql = "INSERT INTO buchungextras (buchung_ID, extras_ID)"
					+ " VALUES ";
		}
		if(youngFloor) {
			sql += "(" + id + ", 3)";
			System.out.println(sql);
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			sql = "INSERT INTO buchungextras (buchung_ID, extras_ID)"
					+ " VALUES ";
		}
		if(flussblick) {
			sql += "(" + id + ", 4)";
			System.out.println(sql);
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			sql = "INSERT INTO buchungextras (buchung_ID, extras_ID)"
					+ " VALUES ";
		}
		if(durchgang) {
			sql += "(" + id + ", 5)";
			System.out.println(sql);
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			sql = "INSERT INTO buchungextras (buchung_ID, extras_ID)"
					+ " VALUES ";
		}
		if(vollpension) {
			sql += "(" + id + ", 6)";	
			System.out.println(sql);
			try {
				st.executeUpdate(sql);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			sql = "INSERT INTO buchungextras (buchung_ID, extras_ID)"
					+ " VALUES ";
		}				
	}
	
	//Lade alle Kundennbuchungen von Kunde mit E-Mail email
	//klappt noch nicht ganz
	public static ArrayList<String> kundeBuchungen(String email) {
		getConnected();
		ArrayList<String> buchungen = new ArrayList<>();
		int id = 0;
		int counter = -1;
		try {
			String sql = "SELECT buchung.buchung_ID, date_IN, date_OUT, buchungextras.extras_ID, raum.raum_ID, raum.groesse"
					+ " FROM buchung"
					+ " JOIN buchungextras ON buchungextras.buchung_ID = buchung.buchung_ID"
					+ " JOIN kunde ON kunde.kunde_ID = buchung.kunde_ID"
					+ " JOIN login ON login.login_ID = kunde.login_ID"
					+ " JOIN raum ON raum.raum_ID = buchung.raum_ID"
					+ " WHERE login.email = '" + email + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				if(rs.getInt("buchung_ID") == id) {
					int extra = rs.getInt("extras_ID");
					String welchesExtra = "";
					
					if(extra == 1) welchesExtra = ", Kinderbett";
					if(extra == 2) welchesExtra = ", Pay-TV"; 
					if(extra == 3) welchesExtra = ", Young Floor"; 
					if(extra == 4) welchesExtra = ", Flussblick"; 
					if(extra == 5) welchesExtra = ", Durchgangszimmer"; 
					if(extra == 6) welchesExtra = ", Vollpension"; 
					
					buchungen.set(counter, buchungen.get(counter) + welchesExtra + "\n");
					System.out.println(buchungen);
					
					
				} else {
					id = rs.getInt("buchung_ID");
					LocalDateTime dateIn = rs.getTimestamp("date_IN").toLocalDateTime();
					LocalDateTime dateOut = rs.getTimestamp("date_OUT").toLocalDateTime();
					int extra = rs.getInt("extras_ID");
					int raum = rs.getInt("raum_ID");
					int groesse = rs.getInt("raum.groesse");
					String welchesExtra = "";
					String zimmerGroesse = "";
					
					if(groesse == 1) {
						zimmerGroesse = "Doppelzimmer";
					} else zimmerGroesse = "Einzelzimmer";
					
					if(extra == 1) welchesExtra = "Kinderbett";
					if(extra == 2) welchesExtra = "Pay-TV"; 
					if(extra == 3) welchesExtra = "Young Floor"; 
					if(extra == 4) welchesExtra = "Flussblick"; 
					if(extra == 5) welchesExtra = "Durchgangszimmer"; 
					if(extra == 6) welchesExtra = "Vollpension"; 
					
					buchungen.add("Buchung: " + id + ", Starttag: " + dateIn + ", Endtag: " + dateOut + ", Zimmer: "
							+ raum + ", " + zimmerGroesse + ", Extras: " + welchesExtra + "\n");
					System.out.println(buchungen);
					counter++;
				}				
			}
			return buchungen;
		} catch(SQLException e) {
			e.printStackTrace();
		}	
		return null;
	
	}
	
	public static ArrayList<Buchung> adminInfo() {
		getConnected();
		ArrayList<Buchung> bookings = new ArrayList<>();
		try {
			String sql = "SELECT buchung.buchung_ID, raum.raum_ID, date_IN, date_OUT, kunde.vorname, kunde.nachname, kunde.telefon,"
					+ " login.email, raum.raum_ID, raum.groesse, buchungextras.extras_ID FROM buchung"
					+ " JOIN kunde ON kunde.kunde_ID = buchung.kunde_ID"
					+ " JOIN login ON login.login_ID = kunde.login_ID"
					+ " JOIN raum ON raum.raum_ID = buchung.raum_ID"
					+ " JOIN buchungextras ON buchungextras.buchung_ID = buchung.buchung_ID;";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int buchung_ID = rs.getInt("buchung_ID");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");;
				String telefon =  rs.getString("telefon");
				String email =  rs.getString("email");
				LocalDateTime dateIn = rs.getTimestamp("date_IN").toLocalDateTime();
				LocalDateTime dateOut = rs.getTimestamp("date_OUT").toLocalDateTime();
				int roomNumber = rs.getInt("raum_ID");
				int groesse = rs.getInt("groesse");
				int extra = rs.getInt("extras_ID");
				
				boolean isDouble = false;
				boolean hatVollpension = false;
				boolean hatKinderbett = false;
				boolean hatPayTV = false;
				boolean hatFlussblick = false;
				boolean hatYoungFloor = false;
				boolean hatDurchgang = false;
				
				switch (extra) {
				case 1:
					hatKinderbett = true;
					break;
				case 2:
					hatPayTV = true;
					break;
				case 3:
					hatYoungFloor = true;
					break;
				case 4:
					hatFlussblick = true;
					break;
				case 5:
					hatDurchgang = true;
					break;
				case 6:
					hatVollpension = true;
					break;
				default:
					System.out.println("Da ist etwas schiefgelaufen");
				}
				
				if(groesse == 1) {
					isDouble = true;
				}
				
				Buchung buchung = new Buchung(buchung_ID, dateIn, dateOut, vorname, nachname, telefon, email,
						isDouble, roomNumber, hatVollpension, hatKinderbett, hatPayTV, hatFlussblick,
						hatYoungFloor, hatDurchgang);
				bookings.add(buchung);
				System.out.println(bookings);
			}
			return bookings;
				
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
