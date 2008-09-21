package slash.resource.agent;

import jade.core.AID;
import slash.entity.Context;
import slash.resource.behaviour.CpuBehaviour;
import slash.resource.behaviour.NotifyReceiverBehaviour;

public class CpuAgent extends ResourceAgent {

	private static final long serialVersionUID = -5570077702241336240L;

	private int network;
	private int bandwidth;
	
	protected void setup() {
		System.out.println("Cpu: "+this.getName());
		
		Object[] args = this.getArguments();
		if(args.length==2) {
			if(args[0].toString().equals("wired"))
				network = Context.WIRED;
			else
				network = Context.WIRELESS;
			
			bandwidth = (Integer)args[1];
		}
		
		AID cmAid = new AID("cm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new CpuBehaviour(cmAid, this));
		this.addBehaviour(new NotifyReceiverBehaviour(this));

	}

	public int getNetwork() {
		return this.network;
	}
	
	public int getBandwidth() {
		return this.bandwidth;
	}
}
