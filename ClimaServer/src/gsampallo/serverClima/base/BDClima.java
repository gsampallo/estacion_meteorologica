package gsampallo.serverClima.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import gsampallo.serverClima.data.Clima;

public class BDClima {

	private Properties parametros;
	
	public BDClima(Properties parametros) {
		this.parametros = parametros;
	}
	
	protected Connection con;

	public static final String DATABASE_USER = "user";
	public static final String DATABASE_PASSWORD = "password";
	public static final String MYSQL_AUTO_RECONNECT = "autoReconnect";
	public static final String MYSQL_MAX_RECONNECTS = "maxReconnects";	
	
	public boolean establecerConexion() {
	    boolean conectado = false;
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        java.util.Properties connProperties = new java.util.Properties();
	        connProperties.put(DATABASE_USER, parametros.getProperty("usuario").toString());
	        connProperties.put(DATABASE_PASSWORD, parametros.getProperty("pwd").toString());

	        connProperties.put(MYSQL_AUTO_RECONNECT, "true");

	        connProperties.put(MYSQL_MAX_RECONNECTS, "50");
	        
	        con = DriverManager.getConnection("jdbc:mysql://"+parametros.getProperty("server").toString()+"/"+parametros.getProperty("base").toString(),connProperties);

	        System.out.println("Se establecio conexion con la base de datos");
	        
	        conectado  = true;
	      } catch(SQLException e) {
	        System.err.println("SQL Exception: "+e.getMessage());
	        conectado = false;
	      } catch(Exception e) {
	        System.err.println("Exception: "+e.getMessage());
	        conectado = false;
	      }
		return conectado;
	}
	
	public void insertarClima(String fecha,String hora,String temp,String humedad,String temp2,String lluvia,String suelo) {
		try {
			
			PreparedStatement insert = con.prepareStatement(
					"INSERT INTO clima (fecha,hora,fecha_sensor,hora_sensor,temperatura,humedad,temperatura2,lluvia,suelo) VALUES (curdate(),curtime(),curdate(),?,?,?,?,?,?)"     
			);
			//insert.setString(1,fecha);
			insert.setString(1,hora);
			insert.setString(2,temp);
			insert.setString(3,humedad);
			insert.setString(4,temp2);
			insert.setString(5,lluvia);
			insert.setString(6,suelo);
			
			//System.out.println(insert.toString());
			
		    insert.executeUpdate();
		      
		    insert.close();
			
	    } catch(SQLException e) {
	    	System.out.println("SQL Exception: "+e.getMessage());
	    } 
	}
	
}
