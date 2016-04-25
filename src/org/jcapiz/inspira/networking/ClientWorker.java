package org.jcapiz.inspira.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.jcapiz.inspira.shared.Chunk;
import org.jcapiz.inspira.vistas.QuestionSpinner;

public class ClientWorker extends Thread {

	private ServerSocket server;
	
	@Override
	public void run(){
		try{
			server = new ServerSocket(33401);
			Socket socket;
			ObjectInputStream entrada;
			Chunk unidadesAprendizaje;
			while(true){
				socket = server.accept();
				entrada = new ObjectInputStream(socket.getInputStream());
				unidadesAprendizaje = (Chunk)entrada.readObject();
//				new QuestionSpinner(unidadesAprendizaje).start();
			}
		}catch(ClassNotFoundException | IOException e){
			e.printStackTrace();
		}
	}
}
