package org.jcapiz.inspira.networking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.jcapiz.inspira.shared.Chunk;
import org.jcapiz.inspira.threading.ServerHostDiscovery;

public class HandleResponseToServer extends Thread {

	Chunk unidadesSeleccionadas;
	
	public HandleResponseToServer(Chunk unidadesSeleccionadas){
		this.unidadesSeleccionadas = unidadesSeleccionadas;
	}
	
	@Override
	public void run(){
		try{
			Socket socket = new Socket(ServerHostDiscovery.HOST, 3315);
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
			salida.writeObject(unidadesSeleccionadas);
			salida.flush();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
