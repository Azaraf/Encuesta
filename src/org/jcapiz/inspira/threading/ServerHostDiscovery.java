package org.jcapiz.inspira.threading;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.jcapiz.inspira.networking.ClientWorker;

public class ServerHostDiscovery extends Thread {

	private ConcurrentLinkedQueue<Runnable> taskQueue;
	WorkerThread[] workers;
	private static volatile boolean isRunning = true;
	public static volatile String HOST;
	private int offSet;
	private int threadsCompleted; // Number of threads finished.
	
	public ServerHostDiscovery(int offSet){
		this.offSet = offSet;
	}
	
	synchronized private void threadFinished(){
	    threadsCompleted++;
	    if (threadsCompleted == workers.length) { // All threads have finished.
	        isRunning = false; // Make sure running is false after the threads end.
	        workers = null;  // Discard the array that holds the threads.
	        new ClientWorker().start();
	        System.out.println("Servicio iniciado.");
	    }
	}
	
	@Override
	public void run(){
		taskQueue = new ConcurrentLinkedQueue<Runnable>(); // Create the queue.
		int limmit1 = 254;
		int limmit2 = 254;
		for (int i = offSet; i < limmit1; i++) {
		    for(int j = offSet; j < limmit2; j++){
				ConnectionThread task;
			    task = new ConnectionThread("192.168."+String.valueOf(i) + "."+String.valueOf(j));  // Create a task to compute one row of the image.
			    taskQueue.add(task); // Add the task to the queue.
		    }
		}
		int threadCount = 20 ; // Number of threads in the pool
		workers = new WorkerThread[threadCount];
		threadsCompleted = 0;
		isRunning = true;  // Set the signal before starting the threads!
		for (int i = 0; i < threadCount; i++) {
		    workers[i] = new WorkerThread();
		}
	}
	
	private class WorkerThread extends Thread{
		
		public WorkerThread(){
			setPriority(Thread.currentThread().getPriority() -1);
			setDaemon(true);
			start();
		}
		
		@Override
		public void run(){
			try{
				while(isRunning){
					Runnable task = taskQueue.poll();
					if(task != null)
						task.run();
					if( (HOST = ((ConnectionThread)task).getHost()) != null){
						isRunning = false;
					}
				}
			}finally{
				// Do something when finished.
				threadFinished();
			}
		}
	}
}