package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private String nomeDb;
	private String percorso;
	private String username;
	private String password;
	private Connection c;

	private static Database instance = null;

	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}

	private Database() {
		this.nomeDb = "rocketMonster";
		this.username = "root";
		this.password = "root";
		setPercorso(nomeDb);
	}

	private void setPercorso(String nomeDb)  //Dal Main mi arriva il percorso "nomeDatabase"
	{
		String timeZone = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		String connection = "jdbc:mysql://localhost:3306/";

		this.percorso = connection + nomeDb + "?" + timeZone; //percorso = "zooJavaSql"
	}


	public Connection getC() {
		return c;
	}

	public void open() {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(percorso, username, password);
		}
		catch(Exception e)
		{
			System.out.println("Controlla il path, username e password");
		}
	}

	public void close() {
		try
		{
			c.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
