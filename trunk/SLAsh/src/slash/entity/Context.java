package slash.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import jade.core.Location;

public class Context implements Serializable {

	private static final long serialVersionUID = -4290838847903200791L;
	
	public static int WIRED = 0;
	public static int WIRELESS = 1;
	
	public static float CPU_LIMIT = 60.0f;
	public static float RAM_LIMIT = 60.0f;
	public static float MEMORY_LIMIT = 60.0f;
	public static float ENERGY_LIMIT = 20.0f;
	
	private static int RST_INTERVAL = 30;
	
	private List<Float> cpuList;
	private List<Float> ramList;
	private List<Float> memoryList;
	private List<Float> energyList;
	private List<Float> latencyList;
	private List<Float> reliabilityList;
	private List<Float> reqIntervalList;
	
	private Location location;
	private float cpu;
	private float ram;
	private float memory;
	private int network; //WIRED, WIRELESS
	private int bandwidth;
	private float energy;
	private float latency;
	private float reliability;
	private float reqInterval;
	
	public Context() {
		this.cpuList = new LinkedList<Float>();
		this.ramList = new LinkedList<Float>();
		this.memoryList = new LinkedList<Float>();
		this.energyList = new LinkedList<Float>();
		this.latencyList = new LinkedList<Float>();
		this.reliabilityList = new LinkedList<Float>();
		this.reqIntervalList = new LinkedList<Float>();
	}
	
	public void addCpuValue(Float cpuValue) {
//		if(this.cpuList.size() >= Context.RST_INTERVAL)
//			this.cpuList = new LinkedList<Float>();
//		this.cpuList.add(Float.valueOf(cpuValue));
		if(cpuValue!=null) {
			//System.out.println("cpuValue: "+cpuValue);
			this.cpu = cpuValue;
		}
	}
	
	public void addRamValue(Float ramValue) {
//		if(this.ramList.size() >= Context.RST_INTERVAL)
//			this.ramList = new LinkedList<Float>();
//		this.ramList.add(Float.valueOf(ramValue));
		if(ramValue!=null)
			this.ram = ramValue;
	}
	
	public void addMemoryValue(Float memoryValue) {
//		if(this.memoryList.size() >= Context.RST_INTERVAL)
//			this.memoryList = new LinkedList<Float>();
//		this.memoryList.add(Float.valueOf(memoryValue));
		if(memoryValue!=null)
			this.memory = memoryValue;
	}
	
	public void addEnergyValue(Float energyValue) {
//		if(this.energyList.size() >= Context.RST_INTERVAL)
//			this.energyList = new LinkedList<Float>();
//		this.energyList.add(Float.valueOf(energyValue));
		if(energyValue!=null)
			this.energy = energyValue;
	}
	
	public float getAvgCpu() {
//		cpu = 0;
//		for(int i=0; i<this.cpuList.size(); i++) {
//			cpu += this.cpuList.get(i);
//		}
//		cpu /= this.cpuList.size();
		return cpu;
	}
	
	public float getAvgRam() {
//		ram = 0;
//		for(int i=0; i<this.ramList.size(); i++) {
//			ram += this.ramList.get(i);
//		}
//		ram /= this.ramList.size();
		return ram;
	}
	
	public float getAvgMemory() {
//		memory = 0;
//		for(int i=0; i<this.memoryList.size(); i++) {
//			memory += this.memoryList.get(i);
//		}
//		memory /= this.memoryList.size();
		return memory;
	}
	
	public float getAvgEnergy() {
//		energy = 0;
//		for(int i=0; i<this.energyList.size(); i++) {
//			energy += this.energyList.get(i);
//		}
//		energy /= this.energyList.size();
		return energy;
	}
	
	public void addLatencyValue(Float latencyValue) {
		if(latencyValue!=null) {
			if(this.latencyList.size() >= Context.RST_INTERVAL)
				this.latencyList = new LinkedList<Float>();
			this.latencyList.add(Float.valueOf(latencyValue));
		}
	}
	
	public void addReliabilityValue(Float reliabilityValue) {
		if(reliabilityValue!=null) {
			if(this.reliabilityList.size() >= Context.RST_INTERVAL)
				this.reliabilityList = new LinkedList<Float>();
			this.reliabilityList.add(Float.valueOf(reliabilityValue));
		}
	}
	
	public void addReqIntervalValue(Float reqIntervalValue) {
		if(reqIntervalValue!=null) {
			if(this.reqIntervalList.size() >= Context.RST_INTERVAL)
				this.reqIntervalList = new LinkedList<Float>();
			this.reqIntervalList.add(Float.valueOf(reqIntervalValue));
		}
	}
	
	public float getAvgLatency() {
		latency = 0;
		for(int i=0; i<this.latencyList.size(); i++) {
			latency += this.latencyList.get(i);
		}
		latency /= this.latencyList.size();
		return latency;
	}
	
	public float getAvgReliability() {
		reliability = 0;
		for(int i=0; i<this.reliabilityList.size(); i++) {
			reliability += this.reliabilityList.get(i);
		}
		reliability /= this.reliabilityList.size();
		return reliability;
	}
	
	public float getAvgReqInterval() {
		reqInterval = 0;
		for(int i=0; i<this.reqIntervalList.size(); i++) {
			reqInterval += this.reqIntervalList.get(i);
		}
		reqInterval /= this.reqIntervalList.size();
		return reqInterval;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public float calcIndex() {
		return cpu*0.23f+ram*0.23f+memory*0.23f+(100-energy)*0.3f;
	}
	
	public Context(float cpu, float ram, float memory, float energy, int network, int bandwidth, float latency, float reliability, float reqInterval) {
		this.cpu = cpu;
		this.ram = ram;
		this.memory = memory;
		this.energy = energy;
		this.network = network;
		this.bandwidth = bandwidth;
		this.latency = latency;
		this.reliability = reliability;
		this.reqInterval = reqInterval;
	}
	
	public void setCpu(float cpu) {
		this.cpu = cpu;
	}
	
	public float getCpu() {
		return this.cpu;
	}

	public void setRam(float ram) {
		this.ram = ram;
	}
	
	public float getRam() {
		return this.ram;
	}
	
	public void setMemory(float memory) {
		this.memory = memory;
	}
	
	public float getMemory() {
		return this.memory;
	}
	
	public void setNetwork(int network) {
		this.network = network;
	}
	
	public int getNetwork() {
		return this.network;
	}
	
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}
	
	public int getBandwidth() {
		return this.bandwidth;
	}
	
	public void setEnergy(float energy) {
		this.energy = energy;
	}
	
	public float getEnergy() {
		return this.energy;
	}
}
