package main;

public class NodeData { // The data portion for the display node
    private String id;
    private int at, bt;
    
    public NodeData() { // Default Constructor
        this.id = "";
        this.at = this.bt = 0;
    }
    
    // Mutators
    public void sid(String id) {
        this.id = id;
    }
    
    public void sat(int at) {
        this.at = at;
    }
    
    public void sbt(int bt) {
        this.bt = bt;
    }
    
    // Accessors
    public String gid() {
        return this.id;
    }
    
    public int gat() {
        return this.at;
    }
    
    public int gbt() {
        return this.bt;
    }
}
