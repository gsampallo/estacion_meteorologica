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
					
					String lectura = getLectura();
					boolean continuar = procesarLectura(lectura);
					/*
					while(!continuar) {
						//System.out.println("Se vuelve a solicitar lectura");
						lectura = getLectura();
						continuar = procesarLectura(lectura);
						sleep(tiempo);
					}
					*/
					sleep(tiempo);
					
						
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*	
				} catch(Exception e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				 */
				
			}
		}
		
		private boolean procesarLectura(String lectura) {
			boolean procesarLectura = false;
			if(lectura == null) {
				//System.out.println("Lectura erronea");
				return false;
			}
			String[] lec = lectura.split(",");
			
			if(lec[4] == "0" ) {
				if(lec[5] == "0") {
					procesarLectura = false;
				}
			} else {
				procesarLectura = true;
				dbClima.insertarClima("", lec[1], lec[2], lec[3], lec[4], lec[5],lec[6]);
			}
			
			//System.out.println(lec[0]);
			//lec[0] = lec[0].replace(".","-");
			//String[] fe = lec[0].split(".");
			
			//String fecha = fe[2]+"-"+fe[1]+"-"+fe[0];
			
			//System.out.println("Fecha = "+fecha);
			
			//dbClima.insertarClima("", lec[1], lec[2], lec[3], lec[4], lec[5],lec[6]);
			
			return procesarLectura;
		}
		
		
		
		private String getLectura() {
			String inputLine = "";
			try {
				
				URL url = new URL(parametros.getProperty("url").toString());
				
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
					procesarLectura(inputLine);
				}
				in.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
			return inputLine;
		}
		
		
	}
	
}
