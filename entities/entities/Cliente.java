package entities;

public class Cliente extends Entity{


	private String nome;
	private String cognome;
	private String email;
	private String telefono;

	public Cliente() {
		super();	
	}
	public Cliente(int id,String nome,String cognome,String email,String telefono) {
		super(id);
		setNome(nome);
		setCognome(cognome);
		setEmail(email);
		setTelefono(telefono);
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
	public String getEmail(){
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente:" + super.toString() 
		+ "\n nome : " + nome + ",\n cognome :" + cognome + ",\n email :"
		+ email + ",\n telefono : " + telefono;
	}
}
