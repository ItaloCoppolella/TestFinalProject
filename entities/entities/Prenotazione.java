package entities;

import java.sql.*;

public class Prenotazione extends Entity{
	
	private Date dataPrenotazione;
	private double prezzo;
	private int idCliente;
	private int idGita;
	private int numeroPrenotazioni;
	
	public Prenotazione() {
		super();
	}
	
	public Prenotazione(int id,Date dataPrenotazione,double prezzo,int idCliente,int idGita) {
		super(id);
		setDataPrenotazione(dataPrenotazione);
		setPrezzo(prezzo);
		setIdCliente(getIdCliente());
		setIdGita(getIdGita());
	}

	public Prenotazione(int nPrenotazioni) {
		setNumeroPrenotazioni(nPrenotazioni);
	}

	public Date getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdGita() {
		return idGita;
	}

	public void setIdGita(int idGita) {
		this.idGita = idGita;
	}
	
	

	public int getNumeroPrenotazioni() {
		return numeroPrenotazioni;
	}

	public void setNumeroPrenotazioni(int numeroPrenotazioni) {
		this.numeroPrenotazioni = numeroPrenotazioni;
	}

	@Override
	public String toString() {
		return "Prenotazione:\n" + super.toString() + ",\n data Prenotazione : " + dataPrenotazione + ",\n Prezzo :"
				+ prezzo + ",\n idCliente : " + idCliente + ",\n idGita : " + idGita + "\n-------------------\n";
	}
	
	
	
	

}
