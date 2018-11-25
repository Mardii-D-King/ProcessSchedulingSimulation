package main;

public class Node { // The node for the display list
    NodeData data; // The data portion for this node
    Node nextNode; // The reference to the next node
    
    // Mutators
    public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
    
    public void setData(NodeData data) {
        this.data = data;
    }

    // Accessors
	public final Node getNextNode() {
		return this.nextNode;
	}
    
    public final NodeData getData()  {
        return this.data;
    }
	
    public Node() { // Default Constructor
        this.data = new NodeData();
        this.nextNode = null;
    }
    
	public Node(NodeData data, Node nextNode) { // Primary Constructor
		this.data = data;
		this.nextNode = nextNode;
	}
}
