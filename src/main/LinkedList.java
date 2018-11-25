package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LinkedList  {	// The main linked list used to execute the scheduling algorithms
    private int count, bt;
    private linkedListNode headNode;
    private linkedListNode tailNode;
    
    public LinkedList() { // Default Constructor to create the linked list
        this.headNode = this.tailNode = null;
        this.count = bt = 0;
    }
	
	public boolean delete(String processID) { // Used to delete a process from the list by process ID
		linkedListNode current, prevlinkedListNode, node;
		int counter = 0;
		
		boolean removed = false;

		current = this.headNode;
		
		prevlinkedListNode = this.headNode;
		
		if(!this.isEmpty()) {
			if(this.headNode.getData().getpid().equals(processID)) { // If the process to be deleted is the one at the head of the list
				node = this.headNode;

				if(this.headNode.getNextNode() == null) {
					this.headNode = null;
					this.tailNode = null;
				}
				else {
					this.headNode = this.headNode.getNextNode();
				}
				node = null;
				this.count--;
				removed = true;
			}
			else if(this.tailNode.getData().getpid().equals(processID)) { // If the process to be deleted is the one at the tail of the list
				node = this.headNode;

				while(node.getData().getpid() != this.tailNode.getData().getpid()) {
					prevlinkedListNode = node;
					node = node.getNextNode();
				}
				
				prevlinkedListNode.setNextNode(null);
				this.tailNode = prevlinkedListNode;
				this.count--;
				node = null;
				removed = true;
			}
			else {
				while(current.getNextNode() != null) { // If the process to be deleted is somewhere in the middle of the list
					counter++;
					node = current;
					current = current.getNextNode();

					if(node.getData().getpid().equals(processID)) {
						prevlinkedListNode.setNextNode(current);
						node = null;
						this.count--;
						removed = true;
						
						break;
					}
					if(counter <= 1) { }
					else {
						prevlinkedListNode = prevlinkedListNode.getNextNode();
					}
				}
			}
		}
		else {
			System.out.println("\nThe linked list is empty");
		}
		
		return removed;
	}
	
	public void storeDeletedprocessManifest(processManifest node) { // Used the deleted process to the list
		FileWriter file = null;
		
		try {
			file = new FileWriter("output.txt", true);
			
			file.write("DELETED: Process ID: "+node.getpid()+"\tArrival Time: "+node.getat()+"\tBurst Time: "+node.getbt()+
					"\tSleep Time: "+node.getSleepTime()+"\tCreate Time: "+node.getCreateTime()+"\tEnd Time: "+System.currentTimeMillis()
					+"\tPriority: "+node.getPriority()+"\tBase Address: "+node.getBaseAddress()+"\n");
			
			file.close();
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}

		finally
		{
			try{
				file.close();
			}
			catch (IOException ioex){
			}
		}
		
		saveToFile();
	}
    
    @SuppressWarnings("unused")
	public void insertAtBack(processManifest data) { // Used to insert a node to the back of the list
        linkedListNode node = new linkedListNode(data, null);
        
        if(node != null) {
            if(isEmpty()) {
                this.headNode = node;
                this.tailNode = node;
                this.count++;
            }
            else {
                this.tailNode.setNextNode(node);
                this.tailNode = node;
                this.count++;
            }
        }
        else {
        	System.out.println("error inserting");
        }
    }
 
    
    
    public int getCount() {
    	return this.count;
    }
    
    public void sort() { // Used to sort the list. This sorting method is called Selection Sort
    	linkedListNode node = this.headNode, nextlinkedListNode = null;
    	
    	boolean somethingChanged = false;
    	
    	if(!isEmpty()) {
    		System.out.println("\nSorting the linked list\nThis is the current list:\n");
    		display();
    		while(node != null) {
        		nextlinkedListNode = node.getNextNode();
        		
        		if(nextlinkedListNode != null) {
        			while(nextlinkedListNode != null) {
        				if(nextlinkedListNode.getData().getat() <= node.getData().getat()) {
                			processManifest nodeData = nextlinkedListNode.getData();        			
                			nextlinkedListNode.setData(node.getData());
                			node.setData(nodeData);
                			somethingChanged = true;
                		}
        				
        				nextlinkedListNode = nextlinkedListNode.getNextNode();
        			}
        		}
        		else {
        			break;
        		}
        		
        		node = node.getNextNode();
        	}
    		
    		if(somethingChanged) {
        		System.out.println("\nThe list was sorted");
    	    	System.out.println("The sorted list is:\n");
    	    	
    	    	display();
        	}
        	else {
        		System.out.println("Nothing was sorted\n");
        	}
    	}
    	else {
    		System.out.println("\nThe list is empty");
    	}
    }
    
    public void insertByArrivalTime(processManifest data) { // Used to insert the nodes at the correct positions in the linkedlist by arrival times
    	if(isEmpty()) {
            insertAtBack(data);
        }
        else {
            int headArrivalTime = getheadNode().getat();
            int tailArrivalTime = gettailNode().getat();
            
            if((data.getat() >= headArrivalTime) && (data.getat() >= tailArrivalTime)) {
                insertAtBack(data);
            }
            else if((data.getat() >= headArrivalTime) && (data.getat() <= tailArrivalTime)) {
                insertAtMiddle(data);
            }
            else if(data.getat() < headArrivalTime) {
                insertAtFront(data);
            }
        }
    }
    
    public processManifest getheadNode() { // Get the data from the head of the linked list
        processManifest data = null;
        
        if(this.headNode != null) {
            data = this.headNode.getData();
        }
        return data;
    }
    
    public processManifest gettailNode() { // Get the data from the tail of the linked list
        processManifest data = null;
        
        if(this.tailNode != null) {
            data = this.tailNode.getData();
        }
        return data;
    }
    
    public int getbt() {
    	return this.bt;
    }
    
    @SuppressWarnings("unused")
	public void insertAtFront(processManifest data) { // Used to insert data at the back of the linked list
        linkedListNode node = new linkedListNode(data, null);
        
        if(node != null) {
            if(isEmpty()) {
                this.headNode = node;
                this.tailNode = node;
                this.count++;
            }
            else {
                node.setNextNode(this.headNode);
                this.headNode = node;
                this.count++;
            }
        }
        else {
            System.out.println("error inserting");
        }
    }
     
    public void saveToFile() { // Used to append all the data from the linked list to the file
    	linkedListNode node = this.headNode;
    	FileWriter file = null;
		
		try {
			file = new FileWriter("output.txt", true);
			
			while(node != null) {
				
				file.write("Process ID: "+node.getData().getpid()+"\tArrival Time: "+node.getData().getat()+(node.getData().getat()<10?"\t":"")+"\tBurst Time: "+node.getData().getbt()+
						"\tSleep Time: "+node.getData().getSleepTime()+"\tCreate Time: "+node.getData().getCreateTime()+"\tEnd Time: "+System.currentTimeMillis()+"\tPriority: "+node.getData().getPriority()+"\n");
				
	    		node = node.getNextNode();
	    	}
			file.write("\n\n");
			file.close();
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}

		finally
		{
			try{
				file.close();
			}
			catch (IOException ioex){
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
    
    public void search(String processID) { // Used to search for a node
    	linkedListNode node = this.headNode;
    	boolean found = false;
    	
    	System.out.println("\nSearching for ID: "+processID);
    	
    	while(node != null) {
    		if(node.getData().getpid().equals(processID)) {
    			System.out.println(processID+" was found");
    			found = true;
    			break;
    		}
    		node = node.getNextNode();
    	}
    	
    	if(!found) {
    		System.out.println("No process was found");
    	}
    }
    
	@SuppressWarnings("unused")
	public void destroy() {
		linkedListNode node;
		
		if(!isEmpty()) {
			while(!isEmpty()) {
				node = this.headNode;
				this.headNode = this.headNode.getNextNode();
				node = null;
				this.count--;
			}
		}
	}
    
    @SuppressWarnings("unused")
	public void insertAtMiddle(processManifest data) { // used to insert a node somewhere in the middle of the list
    	linkedListNode previouslinkedListNode, nextlinkedListNode, node = new linkedListNode(data, null);
		int nodeSize;

		previouslinkedListNode = headNode;
		
		nextlinkedListNode = headNode;
		
		if(node != null) {
			while(previouslinkedListNode != null) {
				nextlinkedListNode = previouslinkedListNode.getNextNode();

				if(nextlinkedListNode != null) {
					nodeSize = nextlinkedListNode.getData().getat();
					
					if(data.getat() <= nodeSize) {
						previouslinkedListNode.setNextNode(node);
						node.setNextNode(nextlinkedListNode);
						this.count++;
						
						break;
					}
					
					previouslinkedListNode = previouslinkedListNode.getNextNode();
				}
				
				else {
					break;
				}
			}
		}
		else {
			System.out.println("error inserting");
		}
	}
   
	public boolean delete(String processID, boolean status) { // Used to delete a node and display the deleted node. It's the same as the previous delete method but with a boolean to determine if the deleted
		linkedListNode current, prevlinkedListNode, node; 	// Node must be shown or not
		
		int counter = 0;
		boolean removed = false;

		current = this.headNode;
		prevlinkedListNode = this.headNode;
		processManifest pcb = new processManifest();
		
		if(!this.isEmpty()) {
			if(this.headNode.getData().getpid().equals(processID)) {
				node = this.headNode;

				if(this.headNode.getNextNode() == null) {
					this.headNode = null;
					
					this.tailNode = null;
				}
				else {
					this.headNode = this.headNode.getNextNode();
				}
				
				pcb = node.getData();
				
				if(status) {
					
					System.out.println("\nDeleted Process ID: "+node.getData().getpid()+"\tArrival Time: "+node.getData().getat()+"\tBurst Time: "+node.getData().getbt()+
		    				"\tSleep Time: "+node.getData().getSleepTime()+"\tCreate Time: "+node.getData().getCreateTime()+"\tEnd Time: "+System.currentTimeMillis()+
		    				"\tBase Address: "+node.getData().getBaseAddress());
				}
				else {

					System.out.print("\nProcess ID: "+processID+" was deleted");
				}
				
				node = null;
				
				this.count--;
				
				removed = true;
			}
			else if(this.tailNode.getData().getpid().equals(processID)) {
				node = this.headNode;

				while(node.getData().getpid() != this.tailNode.getData().getpid()) {
					prevlinkedListNode = node;
					node = node.getNextNode();
				}
				
				prevlinkedListNode.setNextNode(null);
				this.tailNode = prevlinkedListNode;
				this.count--;
				
				pcb = node.getData();
				
				if(status) {
					
					System.out.println("\nDeleted Process ID: "+node.getData().getpid()+"\tArrival Time: "+node.getData().getat()+"\tBurst Time: "+node.getData().getbt()+
		    				"\tSleep Time: "+node.getData().getSleepTime()+"\tCreate Time: "+node.getData().getCreateTime()+"\tEnd Time: "+System.currentTimeMillis()+
		    				"\tBase Address: "+node.getData().getBaseAddress());
				}
				
				node = null;
				removed = true;
			}
			else {
				while(current.getNextNode() != null) {
					counter++;
					node = current;
					current = current.getNextNode();

					if(node.getData().getpid().equals(processID)) {
						prevlinkedListNode.setNextNode(current);
						
						pcb = node.getData();
						if(status) {
							System.out.println("\nDeleted Process ID: "+node.getData().getpid()+"\tArrival Time: "+node.getData().getat()+"\tBurst Time: "+node.getData().getbt()+
				    				"\tSleep Time: "+node.getData().getSleepTime()+"\tCreate Time: "+node.getData().getCreateTime()+"\tEnd Time: "+System.currentTimeMillis()+
				    				"\tBase Address: "+node.getData().getBaseAddress());
						}
						
						node = null;
						this.count--;
						removed = true;
						
						break;
					}
					if(counter <= 1)
						
					{}
					else {
						prevlinkedListNode = prevlinkedListNode.getNextNode();
					}
				}
			}
		}
		else {
			System.out.println("\nThe linked list is empty");
		}
		
		if(removed) {
			System.out.println("\nDisplaying Linked List:");
			display();
			storeDeletedprocessManifest(pcb);
		}
		else {
			System.out.println("No process was removed");
		}
		
		return removed;
	}
	 
    public void nonPreEmp(int pLevel, Queue dList, turnAroundTime turnAroundTime, int bt, String eID) { // Used to schedule the processes in the linked list
    	processManifest data; 								// using the non-preemptive priority scheduling algorithm
    	NodeData data1 = new NodeData();
 		processManifest turnAroundTimeData = new processManifest();
 		
 		if(!isEmpty()) { // IF the list is empty
 			data = this.highestPriority(bt, pLevel); // Get the node with the highest priority
 			
 			if(data.getpid().equals("")) {
 				data = getheadNode();
 			}
 			
 			eID = data.getpid();
 			
 			data1.sid(data.getpid());
 			data1.sat(bt);
            bt += data.getbt();
            data1.sbt(bt);
             
            dList.insertAtBack(data1);
            
 			turnAroundTimeData.setpid(data.getpid());
 			turnAroundTimeData.setat(data.getat());
 			turnAroundTimeData.setbt(data.getbt());
 			turnAroundTimeData.cat(bt);
 			
 			turnAroundTime.insertAtBack(turnAroundTimeData);
 			
 			this.delete(data.getpid(), true);
 			
 			nonPreEmp(pLevel, dList, turnAroundTime, bt, eID);
 		}
    }
    
    @SuppressWarnings("rawtypes") 

    // Used to allocate processes in memory using preemptive priority scheduling algorithm	
	public void preEmptivePriority(HashMap<String, Integer> map, processManifest pcb, int level, int arrivalTime, int burstTime) {
        linkedListNode temp;
        processManifest data, data1;
        int tempBurstTime = 0;
        int size = 0;
        
        if (!this.isEmpty()) {
            temp = this.headNode;
            
            data = this.highestPriority(burstTime, level);
            
            if (data.getpid().equals("")) {
                data = this.headNode.getData();
            }
            
            while (temp != null) {
                if (temp.getData().getpid().equals(data.getpid())) {
                    data = temp.getData();
                    break;
                }
                temp = temp.getNextNode();
            }
            
            tempBurstTime = data.getbt();
            
            for (int i = 0; i < tempBurstTime; i++) {
                burstTime++;
                data.setbt(data.getbt() - 1);
                temp.setData(data);
                
                data1 = this.highestPriority(burstTime, 1);
                
                if (!data.getpid().equals(data1.getpid())) {
					break;
				}
            }
            
            arrivalTime = burstTime;
            
            if(temp.getData().getbt() == 0) {
                this.delete(temp.getData().getpid());
            }
            
            Iterator iter = map.entrySet().iterator();
			
			while(iter.hasNext()) {
				Map.Entry entry = (Map.Entry)iter.next();
				String key = (String)entry.getKey();
				
				if(key.equals(data.getpid())) {
					size = (Integer)entry.getValue();
				}
			}
			
			map.replace("Shared List Value", map.get("Shared List Value").intValue() + size);
			map.remove(data.getpid());
			
			if((pcb.getSize() != 0) && (map.get("Shared List Value").intValue() >= pcb.getSize())) { // Try to see if the new pcb can be inserted after a process was removed
					this.insertByArrivalTime(pcb);
					
					map.put(pcb.getpid(), pcb.getSize());
					
					System.out.println("\nShared Integer Is Available");
					
					map.replace("Shared List Value", map.get("Shared List Value").intValue() - pcb.getSize());
			} else { // If the pcb was not inserted, call the method again to do another scheduling until the process is inserted
				preEmptivePriority(map, pcb, level, arrivalTime, burstTime);
			}
		}
	}
    
    public void calculateTotal() { // Used to calculate the total integers in the list
    	linkedListNode node = headNode;
    	int arrivalTime = 0, bt = 0;
    	
    	while(node != null) {
    		arrivalTime = arrivalTime + node.getData().getat();
    		bt = bt + node.getData().getbt();
    		node = node.getNextNode();
    	}
    	
    	System.out.println("\nThe total arrival time: "+arrivalTime);
    	System.out.println("The total burst time: "+bt+"\n");
    }
    
    public void display() {
    	linkedListNode node = this.headNode;
    	
    	while(node != null) {
    		System.out.println("Process ID: "+node.getData().getpid()+"\tArrival Time: "+node.getData().getat()+"\tBurst Time: "+node.getData().getbt()+
    				"\tSleep Time: "+node.getData().getSleepTime()+"\tCreate Time: "+node.getData().getCreateTime());
    		node = node.getNextNode();
    	}
    }
    
    public processManifest highestPriority(int bt, int pLevel) { // Get the pcb with the highest priority
		processManifest data = new processManifest();
		        
		linkedListNode node = headNode;
        data.setpid("");

		while(node != null) {
			if(node.getData().getat() > bt) {
				break;
			} else if(node.getData().getpid().equals(this.headNode.getData().getpid())) {
				data = node.getData();
			} else if((pLevel == 1) && (node.getData().getPriority() < data.getPriority())) { // 1 means the lowest priority means highest priority
				data = node.getData();
			} else if((pLevel == 2) && (node.getData().getPriority() > data.getPriority())) { // 2 means the highest priority means highest priority
				data = node.getData();
			}
			
			node = node.getNextNode();
		}
		return data;
	}
}
