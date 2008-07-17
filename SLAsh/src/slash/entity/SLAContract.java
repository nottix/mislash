package slash.entity;

import java.io.Serializable;

public class SLAContract implements Serializable {

	private static final long serialVersionUID = 1280366527966115376L;
	
	private int latency;
	private float reliability;
	private int reqInterval;
	
	public SLAContract(int latency, float reliability, int reqInterval) {
		this.latency = latency;
		this.reliability = reliability;
		this.reqInterval = reqInterval;
	}
	
	public SLAContract() {
		this.latency = 0;
		this.reliability = 0.0f;
		this.reqInterval = 0;
	}
	
	public void setLatency(int latency) {
		this.latency = latency;
	}
	
	public int getLatency() {
		return this.latency;
	}
	
	public void setReliability(float reliability) {
		this.reliability = reliability;
	}
	
	public float getReliability() {
		return this.reliability;
	}
	
	public void setReqInterval(int reqInterval) {
		this.reqInterval = reqInterval;
	}
	
	public int getReqInterval() {
		return this.reqInterval;
	}
	

}
