package utility;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ControlliUtente {


	static ControlliUtente cu = new ControlliUtente();
	static Scanner scan = new Scanner(System.in);


	// QUESTI METODI SERVONO A CONTROLLARE CHE QUELLO CHE L'UTENTE INSERISCE FUNZIONI
	public static String controlloStringa() {

		StringBuilder sb = new StringBuilder();

		String ris;
		String sample;

		boolean d1;

		do {

			ris = " ";
			sample = null;

			sample = scan.nextLine(); 
			char[] chars = sample.toCharArray();
			for(char c : chars){
				if(Character.isAlphabetic(c)){
					sb.append(c);

				}
			}
			ris = sb.toString();

			if (ris !=null && ris != "") {d1 = true;}
			else { System.out.println("Hai sbagliato ad inserire. Riprova!"); d1 = false;}


		}while (!d1);

		return ris;

	}

	public static int controlloInt() {

		boolean d1;
		String ris = " ";
		String sample = null;
		StringBuilder sb = new StringBuilder();


		do {
			sample = scan.nextLine(); 
			char[] chars = sample.toCharArray();
			for(char c : chars){
				if(Character.isDigit(c)){
					sb.append(c);
				}
			}
			ris = sb.toString();

			if (ris !=null && ris != "") {d1 = true;}
			else {System.out.println("Scrivi in numeri non in lettere."); d1 = false;}

		}while (!d1);

		return Integer.parseInt(ris);

	}

	public static boolean controlloBoolean() {

		boolean bool = false;
		boolean d1 = false;


		do { 
			switch (scan.nextLine().toLowerCase().toLowerCase()) {

			case "true":
				d1 = true;
				bool = true;
				break;

			case "yes":
				d1 = true;
				bool = true;
				break;

			case "si":
				d1 = true;
				bool = true;
				break;

			case "no":
				d1 = true;
				bool = false;
				break;

			case "false":
				d1 = true;
				bool = false;
				break;	

			default:
				System.out.println("Seleziona un'opzione valida.");
				d1 = false;
				break;
			}
		}while(!d1);

		return bool;
	}


	public static double controlloDouble() {

		boolean d1;
		String ris = " ";
		String sample = null;
		StringBuilder sb = new StringBuilder();


		do {
			sample = scan.nextLine(); 
			char[] chars = sample.toCharArray();
			for(char c : chars){
				if(Character.isDigit(c)){
					sb.append(c);
				}
			}
			ris = sb.toString();

			if (ris !=null && ris != "") {d1 = true;}
			else { System.out.println("Hai sbagliato ad inserire. Riprova!"); d1 = false;}

		}while (!d1);

		return Double.parseDouble(ris);
	}

	public static Date controlloData() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilData = null;

		boolean dataValida = false;
		while (!dataValida) {
			System.out.print("Inserisci una data nel formato 'yyyy-MM-dd': ");
			String input = ControlliUtente.controlloStringa();

			try {
				utilData  =   dateFormat.parse(input);
				dataValida = true;
			} catch (ParseException e) {
				System.out.println("Formato data non valido. Riprova.");
			}
		}
		Date sqlDate = new Date(utilData.getTime());
		return sqlDate;
	}


}
