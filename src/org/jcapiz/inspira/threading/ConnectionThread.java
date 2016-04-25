package org.jcapiz.inspira.threading;

import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread{

	private String tmpHost;
	private static final int PORT = 3315;
	private boolean success = false;
	
	public ConnectionThread(String host){
		tmpHost = host;
	}
	
	public String getHost(){
		return  success ? tmpHost : null;
	}
	
	@Override
	public void run(){
		try{
			Socket socket = new Socket(tmpHost,PORT);
			success = true;
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
