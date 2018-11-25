package main;

public class turnAroundTimeNode { // The node for the turn around linkedlist
    private processManifest dataPortion; // The data portion
    private turnAroundTimeNode nextNode; // Reference to the next node
    
    // Mutators
	 public void setData(processManifest dataPortion) {
		 this.dataPortion = dataPortion;
	 }

	 public void setNextNode(turnAroundTimeNode nextNode) {
		 this.nextNode = nextNode;
	 }

	 // Accessors
	 public final processManifest getData() {
		 return this.dataPortion;
	 }
	 
	 public turnAroundTimeNode getNextNode() {
		 return this.nextNode;
	 }

	 public turnAroundTimeNode() { // Default Constructor
		 dataPortion = new processManifest(); 
		 this.nextNode = null;
	 }

	 public turnAroundTimeNode(processManifest dataPortion, turnAroundTimeNode nextNode) { // Primary Constructor
		 this.dataPortion = dataPortion;
		 this.nextNode = nextNode;
	 }
}