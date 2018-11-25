package main;

import java.io.FileWriter;
import java.io.IOException;

public class TurnAroundTimeLinkedList { // Used to calculate the waiting and turn around time for the processes
	TurnAroundTimeLinkedListNode headNode, tailNode;
	int count;
	
	public TurnAroundTimeLinkedList() { // Default Constructor
		this.count = 0;
		this.headNode = this.tailNode = null;
	}

	public void display() { // Displays and append the waiting and turn around time to the file
		TurnAroundTimeLinkedListNode node = this.headNode;
		double waitingTime = 0.0, turnAroundTime = 0.0;
		
		while(node != null) {
			waitingTime += node.getData().getwt();
			turnAroundTime += node.getData().getaroundtime();
			
			node = node.getNextNode();
		}
		
		System.out.println("\nAvg Waiting Time: "+(waitingTime / count));
		System.out.println("Avg Turn Around Time: "+(turnAroundTime / count));
		System.out.println("-------------------------------\n");

		FileWriter file = null;
		
		try {
			file = new FileWriter("output.txt", true);
			
			file.write("Avg Waiting Time: "+(waitingTime / count));
			file.write("\nAvg Turn Around Time: "+(turnAroundTime / count));
			
			file.close();
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}

		finally {
			try{
				file.close();
			}
			catch (IOException ioex){
			}
		}
		
		destroy();
	}

	@SuppressWarnings("unused")
	public void destroy() {
		if(!isEmpty()) {
			while(!isEmpty()) {
				TurnAroundTimeLinkedListNode node = this.headNode;
				this.headNode = this.headNode.getNextNode();
				node = null;
				this.count--;
			}
		}
	}
	
	public void insertAtBack(ProcessControlBlock data) { // Used to insert a new node at the back of the list
		TurnAroundTimeLinkedListNode node = new TurnAroundTimeLinkedListNode(data, null);

		if(node != null) {
			if (isEmpty()) {
				this.headNode = node;
				this.tailNode = node;
				this.count++;
			}
			else {
				this.tailNode.setNextNode(node);
				this.tailNode = node;
				count++;
			}
		}
	}

	public boolean isEmpty() {
		if(this.headNode == null) {
			return true;
		}
		else { 
			return false;
		}
	}
}