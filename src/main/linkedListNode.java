package main;

public class linkedListNode {
	public void setData(processManifest data) {
        this.data = data;
    }
    
    public processManifest getData() {
        return this.data;
    }
    
    public void setNextNode(linkedListNode nextNode) {
        this.nextNode = nextNode;
    }
    
    public linkedListNode getNextNode() {
        return this.nextNode;
    }
    
    public linkedListNode() {
    	data = new processManifest();
        this.nextNode = null;
    }
    
    public linkedListNode(processManifest data, linkedListNode nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }
    
    private processManifest data;
    private linkedListNode nextNode;
}