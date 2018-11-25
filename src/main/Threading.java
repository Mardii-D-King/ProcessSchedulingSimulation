package main;

public class Threading implements Runnable { // Thread used to execute the processes
	private LinkedList mainLinkedList;
	private linkedListAccessControl accessControl;
	private processManifest pcb;
	
	public void run() {
		System.out.println("\nThread was started --"); this.accessControl.release();
		
		synchronized(this.mainLinkedList) { // The thread executing the task for the process
			if(this.pcb.getTask() == 1) { this.mainLinkedList.insertAtBack(this.pcb); System.out.println("\nDisplaying the LinkedList: "); this.mainLinkedList.display(); }
			else if(this.pcb.getTask() == 2) { this.mainLinkedList.delete(this.pcb.getpid(), false); }
			else if(this.pcb.getTask() == 3) { this.mainLinkedList.search(this.pcb.getpid()); }
			else if(this.pcb.getTask() == 4) { this.mainLinkedList.sort(); }
			else if(this.pcb.getTask() == 5) { System.out.println("\nThe task for this process is to calculate total"); this.mainLinkedList.calculateTotal(); }
		}
		this.accessControl.take();
		System.out.println("Thread was ended --");
	}
	
	public Threading(LinkedList mainLinkedList, linkedListAccessControl accessControl, processManifest pcb) { // Constructor to create the thread
		this.accessControl = accessControl;
		this.mainLinkedList = mainLinkedList;
		this.pcb = pcb;
	}
}