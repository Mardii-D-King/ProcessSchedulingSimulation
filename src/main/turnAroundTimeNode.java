package main;

public class TurnAroundTimeLinkedListNode { // The node for the turn around linkedlist
    private ProcessControlBlock dataPortion; // The data portion
    private TurnAroundTimeLinkedListNode nextNode; // Reference to the next node
    
    // Mutators
	 public void setData(ProcessControlBlock dataPortion) {
		 this.dataPortion = dataPortion;
	 }

	 public void setNextNode(TurnAroundTimeLinkedListNode nextNode) {
		 this.nextNode = nextNode;
	 }

	 // Accessors
	 public final ProcessControlBlock getData() {
		 return this.dataPortion;
	 }
	 
	 public TurnAroundTimeLinkedListNode getNextNode() {
		 return this.nextNode;
	 }

	 public TurnAroundTimeLinkedListNode() { // Default Constructor
		 dataPortion = new ProcessControlBlock(); 
		 this.nextNode = null;
	 }

	 public TurnAroundTimeLinkedListNode(ProcessControlBlock dataPortion, TurnAroundTimeLinkedListNode nextNode) { // Primary Constructor
		 this.dataPortion = dataPortion;
		 this.nextNode = nextNode;
	 }
}