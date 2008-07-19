package slash.entity;

import java.io.Serializable;
import java.util.*;
import slash.entity.*;

public class Status implements Serializable {

	private static final long serialVersionUID = -4467088461237807527L;

	private List<Integer> latencyList;
	private List<Float> reliabilityList;
	private List<Integer> reqIntervalList;
	
	private int latency;
	private float reliability;
	private int reqInterval;
	
	public Status() {
		this.latencyList = new LinkedList<Integer>();
		this.reliabilityList = new LinkedList<Float>();
		this.reqIntervalList = new LinkedList<Integer>();
	}
	
	public void addLatencyValue(int latencyValue) {
		this.latencyList.add(Integer.valueOf(latencyValue));
	}
	
	public void addReliabilityValue(float reliabilityValue) {
		this.reliabilityList.add(Float.valueOf(reliabilityValue));
	}
	
	public void addReqIntervalValue(int reqIntervalValue) {
		this.reqIntervalList.add(Integer.valueOf(reqIntervalValue));
	}
	
	public int getAvgLatency() {
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
	
	public int getAvgReqInterval() {
		reqInterval = 0;
		for(int i=0; i<this.reqIntervalList.size(); i++) {
			reqInterval += this.reqIntervalList.get(i);
		}
		reqInterval /= this.reqIntervalList.size();
		return reqInterval;
	}
	
}
