package entities;

public class Dipendente extends Entity{
	
	private String nome;
	private String cognome;
	private String ruolo;
	private double stipendio;


	public Dipendente() {
		super();

	}
	public Dipendente(int id, String nome, String cognome, String ruolo, double stipendio) {
		super(id);
		setNome(nome);
		setCognome(cognome);
		setRuolo(ruolo);
		setStipendio(stipendio);

	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public double getStipendio() {
		return stipendio;
	}
	public void setStipendio(double stipendio) {
		this.stipendio = stipendio;
	}
	@Override
	public String toString()
	{
		return super.toString() + 
				"Nome : " + nome +
				"\nCognome : " + cognome +
				"\nRuolo : " + ruolo +
				"\nStipendio : " + stipendio +
				"\n----------------------\n";


	}

}
