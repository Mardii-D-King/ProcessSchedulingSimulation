package main;

public class Semaphore { // Used to control access to the linked list when another thread is using it
	private boolean signal = false;
	
	public Semaphore(boolean signal) {
		this.signal = signal;
	}

	public synchronized void release() { // Tells the current thread to wait until the signal changes when another thread is finish with its task
		while(!this.signal) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.signal = false;
	}
	
	public synchronized void take() { // Used to let a thread know that no thread is currently running operations on the list
		this.signal = true;
		
		this.notify();
	}
}