package de.myRemoteControlServer;

import de.myRemoteControlServer.control.ServerMainThread;
import de.myRemoteControlServer.ui.ServerFrame;

public class Main {

	private Main(){
		initiale();
	}
	
	private void initiale(){
		frame = new ServerFrame();
		mainThread = new ServerMainThread(1111);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	
	private ServerMainThread mainThread;
	private ServerFrame frame;
}
