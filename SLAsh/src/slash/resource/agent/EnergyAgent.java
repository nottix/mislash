package slash.resource.agent;

import jade.core.AID;
import slash.entity.Context;
import slash.resource.behaviour.EnergyBehaviour;
import slash.resource.behaviour.NotifyReceiverBehaviour;

public class EnergyAgent extends ResourceAgent {

	private static final long serialVersionUID = 7577668356120531127L;

	private int network;
	private int bandwidth;
	
	protected void setup() {
		System.out.println("Energy: "+this.getName());
		
		Object[] args = this.getArguments();
		if(args.length==2) {
			if(args[0].toString().equals("wired"))
				network = Context.WIRED;
			else
				network = Context.WIRELESS;
			
			bandwidth = (Integer)args[1];
		}
		
		AID cmAid = new AID("cm"+this.getLocalName().charAt(this.getLocalName().length()-1), AID.ISLOCALNAME);
		this.addBehaviour(new EnergyBehaviour(cmAid, this));
		this.addBehaviour(new NotifyReceiverBehaviour(this));

	}
	
	public int getNetwork() {
		return this.network;
	}
	
	public int getBandwidth() {
		return this.bandwidth;
	}
}
