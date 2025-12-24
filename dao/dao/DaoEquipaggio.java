package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import database.Database;
import entities.Cliente;
import entities.Entity;
import entities.Equipaggio;
import interfaces.IDao;

public class DaoEquipaggio implements IDao{

	private Database db;
	private String read = "select * from equipaggio";
	private String insert = "insert into Equipaggio(nome,cognome,ruolo,Gita_ID) values (?,?,?,?)";
	private String update = "update equipaggio set nome = ? , cognome = ?, ruolo = ?, Gita_id = ?  where id = ?";
    private String delete = "delete from equipaggio where id = ?";
	
	private static DaoEquipaggio instance = null;

	public static DaoEquipaggio getInstance() {
		if(instance == null) {
			instance = new DaoEquipaggio();
		}
		return instance;

	}
	private DaoEquipaggio() {
		db = Database.getInstance();
	}

	@Override
	public void create(Entity e) {
		Equipaggio eq = (Equipaggio)e;

		try {
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(update);
			ps.setString(1, eq.getNome());
			ps.setString(2, eq.getCognome());
			ps.setString(3, eq.getRuolo());
			ps.setInt(4, eq.getGita_id());

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
		List<Map<String,String>> listaMappe = new ArrayList<Map<String,String>>();
		Entity e;
		
		try
		{
			listaMappe = leggiMappe(read);
			for(Map<String,String> m : listaMappe)
			{
				e = new Equipaggio();  //Creo oggetto vuoto
				e.fromMap(m);  //Valorizzo l'oggetto
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
		Equipaggio eq = (Equipaggio)e;

		//Che bello il lavoro del programmatore moderno =)
		try {
			db.open();
			PreparedStatement ps = db.getC().prepareStatement(insert);
			ps.setString(1, eq.getNome());
			ps.setString(2, eq.getCognome());
			ps.setString(3, eq.getRuolo());
			ps.setInt(4, eq.getGita_id());
			ps.setInt(5, eq.getId());

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

}
