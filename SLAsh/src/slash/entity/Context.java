package slash.entity;

import java.io.Serializable;

public class Context implements Serializable {

	private static final long serialVersionUID = -4290838847903200791L;
	
	public static int WIRED = 0;
	public static int WIRELESS = 1;
	
	private float cpu;
	private float ram;
	private float memory;
	private int network; //WIRED, WIRELESS
	private int bandwidth;
	private float energy;
	
	public Context() {
		
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
