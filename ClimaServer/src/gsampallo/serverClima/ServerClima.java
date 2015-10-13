package gsampallo.serverClima;

import gsampallo.serverClima.base.BDClima;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

public class ServerClima {

	private BDClima dbClima;
	
	public ServerClima() {
		leerParametros();
		
		dbClima = new BDClima(parametros);
		if(dbClima.establecerConexion()) {
			HiloClima hilo = new HiloClima();
			Thread trid = new Thread(hilo);
			trid.start();
		} else {
			System.err.println("No se pudo crear la conexion a la base de datos");
			System.exit(0);
		}
		
	}
	
	
	private Properties parametros;
	
	private void leerParametros() {
		parametros = new Properties();
		
		parametros.put("url", "http://192.168.1.177");
		parametros.put("tiempo", "60000");
		
		parametros.put("server", "localhost");
		parametros.put("usuario", "root");
		parametros.put("pwd", "");
		parametros.put("base", "meteorilogico");
	}
	
	public static void main(String[] args) {
		ServerClima server = new ServerClima();
	}
	
	
	public class HiloClima extends Thread {
		
		private boolean detener = false;
		
		public void run() {
			
			int tiempo = Integer.parseInt(parametros.getProperty("tiempo").toString());
			
			while(!detener) {
			
				try {
					
					URL url = new URL(parametros.getProperty("url").toString());
					
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	
					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						System.out.println(inputLine);
						procesarLectura(inputLine);
					}
					in.close();
					
					sleep(tiempo);
					
				} catch(Exception e) {
					System.err.println(e.getMessage());
				}
			
				
			}
		}
		
		private void procesarLectura(String lectura) {
			
			String[] lec = lectura.split(",");
			//System.out.println(lec[0]);
			//lec[0] = lec[0].replace(".","-");
			//String[] fe = lec[0].split(".");
			
			//String fecha = fe[2]+"-"+fe[1]+"-"+fe[0];
			
			//System.out.println("Fecha = "+fecha);
			
			dbClima.insertarClima("", lec[1], lec[2], lec[3], lec[4], lec[5],lec[6]);
			
		}
		
	}
	
}
