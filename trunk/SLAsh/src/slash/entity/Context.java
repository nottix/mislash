package slash.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
	
	private float cpu;
	private float ram;
	private float memory;
	private int network; //WIRED, WIRELESS
	private int bandwidth;
	private float energy;
	
	public Context() {
		this.cpuList = new LinkedList<Float>();
		this.ramList = new LinkedList<Float>();
		this.memoryList = new LinkedList<Float>();
		this.energyList = new LinkedList<Float>();
	}
	
	public void addCpuValue(float cpuValue) {
		if(this.cpuList.size() >= Context.RST_INTERVAL)
			this.cpuList = new LinkedList<Float>();
		this.cpuList.add(Float.valueOf(cpuValue));
	}
	
	public void addRamValue(float ramValue) {
		if(this.ramList.size() >= Context.RST_INTERVAL)
			this.ramList = new LinkedList<Float>();
		this.ramList.add(Float.valueOf(ramValue));
	}
	
	public void addMemoryValue(float memoryValue) {
		if(this.memoryList.size() >= Context.RST_INTERVAL)
			this.memoryList = new LinkedList<Float>();
		this.memoryList.add(Float.valueOf(memoryValue));
	}
	
	public void addEnergyValue(float energyValue) {
		if(this.energyList.size() >= Context.RST_INTERVAL)
			this.energyList = new LinkedList<Float>();
		this.energyList.add(Float.valueOf(energyValue));
	}
	
	public float getAvgCpu() {
		cpu = 0;
		for(int i=0; i<this.cpuList.size(); i++) {
			cpu += this.cpuList.get(i);
		}
		cpu /= this.cpuList.size();
		return cpu;
	}
	
	public float getAvgRam() {
		ram = 0;
		for(int i=0; i<this.ramList.size(); i++) {
			ram += this.ramList.get(i);
		}
		ram /= this.ramList.size();
		return ram;
	}
	
	public float getAvgMemory() {
		memory = 0;
		for(int i=0; i<this.memoryList.size(); i++) {
			memory += this.memoryList.get(i);
		}
		memory /= this.memoryList.size();
		return memory;
	}
	
	public float getAvgEnergy() {
		energy = 0;
		for(int i=0; i<this.energyList.size(); i++) {
			energy += this.energyList.get(i);
		}
		energy /= this.energyList.size();
		return energy;
	}
	
	public Context(float cpu, float ram, float memory, float energy, int network, int bandwidth) {
		this.cpu = cpu;
		this.ram = ram;
		this.memory = memory;
		this.energy = energy;
		this.network = network;
		this.bandwidth = bandwidth;
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
