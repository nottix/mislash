package slash.entity;

import java.io.Serializable;
import jade.core.*;

public class SLAContract implements Serializable {

	private static final long serialVersionUID = 1280366527966115376L;
	
	private AID publisher;
	private AID cmPub;
	private AID subscriber;
	private AID cmSub;
	private int latency;
	private float reliability;
	private int reqInterval;
	
	public SLAContract(AID publisher, AID cmPub, AID subscriber, AID cmSub, int latency, float reliability, int reqInterval) {
		this.publisher = publisher;
		this.cmPub = cmPub;
		this.subscriber = subscriber;
		this.cmSub = cmSub;
		this.latency = latency;
		this.reliability = reliability;
		this.reqInterval = reqInterval;
	}
	
	public SLAContract() {
		this.publisher = null;
		this.cmPub = null;
		this.subscriber = null;
		this.cmSub = null;
		this.latency = 0;
		this.reliability = 0.0f;
		this.reqInterval = 0;
	}
	
	public void setPublisher(AID publisher) {
		this.publisher = publisher;
	}
	
	public AID getPublisher() {
		return this.publisher;
	}
	
	public void setCmPub(AID cmPub) {
		this.cmPub = cmPub;
	}
	
	public AID getCmPub() {
		return this.cmPub;
	}
	
	public void setSubscriber(AID subscriber) {
		this.subscriber = subscriber;
	}
	
	public AID getSubscriber() {
		return this.subscriber;
	}
	
	public void setCmSub(AID cmSub) {
		this.cmSub = cmSub;
	}
	
	public AID getCmSub() {
		return this.cmSub;
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
