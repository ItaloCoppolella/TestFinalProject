package entities;

public class Equipaggio extends Entity{
	private String nome;
	private String cognome;
	private String ruolo;
	private int gita_id;
	
	
	public Equipaggio() {
		super();
	}
	
	public Equipaggio(int id,String nome,String cognome,String ruolo,int gita_id) {
		super(id);
		setNome(nome);
		setCognome(cognome);
		setRuolo(ruolo);
		setGita_id(gita_id);
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
	
	public int getGita_id() {
		return gita_id;
	}
	
	public void setGita_id(int gita_id) {
		this.gita_id = gita_id;
	}

	@Override
	public String toString() {
		return "Equipaggio :" + super.toString() 
		+ "\n Nome : " + nome + ",\n Cognome : " + cognome + ",\n ruolo : "
		+ ruolo + ",\n gita_id : " + gita_id + "\n----------------------\n";
	}
}
