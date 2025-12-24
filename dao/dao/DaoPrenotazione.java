package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import database.Database;
import entities.Dipendente;
import entities.Entity;
import entities.Prenotazione;
import interfaces.EntityFactory;
import interfaces.IDao;

public class DaoPrenotazione implements IDao{
	private Database db;
	private String read = "select * from prenotazioni";
	private String update = "update prenotazioni set dataPrenotazione = ? , prezzo = ?, Cliente_ID = ?, Gita_id = ?  where id = ?";
	private String insert = "insert into prenotazioni(dataPrenotazione,prezzo,Cliente_ID,Gita_id) values (?,?,?,?)";
	private String delete= "delete from prenotazioni where id = ?";


	private static DaoPrenotazione instance = null;

	public static DaoPrenotazione getInstance() {
		if(instance == null) {
			instance = new DaoPrenotazione();
		}
		return instance;

	}
	private DaoPrenotazione() {
		db = Database.getInstance();
	}

	@Override
	public void create(Entity e) {
		Prenotazione p = (Prenotazione)e;

		//Che bello il lavoro del programmatore moderno =)
		try
		{
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(insert);
			//"update bevande set nome = ? , prezzo = ? where id = ?
			java.util.Date d = new java.util.Date();

			ps.setDate(1, new Date(d.getTime()));
			ps.setDouble(2, p.getPrezzo());
			ps.setInt(3, p.getIdCliente());
			ps.setInt(4, p.getIdGita());


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
	public List<Entity> read() {
		List<Entity> ris = new ArrayList<Entity>();
		List<Map<String,String>> listaMappe = new ArrayList<Map<String,String>>();
		Entity e;
		try
		{
			listaMappe= leggiMappe(read);
			for(Map<String,String> m: listaMappe)
			{
				m.put("elemento", "prenotazione");
				e = EntityFactory.make(m);
				if(e !=null && e instanceof Prenotazione)
				{
					ris.add(e);
				}
				else
				{
					System.out.println("non è una prenotazione");
				}
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
		Prenotazione p = (Prenotazione)e;

		//Che bello il lavoro del programmatore moderno =)
		try
		{
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(update);
			//"update bevande set nome = ? , prezzo = ? where id = ?
			java.util.Date d = new java.util.Date();

			ps.setDate(1, new Date(d.getTime()));
			ps.setDouble(2, p.getPrezzo());
			ps.setInt(3, p.getIdCliente());
			ps.setInt(4, p.getIdGita());
			ps.setInt(5, p.getId());

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
		try
		{
			db.open();
			PreparedStatement ps= db.getC().prepareStatement(delete);
			ps.setInt(1, id);
			int c= ps.executeUpdate();
			if(c> 0)
			{
				System.out.println("Record cancellato");
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

	public List<Map<String, String>> leggiMappe(String query){
		List<Map<String, String>> ris = new ArrayList<Map<String, String>>();

		Map<String, String> map;

		try {
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(query);
			ResultSet tabella = ps.executeQuery();

			while(tabella.next()) {
				map = new LinkedHashMap<String, String>();
				int nColonne = tabella.getMetaData().getColumnCount();
				for(int i = 1; i <= nColonne;i++) {
					String chiave = tabella.getMetaData().getColumnName(i);
					String valore = tabella.getString(i);
					map.put(chiave, valore);
				}
				ris.add(map);
				System.out.println(map);
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close();
		}

		return ris;

	}

	public void StampaMediaPrezzoPrenotazioni() {

		try {

			db.open();

			String query = "SELECT AVG(Prezzo) AS MediaPrezzo FROM Prenotazioni";

			PreparedStatement ps = db.getC().prepareStatement(query);

			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next()) {
				double mediaPrezzo = resultSet.getDouble("MediaPrezzo");
				System.out.println("Media dei prezzi delle prenotazioni: " + "\n" + mediaPrezzo + "$" + ";");
			}

			resultSet.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

	}
	
	public void getPrenotazioneCliente() {
		try {
			db.open();
			String ris = "";
			// Query per selezionare i dati dalla vista "prenotazioni_clienti"
			String query = "SELECT * FROM prenotazioni_clienti";
			PreparedStatement ps = db.getC().prepareStatement(query);
			ResultSet resultSet = ps.executeQuery(query);

			// Itera sui risultati e stampa le informazioni
			while (resultSet.next()) {
				int clienteID = resultSet.getInt("Cliente_ID");
				String nomeCliente = resultSet.getString("Nome_Cliente");
				String cognomeCliente = resultSet.getString("Cognome_Cliente");
				int prenotazioneID = resultSet.getInt("Prenotazione_ID");
				String nomeGita = resultSet.getString("Nome_Gita");
				String dataPrenotazione = resultSet.getString("DataPrenotazione");
				double prezzo = resultSet.getDouble("Prezzo");

				System.out.println("Cliente ID: " + clienteID);
				System.out.println("Nome Cliente: " + nomeCliente);
				System.out.println("Cognome Cliente: " + cognomeCliente);
				System.out.println("Prenotazione ID: " + prenotazioneID);
				System.out.println("Nome Gita: " + nomeGita);
				System.out.println("Data Prenotazione: " + dataPrenotazione);
				System.out.println("Prezzo: " + prezzo);
				System.out.println("--------------------------");

			}
			// Chiudi le risorse
			resultSet.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	

}
