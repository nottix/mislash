package slash.resource.agent;

import slash.df.DFUtil;
import slash.entity.Context;
import slash.resource.behaviour.*;
import jade.core.AID;
import jade.core.Agent;

public class MemoryAgent extends Agent {

	private static final long serialVersionUID = -5491334259558404278L;

	private int network;
	private int bandwidth;
	
	protected void setup() {
		System.out.println("Memory: "+this.getName());

		//DFUtil.register(this, this.getLocalName(), "resource");
		
		Object[] args = this.getArguments();
		if(args.length==2) {
			if(args[0].toString().equals("wired"))
				network = Context.WIRED;
			else
				network = Context.WIRELESS;
			
			bandwidth = Integer.parseInt(args[1].toString());
		}
		
		AID cmAid = new AID("cm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new MemoryBehaviour(cmAid));

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
