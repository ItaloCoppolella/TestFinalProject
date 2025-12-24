package utility;


import dao.DaoCliente;
import dao.DaoDipendente;
import dao.DaoEquipaggio;
import dao.DaoGita;
import dao.DaoPrenotazione;
import entities.*;

public class Menu {
	

	public static final void stampaMenu() {
		
		System.out.println("Buongiorno/Buonasera, inserisca un numero intero  per entrare nel sito");
		ControlliUtente.controlloInt();
		System.out.println("Buongiorno/Buonasera, inserisca un numero intero diverso da 0 per visualizzare il menù");

        while (ControlliUtente.controlloInt() != 0) {
        	
            System.out.println("Menu:");
            System.out.println("1. Visualizza l'elenco delle Gite e delle prenotazioni");
            System.out.println("2. Visualizza l'elenco delle Gite");
            System.out.println("3. Aggiungi una nuova Gita");
            System.out.println("4. Modifica una Gita");
            System.out.println("5. Cancella una Gita");
            System.out.println("6. Crea una nuova prenotazione");
            System.out.println("7. Visualizza la media dei prezzi delle prenotazioni");
            System.out.println("8. Visualizza le prenotazioni dei clienti");
            System.out.println("9. Cancella una prenotazione");
            System.out.println("10. Aggiorna una prenotazione");
            System.out.println("11. Aggiungi un nuovo dipendente");
            System.out.println("12. Modifica i dati di un dipendente");
            System.out.println("13. Cancella un dipendente");
            System.out.println("14. Stampa dipendenti per ruolo");
            System.out.println("15. Cancella un reparto");
            System.out.println("16. Stampa la lista dei clienti");
            System.out.println("17. Aggiungi un nuovo cliente");
            System.out.println("18. Aggiorna i dati di un cliente");
            System.out.println("19. Cancella un cliente");
            System.out.println("20. Stampa la lista dei membri dell'equipaggio");
            System.out.println("21. Aggiungi un nuovo membro dell'equipaggio");
            System.out.println("22. Modifica i dati di un membro dell'equipaggio");
            System.out.println("23. Cancella un membro dell'equipaggio");
            System.out.println("24. Esci");

            System.out.print("Inserisci la tua scelta: ");
            int scelta = ControlliUtente.controlloInt();
            

            switch (scelta) {
                case 1:
                    DaoGita.getInstance().getVistaGita();
                    break;
                case 2:
                	for(Entity en : DaoGita.getInstance().read()) {
                		System.out.println(en);
                	}
                	//da problemi per la data credo
                	break;
                case 3:
                	System.out.println("\ninserisci in questo ordine : -1) nome della gita/spedizione \n -2) la data di partenza seguendo la formula inglese yyyy/mm/dd \n"
                			+ "-3) destinazione dlla gita/spedizione");
                	Entity e = new Gita();
                	((Gita)e).setNome(ControlliUtente.controlloStringa());
                	((Gita)e).setDataPartenza(ControlliUtente.controlloData());
                	((Gita)e).setDestinazione(ControlliUtente.controlloStringa());
                	//((Gita)e)
                	
                	DaoGita.getInstance().create(e);
                    break;
                case 4:
                	System.out.println("\ninserisci in questo ordine : -1) il nuovo nome della gita/spedizione \n -2) la nuova data di partenza seguendo la formula inglese yyyy/mm/dd \n"
                			+ "-3) la nuova destinazione dlla gita/spedizione \n-4) l'id sul quale attuare la modifica");
                	Entity e1 = new Gita();
                	((Gita)e1).setNome(ControlliUtente.controlloStringa());
                	((Gita)e1).setDataPartenza(ControlliUtente.controlloData());
                	((Gita)e1).setDestinazione(ControlliUtente.controlloStringa());
                	((Gita)e1).setId(ControlliUtente.controlloInt());
                	ControlliUtente.controlloInt();
                	DaoGita.getInstance().update(e1);
                    break;
                case 5:
                    System.out.println("inserisci l'id per cancellare una gita");
                    DaoGita.getInstance().delete(ControlliUtente.controlloInt());
                    break;
                case 6:
                	System.out.println("\nInserisci in questo ordine:\n -1) Data della prenotazione  \n -2) Costo della Prenotazione \n"
                			+ "-3) N-Identificativo del Cliente(id del cliente) \n -4) N-Identificatido della Gita(id della gita)");
                	Entity e2 = new Prenotazione();
                	
                	((Prenotazione)e2).setDataPrenotazione(ControlliUtente.controlloData());
                	((Prenotazione)e2).setPrezzo(ControlliUtente.controlloDouble());
                	((Prenotazione)e2).setIdCliente(ControlliUtente.controlloInt());
                	((Prenotazione)e2).setIdGita(ControlliUtente.controlloInt());
                	
                	DaoPrenotazione.getInstance().create(e2);
                	break;
                case 7:
                	
                    DaoPrenotazione.getInstance().StampaMediaPrezzoPrenotazioni();
                	break;
                case 8:
                	
                	DaoPrenotazione.getInstance().getPrenotazioneCliente();
                	break;
                case 9:
                	System.out.println("inserisci l'id da cancellare");
                	DaoPrenotazione.getInstance().delete(ControlliUtente.controlloInt());
                	break;
                case 10:
                	System.out.println("\nInserisci in questo ordine:\n -1) La nuova data della prenotazione  \n -2) Il nuovo costo della Prenotazione \n -3)"
                			+ " Il nuovo N-Identificativo del Cliente(id del cliente) \n -4) Il nuovo N-Identificatido della Gita(id della gita)");
                	Entity e3 = new Prenotazione();
                	((Prenotazione)e3).setDataPrenotazione(ControlliUtente.controlloData());
                	((Prenotazione)e3).setPrezzo(ControlliUtente.controlloDouble());
                	((Prenotazione)e3).setIdCliente(ControlliUtente.controlloInt());
                	((Prenotazione)e3).setIdGita(ControlliUtente.controlloInt());
                	
                	DaoPrenotazione.getInstance().update(e3);
                	break;
                case 11:
                	System.out.println("\nInserisci in questo ordine : \n -1) Nome del Dipendente \n -2) Cognome del Dipendente \n "
                			+ "-3) Ruolo del Dipendente \n -4) Stipendio del Dipendente ");
                    Entity e4 = new Dipendente();
                    ((Dipendente)e4).setNome(ControlliUtente.controlloStringa());
                    ((Dipendente)e4).setNome(ControlliUtente.controlloStringa());
                    ((Dipendente)e4).setNome(ControlliUtente.controlloStringa());
                    ((Dipendente)e4).setStipendio(ControlliUtente.controlloDouble());
                    
                    DaoDipendente.getInstance().create(e4);
                    break;
                case 12:
                	System.out.println("\nInserisci in questo ordine : \n -1) Il nuovo Nome del Dipendente \n -2) Il nuovo Cognome del Dipendente \n "
                			+ "-3) Il nuovo Ruolo del Dipendente \n -4) Il nuovo Stipendio del Dipendente ");
                    Entity e5 = new Dipendente();
                    ((Dipendente)e5).setNome(ControlliUtente.controlloStringa());
                    ((Dipendente)e5).setNome(ControlliUtente.controlloStringa());
                    ((Dipendente)e5).setNome(ControlliUtente.controlloStringa());
                    ((Dipendente)e5).setStipendio(ControlliUtente.controlloDouble());
                    
                    DaoDipendente.getInstance().create(e5);
                    break;
                case 13:
                	System.out.println("inserisci l'id da cancellare");
                	DaoDipendente.getInstance().delete(ControlliUtente.controlloInt());
                	break;
                case 14:
                	DaoDipendente.getInstance().Dipendenti_per_ruolo();
                	break;
                case 15:
                	System.out.println("\nInserisci il nome del reparto da eliminare...Ma prima...");
                	System.out.println("\n\nCosa è qualcosa che tutti ti danno, ma nessuno lo prende mai?");
                	while(ControlliUtente.controlloStringa() == ("consiglio")) {
                	System.out.println("Nah riprova brutta pippa,Cosa è qualcosa che tutti ti danno, ma nessuno lo prende mai?");
                	}
                	System.out.println("Dimmi quale reparto cancellare");
                	DaoDipendente.getInstance().dropRepartoAfterFallimento(ControlliUtente.controlloStringa().toLowerCase());
                	break;
                case 16:
                	
                	for(Entity en : DaoCliente.getInstance().read()) {
                		System.out.println(en);
                	}
                	break;
                case 17:
                	System.out.println("inserisci in questo ordine :\n -1) Nome del cliente \n -2) Cognome del cliente \n"
                			+ "-3) Email del cliente \n -4) Numero di telefono del cliente");
                	Entity e6 = new Cliente();
                	((Cliente)e6).setNome(ControlliUtente.controlloStringa());
                	((Cliente)e6).setCognome(ControlliUtente.controlloStringa());
                	((Cliente)e6).setEmail(ControlliUtente.controlloStringa());
                	((Cliente)e6).setTelefono(ControlliUtente.controlloStringa());
                	
                	DaoCliente.getInstance().create(e6);
                	break;
                case 18:
                	System.out.println("inserisci in questo ordine :\n -1) Il nuovo Nome del cliente \n -2) Il nuovo Cognome del cliente \n"
                			+ "-3) La nuova Email del cliente \n -4) Il nuovo Numero di telefono del cliente");
                	Entity e7 = new Cliente();
                	((Cliente)e7).setNome(ControlliUtente.controlloStringa());
                	((Cliente)e7).setCognome(ControlliUtente.controlloStringa());
                	((Cliente)e7).setEmail(ControlliUtente.controlloStringa());
                	((Cliente)e7).setTelefono(ControlliUtente.controlloStringa());                
                	DaoCliente.getInstance().delete(ControlliUtente.controlloInt());
                	break;
                case 19:
                	System.out.println("Inserisci l'id da cancellare");
                	DaoCliente.getInstance().delete(ControlliUtente.controlloInt());
                	break;
                case 20:
                	for(Entity en : DaoEquipaggio.getInstance().read()) {
                		System.out.println(en);
                	}
                	
                	break;
                case 21:
                	System.out.println("Inserisci in questo ordine : -1) Nome del membro dell'equipaggio \n -2) Cognome del membro dell'equipaggio \n"
                			+ "-3) Ruolo del Membro dell'equipaggio 1n -4) N-Identificativo della gita(id)");
                	Entity e8 = new Equipaggio();
                	((Equipaggio)e8).setNome(ControlliUtente.controlloStringa());
                	((Equipaggio)e8).setCognome(ControlliUtente.controlloStringa());
                	((Equipaggio)e8).setRuolo(ControlliUtente.controlloStringa());
                	((Equipaggio)e8).setGita_id(ControlliUtente.controlloInt());
                	
                	DaoEquipaggio.getInstance().create(e8);
                	break;
                case 22:
                	System.out.println("Inserisci in questo ordine : -1) Il nuovo Nome del membro dell'equipaggio \n -2) Il nuovo Cognome del membro dell'equipaggio \n"
                			+ "-3) Il nuovo Ruolo del Membro dell'equipaggio 1n -4) Il nuovo N-Identificativo della gita(id)");
                	Entity e9 = new Equipaggio();
                	((Equipaggio)e9).setNome(ControlliUtente.controlloStringa());
                	((Equipaggio)e9).setCognome(ControlliUtente.controlloStringa());
                	((Equipaggio)e9).setRuolo(ControlliUtente.controlloStringa());
                	((Equipaggio)e9).setGita_id(ControlliUtente.controlloInt());
                	
                	DaoEquipaggio.getInstance().update(e9);
                	break;
                case 23:
                	System.out.println("Inserisci l'id da cancellare");
                	DaoEquipaggio.getInstance().delete(ControlliUtente.controlloInt());
                	break;
                case 24:
                	System.out.println("Arrivederci!");
                	System.exit(0);
                	break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
            System.out.println("\n\nBuongiorno/Buonasera, inserisca un numero intero diverso da 0 per visualizzare il menù");
        }
	}
}
