package main;

import java.util.Random;

public class ProcessControlBlock { // The Process Control Block used to store the data for each process, id, burst time, arrival time etc
    private int size, startTime, task, attempts, sleepTime, priority; // Variables used in the pcb
    private long createTime, endTime = 0;
    private String pid;
    private int bt, at, wt, aroundTime, baseAddress;
   
    // Accessors
    public int getwt() {
    	return this.wt;
    }
    
    public int getaroundtime() {
    	return this.aroundTime;
    }

    public String getpid() {
        return pid;
    }
    
    public int getbt() {
        return this.bt;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public final int getBaseAddress() {
    	return this.baseAddress;
    }
    
    public int getAttempts() {
    	return this.attempts;
    }
    
    public int getat() {
    	return this.at;
    }
    
    public long getCreateTime() {
    	return this.createTime;
    }
    
    public int getTask() {
    	return this.task;
    }
    
    public int getSleepTime() {
    	return this.sleepTime;
    }
    public int getStartTime() {
    	return this.startTime;
    }
    
    public long getEndTime()  {
    	return this.endTime;
    }
    
    public int getPriority() {
    	return this.priority;
    }
    
    // Mutators
    public void sst() { // Used to set the random sleep time for the process. This is called automatically when a pcb is created
    	Random random = new Random();
    	int value = random.nextInt(5);
    	
    	if(value == 0) {
    		this.sleepTime = 1;
    	}
    	else {
    		this.sleepTime = value;
    	}
    }
    
    
    public void setpid(String pid) {
        this.pid = pid;
    }
    
    public void setbt(int bt) {
        this.bt = bt;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public void setat(int at) {
    	this.at = at;
    }
    
    public void setBaseAddress(int baseAddress) {
    	this.baseAddress = baseAddress;
    }
    
	public void setAttempts(int attempts) {
    	this.attempts = attempts;
    }
    
    public void setSleepTime(int sleepTime) {
    	this.sleepTime = sleepTime;
    }
    
    public void setCreateTime(long createTime) {
    	this.createTime = createTime;
    }
    
    public void setTask(int task) {
    	this.task = task;
    }
    
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
    public void cat(int endTime) { // Used to set the end time for the process
        this.aroundTime = endTime - this.at;
        this.wt = this.aroundTime - this.bt;
        
        this.setEndTime(endTime);
    }
    
    public void rpriority() {
    	Random random = new Random();
    	int value = random.nextInt(6);
    	
    	if(value == 0) {
    		this.priority = 1;
    	}
    	else {
    		this.priority = value;
    	}
    }
    
    public void random() {
    	Random random = new Random();
    	int value = random.nextInt(5);
    	
    	if(value == 0) {
    		this.task = 1;
    	}
    	else {
    		this.task = value;
    	}
    }
    
    public ProcessControlBlock() { // Default Constructor
    	this.size = this.startTime = this.task = this.attempts =  this.sleepTime = this.priority = 0;
        this.createTime = this.endTime = 0;
        this.pid = "";
        this.bt = this.at = this.wt = this.aroundTime = 0;
    }
    
    public ProcessControlBlock(String pid, int at, int bt) { // Primary Constructor
    	this.rpriority();
    	this.sst();
    	this.createTime = System.currentTimeMillis();
    	this.wt = this.aroundTime  = this.startTime = this.attempts = 0;
    	this.pid = pid;
    	this.size = 40;
    	this.at = at;
    	this.bt = bt;
    	this.random();
    }
}