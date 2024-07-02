package main;

public class Kunde {
	private int kunde_ID;
	private String vorname, nachname, telefon, email;
	
	public Kunde(int kunde_ID, String vorname, String nachname, String telefon, String email) {
		this.kunde_ID = kunde_ID;
		this.vorname = vorname;
		this.nachname = nachname;
		this.telefon = telefon;
		this.email = email;
	}
	
	public String toString() {
		return "ID: " + kunde_ID + ", Vorname: " + vorname + ", Nachname: " + nachname + ", Telefon: " + telefon + ", E-Mail: " + email;
	}
	
	public int getKunde_ID() {
		return kunde_ID;
	}
	public String getVorname() {
		return vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public String getTelefon() {
		return telefon;
	}

}
