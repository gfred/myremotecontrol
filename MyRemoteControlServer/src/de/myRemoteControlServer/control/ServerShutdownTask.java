package de.myRemoteControlServer.control;

import java.util.TimerTask;

public class ServerShutdownTask extends TimerTask{
	
	public ServerShutdownTask(long starttime) {
		this.logger = Logger.getInstance();
		this.starttime = starttime;
	}
	
	@Override
	public void run() {
		//TODO shutdown server / pc
		System.exit(0);
	}
	
	public long getStarttime() {
		return starttime;
	}
	
	private long starttime;	
	private Logger logger = null;
}
