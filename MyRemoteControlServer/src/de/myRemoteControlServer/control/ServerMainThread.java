package de.myRemoteControlServer.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import de.myRemoteControlServer.data.InformationMessage;

public class ServerMainThread extends Thread{	
	
	public ServerMainThread(int port) {
		try{
			server = new ServerSocket(port);
			this.start();
		}catch(IOException e){
			//TODO
		}
	}
	
	@Override
	public void run() {
		while(true){
			try{
				logger.addMessage("Waiting for client...");				
				Socket client = server.accept();
				infoMsg.setConnected(true);
				new ServerControlThread(client);
				
				sleep(250);
			}catch (Exception e) {
				logger.addMessage("ServerMainThread Exception: "+e.getLocalizedMessage());
			}			
		}
	}
	
	private InformationMessage infoMsg = InformationMessage.getInstance();
	private Logger logger = Logger.getInstance();
	private ServerSocket server;
}
