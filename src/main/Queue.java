package main;

import java.io.FileWriter;
import java.io.IOException;

//This Linkedlist is used to store and draw the diagram for the processes at the end of the program
public class Queue { 

	private Node Head, Tail; 
	
	public Queue() {
		Head = null;
		Tail = null;
	}

	//Insert At Back of Linked List
	@SuppressWarnings("unused")
	public void insertAtBack(NodeData Data) { 
		Node temp = new Node(Data, null);

		if (temp != null) {
			if (checkIfEmpty()) {
				Head = temp;
				Tail = temp;
			}
			else {
				Tail.setNextNode(temp);
				Tail = temp;
			}
		}
        else {
            System.out.println("Error inserting node at back");
        }
	}
	
	// Used to check if the linked list is empty
	boolean checkIfEmpty() { 
		if(Head == null) {
			return true;
		}
		return false;
	}

	public void deleteNode(Node newNode) { // Used to delete a node from the linkedlist.
		Node current, prevNode, temp;
		int counter = 0;

		current = Head;
		prevNode = Head;

		if(Head != null) {
			if (Head == newNode) { // If the node to be deleted is at the head of the linkedlist
				temp = Head;

				if(Head.getNextNode() == null) {
					Head = null;
					Tail = null;
				}
				else {
					Head = Head.getNextNode();
				}
				temp = null;
			}
			else if(Tail == newNode) { // If the node to be deleted is at the tail of the linkedlist
				temp = Head;

				while(temp != Tail) {
					prevNode = temp;
					temp = temp.getNextNode();
				}

				prevNode.setNextNode(null);
				Tail = prevNode;
				temp = null;
			}
			else {
				while(current.getNextNode() != null) { // If the node to be deleted is somewhere in the middle of the list, it will search till it finds the node and remove it
					counter++;                         // then rejoins the list so all the nodes stay connected
					temp = current;
					current = current.getNextNode();

					if(temp == newNode) {
						prevNode.setNextNode(current);
						temp = null;
						break;
					}
					if (counter <= 1) { }
					else {
						prevNode = prevNode.getNextNode();
					}
				}
			}
		}
		else {
			System.out.println("\nList is empty");
		}
	}

	// Displaying List
	public void display() { // This is used to create the driagram for all the nodes at the end of the execution of the program
		Node temp = Head;
		int ranCount = 0, oneDigit = 0;
		long oneID = 0;
		
		System.out.println();
		String first = "", second = "", third = "", fourth = "";

		while(temp != null) {
			oneID = temp.getData().getid().length();

			System.out.print((ranCount==0?"|":""));
			first += (ranCount==0?"|":"");
			
			if(oneID==1)
			{ System.out.print("---|"); first += "---|"; }
			else if(oneID==2)
			{ System.out.print("----|"); first += "----|"; }
			else if(oneID==3)
			{ System.out.print("-----|"); first += "-----|"; }

			ranCount = 1;

			temp = temp.getNextNode();
		}

		temp = Head;
		ranCount = 0; System.out.println();;

		while(temp != null) {
			System.out.print((ranCount==0?"|":"")+" "+temp.getData().getid()+" |");
			second += (ranCount==0?"|":"")+" " +temp.getData().getid()+" |";
			
			ranCount = 1;

			temp = temp.getNextNode();
		}

		temp = Head;
		ranCount = 0; System.out.println();

		while(temp != null) {
			oneID = temp.getData().getid().length();

			System.out.print((ranCount==0?"|":""));
			third += (ranCount==0?"|":"");
			
			if(oneID==1)
			{ System.out.print("---|"); third += "---|"; }
			else if(oneID==2)
			{ System.out.print("----|"); third += "----|"; }
			else if(oneID==3)
			{ System.out.print("-----|"); third += "-----|"; }

			ranCount = 1;

			temp = temp.getNextNode();
		}

		temp = Head;
		ranCount = 0;System.out.println();

		while(temp != null) {
			if(ranCount == 0) {
				if(temp.getData().getarrTime() <= 9) {
					oneDigit = 1;
				}
				else if(temp.getData().getarrTime() >= 100) {
					oneDigit = 3;
				}
				else {
					oneDigit = 2;
				}
			}
			else {
				if(temp.getData().getarrTime() <= 9) {
					oneDigit = 1;
				}
				else if(temp.getData().getarrTime() >= 100) {
					oneDigit = 3;
				}
				else {
					oneDigit = 2;
				}
			}

			if(temp.getData().getid().length() == 1) {
				oneID = 1;
			}
			else if(temp.getData().getid().length() == 2) {
				oneID = 2;
			}
			
			if(ranCount == 0) {
				System.out.print(temp.getData().getarrTime());
				fourth += temp.getData().getarrTime();
			}
			if(oneID == 1 && oneDigit == 1) {
				System.out.print("   "+temp.getData().getburTime());
				fourth += "   "+temp.getData().getburTime();
			}
			else if(oneID == 2 && oneDigit == 2) {
				System.out.print("   "+temp.getData().getburTime());
				fourth += "   "+temp.getData().getburTime();
			}
			else if(oneID == 1 && oneDigit == 2) {
				System.out.print("  "+temp.getData().getburTime());
				fourth += "  "+temp.getData().getburTime();
			}
			else if(oneID == 2 && oneDigit == 1) {
				System.out.print("    "+temp.getData().getburTime());
				fourth += "    "+temp.getData().getburTime();
			}
			else if(oneID == 1 && oneDigit == 3) {
				System.out.print(" "+temp.getData().getburTime());
				fourth += " "+temp.getData().getburTime();
			}
			else if(oneID == 2 && oneDigit == 3) {
				System.out.print("  "+temp.getData().getburTime());
				fourth += "  "+temp.getData().getburTime();
			}

			ranCount = 1;
			temp = temp.getNextNode();
		}
		System.out.println();
		String list[] = {first, second, third, fourth};
		
		storeListToFile(list);
		first = second = third = fourth = "";
	}

	public void storeListToFile(String list[]) { // Used to append the diagram to the file
		FileWriter file = null;
		
		try {
			file = new FileWriter("output.txt", true);
			
			file.write("Non-Preemptive Scheduling\n");
			file.write(list[0]+"\n"+list[1]+"\n"+list[2]+"\n"+list[3]+"\n\n");
			
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
	
	// Destroying List
	public void destroy() {
		if(!checkIfEmpty()) {
			while(!checkIfEmpty()) {
				@SuppressWarnings("unused")
				Node temp = Head;
				Head = Head.getNextNode();
				temp = null;
			}
		}
		else {
		}
	}
}
