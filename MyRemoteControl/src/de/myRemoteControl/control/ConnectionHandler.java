package de.myRemoteControl.control;

import java.io.PrintWriter;
import java.net.Socket;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

public class ConnectionHandler {
	
	public ConnectionHandler(Context context) {
		this.context = context;
	}
	
	public void connect(String server,int port){
		try{
			this.server = new Socket(server, port);
			outStream = new PrintWriter(this.server.getOutputStream());
		}catch (Exception e) {
			Log.e("Error on connect", e.getLocalizedMessage());
		}
	}
	
	public void sendMessage(String message){
		try{
			for(int i=0;i<500;i+=2){
				outStream.println("<mrc:crtl mouseX=\""+i+"\" mouseY=\""+i+"\"/>");
				Thread.sleep(10);
				outStream.flush();	
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String receiveMessage(){
		return "";
	}
	
	public void disconnect(){
		try{
			outStream.println("<mrc:crtl connected=\"false\" />");
			outStream.flush();
			outStream.close();
			server.close();	
		}catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	private Context context = null;
	private PrintWriter outStream = null; //
	private Socket server = null;
}
