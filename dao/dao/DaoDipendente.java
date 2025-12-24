package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import database.Database;
import entities.Cliente;
import entities.Dipendente;
import entities.Entity;
import entities.Equipaggio;
import interfaces.EntityFactory;
import interfaces.IDao;

public class DaoDipendente implements IDao{
	private Database db;
	private String insert = "insert into Dipendenti(nome,cognome,ruolo,stipendio) values (?,?,?,?)";
	private String read = "select * from dipendenti";
	private String update = "update dipendenti set nome = ? , cognome = ?, ruolo = ?, stipendio = ?  where id = ?";
	private String delete = "delete from dipendenti where id = ?";
	private static DaoDipendente instance = null;

	public static DaoDipendente getInstance() {
		if(instance == null) {
			instance = new DaoDipendente();
		}
		return instance;

	}
	private DaoDipendente() {
		db = Database.getInstance();
	}

	@Override
	public void create(Entity e) {
		Dipendente d = (Dipendente)e;

		try {
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(insert);
			ps.setString(1, d.getNome());
			ps.setString(2, d.getCognome());
			ps.setString(3, d.getRuolo());
			ps.setDouble(4, d.getStipendio());

			int b = ps.executeUpdate();
			if(b > 0) {
				System.out.println("tutto ok"); 
			}
			ps.close();
		} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
		} 
		finally 
		{
			db.close();
		}
	}

	@Override
	public List<Entity> read() {
		List<Entity> ris = new ArrayList<Entity>();
		List<Map<String,String>> listaMappe = new ArrayList<>();
		Entity e;


		try
		{
			listaMappe = leggiMappe(read);
			for(Map<String,String> m : listaMappe)
			{


				e = new Dipendente();  //Creo oggetto vuoto
				e.fromMap(m);
				ris.add(e);

			}
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		return ris;	
	}

	@Override
	public void update(Entity e) {

		Dipendente d = (Dipendente)e;

		//Che bello il lavoro del programmatore moderno =)
		try
		{
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(update);
			//"update bevande set nome = ? , prezzo = ? where id = ?

			ps.setString(1, d.getNome());
			ps.setString(2, d.getCognome());
			ps.setString(3, d.getRuolo());
			ps.setDouble(4, d.getStipendio());
			ps.setInt(5, d.getId());

			ps.executeUpdate();

			ps.close();
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		finally
		{
			db.close();
		}


	}

	@Override
	public void delete(int id) {
		try {
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(delete);

			ps.setInt(1, id);

			int c = ps.executeUpdate();
			if(c > 0)
			{
				System.out.println("Record cancellato correttamente");
			}

			ps.close();
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		finally
		{
			db.close();


		}
	}



	public List<Map<String,String>> leggiMappe(String query)
	{
		List<Map<String,String>> ris = new ArrayList<Map<String,String>>();

		Map<String,String> map;

		try
		{
			db.open();

			PreparedStatement ps = db.getC().prepareStatement(query);
			ResultSet tabella = ps.executeQuery();

			while(tabella.next())
			{
				map = new LinkedHashMap<String, String>();  //Esatto, mappa ordinata
				int nColonne = tabella.getMetaData().getColumnCount();
				for(int i = 1; i <= nColonne; i++)
				{
					String chiave = tabella.getMetaData().getColumnName(i);
					String valore = tabella.getString(i);
					map.put(chiave,valore);
				}
				ris.add(map);


			}

			tabella.close();
			ps.close();
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		finally
		{
			db.close();
		}

		return ris;
	}
	
	
	public void Dipendenti_per_ruolo() {
		try  {
			db.open();
			String sqlQuery = "SELECT * FROM Dipendenti_Per_Ruolo";
			PreparedStatement ps = db.getC().prepareStatement(sqlQuery);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				String ruolo = resultSet.getString("Ruolo");
				String anagraficaDipendente = resultSet.getString("Anagrafica_Dipendente");
				System.out.println("Ruolo: " + ruolo);
				System.out.println("Anagrafica Dipendente: " + anagraficaDipendente);
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dropRepartoAfterFallimento(String nomeTabella) {
		 

	        try {
	            // Crea una connessione al database
	            db.open();

	            // Crea una dichiarazione SQL
	            Statement statement = db.getC().createStatement();

	            // Query SQL per cancellare la tabella
	            String sqlQuery = "DROP TABLE " + nomeTabella;

	            // Esegui la query per cancellare la tabella
	            statement.executeUpdate(sqlQuery);

	            // Chiudi le risorse
	            statement.close();
	            db.close();

	            System.out.println("La tabella " + nomeTabella + " è stata cancellata con successo.");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	

}
