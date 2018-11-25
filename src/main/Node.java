package main;

//The node for the Queue	
public class Node { 
    NodeData data;
    Node nextNode; 
    	
    public Node() {
        this.data = new NodeData();
        this.nextNode = null;
    }
    
	public Node(NodeData data, Node nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}
	
    public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
    
  	public final Node getNextNode() {
  		return this.nextNode;
  	}
  	
    public void setData(NodeData data) {
        this.data = data;
    }

    public final NodeData getData()  {
        return this.data;
    }
}
