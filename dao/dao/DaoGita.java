package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import database.Database;
import entities.Entity;
import entities.Gita;
import entities.Prenotazione;
import interfaces.EntityFactory;
import interfaces.IDao;

public class DaoGita implements IDao {

	private Database db;
	private static DaoGita instance= null;
	private String read= "select* from gite";
	private String insert= "insert into gite(nome,dataPartenza,destinazione) values(?,?,?)";
	private String update= "update gite set nome = ? , dataPartenza = ?, destinazione = ? where id = ?";
	private String delete= "delete from gite where id= ?";

	public static DaoGita getInstance()
	{
		if (instance== null)
		{
			instance= new DaoGita();
		}
		return instance;
	}

	private DaoGita()
	{
		db= Database.getInstance();
	}

	@Override
	public void create(Entity e)
	{
		Gita g = (Gita)e;
		try
		{
			db.open();
			PreparedStatement ps= db.getC().prepareStatement(insert);
			java.util.Date d= new java.util.Date();
			ps.setString(1, g.getNome());
			ps.setDate(2, new Date(d.getTime()));
			ps.setString(3, g.getDestinazione());
			int c= ps.executeUpdate();
			if(c> 0)
			{
				System.out.println("Gita inserita correttamente");
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

	@Override
	public List<Entity> read() 
	{
		List<Entity> ris= new ArrayList<Entity>();
		List<Map<String,String>> listaMappe= new ArrayList<Map<String,String>>();
		Entity e;
		try
		{
			listaMappe= leggiMappe(read);
			for(Map<String,String> m: listaMappe)
			{
				m.put("elemento", "gita");
				e = EntityFactory.make(m);
				if(e !=null && e instanceof Gita)
				{
					ris.add(e);
				}
				else
				{
					System.out.println("non e' una gita");
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
	public void update(Entity e)
	{
		Gita g = (Gita)e;
		try
		{
			db.open();
			PreparedStatement ps= db.getC().prepareStatement(update);
			java.util.Date d= new java.util.Date();
			ps.setString(1, g.getNome());
			ps.setDate(2, new Date(d.getTime()));
			ps.setString(3, g.getDestinazione());
			ps.setInt(4, g.getId()); 
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
	public void delete(int id) 
	{
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

	public List<Map<String,String>> leggiMappe(String query)
	{
		List<Map<String,String>> ris= new ArrayList<Map<String,String>>();
		Map<String,String> map;
		try
		{
			db.open();
			PreparedStatement ps= db.getC().prepareStatement(query);
			ResultSet tabella= ps.executeQuery();
			while(tabella.next())
			{
				map= new LinkedHashMap<String, String>();
				int nColonne= tabella.getMetaData().getColumnCount();
				for(int i= 1; i<= nColonne; i++)
				{
					String chiave= tabella.getMetaData().getColumnName(i);
					String valore= tabella.getString(i);
					map.put(chiave, valore);
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

	public void getVistaGita(){
		try {
			db.open();
			String sql = "SELECT * FROM view_Gite";
			PreparedStatement ps = db.getC().prepareStatement(sql);
			try (ResultSet resultSet = ps.executeQuery()) {
				System.out.println("Elenco delle Gite:");
				System.out.println("========================================================================================================");
				System.out.printf("%-5s %-30s %-15s %-30s %-20s%n", "ID", "Nome Gita", "Data Partenza", "Destinazione", "Numero Prenotazioni");
				System.out.println("========================================================================================================");
				while (resultSet.next()) {
					int id = resultSet.getInt("Gita_ID");
					String nomeGita = resultSet.getString("Nome_Gita");
					String dataPartenza = resultSet.getDate("DataPartenza").toString();
					String destinazione = resultSet.getString("Destinazione");
					int numeroPrenotazioni = resultSet.getInt("Numero_Prenotazioni");
					System.out.printf("%-5d %-30s %-15s %-30s %-20d%n", id, nomeGita, dataPartenza, destinazione, numeroPrenotazioni);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.close();
		}
	}


	

	

	





}
