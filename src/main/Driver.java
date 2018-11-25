package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) {
		new Driver();
	}
	
	// Used to clear the output file. Re-creates the file with no data
	public  void clearDataFromFile() { 
		FileWriter file = null;
		
		try {
			file = new FileWriter("output.txt");
			file.write("");
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
	}
	
	// Accept user input
	public Driver() {
		Scanner scan = new Scanner(System.in); 
		
		int totatprocesses = 0, arrivalTime = 0, burstTime = 0, total = 0, shared = 200;
		
		String processID = "";
		HashMap<String, Integer> sharedDictionary = new HashMap<String, Integer>();
		sharedDictionary.put("Shared List Value", shared);
		linkedListAccessControl sema = new linkedListAccessControl(true);
		
		// This list is used to allocate the processes in RAM
		LinkedList mainLinkedList1 = new LinkedList();
		// This list is used to execute the tasks in the different threads
		LinkedList mainLinkedList2 = new LinkedList(); 
		LinkedList mainLinkedList3 = new LinkedList(); // This list is used to execute the processes using pre-emptive priority
		
		turnAroundTime turnAroundTime = new turnAroundTime();
		
		Queue dList = new Queue();
		
		ArrayList<processManifest> actions = new ArrayList<>();
		
		System.out.println("Please enter the total amount of processes would you like to use. Thanks");
		totatprocesses = scan.nextInt();
		
		if((totatprocesses < 10) || (totatprocesses > 30)) {
			System.out.println("Please enter a value that is less than 30 and greater than 10");
		}
		else {
			for(int index = 0; index < totatprocesses; index++) {
				
				processID = Integer.toString(index+1);
				System.out.println("\nProcess ID is: "+processID);
				
				// Gets the random arrival time
				arrivalTime = getArrivalTimes(index+1); 
				System.out.println("Arrival Time: "+arrivalTime);
				 
				 // Gets the random burst time
				burstTime = getBurstTimes(totatprocesses);
				System.out.println("Burst Time: "+burstTime);
				
				// Adds 40 to the total, since every process has a size of 40
				total += 40; 
				
				if(40 > shared) { 
					System.out.println("Size is too large. Shared Integer: "+shared);
				}
				else { 
					if(40 <= sharedDictionary.get("Shared List Value").intValue()) { // If the current process can allocated
						mainLinkedList1.insertByArrivalTime(new processManifest(processID, arrivalTime, burstTime));
						sharedDictionary.replace("Shared List Value", sharedDictionary.get("Shared List Value").intValue() - 40); // Minus 40 from the total RAM that is available
						sharedDictionary.put(processID, 40);
					}
					else { // If the current process can't be allocated
						System.out.println("\nShared Integer List is Full. Please wait. \nProcess ID: "+processID+" is waiting to be processed");
						processManifest processCB = new processManifest(processID, arrivalTime, burstTime);
						total = total - sharedDictionary.get("Shared List Value").intValue();
						mainLinkedList1.preEmptivePriority(sharedDictionary, processCB, 1, 1, burstTime);
					}
					
					processManifest processManifest = new processManifest(processID, arrivalTime, burstTime); // Creates the PCB
					processManifest.setBaseAddress(mainLinkedList3.getCount());
					
					System.out.println("The task for: "+processID+" is: "+descriptiveDescription(processManifest.getTask())); // Displays the task for the pCB
					
					Thread thread = new Thread(new Threading(mainLinkedList2, sema, processManifest)); // Creates a new thread which will run the task for process
					thread.start(); // Starts the thread
					 
					if ((processManifest.getTask() != 4) || (processManifest.getTask() != 5)) { // If the task is Adding, Removing or Sorting, multiple processes can be executed
						try {  								// without having to wait for another thread. If the task is Retrieve or Calculate, the read must run to completion before it can execute another thread
							thread.join();					// thread.join() waits for thread to die
						} catch (InterruptedException e) {
							e.printStackTrace();
						}						
					}
					
					actions.add(processManifest); // Adds that pcb to the list of thread, which will show all the processes when the tasks are executed
					mainLinkedList3.insertByArrivalTime(processManifest);
				}
				
				System.out.println("REMAINING SHARED INTEGER: "+sharedDictionary.get("Shared List Value").intValue()+"\n");
			}
			
			mainLinkedList2.destroy();
			
			mainLinkedList1.preEmptivePriority(sharedDictionary, new processManifest(), 1, mainLinkedList1.getheadNode().getat(), mainLinkedList1.getbt()); // Schedules the tasks in RAM to free all memory allocations and gain the original total space of 200
			System.out.println("TOTAL FREE SHARED INTEGER: "+sharedDictionary.get("Shared List Value").intValue()+"\n");
			mainLinkedList1.destroy(); // Destroys that list
			
			System.out.println("\nActions To Be Performed On List\n");
			for(processManifest processCB: actions) { // Displays all the processses
				System.out.println("ID: "+processCB.getpid()+"\tArrivalTime: "+processCB.getat()+"\tBurstTime: "+processCB.getbt()+"\tTask: "+
						descriptiveDescription(processCB.getTask())+"\tPriority: "+processCB.getPriority());
			}
			
			System.out.println("\nNON-PREEMPTIVE PRIORITY SCHEDULING");
			actions = null;
	
			this.clearDataFromFile();
			
			mainLinkedList3.nonPreEmp(1, dList, turnAroundTime, mainLinkedList3.getheadNode().getat(), ""); // Schedule the processes using non-preemptive priority
	
			dList.display(); // Creates the diagram to display the processes
			turnAroundTime.display(); // Display the turn around and waiting time for the processes
			dList.destroy();
			mainLinkedList3.destroy();
		}
		
		scan.close();
	}
	
	public final int getArrivalTimes(int range) { // Used to create a random arrival time for the process within a range from 0 to the index counter in the number of processes loop
		Random random = new Random();
		
		int v = random.nextInt(range);
		
		return v;
	}
	
	public final int getBurstTimes(int range) { // Used to create a random burst time for the process within a range from 0 to the number of processes
		Random random = new Random();
		
		int v = random.nextInt(range);
		
		if(v == 0) {
			return 1;
		}
		else {
			return v;
		}
	}
	
	public final String descriptiveDescription(int task) { // This is used to get the description for the different tasks
		String description = "";
		if(task == 1) {
			description = "Adding";
		}
		else if(task == 2) {
			description = "Removing";
		}
		else if(task == 3) {
			description = "Retrieving";
		}
		else if(task == 4) {
			description = "Sort";
		}
		else if(task == 5) {
			description = "Calculate Total";
		}
		
		return description;
		
	}
}
