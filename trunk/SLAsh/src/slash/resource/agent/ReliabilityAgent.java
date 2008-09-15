package slash.resource.agent;

import slash.df.DFUtil;
import slash.entity.Context;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class ReliabilityAgent extends ResourceAgent {

	private static final long serialVersionUID = -4576966086955385673L;

	private int network;
	private int bandwidth;
	
	protected void setup() {
		System.out.println("Reliability: "+this.getName());

		//DFUtil.register(this, this.getLocalName(), "resource");
		
		Object[] args = this.getArguments();
		if(args.length==2) {
			if(args[0].toString().equals("wired"))
				network = Context.WIRED;
			else
				network = Context.WIRELESS;
			
			bandwidth = (Integer)args[1];
		}
		
		this.rm = new AID("rm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new ReliabilityBehaviour(rm, this));
		this.addBehaviour(new NotifyReceiverBehaviour(this));

	}
	
	protected void takeDown() {
		//DFUtil.deregister(this, this.getLocalName(), "resource");
	}
	
	public int getNetwork() {
		return this.network;
	}
	
	public int getBandwidth() {
		return this.bandwidth;
	}
}