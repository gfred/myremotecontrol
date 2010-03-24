import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

import javax.swing.SwingUtilities;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		File file = new File("C:/Users/Fred/Desktop/MRC/crtlMessages");
//		try{
//			BufferedReader br = new BufferedReader(new FileReader(file));
//			String fw = br.readLine();
//			
//			while(fw!=null){
//				
//				setControlMsgToObject(fw);
//				
//				fw = br.readLine();
//			}
//			
//		}catch (Exception e) {
//			System.out.println(e);
//		}
		try{
			Socket server = new Socket("localhost",1111); 	
			PrintWriter out = new PrintWriter(server.getOutputStream());
//			for(int i=97;i<150;i++){
//				out.println("<mrc:crtl key=\""+i+"\" />");
//				Thread.sleep(400);
//				out.flush();	
//			}
//			out.println("<mrc:crtl shutdownTime=\"360000ms\" shutdownStatus=\"start\" />");
			out.println("<mrc:crtl connected=\"false\" />");
			out.flush();
			server.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
