package main;

//Node data
public class NodeData { 
    private String id;
    private int arrivalTime;
    int burstTime;
    
    public NodeData() {
        this.id = "";
        this.arrivalTime = this.burstTime = 0;
    }
    
    public void setid(String id) {
        this.id = id;
    }
    
    public String getid() {
        return this.id;
    }
    
    public void setarrTime(int at) {
        this.arrivalTime = at;
    }
    
    public int getarrTime() {
        return this.arrivalTime;
    }
    
    public void setburTime(int bt) {
        this.burstTime = bt;
    }
    
    public int getburTime() {
        return this.burstTime;
    }
}
