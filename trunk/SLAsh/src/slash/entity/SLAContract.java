package slash.entity;

import jade.core.AID;

import java.io.Serializable;

public class SLAContract implements Serializable {

	private static final long serialVersionUID = 1280366527966115376L;
	
	private AID publisher;
	private AID subscriber;
	private float latency;
	private float reliability;
	private float reqInterval;
	
	public SLAContract(AID publisher, AID subscriber, float latency, float reliability, float reqInterval) {
		this.publisher = publisher;
		this.subscriber = subscriber;
		this.latency = latency;
		this.reliability = reliability;
		this.reqInterval = reqInterval;
	}
	
	public SLAContract() {
		this.publisher = null;
		this.subscriber = null;
		this.latency = 0.0f;
		this.reliability = 0.0f;
		this.reqInterval = 0.0f;
	}
	
	public void setPublisher(AID publisher) {
		this.publisher = publisher;
	}
	
	public AID getPublisher() {
		return this.publisher;
	}
	
	public void setSubscriber(AID subscriber) {
		this.subscriber = subscriber;
	}
	
	public AID getSubscriber() {
		return this.subscriber;
	}
	
	public void setLatency(float latency) {
		this.latency = latency;
	}
	
	public float getLatency() {
		return this.latency;
	}
	
	public void setReliability(float reliability) {
		this.reliability = reliability;
	}
	
	public float getReliability() {
		return this.reliability;
	}
	
	public void setReqInterval(float reqInterval) {
		this.reqInterval = reqInterval;
	}
	
	public float getReqInterval() {
		return this.reqInterval;
	}
	

}
