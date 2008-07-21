package slash.resource.agent;

import slash.df.DFUtil;
import slash.resource.behaviour.*;
import slash.resourcemonitor.behaviour.SLAReqReceiverBehaviour;
import jade.core.AID;
import jade.core.Agent;
import slash.entity.*;

public class LatencyAgent extends ResourceAgent {

	private static final long serialVersionUID = -5626212945073184408L;

	private int network;
	private int bandwidth;
	
	protected void setup() {
		System.out.println("Latency: "+this.getName());

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
		this.addBehaviour(new LatencyBehaviour(rm, this));
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
