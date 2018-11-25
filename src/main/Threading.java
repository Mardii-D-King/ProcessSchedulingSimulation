package main;

public class Threading implements Runnable { // Thread used to execute the processes
	private MainLinkedList mainLinkedList;
	private Semaphore semaphore;
	private ProcessControlBlock pcb;
	
	public void run() {
		System.out.println("\nThread was started --"); this.semaphore.release();
		
		synchronized(this.mainLinkedList) { // The thread executing the task for the process
			if(this.pcb.getTask() == 1) { this.mainLinkedList.insertAtBack(this.pcb); System.out.println("\nDisplaying the LinkedList: "); this.mainLinkedList.display(); }
			else if(this.pcb.getTask() == 2) { this.mainLinkedList.delete(this.pcb.getpid(), false); }
			else if(this.pcb.getTask() == 3) { this.mainLinkedList.search(this.pcb.getpid()); }
			else if(this.pcb.getTask() == 4) { this.mainLinkedList.sort(); }
			else if(this.pcb.getTask() == 5) { System.out.println("\nThe task for this process is to calculate total"); this.mainLinkedList.calculateTotal(); }
		}
		this.semaphore.take();
		System.out.println("Thread was ended --");
	}
	
	public Threading(MainLinkedList mainLinkedList, Semaphore semaphore, ProcessControlBlock pcb) { // Constructor to create the thread
		this.semaphore = semaphore;
		this.mainLinkedList = mainLinkedList;
		this.pcb = pcb;
	}
}