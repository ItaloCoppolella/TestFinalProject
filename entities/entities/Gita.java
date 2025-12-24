package entities;

import java.sql.Date;

//marco brasile
public class Gita extends Entity{
	private String nome;
	private Date dataPartenza;
	private String destinazione;
	
	private int numeroPrenotazioni;
	
	public Gita() {
		super();
	}
	
	public Gita(int id, String nome, Date dataPartenza, String destinazione)
	{
		super(id);
		setNome(nome);
		setDataPartenza(dataPartenza);
		setDestinazione(destinazione);
	}
	
	 public Gita(int id, String nome, Date dataPartenza, String destinazione, int numeroPrenotazioni) {
	        super(id);
	        setNome(nome);
	        setDataPartenza(dataPartenza);
	        setDestinazione(destinazione);
	        setNumeroPrenotazioni(numeroPrenotazioni);
	    }
	 


	public int getNumeroPrenotazioni() {
		return numeroPrenotazioni;
	}

	public void setNumeroPrenotazioni(int numeroPrenotazioni) {
		this.numeroPrenotazioni = numeroPrenotazioni;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}

	@Override
	public String toString() {
		return super.toString() + "Nome : " + nome + "\nData Partenza : " + dataPartenza
				+ "\nDestinazione : " + destinazione + "\n----------------------\n";
	}

}
