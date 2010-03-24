package de.myRemoteControlServer.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import de.myRemoteControlServer.data.ControlMessage;
import de.myRemoteControlServer.data.InformationMessage;

public class ServerControlThread extends Thread{

	public ServerControlThread(Socket client){
		try{
			this.socket = client;
			message = ControlMessage.getInstance();
			msgHandler = ServerMessageHandler.getInstance();
			infoMessage = InformationMessage.getInstance();
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			this.start();	
		}catch (IOException e) {
			logger.addMessage("IOEXCEPTION > (de.myRemoteControlServer.control.ServerControlThread#ServerControlThread) > in or out stream failed");
		}
	}
	
	@Override
	public void run() {
		logger.addMessage("Client from "+ socket.getInetAddress() + " connected.");
		while(infoMessage.isConnected()){
			controlComputer();
		}
		logger.addMessage("Client from "+ socket.getInetAddress() + " disconnected.");
	}
	
	private void controlComputer(){
		try{
			String msg = in.readLine();
			
			if(msg!=null){
				message.setControlMessage(msg);
				changeComputerSettings();	
			}			
		}catch (IOException e) {
			logger.addMessage("IOEXCEPTION > (de.myRemoteControlServer.control.ServerControlThread#controlComputer) > in or out stream failed");
		}
	}
	
	private void sendControlMessage(){
		out.println(message.getControlMessage());
		out.flush();
	}
	
	private void changeComputerSettings(){
		msgHandler.crtlMessageChanged();
	}
	
	private ServerMessageHandler msgHandler = null;
	private InformationMessage infoMessage = null;
	private ControlMessage message;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Logger logger = Logger.getInstance();
	private Socket socket;
}
