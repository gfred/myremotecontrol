package de.myRemoteControlServer.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public class Logger {
	
	private Logger(){
		log.add(getDateTime() + "Server Start");
		writeLogTimer();
	}
	
	public static Logger getInstance(){
		if(instance == null){
			instance = new Logger();
		}
		return instance;
	}
	
	public synchronized void addMessage(String msg){
		if(available){
			try{
				wait();
			}catch (Exception e) {}
		}		
		log.add(getDateTime()+msg);
		available = true;
		notifyAll();
	}
	
	public synchronized List<String> getLog(){
		if(!available){
			try{
				wait();
			}catch (Exception e) {}
		}
		available = false;
		notifyAll();
		return log;
	}
	
	private String getDateTime(){
		return new SimpleDateFormat("# yyyy.MM.dd - H:mm:ss > ").format(new Date(System.currentTimeMillis()));
	}
	
	private void writeLogTimer(){
		Timer timer = new Timer();
		timer.schedule(new WriteLogTask(log), 3600000, 3600000);		
	}
	
	public void writeLog(){
		new WriteLogTask(log).run();
	}
	
	private boolean available = true; 
	private static Logger instance = null;
	private ArrayList<String> log = new ArrayList<String>();
}