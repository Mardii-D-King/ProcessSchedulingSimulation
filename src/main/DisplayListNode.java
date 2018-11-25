package main;

public class DisplayListNode { // The node for the display list
    DisplayListNodeData data; // The data portion for this node
    DisplayListNode nextNode; // The reference to the next node
    
    // Mutators
    public void setNextNode(DisplayListNode nextNode) {
		this.nextNode = nextNode;
	}
    
    public void setData(DisplayListNodeData data) {
        this.data = data;
    }

    // Accessors
	public final DisplayListNode getNextNode() {
		return this.nextNode;
	}
    
    public final DisplayListNodeData getData()  {
        return this.data;
    }
	
    public DisplayListNode() { // Default Constructor
        this.data = new DisplayListNodeData();
        this.nextNode = null;
    }
    
	public DisplayListNode(DisplayListNodeData data, DisplayListNode nextNode) { // Primary Constructor
		this.data = data;
		this.nextNode = nextNode;
	}
}
