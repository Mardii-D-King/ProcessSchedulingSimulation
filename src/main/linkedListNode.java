package main;

public class MainLinkedListNode {
	public void setData(ProcessControlBlock data) {
        this.data = data;
    }
    
    public ProcessControlBlock getData() {
        return this.data;
    }
    
    public void setNextNode(MainLinkedListNode nextNode) {
        this.nextNode = nextNode;
    }
    
    public MainLinkedListNode getNextNode() {
        return this.nextNode;
    }
    
    public MainLinkedListNode() {
    	data = new ProcessControlBlock();
        this.nextNode = null;
    }
    
    public MainLinkedListNode(ProcessControlBlock data, MainLinkedListNode nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }
    
    private ProcessControlBlock data;
    private MainLinkedListNode nextNode;
}