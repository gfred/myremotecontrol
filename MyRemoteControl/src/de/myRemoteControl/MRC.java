package de.myRemoteControl;

import de.myRemoteControl.control.ConnectionHandler;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MRC extends Activity {

	private ConnectionHandler connectionHandler = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		connectionHandler = new ConnectionHandler(this);
		
		setContentView(R.layout.main);
		
		
		Button connect = (Button) findViewById(R.id.Button01);
		Button send = (Button) findViewById(R.id.Button02);
		Button disconnect = (Button) findViewById(R.id.Button03);
		
		connect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				connectionHandler.connect("192.168.2.103", 1111);
			}
		});
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				connectionHandler.sendMessage("");
			}
		});
		
		disconnect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				connectionHandler.disconnect();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		connectionHandler.disconnect();
	}
}