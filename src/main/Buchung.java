package main;

import java.time.LocalDateTime;

public class Buchung {
	private LocalDateTime dateIn, dateOut;
	private String vorname, nachname, telefon, email;
	private int buchung_ID, roomNumber;
	private boolean isDouble, hatVollpension, hatKinderbett, hatPayTV, hatFlussblick, hatYoungFloor, hatDurchgang;
	public Buchung(int buchung_ID, LocalDateTime dateIn, LocalDateTime dateOut, String vorname, String nachname, String telefon, String email,
			boolean isDouble, int roomNumber, boolean hatVollpension, boolean hatKinderbett, boolean hatPayTV,
			boolean hatFlussblick, boolean hatYoungFloor, boolean hatDurchgang) {
		this.buchung_ID = buchung_ID;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.vorname = vorname;
		this.nachname = nachname;
		this.telefon = telefon;
		this.email = email;
		this.isDouble = isDouble;
		this.roomNumber = roomNumber;
		this.isDouble = isDouble;
		this.hatVollpension = hatVollpension;
		this.hatKinderbett = hatKinderbett;
		this.hatPayTV = hatPayTV;
		this.hatFlussblick = hatFlussblick;
		this.hatYoungFloor = hatYoungFloor;
		this.hatDurchgang = hatDurchgang;
	}
	
	public String toString() {
		return "Buchung: " + buchung_ID + "Start:, " + dateIn + ", Ende: " + dateOut + ", Vorname: " + vorname + ", Nachname: " + nachname
				+ ", Telefon: " + telefon + ", E-Mail: " +  email + ", Doppelzimmer: " + isDouble + "Zimmernummer: " + roomNumber
				+ ", Vollpension: " + hatVollpension + ", Kinderbett: " + hatKinderbett + ", Pay-TV: " + hatPayTV + ", Flussblick: "
				+ hatFlussblick + ", Young Floor: " + hatYoungFloor + ", Durchgangszimmer" + hatDurchgang + "\n";
	}
}
