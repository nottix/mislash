package slash.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Status implements Serializable {

	private static final long serialVersionUID = -4467088461237807527L;

	private List<Float> latencyList;
	private List<Float> reliabilityList;
	private List<Float> reqIntervalList;
	
	private float latency;
	private float reliability;
	private float reqInterval;
	
	public Status() {
		this.latencyList = new LinkedList<Float>();
		this.reliabilityList = new LinkedList<Float>();
		this.reqIntervalList = new LinkedList<Float>();
	}
	
	public void addLatencyValue(float latencyValue) {
		this.latencyList.add(Float.valueOf(latencyValue));
	}
	
	public void addReliabilityValue(float reliabilityValue) {
		this.reliabilityList.add(Float.valueOf(reliabilityValue));
	}
	
	public void addReqIntervalValue(float reqIntervalValue) {
		this.reqIntervalList.add(Float.valueOf(reqIntervalValue));
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
	
}
