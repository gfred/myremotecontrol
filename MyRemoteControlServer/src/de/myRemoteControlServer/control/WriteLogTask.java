package de.myRemoteControlServer.control;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

class WriteLogTask extends TimerTask{
	
	public WriteLogTask(List<String> logList){
		this.logList = logList;
	}
	
	@Override
	public void run() {
		File folder = new File(System.getProperty("user.home")+"\\MRCServer"); 
		
		if(!folder.exists()){
			folder.mkdir();
		}
		
		File file = new File(folder.getAbsolutePath()+"/mrcServer-"+new SimpleDateFormat("yyyy.MM.dd-H.mm.ss").format(new Date(System.currentTimeMillis()))+".log");
		
		if(logList!=null){
			try{
				FileWriter fw = new FileWriter(file);	
				for(String entry : logList){
					fw.write(entry+"\n");
					fw.flush();
				}
				fw.close();
				logList.clear();
				
				Logger.getInstance().addMessage("Logfile succesfull saved! "+file.getAbsolutePath());
			}catch (Exception e) {
				Logger.getInstance().addMessage("Error Saving Logfile: "+e.getLocalizedMessage());
			}			
		}
	}
	
	private List<String> logList = null;
}
