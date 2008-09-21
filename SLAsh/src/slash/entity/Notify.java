package slash.entity;

import java.io.Serializable;

public class Notify implements Serializable {

	private static final long serialVersionUID = 6443212295017254395L;
	
	private int src;
	private int dest;
	
	public Notify(int src, int dest) {
		this.src = src;
		this.dest = dest;
	}
	
	public void setSrc(int src) {
		this.src = src;
	}
	
	public int getSrc() {
		return this.src;
	}
	
	public void setDest(int dest) {
		this.dest = dest;
	}
	
	public int getDest() {
		return this.dest;
	}
}
